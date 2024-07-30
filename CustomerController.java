import java.util.ArrayList;
import java.util.Comparator;

class CustomerController {
    public static void addCustomer(Contact customer) {
        CustomerDBConnection.getInstance().getCustomerList().add(customer);
    }

    public static void updateCustomer(Contact customer) {
        ArrayList<Contact> customerList = CustomerDBConnection.getInstance().getCustomerList();
        for (int i = 0; i < customerList.size(); i++) {
            Contact existingContact = customerList.get(i);
            if (existingContact.getId().equals(customer.getId())) {
                customerList.set(i, customer);
                break;
            }
        }
    }

    public static boolean deleteContact(Contact contact) {
        ArrayList<Contact> customerList = CustomerDBConnection.getInstance().getCustomerList();
        boolean removed = customerList.remove(contact);
        System.out.println("Contact removed: " + removed);
        return removed;
    }

    public static ArrayList<Contact> getSortedContacts(String sortBy) {
        ArrayList<Contact> sortedContacts = new ArrayList<>(CustomerDBConnection.getInstance().getCustomerList());

        switch (sortBy) {
            case "Name":
                sortedContacts.sort(Comparator.comparing(Contact::getName));
                break;
            case "Salary":
                sortedContacts.sort(Comparator.comparingDouble(Contact::getSalary));
                break;
            case "Birthday":
                sortedContacts.sort(Comparator.comparing(Contact::getBirthday));
                break;
            default:
                System.err.println("Unknown sort criteria: " + sortBy);
                return null;
        }

        return sortedContacts;
    }
}
