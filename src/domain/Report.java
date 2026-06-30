package domain;

import java.util.List;

/**
 * Report class generates simple system summaries.
 */
public class Report {

    /**
     * Generates a summary of total customers.
     */
    public static String generateCustomerReport(List<Customer> customers) {
        return "Total Customers: " + customers.size();
    }

    /**
     * Generates a summary of total equipment.
     */
    public static String generateEquipmentReport(List<Equipment> equipmentList) {
        return "Total Equipment: " + equipmentList.size();
    }

    /**
     * Generates a summary of total rentals.
     */
    public static String generateRentalReport(List<Rental> rentals) {
        return "Total Rentals: " + rentals.size();
    }
}