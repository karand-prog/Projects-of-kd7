import javax.swing.*;
import java.awt.*;

public class Electronic1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Attractive Design Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Create a panel for the main layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Create a label for the name at the top
        JLabel nameLabel = new JLabel("Electronic Shop");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.DARK_GRAY);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a panel for the image and information
        JPanel imageAndInfoPanel = new JPanel();
        imageAndInfoPanel.setLayout(new FlowLayout());
        imageAndInfoPanel.setBackground(new Color(200, 200, 255)); // Light purple background
        imageAndInfoPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        // Load the image
        ImageIcon imageIcon = new ImageIcon("images/ele1.png"); // Replace with the actual path to your image
        Image image = imageIcon.getImage().getScaledInstance(300, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        // Create a panel for the information
        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
        informationPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        informationPanel.setBackground(new Color(255, 200, 200)); // Light orange background

        // Add information labels with boxes
        informationPanel.add(createInfoLabel("Information :-"));
        informationPanel.add(createInfoLabel("Name: Siddhesh Store"));
        informationPanel.add(createInfoLabel("Location: Vijay Nagar,Nanded"));
        informationPanel.add(createInfoLabel("Type: Retail Shop"));
        informationPanel.add(createInfoLabel("Mobile Number: 1234567890"));
        informationPanel.add(createInfoLabel("Payment Mode: Online & Offline"));
        informationPanel.add(createInfoLabel("Time: 10 AM To 10 PM"));

        // Add image and information panels to the main panel
        imageAndInfoPanel.add(imageLabel);
        imageAndInfoPanel.add(Box.createHorizontalStrut(10));
        imageAndInfoPanel.add(informationPanel);

        // Add the name label and imageAndInfoPanel to the main panel
        mainPanel.add(nameLabel);
        mainPanel.add(imageAndInfoPanel);

        // Create a panel for the block below
        JPanel blockBelowPanel = new JPanel();
        blockBelowPanel.setLayout(new GridLayout(0, 6, 10, 10)); // Six columns, 10-pixel gap
        blockBelowPanel.setBackground(Color.LIGHT_GRAY);

        // Add small icons with modified text to the block below
        blockBelowPanel.add(createIconLabela("images/ac.jpeg" , "A.C."));
        blockBelowPanel.add(createIconLabela("images/camera.jpeg" , "camera"));
        blockBelowPanel.add(createIconLabela("images/charger.jpeg" , "charger"));
        blockBelowPanel.add(createIconLabela("images/fan.jpeg" , "fan"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("images/frigde.jpeg" , "fridge"));
        blockBelowPanel.add(createIconLabela("images/laptop.jpeg" , "laptop"));
        blockBelowPanel.add(createIconLabela("images/mobile.jpeg" , "Mobile"));
        blockBelowPanel.add(createIconLabela("images/tv.jpeg" , "T.V."));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("images/wgrezer.jpeg" , "Water Grezer"));
        blockBelowPanel.add(createIconLabela("images/wmachine.jpeg" , "Washing Machine"));
        blockBelowPanel.add(createIconLabela("images/cinthol.jpeg" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("images/cinthol.jpeg" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("images/fan.png" , "Fan"));
        blockBelowPanel.add(createIconLabela("images/tv.jpeg" , "T.V."));
        blockBelowPanel.add(createIconLabela("images/manye.jpeg" , "And Many More"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));
        blockBelowPanel.add(createIconLabela("Icon" , "ModifiedText"));

        // Add the block below to the main panel
        mainPanel.add(blockBelowPanel);

        // Create a JScrollPane and set the main panel as its viewport
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Set the scroll pane on the frame
        frame.getContentPane().add(scrollPane);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Helper method to create information labels with boxes
    private static JPanel createInfoLabel(String labelText) {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        labelPanel.add(label);

        // Add a box around the label
        labelPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        return labelPanel;
    }

    // Helper method to create small icons with modified text
    private static JLabel createIconLabela(String iconPath, String labelText) {
        JLabel label = new JLabel(labelText, JLabel.CENTER);
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledIcon));
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setBackground(new Color(20, 199, 20)); // Light green background

        // Add modified text below the icon
        JLabel textLabel = new JLabel(labelText);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.add(textLabel);

        // Add a box around the icon and text
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        return label;
    }
    private static JPanel createIconLabel(String iconName, String labelText) {
        JPanel iconLabelPanel = new JPanel();
        iconLabelPanel.setLayout(new BoxLayout(iconLabelPanel, BoxLayout.Y_AXIS));
        iconLabelPanel.setBackground(new Color(200, 255, 200)); // Light green background

        // Add modified text below the icon
        JLabel textLabel = new JLabel(labelText);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabelPanel.add(textLabel);

        // Add a box around the icon and text
        iconLabelPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        return iconLabelPanel;
    }
}
