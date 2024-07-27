import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        addComponent(panel, new JLabel("Username:"), constraints, 0, 0);
        JTextField usernameField = new JTextField(20);
        addComponent(panel, usernameField, constraints, 1, 0);

        addComponent(panel, new JLabel("Password:"), constraints, 0, 1);
        JPasswordField passwordField = new JPasswordField(20);
        addComponent(panel, passwordField, constraints, 1, 1);

        JButton signInButton = new JButton("Sign In");
        JButton registerButton = new JButton("Register");

        addComponent(panel, signInButton, constraints, 1, 2);
        addComponent(panel, registerButton, constraints, 2, 3);

        // Add ActionListener to handle Sign In button click
        signInButton.addActionListener(e -> executeExternalJavaFile("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\Check.java"));

        // Add ActionListener to handle Register button click
        registerButton.addActionListener(e -> executeExternalJavaFile("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\register.java"));

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addComponent(JPanel panel, JComponent component, GridBagConstraints constraints, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        panel.add(component, constraints);
    }

    private static void executeExternalJavaFile(String filePath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", filePath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Print output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            process.waitFor();

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error executing external Java file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
