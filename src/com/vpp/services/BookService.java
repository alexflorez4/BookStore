package com.vpp.services;

import com.vpp.exceptions.BookNotFoundException;
import com.vpp.domain.Book;
import java.util.List;

public interface BookService
{
    public List<Book> getAllBooksByAuthor(String author);
    public List<Book> getAllRecommendedBooks(String userId);
    public Book getBookByIsbn(String isbn)  throws BookNotFoundException;
    public List<Book> getEntireCatalogue();
    public void registerNewBook(Book newBook);
    public void deleteFromStock(Book oldBook);
}
