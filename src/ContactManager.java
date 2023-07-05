import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(int index, Contact updatedContact) {
        contacts.set(index, updatedContact);
    }

    public void deleteContact(int index) {
        contacts.remove(index);
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }
}
