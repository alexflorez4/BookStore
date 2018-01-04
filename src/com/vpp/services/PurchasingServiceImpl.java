package com.vpp.services;


import com.vpp.exceptions.BookNotFoundException;
import com.vpp.exceptions.CustomerCreditExcededException;
import com.vpp.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service  //@Named  (Usually you don't mix these, we used @Service  and @Autowire)
public class PurchasingServiceImpl implements PurchasingService
{
    @Autowired //@Inject
    private AccountService accounts;
    @Autowired
    private BookService books;

    @Transactional(rollbackFor = {CustomerCreditExcededException.class, BookNotFoundException.class},
                    timeout = 10, isolation = Isolation.SERIALIZABLE)
    public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExcededException
    {
        //find the correct book
        Book requiredBook = books.getBookByIsbn(isbn);

        //delete the book from the stock
        books.deleteFromStock(requiredBook);

        //now raise the invoise
        //try {
            accounts.raiseInvoice(requiredBook);
        //}
        /*catch (CustomerCreditExcededException e)
        {
            //tell spring to rollback
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            //throw the exception back to client
            throw e;
        }*/
    }
}
