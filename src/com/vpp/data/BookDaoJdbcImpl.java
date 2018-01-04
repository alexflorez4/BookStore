package com.vpp.data;

import com.vpp.exceptions.BookNotFoundException;
import com.vpp.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
//@Component  //@Repository
public class BookDaoJdbcImpl implements BookDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_BOOK_SQL = "insert into BOOK (ISBN, TITLE, AUTHOR, PRICE) values (?,?,?,?)";
    private static final String CREATE_TABLE_SQL = "create table BOOK(ISBN VARCHAR(20), TITLE VARCHAR(50), AUTHOR VARCHAR(50), PRICE DOUBLE)";
    private static final String GET_ALL_BOOKS_SQL = "select * from BOOK";
    private static final String GET_BOOKS_BY_AUTHOR = "select * from BOOK where AUTHOR = ?";
    private static final String GET_BOOKS_BY_ISBN = "select * from BOOK where ISBN = ?";
    private static final String DELETE_BOOK = "delete from BOOK where ISBN = ? ";


//    public BookDaoJdbcImpl(JdbcTemplate template)
//    {
//        this.jdbcTemplate = template;
//    }

    @PostConstruct
    private void createTables()
    {
        try
        {
            jdbcTemplate.update(CREATE_TABLE_SQL);
        }
        catch (BadSqlGrammarException e)
        {
            System.out.println("Assuming table already exists.");
        }
    }

    @Override
    public List<Book> allBooks() {
        return  jdbcTemplate.query(GET_ALL_BOOKS_SQL, new BookMapper());
    }

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException {
        try
        {
            return jdbcTemplate.queryForObject(GET_BOOKS_BY_ISBN, new BookMapper(), isbn);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new BookNotFoundException();
        }
    }

    @Override
    public void create(Book newBook)
    {
        jdbcTemplate.update(INSERT_BOOK_SQL, new Object[]{newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPrice()});
    }

    @Override
    public void delete(Book redundantBook) {
        jdbcTemplate.update(DELETE_BOOK, redundantBook.getIsbn());
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return jdbcTemplate.query(GET_BOOKS_BY_AUTHOR, new BookMapper(), author);
    }
}

//Inner class.  Java rule is you can have multiple classes but only one public.

class BookMapper implements RowMapper<Book>
{
    @Override
    public Book mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        String isbn = resultSet.getString("ISBN");
        String title = resultSet.getString("TITLE");
        String author = resultSet.getString("AUTHOR");
        double price = resultSet.getDouble("PRICE");

        Book theBook = new Book(isbn, title, author, price);
        return theBook;
    }
}