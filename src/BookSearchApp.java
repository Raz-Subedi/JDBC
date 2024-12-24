import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookSearchApp extends JFrame {
    private JTextField authorTextField;
    private JTextField priceTextField;
    private JTextArea resultTextArea;

    public BookSearchApp() {
        setTitle("Book Search");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel authorLabel = new JLabel("Author:");
        authorTextField = new JTextField();
        JLabel priceLabel = new JLabel("Maximum Price:");
        priceTextField = new JTextField();
        JButton searchButton = new JButton("Search");
        resultTextArea = new JTextArea();

        panel.add(authorLabel);
        panel.add(authorTextField);
        panel.add(priceLabel);
        panel.add(priceTextField);
        panel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input
                String author = authorTextField.getText();
                double maxPrice = Double.parseDouble(priceTextField.getText());

                // Perform database query and display results
                searchBooks(author, maxPrice);
            }
        });

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
    }

    public void searchBooks(String author, double maxPrice) {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "username", "password");

            // Construct SQL query
            String sql = "SELECT id, name, author, published_date, price FROM Book WHERE author = ? AND price < ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, author);
            preparedStatement.setDouble(2, maxPrice);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Display results in the text area
            resultTextArea.setText("");
            while (resultSet.next()) {
                String bookInfo = "ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Author: " + resultSet.getString("author") +
                        ", Published Date: " + resultSet.getString("published_date") +
                        ", Price: " + resultSet.getDouble("price") + "\n";
                resultTextArea.append(bookInfo);
            }

            // Close database resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BookSearchApp().setVisible(true);
            }
        });
    }
}
