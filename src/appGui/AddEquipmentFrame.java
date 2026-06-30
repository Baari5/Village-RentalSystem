package appGui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * AddEquipmentFrame provides a form for adding new equipment to the system.
 *
 * This frame collects equipment information from the user and sends it
 * to UIManager for processing.
 */
public class AddEquipmentFrame extends JFrame {

    /** Reference to the UIManager used to access backend logic. */
    private UIManager uiManager;

    /** Text field for the equipment ID. */
    private JTextField equipmentIdField;

    /** Text field for the category ID. */
    private JTextField categoryIdField;

    /** Text field for the equipment name. */
    private JTextField nameField;

    /** Text field for the equipment description. */
    private JTextField descriptionField;

    /** Text field for the daily rental cost. */
    private JTextField dailyRentalCostField;

    /** Button used to submit the form. */
    private JButton addButton;

    /**
     * Constructs the Add Equipment frame.
     *
     * @param uiManager the UIManager used by the application
     */
    public AddEquipmentFrame(UIManager uiManager) {
        this.uiManager = uiManager;

        // Set up the frame properties.
        setTitle("Village Rentals - Add Equipment");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Build the user interface.
        initializeComponents();

        // Make the frame visible.
        setVisible(true);
    }

    /**
     * Initializes and arranges all GUI components in the frame.
     */
    private void initializeComponents() {

        // Create the form panel with labels and text fields.
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        formPanel.add(new JLabel("Equipment ID:"));
        equipmentIdField = new JTextField();
        formPanel.add(equipmentIdField);

        formPanel.add(new JLabel("Category ID:"));
        categoryIdField = new JTextField();
        formPanel.add(categoryIdField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        formPanel.add(descriptionField);

        formPanel.add(new JLabel("Daily Rental Cost:"));
        dailyRentalCostField = new JTextField();
        formPanel.add(dailyRentalCostField);

        // Create the button panel.
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Equipment");
        buttonPanel.add(addButton);

        // Add panels to the frame.
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Register button action.
        registerButtonActions();
    }

    /**
     * Registers the button event handlers.
     */
    private void registerButtonActions() {

        addButton.addActionListener(e -> {
            try {
                // Read and convert input values from the text fields.
                int equipmentId = Integer.parseInt(equipmentIdField.getText().trim());
                int categoryId = Integer.parseInt(categoryIdField.getText().trim());
                String name = nameField.getText().trim();
                String description = descriptionField.getText().trim();
                double dailyRentalCost = Double.parseDouble(dailyRentalCostField.getText().trim());

                // Validate text fields that should not be empty.
                if (name.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Name and description cannot be empty.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Call UIManager to add the equipment.
                boolean added = uiManager.addEquipment(
                        equipmentId,
                        categoryId,
                        name,
                        description,
                        dailyRentalCost
                );

                // Show result message.
                if (added) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Equipment added successfully."
                    );

                    // Clear the form after successful submission.
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Equipment could not be added. The ID may already exist.",
                            "Add Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {
                // Show an error if numeric fields contain invalid values.
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid numeric values for Equipment ID, Category ID, and Daily Rental Cost.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    /**
     * Clears all input fields in the form.
     */
    private void clearForm() {
        equipmentIdField.setText("");
        categoryIdField.setText("");
        nameField.setText("");
        descriptionField.setText("");
        dailyRentalCostField.setText("");
    }
}
