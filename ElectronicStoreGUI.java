import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ElectronicStoreGUI extends JFrame {
    private static final int ROWS = 4;
    private static final int COLS = 4;

    private JPanel imagePanel;
    private JLabel titleLabel;
    private JPanel slidePanel;
    private Timer slideTimer;
    private String[] slideImagePaths = {"images/img1.png", "images/img1.png", "images/img2.png"};
    private int currentSlideIndex = 0;

    public ElectronicStoreGUI() {
        setTitle("Electronic Store GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Background color
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Title label
        titleLabel = new JLabel("Electronic Store");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.DARK_GRAY);
        titleLabel.setForeground(Color.WHITE);

        // Sliding images panel
        slidePanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(slidePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Image panel
        imagePanel = new JPanel(new GridLayout(ROWS, COLS, 5, 5));

        // Add images to the grid
        addImagesToGrid();

        // Timer for sliding images
        slideTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextSlide();
            }
        });
        slideTimer.start();

        // Set layout and add components to the content pane
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addImagesToGrid() {
        // Replace "imagePath1", "imagePath2", ... with your actual image paths
        String[] imagePaths = {"images/one.png", "images/two.png", "images/three.png", "images/four.png",
                "images/one.png", "images/two.png", "images/three.png", "images/four.png",
                "images/one.png", "images/two.png", "images/three.png", "images/four.png",
                "images/one.png", "images/two.png", "images/three.png", "images/four.png",
                "images/one.png", "images/two.png", "images/three.png", "images/four.png",
                "images/one.png", "images/two.png", "images/three.png", "images/four.png"};

        int maxImages = ROWS * COLS;
        int imagesToAdd = Math.min(maxImages, imagePaths.length);

        for (int i = 0; i < imagesToAdd; i++) {
            ImageIcon imageIcon = new ImageIcon(imagePaths[i]);
            Image image = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);

            // Add text box below each image with a dynamically generated name
            JTextArea textBox = new JTextArea("Electronic Store " + (i + 1));
            textBox.setEditable(false);
            textBox.setBackground(Color.LIGHT_GRAY);
            textBox.setLineWrap(true);
            textBox.setWrapStyleWord(true);
            textBox.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

            // Add a mouse listener to open another Java code on click
            int finalI = i;
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Replace the code below with the action you want to perform
                    JOptionPane.showMessageDialog(null, "Welcome To Electonic Shop");
                    String programClassName= "C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\BookAndGift1.java";
                    runJavaProgram(programClassName);
                }
            });

            // Create a panel to hold both the image label and text box
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.add(imageLabel, BorderLayout.CENTER);
            imagePanel.add(textBox, BorderLayout.SOUTH);

            this.imagePanel.add(imagePanel);
        }
    }

    private void showNextSlide() {
        if (slideImagePaths.length > 0) {
            ImageIcon imageIcon = new ImageIcon(slideImagePaths[currentSlideIndex]);
            Image image = imageIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            JLabel slideLabel = new JLabel(imageIcon);
            slidePanel.removeAll();
            slidePanel.add(slideLabel);
            currentSlideIndex = (currentSlideIndex + 1) % slideImagePaths.length;
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ElectronicStoreGUI().setVisible(true);
            }
        });
    }
    private static void runJavaProgram(String className) {
        try {
            // Assuming the classes are in the same package, otherwise provide the full path
            String command = "java -cp . " + className;
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}