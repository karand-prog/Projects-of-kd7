import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class check {
    private static JTextField searchBar;
    private static JPanel centerPanel;
    private static JPanel bottomPanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Menu and Search Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 800);

            // Create a panel to hold the components
            JPanel mainPanel = new JPanel(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane(mainPanel);
            frame.add(scrollPane);

            mainPanel.setMinimumSize(new Dimension(800, 600));

            // Create a top panel for menu and search
            JPanel topPanel = createTopPanel();
            mainPanel.add(topPanel, BorderLayout.NORTH);

            // Create a center panel for displaying images
            centerPanel = createCenterPanel();
            mainPanel.add(centerPanel, BorderLayout.CENTER);

            // Create a bottom panel for icons
            bottomPanel = createBottomPanel();
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);

            // Add a back button
            JButton backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.previous(bottomPanel);
                }
            });
            topPanel.add(backButton);

            frame.setVisible(true);
        });
    }

    private static JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        JButton menuButton = new JButton("Menu");
        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Grocery Store");
        JMenuItem menuItem2 = new JMenuItem("Book And Gift Store");
        JMenuItem menuItem3 = new JMenuItem("Electronic Store");
        JMenuItem menuItem4 = new JMenuItem("Restuarent And Food Store");
        JMenuItem menuItem5= new JMenuItem("Clothes Store");

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runJavaProgram("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\GroceryStoreGUI.java");
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runJavaProgram("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\BookAndGiftStoreGUI.java");
            }
        });
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runJavaProgram("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\ElectronicStoreGUI.java");
            }
        });
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runJavaProgram("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\RestuarentAndFoodStoresGUI.java");
            }
        });
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runJavaProgram("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\ClothesStoreGUI.java");
            }
        });

        // Add more menu items and corresponding action listeners as needed

        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.add(menuItem5);

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.show(menuButton, 0, menuButton.getHeight());
            }
        });

        searchBar = new JTextField(20);
        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAndUpdateContent(searchBar.getText(), centerPanel);
                searchAndUpdateBottomPanel(searchBar.getText(), bottomPanel);
            }
        });

        topPanel.add(menuButton);
        topPanel.add(searchBar);

        // Set the background color of the upper row to green
        topPanel.setBackground(Color.GREEN);

        return topPanel;
    }

    private static JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        centerPanel.add(imageLabel, BorderLayout.CENTER);
        imageLabel.setPreferredSize(new Dimension(800, 300));
        createImageSlider(imageLabel);
        return centerPanel;
    }

    private static void createImageSlider(JLabel imageLabel) {
        String[] images = {"images/grc5.png", "images/med4.png", "images/ele6.png"};
        final int[] imageIndex = {0};
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imageIndex[0] < images.length) {
                    String imagePath = images[imageIndex[0]];
                    ImageIcon image = new ImageIcon(imagePath);
                    imageLabel.setIcon(image);
                    imageIndex[0]++;
                } else {
                    imageIndex[0] = 0;
                }
            }
        });
        timer.start();
    }

    private static JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new GridLayout(6, 4)); // 4 rows

        // Create an upper row panel with one icon
        JPanel upperRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        upperRowPanel.add(createIconLabelWithLink("images/grc1.png", "Grocery Store"+"  :- ","C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\GroceryStoreGUI.java"));
        upperRowPanel.add(createIconLabelWithLink("images/grc1.png", "Grocery Store",""));
        upperRowPanel.add(createIconLabelWithLink("images/grc1.png", "Grocery Store",""));
        upperRowPanel.add(createIconLabelWithLink("images/grc1.png", "Grocery Store",""));
        upperRowPanel.setBackground(new Color(173, 216, 230)); // Light Sky Blue
        bottomPanel.add(upperRowPanel);

        // Create a second row panel for the rest of the icons
        JPanel secondRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        secondRowPanel.add(createIconLabelWithLink("images/bk1.png", "Book And Gift Store"+"  :- ",""));
        secondRowPanel.add(createIconLabelWithLink("images/ele1.png", "Book And Gift Store",""));
        secondRowPanel.add(createIconLabelWithLink("images/fd1.png", "Book And Gift Store",""));
        secondRowPanel.add(createIconLabelWithLink("images/fd1.png", "Book And Gift Store",""));
        secondRowPanel.setBackground(new Color(206, 230, 173)); // Light green
        bottomPanel.add(secondRowPanel);

        // Create a third row panel for a single icon
        JPanel thirdRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        thirdRowPanel.add(createIconLabelWithLink("images/two.png", "Electronic Store"+"  :- ",""));
        thirdRowPanel.add(createIconLabelWithLink("images/two.png", "Electronic Store",""));
        thirdRowPanel.add(createIconLabelWithLink("images/two.png", "Electronic Store",""));
        thirdRowPanel.add(createIconLabelWithLink("images/two.png", "Electronic Store",""));
        thirdRowPanel.setBackground(new Color(173, 216, 230)); // Light Sky Blue
        bottomPanel.add(thirdRowPanel);

        // Create a fourth row panel for four icons
        JPanel fourthRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fourthRowPanel.add(createIconLabelWithLink("images/three.png", "Restuarent And Food Store"+"  :- ",""));
        fourthRowPanel.add(createIconLabelWithLink("images/four.png", "Restuarent And Food Store",""));
        fourthRowPanel.add(createIconLabelWithLink("images/one.png", "Restuarent And Food Store",""));
        fourthRowPanel.add(createIconLabelWithLink("images/two.png", "Restuarent And Food Store",""));
        fourthRowPanel.setBackground(new Color(206, 230, 173)); // Light green
        bottomPanel.add(fourthRowPanel);

        JPanel thirdRowPanela = new JPanel(new FlowLayout(FlowLayout.LEFT));
        thirdRowPanela.add(createIconLabelWithLink("images/two.png", "Clothes Store"+"  :- ",""));
        thirdRowPanela.add(createIconLabelWithLink("images/two.png", "Clothes Store",""));
        thirdRowPanela.add(createIconLabelWithLink("images/two.png", "Clothes Store",""));
        thirdRowPanela.add(createIconLabelWithLink("images/two.png", "Clothes Store",""));
        thirdRowPanela.setBackground(new Color(173, 216, 230)); // Light Sky Blue
        bottomPanel.add(thirdRowPanela);
        return bottomPanel;
    }

    private static JLabel createIconLabelWithLink(String iconPath, String labelText, String programClassName) {
        JLabel label = new JLabel(labelText, JLabel.CENTER);
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledIcon));
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                runJavaProgram(programClassName);
            }
        });

        return label;
    }
    private static void searchAndUpdateContent(String query, JPanel panel) {
        // Implement the logic to filter and update the content based on the search query

        // Example: Assuming there are JLabels with text in the panel
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                String labelText = label.getText().toLowerCase(); // Convert to lowercase for case-insensitive search
                if (labelText.contains(query.toLowerCase())) {
                    label.setVisible(true); // Show the JLabel if it contains the search query
                } else {
                    label.setVisible(false); // Hide the JLabel if it does not contain the search query
                }
            }
        }
        panel.revalidate();
        panel.repaint();
        // You may need to call revalidate() and repaint() to reflect the changes

    }

    private static void searchAndUpdateBottomPanel(String query, JPanel panel) {
        // Implement the logic to filter and update the bottom panel content based on the search query

        // Example: Assuming there are JLabels with icons and text in the panel
        for (Component component : panel.getComponents()) {
            if (component instanceof JPanel rowPanel) {
                for (Component rowComponent : rowPanel.getComponents()) {
                    if (rowComponent instanceof JLabel) {
                        JLabel label = (JLabel) rowComponent;
                        String labelText = label.getText().toLowerCase(); // Convert to lowercase for case-insensitive search
                        if (labelText.contains(query.toLowerCase())) {
                            rowPanel.setVisible(true); // Show the rowPanel if it contains the search query
                        } else {
                            rowPanel.setVisible(false); // Hide the rowPanel if it does not contain the search query
                        }
                    }
                }
            }
        }

        // You may need to call revalidate() and repaint() to reflect the changes
        panel.revalidate();
        panel.repaint();
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
