package appGui;

/**
 * Main class used to launch the Village Rentals application.
 */
public class Main {

    /**
     * Program entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Create the UI manager.
        UIManager uiManager = new UIManager();

        // Load initial data into the system.
        uiManager.loadInitialData();

        // Launch the main menu screen.
        new MainMenuFrame(uiManager);
    }
}
