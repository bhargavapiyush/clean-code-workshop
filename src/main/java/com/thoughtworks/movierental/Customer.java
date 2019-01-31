package com.thoughtworks.movierental;

public class Customer {
  private String name;
  private Rentals rentals = new Rentals();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental arg) {
    rentals.add(arg);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    return new TextStatement().display(this.getName(), this.rentals);
  }

  public String htmlStatement() {
    return new HTMLStatement().display(this.getName(), this.rentals);
  }

}
