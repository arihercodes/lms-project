import gui.LoginWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Ensure the GUI uses the system's look and feel for better visuals
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Warning: Could not set system look and feel.");
        }

        // Launch the login window on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new LoginWindow());
    }
}
