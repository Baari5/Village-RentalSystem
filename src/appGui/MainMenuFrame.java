package appGui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * MainMenuFrame represents the main dashboard of the Village Rentals system.
 *
 * This frame provides buttons for the main system functions such as
 * adding equipment, removing equipment, adding customers, displaying records,
 * and processing rentals.
 */
public class MainMenuFrame extends JFrame {

    /** Reference to the UIManager used to connect the GUI to the backend logic. */
    private UIManager uiManager;

    /** Button used to open the Add Equipment screen. */
    private JButton addEquipmentButton;

    /** Button used to open the Remove Equipment screen. */
    private JButton removeEquipmentButton;

    /** Button used to open the Add Customer screen. */
    private JButton addCustomerButton;

    /** Button used to open the Display Equipment screen. */
    private JButton displayEquipmentButton;

    /** Button used to open the Display Customers screen. */
    private JButton displayCustomersButton;

    /** Button used to open the Process Rental screen. */
    private JButton processRentalButton;

    /**
     * Constructs the main menu frame.
     *
     * @param uiManager the UIManager used by the application
     */
    public MainMenuFrame(UIManager uiManager) {
        this.uiManager = uiManager;

        // Set up the frame properties.
        setTitle("Village Rentals - Main Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Build the user interface.
        initializeComponents();

        // Make the frame visible.
        setVisible(true);
    }

    /**
     * Initializes and arranges all GUI components in the frame.
     */
    private void initializeComponents() {

        // Create the title label.
        JLabel titleLabel = new JLabel("Village Rentals System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Create the menu buttons.
        addEquipmentButton = new JButton("Add Equipment");
        removeEquipmentButton = new JButton("Remove Equipment");
        addCustomerButton = new JButton("Add Customer");
        displayEquipmentButton = new JButton("Display Equipment");
        displayCustomersButton = new JButton("Display Customers");
        processRentalButton = new JButton("Process Rental");

        // Create a panel for the buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));

        // Add buttons to the panel.
        buttonPanel.add(addEquipmentButton);
        buttonPanel.add(removeEquipmentButton);
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(displayEquipmentButton);
        buttonPanel.add(displayCustomersButton);
        buttonPanel.add(processRentalButton);

        // Set the layout of the frame and add components.
        setLayout(new BorderLayout(10, 10));
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Register button actions.
        registerButtonActions();
    }

    /**
     * Registers button actions for the main menu.
     *
     * Each button opens the corresponding GUI screen.
     */
    private void registerButtonActions() {

        // Open the Add Equipment frame.
        addEquipmentButton.addActionListener(e -> {
            new AddEquipmentFrame(uiManager);
        });

        // Open the Remove Equipment frame.
        removeEquipmentButton.addActionListener(e -> {
            new RemoveEquipmentFrame(uiManager);
        });

        // Open the Add Customer frame.
        addCustomerButton.addActionListener(e -> {
            new AddCustomerFrame(uiManager);
        });

        // Open the Display Equipment frame.
        displayEquipmentButton.addActionListener(e -> {
            new DisplayEquipmentFrame(uiManager);
        });

        // Open the Display Customers frame.
        displayCustomersButton.addActionListener(e -> {
            new DisplayCustomersFrame(uiManager);
        });

        // Open the Process Rental frame.
        processRentalButton.addActionListener(e -> {
            new ProcessRentalFrame(uiManager);
        });
    }
}