package com.vpp.services;

//13''
import com.vpp.exceptions.CustomerCreditExcededException;
import com.vpp.domain.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
@Service
public class AccountServiceImpl implements  AccountService
{
    @Override
    public void raiseInvoice(Book requiredBook) throws CustomerCreditExcededException
    {
        System.out.println("Raised an invoice");
        //throw new CustomerCreditExcededException();
    }
}
