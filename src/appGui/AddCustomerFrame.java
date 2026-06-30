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
 * AddCustomerFrame provides a form for adding a new customer to the system.
 *
 * This frame collects customer information from the user and sends it
 * to UIManager for processing.
 */
public class AddCustomerFrame extends JFrame {

    /** Reference to the UIManager used to access backend logic. */
    private UIManager uiManager;

    /** Text field for the customer ID. */
    private JTextField customerIdField;

    /** Text field for the customer's first name. */
    private JTextField firstNameField;

    /** Text field for the customer's last name. */
    private JTextField lastNameField;

    /** Text field for the customer's phone number. */
    private JTextField phoneField;

    /** Text field for the customer's email address. */
    private JTextField emailField;

    /** Button used to submit the form. */
    private JButton addButton;

    /**
     * Constructs the Add Customer frame.
     *
     * @param uiManager the UIManager used by the application
     */
    public AddCustomerFrame(UIManager uiManager) {
        this.uiManager = uiManager;

        // Set up the frame properties.
        setTitle("Village Rentals - Add Customer");
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

        formPanel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField();
        formPanel.add(customerIdField);

        formPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        formPanel.add(firstNameField);

        formPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        formPanel.add(lastNameField);

        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        // Create the button panel.
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Customer");
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
                int customerId = Integer.parseInt(customerIdField.getText().trim());
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();

                // Validate text fields that should not be empty.
                if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "All fields must be filled in.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Call UIManager to add the customer.
                boolean added = uiManager.addCustomer(
                        customerId,
                        firstName,
                        lastName,
                        phone,
                        email
                );

                // Show result message.
                if (added) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Customer added successfully."
                    );

                    // Clear the form after successful submission.
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Customer could not be added. The ID may already exist.",
                            "Add Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {
                // Show an error if the customer ID is not a valid number.
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid numeric value for Customer ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                // Show any unexpected error message.
                JOptionPane.showMessageDialog(
                        this,
                        "Unexpected error: " + ex.getMessage(),
                        "System Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    /**
     * Clears all input fields in the form.
     */
    private void clearForm() {
        customerIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }
}