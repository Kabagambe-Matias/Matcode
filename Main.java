package matia; // Package declaration at the top
import java.io.*;
import java.util.*;
public class Main { // Main class with a proper static main method

    private static final String FILE_NAME = "contacts.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) { // Correctly placed main method
        while (true) {
            System.out.println("\nKabagambe Matias phonebook menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    searchContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addContact() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(name + "," + phoneNumber);
            writer.newLine();
            System.out.println("Contact added successfully.");
        } catch (IOException e) {
            System.out.println("Error while adding contact: " + e.getMessage());
        }
    }

    private static void searchContact() {
        System.out.print("Enter the name of the contact to search for: ");
        String nameToSearch = scanner.nextLine();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No contacts found.");
            return;
        }

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equalsIgnoreCase(nameToSearch)) {
                    System.out.println("Contact found: Name: " + parts[0] + ", Phone: " + parts[1]);
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while searching for contact: " + e.getMessage());
        }

        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    private static void updateContact() {
        System.out.print("Enter the name of the contact to update: ");
        String nameToUpdate = scanner.nextLine();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No contacts found.");
            return;
        }

        List<String> contacts = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(nameToUpdate)) {
                    found = true;
                    System.out.print("Enter new Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new Phone Number: ");
                    String newPhoneNumber = scanner.nextLine();
                    contacts.add(newName + "," + newPhoneNumber);
                } else {
                    contacts.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while updating contact: " + e.getMessage());
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (String contact : contacts) {
                    writer.write(contact);
                    writer.newLine();
                }
                System.out.println("Contact updated successfully.");
            } catch (IOException e) {
                System.out.println("Error while saving updated contacts: " + e.getMessage());
            }
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void deleteContact() {
        System.out.print("Enter the name of the contact to delete: ");
        String nameToDelete = scanner.nextLine();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No contacts found.");
            return;
        }

        List<String> contacts = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(nameToDelete)) {
                    found = true;
                } else {
                    contacts.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while deleting contact: " + e.getMessage());
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (String contact : contacts) {
                    writer.write(contact);
                    writer.newLine();
                }
                System.out.println("Contact deleted successfully.");
            } catch (IOException e) {
                System.out.println("Error while saving updated contacts: " + e.getMessage());
            }
        } else {
            System.out.println("Contact not found.");
        }
    }
}