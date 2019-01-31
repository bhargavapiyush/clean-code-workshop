package com.thoughtworks.movierental;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    private Customer testCustomer;

    @Before
    public void setup() {
        testCustomer = new Customer("Piyush Bhargava");
    }

    @Test
    public void testStatementReturnsString() {
        Assert.assertTrue(testCustomer.statement() instanceof String);
        //System.out.print(testCustomer.statement());
    }


    // testStatementForNoRentals
    @Test
    public void testStatementForNoRentals() {
        String expectedOutput = "Rental Record for Piyush Bhargava\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent renter points";
        Assert.assertEquals(expectedOutput, testCustomer.statement());
    }

    // testStatementForRegularMovie
    @Test
    public void testStatementForRegularMovie() {
        String expectedOutput = "Rental Record for Piyush Bhargava\n"
                + "\tManmarziyaan\t2.0\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent renter points";

        testCustomer.addRental(new Rental(new Movie("Manmarziyaan", Movie.REGULAR), 2));
        Assert.assertEquals(expectedOutput, testCustomer.statement());
    }

    // testStatementForRegularMovieRentedMoreThan2Days
    @Test
    public void testStatementForRegularMovieRentedMoreThan2Days() {
        String expectedOutput = "Rental Record for Piyush Bhargava\n"
                + "\tManmarziyaan\t6.5\n"
                + "Amount owed is 6.5\n"
                + "You earned 1 frequent renter points";

        testCustomer.addRental(new Rental(new Movie("Manmarziyaan", Movie.REGULAR), 5));
        Assert.assertEquals(expectedOutput, testCustomer.statement());
    }

    // testStatementForNewReleaseMovie
    @Test
    public void testStatementForNewReleaseMovie() {
        String expectedOutput = "Rental Record for Piyush Bhargava\n"
                + "\tURI: The Surgical Strike\t3.0\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter points";

        testCustomer.addRental(new Rental(new Movie("URI: The Surgical Strike", Movie.NEW_RELEASE), 1));
        Assert.assertEquals(expectedOutput, testCustomer.statement());
    }

    // testStatementForNewReleaseMovieRentedMoreThan1Day
    @Test
    public void testStatementForNewReleaseMovieRentedMoreThan1Day() {
        String expectedOutput = "Rental Record for Piyush Bhargava\n"
                + "\tURI: The Surgical Strike\t12.0\n"
                + "Amount owed is 12.0\n"
                + "You earned 2 frequent renter points";

        testCustomer.addRental(new Rental(new Movie("URI: The Surgical Strike", Movie.NEW_RELEASE), 4));
        Assert.assertEquals(expectedOutput, testCustomer.statement());
    }

    // testStatementForChildrenMovie
    @Test
    public void testStatementForChildrenMovie() {
        String expectedOutput = "Rental Record for Piyush Bhargava\n"
                + "\tKungfu Panda 3\t1.5\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent renter points";

        testCustomer.addRental(new Rental(new Movie("Kungfu Panda 3", Movie.CHILDRENS), 2));
        Assert.assertEquals(expectedOutput, testCustomer.statement());
    }

    // testStatementForChildrenMovieRentedMoreThan3Days
    @Test
    public void testStatementForChildrenMovieRentedMoreThan3Days() {
        String expectedOutput = "Rental Record for Piyush Bhargava\n"
                + "\tKungfu Panda 3\t6.0\n"
                + "Amount owed is 6.0\n"
                + "You earned 1 frequent renter points";

        testCustomer.addRental(new Rental(new Movie("Kungfu Panda 3", Movie.CHILDRENS), 6));
        Assert.assertEquals(expectedOutput, testCustomer.statement());
    }

    @Test
    public void testStatementForRegular5DayChildren6DayNewRelease4Day() {
        String expectedOutput = "Rental Record for Piyush Bhargava\n"
                + "\tManmarziyaan\t6.5\n"
                + "\tKungfu Panda 3\t6.0\n"
                + "\tURI: The Surgical Strike\t12.0\n"
                + "Amount owed is 24.5\n"
                + "You earned 4 frequent renter points";

        testCustomer.addRental(new Rental(new Movie("Manmarziyaan", Movie.REGULAR), 5));
        testCustomer.addRental(new Rental(new Movie("Kungfu Panda 3", Movie.CHILDRENS), 6));
        testCustomer.addRental(new Rental(new Movie("URI: The Surgical Strike", Movie.NEW_RELEASE), 4));
        Assert.assertEquals(expectedOutput, testCustomer.statement());
    }

    @Test
    public void testHTMLStatementFormatting() {
        String expectedOutput = "<h1>Rental Record for <b>Piyush Bhargava</b></h1><br/>"
                + "Manmarziyaan 6.5<br/>"
                + "Kungfu Panda 3 6.0<br/>"
                + "URI: The Surgical Strike 12.0<br/>"
                + "Amount owed is <b>24.5</b><br/>"
                + "You earned <b>4</b> frequent renter points<br/>";
        testCustomer.addRental(new Rental(new Movie("Manmarziyaan", Movie.REGULAR), 5));
        testCustomer.addRental(new Rental(new Movie("Kungfu Panda 3", Movie.CHILDRENS), 6));
        testCustomer.addRental(new Rental(new Movie("URI: The Surgical Strike", Movie.NEW_RELEASE), 4));
        Assert.assertEquals(expectedOutput, testCustomer.htmlStatement());
    }
}