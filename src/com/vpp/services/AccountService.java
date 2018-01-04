package com.vpp.services;


import com.vpp.exceptions.CustomerCreditExcededException;
import com.vpp.domain.Book;

public interface AccountService
{
    public void raiseInvoice(Book requiredBook) throws CustomerCreditExcededException;
}
