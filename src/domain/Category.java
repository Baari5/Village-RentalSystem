package domain;

/**
 * Represents an equipment category in the Village Rentals system.
 * 
 * Examples:
 * Power tools, Yard equipment, Compressors, Generators, Air tools.
 */
public class Category {

    /** Unique ID used to identify the category. */
    private int categoryId;

    /** Name or description of the category. */
    private String name;

    /**
     * Constructs a Category object with the given ID and name.
     * 
     * @param categoryId the unique category ID
     * @param name the category name
     */
    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    /**
     * Returns the category ID.
     * 
     * @return the category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID.
     * 
     * @param categoryId the new category ID
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Returns the category name.
     * 
     * @return the category name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the category name.
     * 
     * @param name the new category name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a readable string version of the category.
     * 
     * @return formatted category details
     */
    @Override
    public String toString() {
        return "Category ID: " + categoryId + ", Name: " + name;
    }
}