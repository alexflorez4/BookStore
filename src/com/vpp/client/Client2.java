package com.vpp.client;

import com.vpp.domain.Book;
import com.vpp.services.BookService;
import com.vpp.services.PurchasingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Client2
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application-mybatis.xml");
        try
        {
            PurchasingService purchasing = container.getBean(PurchasingService.class);
            BookService bookService = (BookService)container.getBean("bookService");

            bookService.registerNewBook(new Book("4154548851", "Java Programming", "Gary Cornell", 47.23));
           /*try {
               Book oldBook = bookService.getBookByIsbn("4154548851");
               bookService.deleteFromStock(oldBook);
           }
           catch (BookNotFoundException e)
           {
               System.out.println("Book not found");
           }*/

            List<Book> allBooks = bookService.getEntireCatalogue();
            for (Book book : allBooks )
            {
                System.out.println(book);
            }
        }
        finally
        {
            container.close();
        }

    }
}
