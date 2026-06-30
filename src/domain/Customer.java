package domain;

/**
 * Represents a customer in the Village Rentals system.
 * 
 * A customer may have a discount rate and may also be banned from renting.
 */
public class Customer {

    /** Unique ID used to identify the customer. */
    private int customerId;

    /** Customer's first name. */
    private String firstName;

    /** Customer's last name. */
    private String lastName;

    /** Customer's phone number. */
    private String phone;

    /** Customer's email address. */
    private String email;

    /** Indicates whether the customer is banned from renting equipment. */
    private boolean isBanned;

    /** Discount rate applied to this customer, such as 0.10 for 10%. */
    private double discountRate;

    /**
     * Constructs a Customer object with all required details.
     * 
     * @param customerId unique customer ID
     * @param firstName customer's first name
     * @param lastName customer's last name
     * @param phone customer's phone number
     * @param email customer's email address
     * @param isBanned whether the customer is banned
     * @param discountRate customer's discount rate
     */
    public Customer(int customerId, String firstName, String lastName, String phone, String email, boolean isBanned, double discountRate) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.isBanned = isBanned;
        this.discountRate = discountRate;
    }

    /**
     * Returns the customer ID.
     * 
     * @return customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     * 
     * @param customerId new customer ID
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns the first name.
     * 
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * 
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name.
     * 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     * 
     * @param lastName new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the phone number.
     * 
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number.
     * 
     * @param phone new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the email address.
     * 
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     * 
     * @param email new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Checks whether the customer is banned.
     * 
     * @return true if banned, false otherwise
     */
    public boolean isBanned() {
        return isBanned;
    }

    /**
     * Bans the customer from renting.
     */
    public void banCustomer() {
        this.isBanned = true;
    }

    /**
     * Removes the banned status from the customer.
     */
    public void unbanCustomer() {
        this.isBanned = false;
    }

    /**
     * Returns the customer's discount rate.
     * 
     * @return discount rate
     */
    public double getDiscountRate() {
        return discountRate;
    }

    /**
     * Sets the customer's discount rate.
     * 
     * @param discountRate new discount rate
     */
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * Updates basic customer information.
     * 
     * @param firstName updated first name
     * @param lastName updated last name
     * @param phone updated phone number
     * @param email updated email address
     */
    public void updateCustomer(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Applies a discount rate to the customer.
     * 
     * @param discountRate discount rate to apply
     */
    public void applyDiscount(double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * Returns the full name of the customer.
     * 
     * @return customer full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns a readable string version of the customer.
     * 
     * @return formatted customer details
     */
    @Override
    public String toString() {
        return "Customer ID: " + customerId
                + ", Name: " + getFullName()
                + ", Phone: " + phone
                + ", Email: " + email
                + ", Banned: " + isBanned
                + ", Discount Rate: " + discountRate;
    }
}