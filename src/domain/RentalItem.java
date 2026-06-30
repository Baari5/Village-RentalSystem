package domain;

/**
 * Represents a single rented equipment item within a rental transaction.
 * 
 * A RentalItem links a rental to one specific equipment item and stores
 * the cost for that item.
 */
public class RentalItem {

    /** Unique ID used to identify the rental item. */
    private int rentalItemId;

    /** The equipment being rented in this rental item. */
    private Equipment equipment;

    /** The cost charged for this rental item. */
    private double cost;

    /**
     * Constructs a RentalItem object with the given details.
     * 
     * @param rentalItemId unique rental item ID
     * @param equipment the equipment being rented
     * @param cost the cost for this item
     */
    public RentalItem(int rentalItemId, Equipment equipment, double cost) {
        this.rentalItemId = rentalItemId;
        this.equipment = equipment;
        this.cost = cost;
    }

    /**
     * Returns the rental item ID.
     * 
     * @return rental item ID
     */
    public int getRentalItemId() {
        return rentalItemId;
    }

    /**
     * Sets the rental item ID.
     * 
     * @param rentalItemId the new rental item ID
     */
    public void setRentalItemId(int rentalItemId) {
        this.rentalItemId = rentalItemId;
    }

    /**
     * Returns the equipment linked to this rental item.
     * 
     * @return rented equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * Sets the equipment for this rental item.
     * 
     * @param equipment the new equipment
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * Returns the cost of this rental item.
     * 
     * @return rental item cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the cost of this rental item.
     * 
     * @param cost the new cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Calculates and returns the cost of this rental item.
     * 
     * @return rental item cost
     */
    public double calculateItemCost() {
        return cost;
    }

    /**
     * Returns a readable string version of the rental item.
     * 
     * @return formatted rental item details
     */
    @Override
    public String toString() {
        return "Rental Item ID: " + rentalItemId
                + ", Equipment: " + equipment.getName()
                + ", Cost: $" + cost;
    }
}
