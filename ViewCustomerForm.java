import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewCustomerForm extends JFrame {

    private DefaultTableModel tableModel;

    public ViewCustomerForm() {
        setTitle("Contacts List");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(173, 216, 230));
        JLabel titleLabel = new JLabel("CONTACTS LIST", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        addButton(buttonPanel, gbc, 0, "List by Name", "Name");
        addButton(buttonPanel, gbc, 1, "List by Salary", "Salary");
        addButton(buttonPanel, gbc, 2, "List by Birthday", "Birthday");

        add(buttonPanel, BorderLayout.CENTER);

        JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("Cancel");
        cancelPanel.add(btnCancel);
        add(cancelPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewCustomerForm.this.dispose();
            }
        });

        String[] columnNames = {"Contact ID", "Name", "Contact Number", "Company", "Salary", "Birthday"};
        tableModel = new DefaultTableModel(columnNames, 0);
    }

    private void addButton(JPanel panel, GridBagConstraints gbc, int gridy, String text, String sortBy) {
        gbc.gridx = 0;
        gbc.gridy = gridy;
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 50));
        panel.add(button, gbc);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                handleButtonClick(sortBy);
            }
        });
    }

    private void handleButtonClick(String sortBy) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showSortedContactsWindow(sortBy);
            }
        });
    }

    private void showSortedContactsWindow(String sortBy) {
        ArrayList<Contact> contacts = CustomerController.getSortedContacts(sortBy);
        if (contacts == null) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve contacts. Displaying an empty list.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.setRowCount(0);

        for (Contact contact : contacts) {
            tableModel.addRow(new Object[]{
                    contact.getId(),
                    contact.getName(),
                    contact.getContactNumber(),
                    contact.getCompany(),
                    contact.getSalary(),
                    contact.getBirthday()
            });
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("List Contacts by " + sortBy);
                frame.setSize(800, 400);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                JPanel titlePanel = new JPanel();
                titlePanel.setBackground(new Color(173, 216, 230));
                JLabel titleLabel = new JLabel("LIST CONTACTS BY " + sortBy.toUpperCase(), JLabel.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
                titlePanel.add(titleLabel);
                frame.add(titlePanel, BorderLayout.NORTH);

                JTable table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);
                frame.add(scrollPane, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JButton btnBack = new JButton("Back To Home");
                btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.dispose();
				SwingUtilities.invokeLater(new Runnable() {
				public void run() {
						new CustomerMainForm().setVisible(true);
						}
					});
					}
				});

                buttonPanel.add(btnBack);
                frame.add(buttonPanel, BorderLayout.SOUTH);

                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewCustomerForm().setVisible(true);
            }
        });
    }
}
