package siit.phoneHomework;

import java.util.ArrayList;

public abstract class Phone {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private ArrayList<String> calls = new ArrayList<>();
    private int batteryLevel;

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void addContact(String index, String phoneNumber, String firstName, String lastName) {
        try {
            validatePhoneNumber(phoneNumber);
            checkPhoneNumbersDuplicate(phoneNumber);

            Contact contact = new Contact(index, phoneNumber, firstName, lastName);
            contacts.add(contact);
        } catch (ValidationException | DuplicateContactException e) {
            System.out.println(e.getMessage());
        }/* catch (DuplicateContactException e) {
            System.out.println(e.getMessage());
        }*/
    }

    public void listContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact.getIndex() + " " + contact.getPhoneNumber() + " " +
                    contact.getFirstName() + " " + contact.getLastName());
        }
    }

    public void sendMessage(String phoneNumber, String messageContent) {
        batteryLevel--;

        for (Contact contact : contacts) {
            if (phoneNumber.equals(contact.getPhoneNumber())) {
                contact.sendMessage(messageContent);
            }
        }
    }

    public void listMessages(String phoneMessages) {
        for (Contact contact : contacts) {
            if (phoneMessages.equals(contact.getPhoneNumber())) {
                contact.listMessages();
            }
        }
    }

    public void call(String phoneNumber) {
        batteryLevel -= 2;

        calls.add(phoneNumber);
    }

    public void viewHistory() {
        for (String call : calls) {
            System.out.println(call);
        }
    }

    private void validatePhoneNumber(String phoneNumber) throws ValidationException{

        if (phoneNumber.charAt(0) != '0') {
            throw new ValidationException("Phone number has to start with '0'!");
        }
        if(phoneNumber.length() != 10) {
            throw new ValidationException("Phone number length is exactly 10!");
        }
    }

    private void checkPhoneNumbersDuplicate(String phoneNumber) throws DuplicateContactException {

        for (Contact contact: contacts) {
            if(contact.getPhoneNumber().equals(phoneNumber)) {
                throw new DuplicateContactException("Phone number is already added!");
            }
        }
    }

}
