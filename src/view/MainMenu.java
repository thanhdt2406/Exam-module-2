package view;

import controller.DirectoryManager;
import controller.validate.Input;
import model.Contact;

import java.io.*;
import java.util.ArrayList;

public class MainMenu {

    public void run() {
        final String MAIN_MENU =
                "---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----" +
                        "\nChọn chức năng theo số (để tiếp tục):" +
                        "\n1. Xem danh sách" +
                        "\n2. Thêm mới" +
                        "\n3. Cập nhật" +
                        "\n4. Xoá" +
                        "\n5. Tìm kiếm" +
                        "\n6. Đọc từ file" +
                        "\n7. Ghi vào file" +
                        "\n8. Thoát";
        Input input = Input.getInput();
        int choice;
        do {
            System.out.println(MAIN_MENU);
            choice = input.inputInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    //Show List;
                    showContacts();
                    break;
                case 2:
                    //Add
                    addContact();
                    break;
                case 3:
                    //Update
                    updateContact();
                    break;
                case 4:
                    //Delete
                    deleteContact();
                    break;
                case 5:
                    //Search
                    searchContact();
                    break;
                case 6:
                    //Read From File
                    try {
                        readFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    //Write To File
                    try {
                        writeFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("No choice! Try again:");
            }
        } while (true);
    }

    private void showContacts() {
        DirectoryManager directoryManager = DirectoryManager.getDirectoryManager();
        directoryManager.read();
    }

    private void addContact() {
        Input input = Input.getInput();
        int phoneNumber = input.inputInt("Enter phone number: ");
        String group = input.inputString("Enter group:");
        String name = input.inputString("Enter name:");
        boolean gender = input.inputGender("Male?(Y/N) ");
        String address = input.inputString("Enter address: ");

        Contact contact = new Contact(phoneNumber, group, name, gender, address);
        DirectoryManager directoryManager = DirectoryManager.getDirectoryManager();
        if (!directoryManager.create(contact)) {
            System.err.println("Contact already exist!");
        } else {
            System.out.println("Done!");
        }
    }

    private void updateContact() {
        Input input = Input.getInput();
        int phoneNumber = input.inputInt("Enter phone number: ");
        String group = input.inputString("Enter group:");
        String name = input.inputString("Enter name:");
        boolean gender = input.inputGender("Male?(Y/N) ");
        String address = input.inputString("Enter address: ");
        Contact contact = new Contact(phoneNumber, group, name, gender, address);
        DirectoryManager directoryManager = DirectoryManager.getDirectoryManager();
        if (!directoryManager.update(contact)) {
            System.err.println("Phone number is not exist!");
        } else {
            System.out.println("Done!");
        }
    }

    private void deleteContact(){
        Input input = Input.getInput();
        int phoneNumber = input.inputInt("Enter phone number: ");
        DirectoryManager directoryManager = DirectoryManager.getDirectoryManager();
        if(!directoryManager.delete(phoneNumber)){
            System.err.println("Phone number is not exist!");
        } else{
            System.out.println("Done!");
        }
    }

    private void searchContact() {
        final String SEARCH_MENU =
                "1. Find by name+" +
                        "\n2. Find by phone number" +
                        "\n3. Back";
        int choice;
        Input input = Input.getInput();
        DirectoryManager directoryManager = DirectoryManager.getDirectoryManager();
        do {
            System.out.println(SEARCH_MENU);
            choice = input.inputInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    String name = input.inputString("Enter name:");
                    directoryManager.searchByName(name);
                    break;
                case 2:
                    //Find by Phone Number
                    int phoneNumber = input.inputInt("Enter phone number:");
                    directoryManager.searchByPhoneNumber(phoneNumber);
                    break;
            }
        } while (choice!=3);
    }

    private void readFile() throws IOException, ClassNotFoundException {
        DirectoryManager directoryManager = DirectoryManager.getDirectoryManager();
        FileInputStream file = new FileInputStream("data.txt");
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        directoryManager.setDirectory((ArrayList<Contact>)is.readObject());
        is.close();
        file.close();
    }

    private void writeFile() throws IOException {
        DirectoryManager directoryManager = DirectoryManager.getDirectoryManager();
        FileOutputStream file = new FileOutputStream("data.txt");
        ObjectOutputStream os = new ObjectOutputStream(file);
        os.writeObject(directoryManager.getDirectory());
        os.close();
        file.close();
    }
}
