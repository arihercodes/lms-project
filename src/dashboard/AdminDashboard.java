package dashboard;

import dao.UserDAO;
import gui.LoginWindow;
import gui.UserEditWindow;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdminDashboard extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private UserDAO userDAO = new UserDAO();

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Role", "Approved"}, 0);
        userTable = new JTable(tableModel);
        refreshUserTable();
        add(new JScrollPane(userTable), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();

        JButton editButton = new JButton("Edit Selected User");
        editButton.addActionListener(this::editUser);
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Delete Selected User");
        deleteButton.addActionListener(this::deleteUser);
        buttonPanel.add(deleteButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginWindow();
        });
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void refreshUserTable() {
        tableModel.setRowCount(0); // Clear old data
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            tableModel.addRow(new Object[]{
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.isApproved()
            });
        }
    }

    private void editUser(ActionEvent e) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            int userId = (int) tableModel.getValueAt(selectedRow, 0);
            new UserEditWindow(userId, this); // call with dashboard reference to refresh
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to edit.");
        }
    }

    private void deleteUser(ActionEvent e) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            int userId = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (userDAO.deleteUser(userId)) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully.");
                    refreshUserTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete user.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
        }
    }

    // For external refresh
    public void reloadUsers() {
        refreshUserTable();
    }
}
