package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import domain.Category;
import domain.Customer;
import domain.Equipment;
import domain.Rental;
import domain.RentalItem;

/**
 * Loads CSV data files for the Village Rentals system.
 *
 * This class reads category, equipment, customer, and rental data
 * from CSV files and converts them into Java objects.
 */
public class DataLoader {

    /** Date format used in the rental CSV file, such as 2/15/2024. */
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("M/d/yyyy");

    /**
     * Loads category data from a CSV file.
     *
     * Expected columns:
     * category_id, name
     *
     * @param filePath path to the categories CSV file
     * @return list of Category objects
     */
    public List<Category> loadCategories(String filePath) {
        List<Category> categories = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line in the file.
            while ((line = reader.readLine()) != null) {

                // Skip empty lines.
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the row into values.
                String[] values = line.split(",", -1);

                // Skip rows that do not have enough columns.
                if (values.length < 2) {
                    continue;
                }

                // Skip header rows or invalid rows where the first column is not numeric.
                if (!values[0].trim().matches("\\d+")) {
                    continue;
                }

                int categoryId = Integer.parseInt(values[0].trim());
                String name = values[1].trim();

                // Create a Category object and add it to the list.
                Category category = new Category(categoryId, name);
                categories.add(category);
            }

        } catch (IOException e) {
            System.out.println("Error loading categories: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in categories file: " + e.getMessage());
        }

        return categories;
    }

    /**
     * Loads equipment data from a CSV file.
     *
     * Expected columns:
     * equipment_id, category_id, name, description, daily_rate
     *
     * @param filePath path to the equipment CSV file
     * @return list of Equipment objects
     */
    public List<Equipment> loadEquipment(String filePath) {
        List<Equipment> equipmentList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line in the file.
            while ((line = reader.readLine()) != null) {

                // Skip empty lines.
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the row into values.
                String[] values = line.split(",", -1);

                // Skip rows that do not have enough columns.
                if (values.length < 5) {
                    continue;
                }

                // Skip header rows or invalid rows where the first column is not numeric.
                if (!values[0].trim().matches("\\d+")) {
                    continue;
                }

                int equipmentId = Integer.parseInt(values[0].trim());
                int categoryId = Integer.parseInt(values[1].trim());
                String name = values[2].trim();
                String description = values[3].trim();
                double dailyRate = Double.parseDouble(values[4].trim());

                // Default all loaded equipment to Available for now.
                Equipment equipment = new Equipment(
                        equipmentId,
                        categoryId,
                        name,
                        description,
                        dailyRate,
                        "Available"
                );

                equipmentList.add(equipment);
            }

        } catch (IOException e) {
            System.out.println("Error loading equipment: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in equipment file: " + e.getMessage());
        }

        return equipmentList;
    }

    /**
     * Loads customer data from a CSV file.
     *
     * Expected columns:
     * customer_id, last_name, first_name, contact_phone, e-mail
     *
     * @param filePath path to the customer CSV file
     * @return list of Customer objects
     */
    public List<Customer> loadCustomers(String filePath) {
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line in the file.
            while ((line = reader.readLine()) != null) {

                // Skip empty lines.
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the row into values.
                String[] values = line.split(",", -1);

                // Skip rows that do not have enough columns.
                if (values.length < 5) {
                    continue;
                }

                // Skip header rows or invalid rows where the first column is not numeric.
                if (!values[0].trim().matches("\\d+")) {
                    continue;
                }

                int customerId = Integer.parseInt(values[0].trim());
                String lastName = values[1].trim();
                String firstName = values[2].trim();
                String phone = values[3].trim();
                String email = values[4].trim();

                // The CSV does not include banned or discount fields,
                // so default values are assigned for now.
                Customer customer = new Customer(
                        customerId,
                        firstName,
                        lastName,
                        phone,
                        email,
                        false,
                        0.0
                );

                customers.add(customer);
            }

        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in customer file: " + e.getMessage());
        }

        return customers;
    }

    /**
     * Loads rental data from a CSV file.
     *
     * Expected columns:
     * rental_id, date, customer_id, equipment_id, rental_date, return_date, cost
     *
     * This method uses the already loaded customer and equipment lists
     * so it can connect rentals to real objects.
     *
     * @param filePath path to the rental CSV file
     * @param customers list of already loaded customers
     * @param equipmentList list of already loaded equipment
     * @return list of Rental objects
     */
    public List<Rental> loadRentals(
            String filePath,
            List<Customer> customers,
            List<Equipment> equipmentList) {

        List<Rental> rentals = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line in the file.
            while ((line = reader.readLine()) != null) {

                // Skip empty lines.
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the row into values.
                String[] values = line.split(",", -1);

                // Skip rows that do not have enough columns.
                if (values.length < 7) {
                    continue;
                }

                // Skip header rows or invalid rows where the first column is not numeric.
                if (!values[0].trim().matches("\\d+")) {
                    continue;
                }

                int rentalId = Integer.parseInt(values[0].trim());

                // Read the document date even though it is not stored in Rental right now.
                String documentDateText = values[1].trim();

                int customerId = Integer.parseInt(values[2].trim());
                int equipmentId = Integer.parseInt(values[3].trim());
                LocalDate rentalDate = LocalDate.parse(values[4].trim(), DATE_FORMAT);
                LocalDate returnDate = LocalDate.parse(values[5].trim(), DATE_FORMAT);
                double cost = Double.parseDouble(values[6].trim());

                // Find the matching customer and equipment.
                Customer customer = findCustomerById(customers, customerId);
                Equipment equipment = findEquipmentById(equipmentList, equipmentId);

                // Only create the rental if both customer and equipment exist.
                if (customer != null && equipment != null) {
                    Rental rental = new Rental(rentalId, customer, rentalDate, returnDate);

                    // Use rentalId as a simple rental item ID for now.
                    RentalItem rentalItem = new RentalItem(rentalId, equipment, cost);

                    rental.addItem(rentalItem);
                    rental.calculateTotalCost();

                    rentals.add(rental);

                    // Mark the equipment as rented after loading.
                    equipment.updateStatus("Rented");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading rentals: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing rentals: " + e.getMessage());
        }

        return rentals;
    }

    /**
     * Finds a customer in a list by customer ID.
     *
     * @param customers list of customers
     * @param customerId customer ID to search for
     * @return matching Customer object, or null if not found
     */
    private Customer findCustomerById(List<Customer> customers, int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Finds equipment in a list by equipment ID.
     *
     * @param equipmentList list of equipment
     * @param equipmentId equipment ID to search for
     * @return matching Equipment object, or null if not found
     */
    private Equipment findEquipmentById(List<Equipment> equipmentList, int equipmentId) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getEquipmentId() == equipmentId) {
                return equipment;
            }
        }
        return null;
    }
}