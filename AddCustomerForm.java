import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class AddCustomerForm extends JFrame {
    private JLabel titleLabel;
    private JButton btnAdd;
    private JButton btnCancel;
    private JButton btnBack;

    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblContactNumber;
    private JLabel lblCompany;
    private JLabel lblSalary;
    private JLabel lblBirthday;

    private JTextField txtName;
    private JTextField txtContactNumber;
    private JTextField txtCompany;
    private JTextField txtSalary;
    private JTextField txtBirthday;

    private static int contactCounter = 1;
    private String contactID;

    AddCustomerForm() {
        setSize(700, 500);
        setTitle("Add Contact");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        
        contactID = "C" + String.format("%04d", contactCounter++);

        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(173, 216, 230)); 
        titleLabel = new JLabel("ADD CONTACT");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); 
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        lblId = new JLabel("Contact ID - " + contactID);
        lblId.setFont(new Font("Arial", Font.PLAIN, 20)); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(lblId, gbc);
        gbc.gridwidth = 1;

        
        lblName = new JLabel("Name");
        lblName.setFont(new Font("Arial", Font.PLAIN, 20)); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblName, gbc);

        txtName = new JTextField(15);
        txtName.setFont(new Font("Arial", Font.PLAIN, 20)); 
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
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(txtBirthday, gbc);

        add(formPanel, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE); 

        
        JPanel addCancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addCancelPanel.setBackground(Color.WHITE);

        btnAdd = new JButton("Add Contact");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 20)); 
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addContact();
            }
        });
        addCancelPanel.add(btnAdd);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 20)); 
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearFields();
            }
        });
        addCancelPanel.add(btnCancel);

       
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.setBackground(Color.WHITE);

        btnBack = new JButton("   Back To HomePage   ");
        btnBack.setFont(new Font("Arial", Font.BOLD, 20)); 
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

        buttonPanel.add(addCancelPanel);
        buttonPanel.add(backPanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addContact() {
        try {
            String name = txtName.getText().trim();
            String contactNumber = txtContactNumber.getText().trim();
            String company = txtCompany.getText().trim();
            String salaryStr = txtSalary.getText().trim();
            String birthday = txtBirthday.getText().trim();

            if (!validateContactNumber(contactNumber)) {
                JOptionPane.showMessageDialog(null, "Invalid Contact Number. Must start with 0 and be 10 digits long.");
                return;
            }

            if (!validateBirthday(birthday)) {
                JOptionPane.showMessageDialog(null, "Invalid Birthday. Must be in YYYY-MM-DD format and not a future date.");
                return;
            }

            double salary;
            try {
                salary = Double.parseDouble(salaryStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Salary. Please enter a valid number.");
                return;
            }

            Contact contact = new Contact(contactID, name, contactNumber, company, salary, birthday);
            CustomerController.addCustomer(contact);
            JOptionPane.showMessageDialog(null, "Contact added successfully!");

            clearFields();
            contactID = "C" + String.format("%04d", contactCounter++);
            lblId.setText("Contact ID - " + contactID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while adding the contact. Please try again.");
        }
    }

    private boolean validateContactNumber(String contactNumber) {
        return contactNumber.startsWith("0") && contactNumber.length() == 10;
    }

    private boolean validateBirthday(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(birthday);
            return !date.after(new Date());
        } catch (ParseException e) {
            return false;
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtContactNumber.setText("");
        txtCompany.setText("");
        txtSalary.setText("");
        txtBirthday.setText("");
    }

    public static void main(String[] args) {
        AddCustomerForm form = new AddCustomerForm();
        form.setVisible(true);
    }
}


