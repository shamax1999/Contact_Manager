import java.util.ArrayList;

public class CustomerDBConnection {
    private static CustomerDBConnection instance;
    private ArrayList<Contact> customerList;

    private CustomerDBConnection() {
        customerList = new ArrayList<>();
    }

    public static CustomerDBConnection getInstance() {
        if (instance == null) {
            instance = new CustomerDBConnection();
        }
        return instance;
    }

    public ArrayList<Contact> getCustomerList() {
        return customerList;
    }
}
