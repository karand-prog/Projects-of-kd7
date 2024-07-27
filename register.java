import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class register {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/registera";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Karan@9970";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(register::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Registration Page");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JTextField nameField = new JTextField(15);
        JTextField emailField = createEmailField();
        JFormattedTextField mobileNumberField = createFormattedTextField();
        JTextField dobField = new JTextField(15);
        JTextField addressField = new JTextField(15);
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        addField(panel, gbc, "Name:", nameField);
        addField(panel, gbc, "Email ID:", emailField);
        addField(panel, gbc, "Mobile Number:", mobileNumberField);
        addField(panel, gbc, "Date of Birth:", dobField);
        addField(panel, gbc, "Address:", addressField);
        addField(panel, gbc, "Username:", usernameField);
        addField(panel, gbc, "Password:", passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e ->
                performRegistration(
                        nameField.getText(),
                        emailField.getText(),
                        mobileNumberField.getText(),
                        dobField.getText(),
                        addressField.getText(),
                        usernameField.getText(),
                        new String(passwordField.getPassword())
                )
        );
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(registerButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent component) {
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private static void performRegistration(String name, String email, String mobileNumber, String dob,
                                            String address, String username, String password) {
        if (name.isEmpty() || email.isEmpty() || mobileNumber.isEmpty() || dob.isEmpty()
                || address.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!isValidMobileNumber(mobileNumber)) {
            JOptionPane.showMessageDialog(null, "Enter a valid 10-digit mobile number.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean registrationSuccess = registerUser(name, email, mobileNumber, dob, address, username, password);

            if (registrationSuccess) {
                JOptionPane.showMessageDialog(null, "Registration Successful");

                // Optionally, perform additional actions or navigate to another page
                String programToExecute = "C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\LoginPage.java"; // Adjust the path accordingly
                try {
                    Process process = Runtime.getRuntime().exec(programToExecute);
                    process.waitFor();
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error registering user.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.matches("\\d{10}") && !mobileNumber.startsWith("0");
    }

    private static boolean isValidEmail(String email) {
        // Regular expression for a simple email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private static JFormattedTextField createFormattedTextField() {
        MaskFormatter maskFormatter = null;
        try {
            maskFormatter = new MaskFormatter("##########");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        JFormattedTextField formattedTextField = new JFormattedTextField();
        formattedTextField.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
        return formattedTextField;
    }

    private static JTextField createEmailField() {
        JTextField emailField = new JTextField(15);
        // Optionally, you can set an input verifier for more advanced email validation
        emailField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField textField = (JTextField) input;
                String email = textField.getText();
                return isValidEmail(email);
            }
        });
        return emailField;
    }

    private static boolean registerUser(String name, String email, String mobile, String dob,
                                        String address, String username, String password) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String insertSql = "INSERT INTO data (name, email, mobile, dob, address, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, mobile);
                    preparedStatement.setString(4, dob);
                    preparedStatement.setString(5, address);
                    preparedStatement.setString(6, username);
                    preparedStatement.setString(7, password);

                    int rowsAffected = preparedStatement.executeUpdate();
                    return rowsAffected > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
