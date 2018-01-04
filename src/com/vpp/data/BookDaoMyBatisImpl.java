package com.vpp.data;

import com.vpp.exceptions.BookNotFoundException;
import com.vpp.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class BookDaoMyBatisImpl implements BookDao
{
    @Autowired
    private BookSqlMapper mapper;

    @PostConstruct
    private void createTables()
    {
        try
        {
            mapper.createTables();
        }
        catch (BadSqlGrammarException e)
        {
            System.out.println("Assume the table already exists");
        }
    }

    @Override
    public List<Book> allBooks() {
        return mapper.findAllBooksSqlStatement();
    }

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException {
        return mapper.findByIsbnSqlStatement(isbn);
    }

    @Override
    public void create(Book newBook) {
        mapper.createBookSqlStatement(newBook);
    }

    @Override
    public void delete(Book redundantBook) {
        mapper.deleteBookSqlStatement(redundantBook);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return mapper.findByAuthorSqlStatement(author);
    }
}
