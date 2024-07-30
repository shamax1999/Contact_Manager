import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CustomerMainForm extends JFrame {

    public static ArrayList<Contact> customerList = new ArrayList<>();

    private AddCustomerForm addCustomerForm;
    private UpdateContactForm updateContactForm;
    private ViewCustomerForm viewCustomerForm;
    private SearchContactForm searchContactForm;
    private DeleteContactForm deleteContactForm;
    

    private JButton btnAddCustomer;
    private JButton btnSearchCustomer;
    private JButton btnDeleteCustomer;
    private JButton btnUpdateCustomer;
    private JButton btnViewCustomer;
    private JButton btnExit;

    CustomerMainForm() {
        setTitle("iFRIEND Contact Manager");
        setSize(600, 400);  
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource("/img.jpg")));
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        imagePanel.setBackground(Color.WHITE);
        mainPanel.add(imagePanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(173, 216, 230));  

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(173, 216, 230));  

        JLabel titleLabel = new JLabel("Home Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(titleLabel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));  

        btnAddCustomer = createButton("Add New Contact");
        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (addCustomerForm == null) {
                    addCustomerForm = new AddCustomerForm();
                }
                addCustomerForm.setVisible(true);
            }
        });
        buttonPanel.add(btnAddCustomer);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));  

        btnUpdateCustomer = createButton("Update Contact");
        btnUpdateCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (updateContactForm == null) {
                    updateContactForm = new UpdateContactForm();
                }
                updateContactForm.setVisible(true);
            }
        });
        buttonPanel.add(btnUpdateCustomer);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        btnSearchCustomer = createButton("Search Contact");
        btnSearchCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (searchContactForm == null) {
                    searchContactForm = new SearchContactForm();
                }
                searchContactForm.setVisible(true);
            }
        });

        buttonPanel.add(btnSearchCustomer);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); 

        btnDeleteCustomer = createButton("Delete Contact");
		btnDeleteCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (deleteContactForm == null) {
                    deleteContactForm = new DeleteContactForm();
                }
                deleteContactForm.setVisible(true);
            }
        });

        buttonPanel.add(btnDeleteCustomer);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        btnViewCustomer = createButton("View Contact");
        btnViewCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (viewCustomerForm == null) {
                    viewCustomerForm = new ViewCustomerForm();
                }
                viewCustomerForm.setVisible(true);
            }
        });
        buttonPanel.add(btnViewCustomer);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));  

        rightPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitPanel.setBackground(new Color(173, 216, 230));  

        btnExit = createButton("EXIT");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        exitPanel.add(btnExit);
        rightPanel.add(exitPanel, BorderLayout.SOUTH);

        mainPanel.add(rightPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 40));
        button.setMaximumSize(new Dimension(250, 40));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public static void main(String args[]) {
        new CustomerMainForm().setVisible(true);
    }
}
