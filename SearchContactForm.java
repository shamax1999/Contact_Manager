import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class SearchContactForm extends JFrame {
    private JLabel titleLabel;
    private JLabel lblId;
    private JButton btnSearch;
    private JButton btnBack;

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

    SearchContactForm() {
        setSize(700, 500);
        setTitle("Search Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(173, 216, 230)); 
        titleLabel = new JLabel("SEARCH CONTACT");
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
                    populateFields(contact);
                } else {
                    JOptionPane.showMessageDialog(null, "Contact not found!");
                    clearFields();
                }
            }
        });
        searchPanel.add(new JLabel("Name: "));
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

        lblId = new JLabel("Customer ID");
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
        

        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private Contact searchContact(String query) {
        ArrayList<Contact> contactList = CustomerDBConnection.getInstance().getCustomerList();
        for (Contact contact : contactList) {
            if (contact.getName().equalsIgnoreCase(query)) {
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
    }

    public static void main(String[] args) {
        SearchContactForm form = new SearchContactForm();
        form.setVisible(true);
    }
}
