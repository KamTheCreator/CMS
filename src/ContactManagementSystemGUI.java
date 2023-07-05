import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContactManagementSystemGUI {
    private JFrame frame;
    private JTextField nameTextField, phoneTextField, emailTextField;
    private DefaultTableModel tableModel;
    private JTable contactTable;
    private JScrollPane tableScrollPane;
    private JButton addButton, editButton, deleteButton;
    private ContactManager contactManager;
    private FileWriterMain fileWriterMain;

    public ContactManagementSystemGUI() {
        contactManager = new ContactManager();
        fileWriterMain = new FileWriterMain();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Contact Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        phoneTextField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneTextField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Table setup
        String[] columnNames = {"Name", "Phone", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        contactTable = new JTable(tableModel);
        tableScrollPane = new JScrollPane(contactTable);
        frame.getContentPane().add(tableScrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String phone = phoneTextField.getText();
                String email = emailTextField.getText();

                Contact contact = new Contact(name, phone, email);
                contactManager.addContact(contact);
                displayContacts();

                clearInputFields();

                // Save contact to file
                fileWriterMain.writeContactToFile(contact);
                fileWriterMain.printToTerminal(contact);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = contactTable.getSelectedRow();
                if (selectedRow != -1) {
                    Contact deletedContact = new Contact(
                            tableModel.getValueAt(selectedRow, 0).toString(),
                            tableModel.getValueAt(selectedRow, 1).toString(),
                            tableModel.getValueAt(selectedRow, 2).toString()
                    );
                    contactManager.deleteContact(selectedRow);
                    displayContacts();

                    clearInputFields();

                    // Save deleted contact to file
                    fileWriterMain.writeDeletedContactToFile(deletedContact);
                    fileWriterMain.printToTerminal(deletedContact);
                }
            }
        });


        frame.setVisible(true);
    }

    private void clearInputFields() {
        nameTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
    }

    private void displayContacts() {
        tableModel.setRowCount(0);
        try {
            List<Contact> contacts = contactManager.getAllContacts();
            for (Contact contact : contacts) {
                Object[] rowData = {contact.getName(), contact.getPhone(), contact.getEmail()};
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error occurred while displaying contacts: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
