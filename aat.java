import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class aat {

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
        JLabel nameLabel = new JLabel("Grocery Shop");
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
        ImageIcon imageIcon = new ImageIcon("images/grc5.png"); // Replace with the actual path to your image
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
        informationPanel.add(createInfoLabel("Name: Chintamani Bakery"));
        informationPanel.add(createInfoLabel("Location: Shree Nagar, Nanded"));
        informationPanel.add(createInfoLabel("Type: Retail Shop"));
        informationPanel.add(createInfoLabel("Mobile Number: 1234567890"));
        informationPanel.add(createInfoLabel("Payment Mode: Online & Offline"));
        informationPanel.add(createInfoLabel("Time: 8 AM To 10 PM"));

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
        for (int i = 1; i <= 6; i++) {
            blockBelowPanel.add(createIconLabela("Icon" + i, "ModifiedText" + i));
        }

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
    private static JPanel createIconLabela(String iconName, String labelText) {
        JPanel iconLabelPanel = new JPanel();
        iconLabelPanel.setLayout(new BoxLayout(iconLabelPanel, BoxLayout.Y_AXIS));
        iconLabelPanel.setBackground(new Color(200, 255, 200)); // Light green background

        // Add a small icon (you can replace this with your own icon)
        ImageIcon icon = new ImageIcon("your_icon_path.png"); // Replace with the actual path to your icon
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.addMouseListener(new IconMouseListener());
        iconLabelPanel.add(iconLabel);

        // Add modified text below the icon
        JLabel textLabel = new JLabel(labelText);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabelPanel.add(textLabel);

        // Add a box around the icon and text
        iconLabelPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        return iconLabelPanel;
    }

    // Custom MouseAdapter for the clickable icons
    private static class IconMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Display a popup with additional icons and a blurred background
            showPopupIcons(e.getComponent());
        }
    }

    // Method to show popup with additional icons and blurred background
    private static void showPopupIcons(Component parent) {
        JDialog popupDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), "Popup Icons", true);
        popupDialog.setSize(400, 400);

        // Create a panel for the popup content
        JPanel popupContentPanel = new JPanel();
        popupContentPanel.setLayout(new FlowLayout());
        popupContentPanel.setBackground(new Color(200, 200, 255)); // Light purple background

        // Add additional icons to the popup panel
        for (int i = 1; i <= 6; i++) {
            ImageIcon additionalIcon = new ImageIcon("your_additional_icon_path_" + i + ".png"); // Replace with the actual path
            JLabel additionalIconLabel = new JLabel(additionalIcon);
            popupContentPanel.add(additionalIconLabel);
        }

        // Apply blur effect to the main content panel
        popupContentPanel = applyBlur(popupContentPanel);

        // Add the blurred content panel to the dialog
        popupDialog.add(popupContentPanel);

        // Center the dialog on the parent component
        popupDialog.setLocationRelativeTo(parent);

        // Make the dialog visible
        popupDialog.setVisible(true);
    }

    // Method to apply a blur effect to a component
    private static JPanel applyBlur(JPanel component) {
        // Create a blurred image of the component
        BufferedImage blurredImage = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = blurredImage.getGraphics();
        component.paint(g);
        g.dispose();

        // Apply the blur effect
        float[] blurKernel = {0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f};
        Kernel blur = new Kernel(3, 3, blurKernel);
        BufferedImageOp blurFilter = new ConvolveOp(blur);
        Image blurredImageResult = blurFilter.filter(blurredImage, null);

        // Create a new JPanel with the blurred image
        JPanel blurredPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(blurredImageResult, 0, 0, null);
            }
        };

        blurredPanel.setLayout(new BorderLayout());
        blurredPanel.add(component);

        return blurredPanel;
    }
}
