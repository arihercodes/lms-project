package gui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class UserApprovalWindow extends JFrame {
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    private UserDAO userDAO = new UserDAO();
    private List<User> unapprovedUsers;
    private JButton approveButton, deleteButton;

    public UserApprovalWindow() {
        setTitle("User Approval");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(userList);

        approveButton = new JButton("Approve");
        deleteButton = new JButton("Delete");

        approveButton.addActionListener(this::approveSelectedUser);
        deleteButton.addActionListener(this::deleteSelectedUser);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(approveButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshUserList();
    }

    private void refreshUserList() {
        listModel.clear();
        unapprovedUsers = userDAO.getUnapprovedUsers();
        for (User user : unapprovedUsers) {
            String entry = user.getName() + " (" + user.getRole() + ") [ID: " + user.getId() + "]";
            listModel.addElement(entry);
        }
    }

    private void approveSelectedUser(ActionEvent e) {
        String selected = userList.getSelectedValue();
        if (selected != null && selected.contains("ID:")) {
            try {
                int userId = Integer.parseInt(selected.split("ID:")[1].trim().replace("]", ""));
                boolean success = userDAO.updateUserApproval(userId, true);
                if (success) {
                    JOptionPane.showMessageDialog(this, "User approved successfully.");
                    refreshUserList();
                } else {
                    JOptionPane.showMessageDialog(this, "Approval failed.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error parsing user ID.");
            }
        }
    }

    private void deleteSelectedUser(ActionEvent e) {
        String selected = userList.getSelectedValue();
        if (selected != null && selected.contains("ID:")) {
            try {
                int userId = Integer.parseInt(selected.split("ID:")[1].trim().replace("]", ""));
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean deleted = userDAO.deleteUser(userId);
                    if (deleted) {
                        JOptionPane.showMessageDialog(this, "User deleted successfully.");
                        refreshUserList();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete user.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error parsing user ID.");
            }
        }
    }
}
