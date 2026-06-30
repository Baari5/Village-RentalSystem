package appGui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * ProcessRentalFrame provides a form for processing the rental of one
 * equipment item to one customer.
 *
 * This frame collects rental information from the user and sends it
 * to UIManager for processing.
 */
public class ProcessRentalFrame extends JFrame {

    /** Reference to the UIManager used to access backend logic. */
    private UIManager uiManager;

    /** Text field for the rental ID. */
    private JTextField rentalIdField;

    /** Text field for the customer ID. */
    private JTextField customerIdField;

    /** Text field for the equipment ID. */
    private JTextField equipmentIdField;

    /** Text field for the rental date. */
    private JTextField rentalDateField;

    /** Text field for the return date. */
    private JTextField returnDateField;

    /** Button used to submit the rental form. */
    private JButton processButton;

    /**
     * Constructs the Process Rental frame.
     *
     * @param uiManager the UIManager used by the application
     */
    public ProcessRentalFrame(UIManager uiManager) {
        this.uiManager = uiManager;

        // Set up the frame properties.
        setTitle("Village Rentals - Process Rental");
        setSize(500, 320);
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

        formPanel.add(new JLabel("Rental ID:"));
        rentalIdField = new JTextField();
        formPanel.add(rentalIdField);

        formPanel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField();
        formPanel.add(customerIdField);

        formPanel.add(new JLabel("Equipment ID:"));
        equipmentIdField = new JTextField();
        formPanel.add(equipmentIdField);

        formPanel.add(new JLabel("Rental Date (YYYY-MM-DD):"));
        rentalDateField = new JTextField();
        formPanel.add(rentalDateField);

        formPanel.add(new JLabel("Return Date (YYYY-MM-DD):"));
        returnDateField = new JTextField();
        formPanel.add(returnDateField);

        // Create the button panel.
        JPanel buttonPanel = new JPanel();
        processButton = new JButton("Process Rental");
        buttonPanel.add(processButton);

        // Add panels to the frame.
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Register the button action.
        registerButtonActions();
    }

    /**
     * Registers the button event handlers.
     */
    private void registerButtonActions() {

        processButton.addActionListener(e -> {
            try {
                // Read and convert input values from the text fields.
                int rentalId = Integer.parseInt(rentalIdField.getText().trim());
                int customerId = Integer.parseInt(customerIdField.getText().trim());
                int equipmentId = Integer.parseInt(equipmentIdField.getText().trim());
                LocalDate rentalDate = LocalDate.parse(rentalDateField.getText().trim());
                LocalDate returnDate = LocalDate.parse(returnDateField.getText().trim());

                // Validate that dates are not empty and return date is not before rental date.
                if (returnDate.isBefore(rentalDate)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Return date cannot be before rental date.",
                            "Date Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Call UIManager to process the rental.
                boolean processed = uiManager.processRental(
                        rentalId,
                        customerId,
                        equipmentId,
                        rentalDate,
                        returnDate
                );

                // Show result message.
                if (processed) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Rental processed successfully."
                    );

                    // Clear the form after successful submission.
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Rental could not be processed.\n"
                            + "Check that the rental ID is unique, the customer exists, "
                            + "the equipment exists, and the equipment is available.",
                            "Process Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {
                // Show an error if numeric fields contain invalid values.
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid numeric values for Rental ID, Customer ID, and Equipment ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                // Show an error if date parsing or another unexpected error occurs.
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid dates in the format YYYY-MM-DD.",
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
        rentalIdField.setText("");
        customerIdField.setText("");
        equipmentIdField.setText("");
        rentalDateField.setText("");
        returnDateField.setText("");
    }
}
