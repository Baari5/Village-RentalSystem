package appGui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.Category;
import domain.Customer;
import domain.Equipment;
import domain.Inventory;
import domain.Rental;
import domain.RentalItem;
import util.DataLoader;

/**
 * UIManager acts as the controller between the GUI layer and the domain layer.
 *
 * It loads system data, manages inventory, customers, and rentals,
 * and provides methods that GUI screens can call.
 */
public class UIManager {

    /** Stores all equipment categories loaded from the CSV file. */
    private List<Category> categories;

    /** Stores all customers loaded from the CSV file. */
    private List<Customer> customers;

    /** Stores all rentals loaded from the CSV file or created during runtime. */
    private List<Rental> rentals;

    /** Inventory object used to manage equipment. */
    private Inventory inventory;

    /** Data loader used to read CSV files into objects. */
    private DataLoader dataLoader;

    /**
     * Constructs a UIManager object and initializes all collections.
     */
    public UIManager() {
        this.categories = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
        this.inventory = new Inventory();
        this.dataLoader = new DataLoader();
    }

    /**
     * Loads initial system data from CSV files.
     *
     * This method should be called once when the application starts.
     */
    public void loadInitialData() {

        // Load categories from file.
        categories = dataLoader.loadCategories("data/categories.csv");

        // Load equipment from file.
        List<Equipment> equipmentList = dataLoader.loadEquipment("data/RentalEquipment.csv");

        // Add loaded equipment into inventory.
        for (Equipment equipment : equipmentList) {
            inventory.addEquipment(equipment);
        }

        // Load customers from file.
        customers = dataLoader.loadCustomers("data/CustomerInformation.csv");

        // Load rentals from file.
        rentals = dataLoader.loadRentals(
                "data/RentalInformation.csv",
                customers,
                inventory.getEquipmentList()
        );
    }

    /**
     * Returns all categories in the system.
     *
     * @return list of categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Returns all customers in the system.
     *
     * @return list of customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Returns all rentals in the system.
     *
     * @return list of rentals
     */
    public List<Rental> getRentals() {
        return rentals;
    }

    /**
     * Returns the inventory object.
     *
     * @return inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Returns all equipment currently in the inventory.
     *
     * @return list of equipment
     */
    public List<Equipment> getAllEquipment() {
        return inventory.getEquipmentList();
    }

    /**
     * Adds a new equipment item to the inventory.
     *
     * @param equipmentId unique equipment ID
     * @param categoryId category ID
     * @param name equipment name
     * @param description equipment description
     * @param dailyRentalCost daily rental price
     * @return true if the equipment was added successfully, false otherwise
     */
    public boolean addEquipment(int equipmentId, int categoryId, String name, String description, double dailyRentalCost) {

        // Prevent duplicate equipment IDs.
        if (inventory.findEquipmentById(equipmentId) != null) {
            return false;
        }

        // Create a new equipment object and add it to inventory.
        Equipment equipment = new Equipment(
                equipmentId,
                categoryId,
                name,
                description,
                dailyRentalCost,
                "Available"
        );

        inventory.addEquipment(equipment);
        return true;
    }

    /**
     * Removes an equipment item from the inventory using its ID.
     *
     * @param equipmentId equipment ID to remove
     * @return true if the equipment was removed, false otherwise
     */
    public boolean removeEquipment(int equipmentId) {
        return inventory.removeEquipment(equipmentId);
    }

    /**
     * Adds a new customer to the system.
     *
     * @param customerId unique customer ID
     * @param firstName customer's first name
     * @param lastName customer's last name
     * @param phone customer's phone number
     * @param email customer's email address
     * @return true if the customer was added successfully, false otherwise
     */
    public boolean addCustomer(int customerId, String firstName, String lastName, String phone, String email) {

        // Prevent duplicate customer IDs.
        if (findCustomerById(customerId) != null) {
            return false;
        }

        // Create a new customer with default values for banned status and discount.
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
        return true;
    }

    /**
     * Finds a customer by customer ID.
     *
     * @param customerId customer ID to search for
     * @return matching customer, or null if not found
     */
    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Processes the rental of one equipment item to one customer.
     *
     * @param rentalId unique rental ID
     * @param customerId customer ID
     * @param equipmentId equipment ID
     * @param rentalDate rental start date
     * @param returnDate rental end date
     * @return true if the rental was created successfully, false otherwise
     */
    public boolean processRental(int rentalId, int customerId, int equipmentId, LocalDate rentalDate, LocalDate returnDate) {

        // Prevent duplicate rental IDs.
        if (findRentalById(rentalId) != null) {
            return false;
        }

        // Find the customer.
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            return false;
        }

        // Prevent banned customers from renting.
        if (customer.isBanned()) {
            return false;
        }

        // Find the equipment.
        Equipment equipment = inventory.findEquipmentById(equipmentId);
        if (equipment == null) {
            return false;
        }

        // Check if the equipment is available.
        if (!equipment.isAvailable()) {
            return false;
        }

        // Calculate rental cost based on number of days.
        long numberOfDays = returnDate.toEpochDay() - rentalDate.toEpochDay();

        // Ensure at least one day is charged.
        if (numberOfDays <= 0) {
            numberOfDays = 1;
        }

        double cost = equipment.getDailyRentalCost() * numberOfDays;

        // Apply customer discount if one exists.
        if (customer.getDiscountRate() > 0) {
            cost = cost - (cost * customer.getDiscountRate());
        }

        // Create rental item.
        RentalItem rentalItem = new RentalItem(rentalId, equipment, cost);

        // Create rental transaction.
        Rental rental = new Rental(rentalId, customer, rentalDate, returnDate);
        rental.addItem(rentalItem);
        rental.calculateTotalCost();

        // Store rental and update equipment status.
        rentals.add(rental);
        equipment.updateStatus("Rented");

        return true;
    }

    /**
     * Finds a rental by rental ID.
     *
     * @param rentalId rental ID to search for
     * @return matching rental, or null if not found
     */
    public Rental findRentalById(int rentalId) {
        for (Rental rental : rentals) {
            if (rental.getRentalId() == rentalId) {
                return rental;
            }
        }
        return null;
    }

    /**
     * Returns a formatted string containing all equipment.
     *
     * This is useful for GUI text areas.
     *
     * @return formatted equipment list
     */
    public String getAllEquipmentAsText() {
        StringBuilder builder = new StringBuilder();

        for (Equipment equipment : inventory.getEquipmentList()) {
            builder.append(equipment).append("\n");
        }

        return builder.toString();
    }

    /**
     * Returns a formatted string containing all customers.
     *
     * This is useful for GUI text areas.
     *
     * @return formatted customer list
     */
    public String getAllCustomersAsText() {
        StringBuilder builder = new StringBuilder();

        for (Customer customer : customers) {
            builder.append(customer).append("\n");
        }

        return builder.toString();
    }

    /**
     * Returns a formatted string containing all rentals.
     *
     * This is useful for GUI text areas.
     *
     * @return formatted rental list
     */
    public String getAllRentalsAsText() {
        StringBuilder builder = new StringBuilder();

        for (Rental rental : rentals) {
            builder.append(rental).append("\n");
        }

        return builder.toString();
    }
}
