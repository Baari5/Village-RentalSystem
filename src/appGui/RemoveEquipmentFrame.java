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
 * RemoveEquipmentFrame provides a form for removing equipment from the system.
 *
 * This frame collects an equipment ID from the user and sends it
 * to UIManager for removal.
 */
public class RemoveEquipmentFrame extends JFrame {

    /** Reference to the UIManager used to access backend logic. */
    private UIManager uiManager;

    /** Text field for the equipment ID to remove. */
    private JTextField equipmentIdField;

    /** Button used to submit the remove request. */
    private JButton removeButton;

    /**
     * Constructs the Remove Equipment frame.
     *
     * @param uiManager the UIManager used by the application
     */
    public RemoveEquipmentFrame(UIManager uiManager) {
        this.uiManager = uiManager;

        // Set up the frame properties.
        setTitle("Village Rentals - Remove Equipment");
        setSize(400, 180);
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

        // Create the form panel with label and text field.
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        formPanel.add(new JLabel("Equipment ID:"));
        equipmentIdField = new JTextField();
        formPanel.add(equipmentIdField);

        // Create the button panel.
        JPanel buttonPanel = new JPanel();
        removeButton = new JButton("Remove Equipment");
        buttonPanel.add(removeButton);

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

        removeButton.addActionListener(e -> {
            try {
                // Read the equipment ID from the text field.
                int equipmentId = Integer.parseInt(equipmentIdField.getText().trim());

                // Call UIManager to remove the equipment.
                boolean removed = uiManager.removeEquipment(equipmentId);

                // Show result message.
                if (removed) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Equipment removed successfully."
                    );

                    // Clear the form after successful removal.
                    equipmentIdField.setText("");
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Equipment could not be removed. Check that the ID exists.",
                            "Remove Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {
                // Show an error if the entered equipment ID is not a valid number.
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid numeric Equipment ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
