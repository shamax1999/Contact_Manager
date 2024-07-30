import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class DeleteContactForm extends JFrame {
    private JLabel titleLabel;
    private JButton btnSearch, btnDelete, btnCancel, btnBack;
    private JLabel lblId, lblName, lblContactNumber, lblCompany, lblSalary, lblBirthday;
    private JTextField txtSearch, txtId, txtName, txtContactNumber, txtCompany, txtSalary, txtBirthday;
    private Contact currentContact;

    public DeleteContactForm() {
        setupUIComponents();
        setupEventListeners();
    }

    private void setupUIComponents() {
        setSize(700, 500);
        setTitle("Delete Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(173, 216, 230));
        titleLabel = new JLabel("DELETE CONTACT");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 20));
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 20));

        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(titlePanel, BorderLayout.NORTH);
        headerPanel.add(searchPanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblId = new JLabel("Contact ID");
        lblId.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblId, gbc);

        txtId = new JTextField(15);
        txtId.setFont(new Font("Arial", Font.PLAIN, 20));
        txtId.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(txtId, gbc);

        lblName = new JLabel("Name");
        lblName.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblName, gbc);

        txtName = new JTextField(15);
        txtName.setFont(new Font("Arial", Font.PLAIN, 20));
        txtName.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(txtName, gbc);

        lblContactNumber = new JLabel("Contact Number");
        lblContactNumber.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblContactNumber, gbc);

        txtContactNumber = new JTextField(10);
        txtContactNumber.setFont(new Font("Arial", Font.PLAIN, 20));
        txtContactNumber.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(txtContactNumber, gbc);

        lblCompany = new JLabel("Company");
        lblCompany.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblCompany, gbc);

        txtCompany = new JTextField(20);
        txtCompany.setFont(new Font("Arial", Font.PLAIN, 20));
        txtCompany.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(txtCompany, gbc);

        lblSalary = new JLabel("Salary");
        lblSalary.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblSalary, gbc);

        txtSalary = new JTextField(7);
        txtSalary.setFont(new Font("Arial", Font.PLAIN, 20));
        txtSalary.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(txtSalary, gbc);

        lblBirthday = new JLabel("Birthday (YYYY-MM-DD)");
        lblBirthday.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lblBirthday, gbc);

        txtBirthday = new JTextField(10);
        txtBirthday.setFont(new Font("Arial", Font.PLAIN, 20));
        txtBirthday.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(txtBirthday, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 20));
        btnDelete.setEnabled(false);
        buttonPanel.add(btnDelete);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 20));
        buttonPanel.add(btnCancel);

        btnBack = new JButton("Back To HomePage");
        btnBack.setFont(new Font("Arial", Font.BOLD, 20));
        buttonPanel.add(btnBack);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupEventListeners() {
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchAndDisplayContact();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                confirmAndDeleteContact();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearFields();
                btnDelete.setEnabled(false);
            }
        });

        btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
		        dispose();
		        SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                new CustomerMainForm().setVisible(true);
		            }
		        });
		    }
		});
    }

    private void searchAndDisplayContact() {
        String searchQuery = txtSearch.getText();
        Contact contact = searchContact(searchQuery);
        if (contact != null) {
            currentContact = contact;
            populateFields(contact);
            btnDelete.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Contact not found!");
            clearFields();
            btnDelete.setEnabled(false);
        }
    }

    private void confirmAndDeleteContact() {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            boolean success = CustomerController.deleteContact(currentContact);
            if (success) {
                clearFields();
                btnDelete.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Contact deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete contact!");
            }
        }
    }

    private Contact searchContact(String query) {
        ArrayList<Contact> contactList = CustomerDBConnection.getInstance().getCustomerList();
        for (Contact contact : contactList) {
            if (contact.getName().equalsIgnoreCase(query) || contact.getContactNumber().equals(query)) {
                return contact;
            }
        }
        return null;
    }

    private void populateFields(Contact contact) {
        txtId.setText(contact.getId());
        txtName.setText(contact.getName());
        txtContactNumber.setText(contact.getContactNumber());
        txtCompany.setText(contact.getCompany());
        txtSalary.setText(String.valueOf(contact.getSalary()));
        txtBirthday.setText(contact.getBirthday());
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtContactNumber.setText("");
        txtCompany.setText("");
        txtSalary.setText("");
        txtBirthday.setText("");
        txtSearch.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DeleteContactForm().setVisible(true);
            }
        });
    }
}



