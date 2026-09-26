import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FanManagerApp extends JFrame {

    // Database connection constants
    private static final String URL = "jdbc:mysql://localhost:3306/databasedb?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "student1";
    private static final String PASS = "pass";

    // GUI Components
    private JTextField txtId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtFavoriteTeam;
    private JButton btnDisplay;
    private JButton btnUpdate;

    public FanManagerApp() {
        setTitle("Fan Information Manager");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Form fields panel
        JPanel panelFields = new JPanel(new GridLayout(4, 2, 8, 8));
        panelFields.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        panelFields.add(new JLabel("Fan ID:"));
        txtId = new JTextField();
        panelFields.add(txtId);

        panelFields.add(new JLabel("First Name:"));
        txtFirstName = new JTextField();
        panelFields.add(txtFirstName);

        panelFields.add(new JLabel("Last Name:"));
        txtLastName = new JTextField();
        panelFields.add(txtLastName);

        panelFields.add(new JLabel("Favorite Team:"));
        txtFavoriteTeam = new JTextField();
        panelFields.add(txtFavoriteTeam);

        add(panelFields, BorderLayout.CENTER);

        // Buttons panel
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnDisplay = new JButton("Display Record");
        btnUpdate = new JButton("Update Record");

        panelButtons.add(btnDisplay);
        panelButtons.add(btnUpdate);
        add(panelButtons, BorderLayout.SOUTH);

        // Action Listeners
        btnDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRecord();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });
    }

    // Database connection helper
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Logic to fetch and display record by ID
    public void displayRecord() {
        String idText = txtId.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID to display.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be a valid integer.", "Format Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT firstname, lastname, favoriteteam FROM fans WHERE ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    txtFirstName.setText(rs.getString("firstname"));
                    txtLastName.setText(rs.getString("lastname"));
                    txtFavoriteTeam.setText(rs.getString("favoriteteam"));
                    JOptionPane.showMessageDialog(this, "Record loaded successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No record found for ID: " + id, "Record Not Found", JOptionPane.WARNING_MESSAGE);
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtFavoriteTeam.setText("");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Logic to update existing record by ID
    public void updateRecord() {
        String idText = txtId.getText().trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String favoriteTeam = txtFavoriteTeam.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID to update.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be a valid integer.", "Format Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, favoriteTeam);
            pstmt.setInt(4, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully!", "Update Complete", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No record found with ID " + id + " to update.", "Update Failed", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FanManagerApp().setVisible(true);
        });
    }
}