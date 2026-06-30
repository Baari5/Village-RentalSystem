package domain;

/**
 * Represents one equipment item in the Village Rentals system.
 * 
 * Each equipment item belongs to a category and has a daily rental cost.
 */
public class Equipment {

    /** Unique ID used to identify the equipment item. */
    private int equipmentId;

    /** ID of the category this equipment belongs to. */
    private int categoryId;

    /** Name of the equipment item. */
    private String name;

    /** Description of the equipment item. */
    private String description;

    /** Daily rental rate for this equipment item. */
    private double dailyRentalCost;

    /** Current status of the equipment, such as Available or Rented. */
    private String status;

    /**
     * Constructs an Equipment object with all required details.
     * 
     * @param equipmentId unique equipment ID
     * @param categoryId category ID for this equipment
     * @param name name of the equipment
     * @param description description of the equipment
     * @param dailyRentalCost daily rental cost
     * @param status current equipment status
     */
    public Equipment(int equipmentId, int categoryId, String name, String description, double dailyRentalCost, String status) {
        this.equipmentId = equipmentId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.dailyRentalCost = dailyRentalCost;
        this.status = status;
    }

    /**
     * Returns the equipment ID.
     * 
     * @return equipment ID
     */
    public int getEquipmentId() {
        return equipmentId;
    }

    /**
     * Sets the equipment ID.
     * 
     * @param equipmentId new equipment ID
     */
    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * Returns the category ID.
     * 
     * @return category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID.
     * 
     * @param categoryId new category ID
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Returns the equipment name.
     * 
     * @return equipment name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the equipment name.
     * 
     * @param name new equipment name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the equipment description.
     * 
     * @return equipment description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the equipment description.
     * 
     * @param description new equipment description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the daily rental cost.
     * 
     * @return daily rental cost
     */
    public double getDailyRentalCost() {
        return dailyRentalCost;
    }

    /**
     * Sets the daily rental cost.
     * 
     * @param dailyRentalCost new daily rental cost
     */
    public void setDailyRentalCost(double dailyRentalCost) {
        this.dailyRentalCost = dailyRentalCost;
    }

    /**
     * Returns the current equipment status.
     * 
     * @return equipment status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the equipment status.
     * 
     * @param status new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Updates equipment details.
     * 
     * @param name updated equipment name
     * @param description updated equipment description
     * @param dailyRentalCost updated daily rental cost
     */
    public void updateEquipment(String name, String description, double dailyRentalCost) {
        this.name = name;
        this.description = description;
        this.dailyRentalCost = dailyRentalCost;
    }

    /**
     * Updates the status of the equipment.
     * 
     * @param status new equipment status
     */
    public void updateStatus(String status) {
        this.status = status;
    }

    /**
     * Checks whether the equipment is currently available for rental.
     * 
     * @return true if equipment status is "Available", false otherwise
     */
    public boolean isAvailable() {
        return status.equalsIgnoreCase("Available");
    }

    /**
     * Returns a readable string version of the equipment.
     * 
     * @return formatted equipment details
     */
    @Override
    public String toString() {
        return "Equipment ID: " + equipmentId
                + ", Category ID: " + categoryId
                + ", Name: " + name
                + ", Description: " + description
                + ", Daily Cost: $" + dailyRentalCost
                + ", Status: " + status;
    }
}
