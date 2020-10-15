package controller;

import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class DirectoryManager implements Manager<Contact> {
    private static DirectoryManager directoryManager;
    private ArrayList<Contact> directory = new ArrayList();

    private DirectoryManager() {
    }

    public static DirectoryManager getDirectoryManager() {
        if (directoryManager == null) {
            directoryManager = new DirectoryManager();
        }
        return directoryManager;
    }

    private boolean isExist(Contact contact) {
        for (Contact contact1 : directory) {
            if (contact1.getPhoneNumber() == contact.getPhoneNumber()) {
                return true;
            }
        }
        return false;
    }

    public static void setDirectoryManager(DirectoryManager directoryManager) {
        DirectoryManager.directoryManager = directoryManager;
    }

    public ArrayList<Contact> getDirectory() {
        return directory;
    }

    public void setDirectory(ArrayList<Contact> directory) {
        this.directory = directory;
    }

    @Override
    public boolean create(Contact contact) {
        if (!isExist(contact)) {
            directory.add(contact);
            return true;
        }
        return false;
    }

    @Override
    public void read() {
        if (directory.isEmpty()) {
            System.out.println("Empty!");
            return;
        }
        for (Contact contact : directory) {
            System.out.println(contact.toString());
        }
    }
    @Override
    public boolean update(Contact contact) {
        if (isExist(contact)) {
            for (Contact contact1 : directory) {
                if (contact1.getPhoneNumber() == contact.getPhoneNumber()) {
                    directory.remove(contact1);
                    directory.add(contact);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int phoneNumber) {
       for(Contact contact: directory){
           if(contact.getPhoneNumber() ==phoneNumber){
               directory.remove(contact);
               return true;
           }
       }
       return false;
    }

    public void searchByPhoneNumber(int phoneNumber){
        for (Contact contact : directory) {
            if (contact.getPhoneNumber() == phoneNumber) {
                System.out.println(contact);
                return;
            }
        }
    }

    public void searchByName(String name){
        List<Contact> result = new ArrayList<>();
        for (Contact contact : directory) {
            String contactName = contact.getName();
            if (contactName.contains(name)) {
                result.add(contact);
            }
        }
        System.out.println(result);
    }
}
