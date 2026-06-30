package appGui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * DisplayEquipmentFrame shows all equipment currently loaded in the system.
 *
 * This frame gets the equipment data from UIManager and displays it
 * inside a scrollable text area.
 */
public class DisplayEquipmentFrame extends JFrame {

    /** Reference to the UIManager used to access backend data. */
    private UIManager uiManager;

    /** Text area used to display all equipment information. */
    private JTextArea equipmentTextArea;

    /**
     * Constructs the Display Equipment frame.
     *
     * @param uiManager the UIManager used by the application
     */
    public DisplayEquipmentFrame(UIManager uiManager) {
        this.uiManager = uiManager;

        // Set up the frame properties.
        setTitle("Village Rentals - Display Equipment");
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

        // Create the text area used to display equipment information.
        equipmentTextArea = new JTextArea();

        // Prevent the user from editing the displayed text.
        equipmentTextArea.setEditable(false);

        // Set a readable font for the text area.
        equipmentTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Load all equipment as formatted text from the UIManager.
        equipmentTextArea.setText(uiManager.getAllEquipmentAsText());

        // Add the text area to a scroll pane in case the list is long.
        JScrollPane scrollPane = new JScrollPane(equipmentTextArea);

        // Use a simple border layout and place the scroll pane in the center.
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
}
