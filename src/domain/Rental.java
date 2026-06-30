package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rental transaction in the Village Rentals system.
 * 
 * A rental belongs to one customer and can contain one or more rental items.
 */
public class Rental {

    /** Unique ID used to identify the rental. */
    private int rentalId;

    /** Customer who made the rental. */
    private Customer customer;

    /** Date the rental starts. */
    private LocalDate rentalDate;

    /** Date the rental is expected to be returned. */
    private LocalDate returnDate;

    /** Total cost of the rental. */
    private double totalCost;

    /** List of rental items included in this rental. */
    private List<RentalItem> rentalItems;

    /**
     * Constructs a Rental object with the given details.
     * 
     * @param rentalId unique rental ID
     * @param customer customer making the rental
     * @param rentalDate rental start date
     * @param returnDate rental return date
     */
    public Rental(int rentalId, Customer customer, LocalDate rentalDate, LocalDate returnDate) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalCost = 0.0;
        this.rentalItems = new ArrayList<>();
    }

    /**
     * Returns the rental ID.
     * 
     * @return rental ID
     */
    public int getRentalId() {
        return rentalId;
    }

    /**
     * Returns the customer linked to this rental.
     * 
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the rental date.
     * 
     * @return rental date
     */
    public LocalDate getRentalDate() {
        return rentalDate;
    }

    /**
     * Returns the return date.
     * 
     * @return return date
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Returns the total rental cost.
     * 
     * @return total cost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the list of rental items.
     * 
     * @return rental item list
     */
    public List<RentalItem> getRentalItems() {
        return rentalItems;
    }

    /**
     * Adds a rental item to the rental.
     * 
     * @param item the rental item to add
     */
    public void addItem(RentalItem item) {
        rentalItems.add(item);
    }

    /**
     * Calculates the total cost of all rental items in this rental.
     * 
     * @return total rental cost
     */
    public double calculateTotalCost() {
        totalCost = 0.0;

        for (RentalItem item : rentalItems) {
            totalCost += item.calculateItemCost();
        }

        return totalCost;
    }

    /**
     * Creates the rental.
     * 
     * This is a placeholder method for future rental creation logic.
     */
    public void createRental() {
        // Rental creation logic can be expanded later.
    }

    /**
     * Closes the rental.
     * 
     * This is a placeholder method for future rental closing logic.
     */
    public void closeRental() {
        // Rental closing logic can be expanded later.
    }

    /**
     * Returns a readable string version of the rental.
     * 
     * @return formatted rental details
     */
    @Override
    public String toString() {
        return "Rental ID: " + rentalId
                + ", Customer: " + customer.getFullName()
                + ", Rental Date: " + rentalDate
                + ", Return Date: " + returnDate
                + ", Total Cost: $" + totalCost
                + ", Number of Items: " + rentalItems.size();
    }
}
