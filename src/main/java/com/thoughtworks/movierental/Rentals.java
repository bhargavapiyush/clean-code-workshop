package com.thoughtworks.movierental;

import java.util.ArrayList;

public class Rentals extends ArrayList<Rental> {

    public int totalFrequentRenterPoints() {
      return stream().mapToInt(rental -> rental.getFrequentRenterPoints()).sum();
    }

    public double totalAmount() {
      return stream().mapToDouble(rental -> rental.amount()).sum();
    }
}
