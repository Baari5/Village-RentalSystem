package appGui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * DisplayCustomersFrame shows all customers currently loaded in the system.
 *
 * This frame gets the customer data from UIManager and displays it
 * inside a scrollable text area.
 */
public class DisplayCustomersFrame extends JFrame {

    /** Reference to the UIManager used to access backend data. */
    private UIManager uiManager;

    /** Text area used to display all customer information. */
    private JTextArea customersTextArea;

    /**
     * Constructs the Display Customers frame.
     *
     * @param uiManager the UIManager used by the application
     */
    public DisplayCustomersFrame(UIManager uiManager) {
        this.uiManager = uiManager;

        // Set up the frame properties.
        setTitle("Village Rentals - Display Customers");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Build the user interface.
        initializeComponents();

        // Make the frame visible.
        setVisible(true);
    }

    /**
     * Initializes and arranges GUI components in the frame.
     */
    private void initializeComponents() {

        // Create the text area used to display customer information.
        customersTextArea = new JTextArea();

        // Prevent the user from editing the displayed text.
        customersTextArea.setEditable(false);

        // Set a readable font for the text area.
        customersTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Load all customers as formatted text from the UIManager.
        customersTextArea.setText(uiManager.getAllCustomersAsText());

        // Add the text area to a scroll pane in case the list is long.
        JScrollPane scrollPane = new JScrollPane(customersTextArea);

        // Use a simple border layout and place the scroll pane in the center.
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
}