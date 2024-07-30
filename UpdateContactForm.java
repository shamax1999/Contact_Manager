import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class UpdateContactForm extends JFrame {
    private JLabel titleLabel;
    private JButton btnSearch;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JButton btnBack;

    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblContactNumber;
    private JLabel lblCompany;
    private JLabel lblSalary;
    private JLabel lblBirthday;

    private JTextField txtSearch;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtContactNumber;
    private JTextField txtCompany;
    private JTextField txtSalary;
    private JTextField txtBirthday;

    private Contact currentContact;

    UpdateContactForm() {
        setSize(700, 500);
        setTitle("Update Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(173, 216, 230)); 
        titleLabel = new JLabel("UPDATE CONTACT");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("", Font.BOLD, 30));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("", Font.PLAIN, 20));
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("", Font.BOLD, 20));
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String searchQuery = txtSearch.getText();
                Contact contact = searchContact(searchQuery);
                if (contact != null) {
                    currentContact = contact;
                    populateFields(contact);
                    promptForUpdates();
                } else {
                    JOptionPane.showMessageDialog(null, "Contact not found!");
                }
            }
        });
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        add(searchPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblId = new JLabel("Contact ID");
        lblId.setFont(new Font("", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblId, gbc);

        txtId = new JTextField(15);
        txtId.setFont(new Font("", Font.PLAIN, 20));
        txtId.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(txtId, gbc);

        lblName = new JLabel("Name");
        lblName.setFont(new Font("", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblName, gbc);

        txtName = new JTextField(15);
        txtName.setFont(new Font("", Font.PLAIN, 20));
        txtName.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(txtName, gbc);

        lblContactNumber = new JLabel("Contact Number");
        lblContactNumber.setFont(new Font("", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblContactNumber, gbc);

        txtContactNumber = new JTextField(10);
        txtContactNumber.setFont(new Font("", Font.PLAIN, 20));
        txtContactNumber.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(txtContactNumber, gbc);

        lblCompany = new JLabel("Company");
        lblCompany.setFont(new Font("", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblCompany, gbc);

        txtCompany = new JTextField(20);
        txtCompany.setFont(new Font("", Font.PLAIN, 20));
        txtCompany.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(txtCompany, gbc);

        lblSalary = new JLabel("Salary");
        lblSalary.setFont(new Font("", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblSalary, gbc);

        txtSalary = new JTextField(7);
        txtSalary.setFont(new Font("", Font.PLAIN, 20));
        txtSalary.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(txtSalary, gbc);

        lblBirthday = new JLabel("Birthday (YYYY-MM-DD)");
        lblBirthday.setFont(new Font("", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lblBirthday, gbc);

        txtBirthday = new JTextField(10);
        txtBirthday.setFont(new Font("", Font.PLAIN, 20));
        txtBirthday.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(txtBirthday, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE); 

        JPanel updateCancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        updateCancelPanel.setBackground(Color.WHITE);

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("", Font.BOLD, 20));
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (currentContact != null) {
                    updateContactFields(currentContact);
                    CustomerController.updateCustomer(currentContact);
                    JOptionPane.showMessageDialog(null, "Contact updated successfully!");

                    int response = JOptionPane.showConfirmDialog(null, "Do you want to update another contact?", "Continue Updating",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.NO_OPTION) {
                        UpdateContactForm.this.dispose();
                    } else {
                        clearFields();
                        setFieldsEditable(false);
                        btnUpdate.setEnabled(false);
                    }
                }
            }
        });
        updateCancelPanel.add(btnUpdate);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("", Font.BOLD, 20));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearFields();
                setFieldsEditable(false);
                btnUpdate.setEnabled(false);
            }
        });
        updateCancelPanel.add(btnCancel);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.setBackground(Color.WHITE);

        btnBack = new JButton("Back To HomePage");
        btnBack.setFont(new Font("", Font.BOLD, 20));
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

        backPanel.add(btnBack);

        buttonPanel.add(updateCancelPanel);
        buttonPanel.add(backPanel);

        add(buttonPanel, BorderLayout.SOUTH);
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

    private void promptForUpdates() {
        Object[] options = {"Name", "Contact Number", "Company", "Salary"};
        boolean[] fieldsToUpdate = new boolean[options.length];
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JCheckBox[] checkboxes = new JCheckBox[options.length];

        for (int i = 0; i < options.length; i++) {
            checkboxes[i] = new JCheckBox((String) options[i]);
            panel.add(checkboxes[i]);
        }

        int result = JOptionPane.showConfirmDialog(null, panel, "Select fields to update", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            setFieldsEditable(false);
            for (int i = 0; i < options.length; i++) {
                fieldsToUpdate[i] = checkboxes[i].isSelected();
                if (fieldsToUpdate[i]) {
                    setFieldEditable(i, true);
                }
            }
            btnUpdate.setEnabled(true);
        }
    }

    private void setFieldsEditable(boolean editable) {
        txtName.setEditable(editable);
        txtContactNumber.setEditable(editable);
        txtCompany.setEditable(editable);
        txtSalary.setEditable(editable);
        txtBirthday.setEditable(editable); 
    }

    private void setFieldEditable(int fieldIndex, boolean editable) {
        switch (fieldIndex) {
            case 0:
                txtName.setEditable(editable);
                break;
            case 1:
                txtContactNumber.setEditable(editable);
                break;
            case 2:
                txtCompany.setEditable(editable);
                break;
            case 3:
                txtSalary.setEditable(editable);
                break;
        }
    }

    private void updateContactFields(Contact contact) {
        contact.setName(txtName.getText());
        contact.setContactNumber(txtContactNumber.getText());
        contact.setCompany(txtCompany.getText());
        contact.setSalary(Double.parseDouble(txtSalary.getText()));
        contact.setBirthday(txtBirthday.getText()); 
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtContactNumber.setText("");
        txtCompany.setText("");
        txtSalary.setText("");
        txtBirthday.setText("");
    }

    public static void main(String[] args) {
        UpdateContactForm form = new UpdateContactForm();
        form.setVisible(true);
    }
}
