package com.vpp.services;


import com.vpp.exceptions.BookNotFoundException;
import com.vpp.exceptions.CustomerCreditExcededException;

public interface PurchasingService
{
    public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExcededException;
}
