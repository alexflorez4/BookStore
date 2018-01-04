package com.vpp.domain.test;

import com.vpp.domain.Book;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookTest
{
    @Test
    public void testBookWithTheSameISBNNumberAreConsiderTheSameProduct()
    {
        //arrange
        Book bookOne = new Book("382828822", "Java Puzzles", "Josh Bloch", 10.99);
        Book bookTwo = new Book("382828822", "Java Puzzles", "Josh Bloch", 10.99);

        //act
        boolean areTheyEqual = bookOne.equals(bookTwo);

        //assert
        assertTrue(areTheyEqual);
    }

    @Test
    public void testFormatOfTheToStringIsCorrect()
    {
        //arrange
        Book bookOne = new Book("382828822", "Java Puzzles", "Josh Bloch", 10.99);
        //act
        String toStringResult = bookOne.toString();
        //assert
        assertEquals("Java Puzzles by Josh Bloch", toStringResult);
    }
}
