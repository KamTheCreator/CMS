import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class FileWriterMain {
    private final String FILE_PATH = "contacts.txt";

    public void writeContactToFile(Contact contact) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();

            writer.write(currentTime.toString() + "\n");
            writer.write("Date: " + currentDate + "\n");
            writer.write("Contact Added:\n");
            writer.write("Name: " + contact.getName() + "\n");
            writer.write("Phone: " + contact.getPhone() + "\n");
            writer.write("Email: " + contact.getEmail() + "\n");
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDeletedContactToFile(Contact contact) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();

            writer.write(currentTime.toString() + "\n");
            writer.write("Date: " + currentDate + "\n");
            writer.write("Contact Deleted:\n");
            writer.write("Name: " + contact.getName() + "\n");
            writer.write("Phone: " + contact.getPhone() + "\n");
            writer.write("Email: " + contact.getEmail() + "\n");
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printToTerminal(Contact contact) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        System.out.println(currentTime.toString());
        System.out.println("Date: " + currentDate);
        System.out.println("Contact Added:");
        System.out.println("Name: " + contact.getName());
        System.out.println("Phone: " + contact.getPhone());
        System.out.println("Email: " + contact.getEmail());
        System.out.println();
    }
}
