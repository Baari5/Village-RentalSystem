package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the inventory of equipment in the Village Rentals system.
 * 
 * The inventory stores and manages all equipment items available in the system.
 */
public class Inventory {

    /** List of all equipment items in the inventory. */
    private List<Equipment> equipmentList;

    /**
     * Constructs an Inventory object with an empty equipment list.
     */
    public Inventory() {
        this.equipmentList = new ArrayList<>();
    }

    /**
     * Returns the full list of equipment items in the inventory.
     * 
     * @return list of equipment
     */
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    /**
     * Adds a new equipment item to the inventory.
     * 
     * @param equipment the equipment item to add
     */
    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }

    /**
     * Removes an equipment item from the inventory using its equipment ID.
     * 
     * @param equipmentId the ID of the equipment to remove
     * @return true if the equipment was removed, false otherwise
     */
    public boolean removeEquipment(int equipmentId) {
        Equipment equipment = findEquipmentById(equipmentId);

        if (equipment != null) {
            equipmentList.remove(equipment);
            return true;
        }

        return false;
    }

    /**
     * Finds an equipment item in the inventory by its ID.
     * 
     * @param equipmentId the equipment ID to search for
     * @return the matching equipment object, or null if not found
     */
    public Equipment findEquipmentById(int equipmentId) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getEquipmentId() == equipmentId) {
                return equipment;
            }
        }
        return null;
    }

    /**
     * Checks whether an equipment item is available for rental.
     * 
     * @param equipmentId the equipment ID to check
     * @return true if the equipment exists and is available, false otherwise
     */
    public boolean checkAvailability(int equipmentId) {
        Equipment equipment = findEquipmentById(equipmentId);

        if (equipment != null) {
            return equipment.isAvailable();
        }

        return false;
    }

    /**
     * Returns a list of only the available equipment items.
     * 
     * @return list of available equipment
     */
    public List<Equipment> listAvailableEquipment() {
        List<Equipment> availableItems = new ArrayList<>();

        for (Equipment equipment : equipmentList) {
            if (equipment.isAvailable()) {
                availableItems.add(equipment);
            }
        }

        return availableItems;
    }

    /**
     * Displays all equipment items in the inventory.
     * 
     * This method prints each equipment item to the console.
     */
    public void displayAllEquipment() {
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment found in inventory.");
            return;
        }

        for (Equipment equipment : equipmentList) {
            System.out.println(equipment);
        }
    }

    /**
     * Returns the total number of equipment items in the inventory.
     * 
     * @return equipment count
     */
    public int getEquipmentCount() {
        return equipmentList.size();
    }
}
