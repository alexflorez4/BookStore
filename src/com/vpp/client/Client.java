package com.vpp.client;

import com.vpp.domain.Book;
import com.vpp.services.BookService;
import com.vpp.services.PurchasingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class Client
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
        try
        {
            PurchasingService purchasing = container.getBean(PurchasingService.class);
            BookService bookService = (BookService)container.getBean("bookService");

            //begin
            //bookService.registerNewBook(new Book("1545415", "Book Title", "Author", 10.85));
            //commit

            //begin
            /*try
            {
                purchasing.buyBook("1545415");
            }
            catch (BookNotFoundException e)
            {
                System.out.println("Sorry, that book does not exists");
            }
            catch (CustomerCreditExcededException e)
            {
                System.out.println("Sorry, you can't afford it, go away!");
            }*/
            //commit


           /* BookService service = container.getBean(BookService.class);

            List<Book> allBooks = service.getEntireCatalogue();
            for(Book next: allBooks)
            {
                System.out.println(next);
            }

            String requiredIsbn = "ISBN1";
            PurchasingService purchasing = container.getBean(PurchasingService.class);
            PurchasingService purchasing2 = container.getBean(PurchasingService.class);
            purchasing.buyBook(requiredIsbn);*/


            /*BookService bookService = container.getBean("bookService", BookService.class);
            bookService.registerNewBook(new Book("4158524", "War and Peace", "Leo Tolstoy", 10.99));

            List<Book> allBooks = bookService.getEntireCatalogue();
            for (Book next : allBooks) {
                System.out.println(next);
            }

            try {
                Book foundBook = bookService.getBookByIsbn("ababc");
            } catch (BookNotFoundException e) {
                System.out.println("Sorry, that book does not exists.");
            }*/


            //Hibernate testing:
            bookService.registerNewBook(new Book("4154548851", "Java Programming", "Gary Cornell", 47.23));
            //List<Book> allBooks = bookService.getEntireCatalogue();
            List<Book> allBooks = bookService.getAllBooksByAuthor("Josh Bloch");
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
