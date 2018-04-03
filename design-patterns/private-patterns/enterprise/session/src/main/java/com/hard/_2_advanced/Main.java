package com.hard._2_advanced;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SessionServer server = new SessionServerImpl();
        SessionClient client1 = new SessionClient();
        SessionClient client2 = new SessionClient();

        Contact contact1 = new ContactImpl("First", "Contact", "primo", "OOI", null);
        Contact contact2 = new ContactImpl("Second", "Contact", "secondo", "OOI", null);
        Address workAddress = new AddressImpl("Work address", "5440 Division", "Fargo", "ND", "54321");
        Address homeAddress = new AddressImpl("Home address", "40 Planar Way", "Paris", "TX", "84301");

        try {
            client1.addContact(contact1);
            client2.addContact(contact1);
        } catch (SessionException e) {
            System.err.println("Exception encountered:" + e);
        }

        try {
            System.out.println("Adding a different contact to the second client");
            client2.addContact(contact2);

            System.out.println("Adding addresses to the first and second clients");
            client1.addAddress(homeAddress);

            client2.addAddress(workAddress);
            client2.addAddress(workAddress);
            client2.addAddress(homeAddress);

            System.out.println("Removing address from a client");
            client2.removeAddress(homeAddress);

            System.out.println("Finalizing the edits to the contacts");
            client1.commitChanges();
            client2.commitChanges();

            System.out.println("Changes finalized");
            client2.addContact(contact1);
        } catch (SessionException e) {
            System.err.println("Exception encountered: " + e);
        }

        System.out.println("Contact list:");
        System.out.println(SessionServerDelegate.getContacts());
        System.out.println("Address list:");
        System.out.println(SessionServerDelegate.getAddresses());
        System.out.println("Edit contacts:");
        System.out.println(SessionServerDelegate.getEditContacts());
    }
}

/**
 * Address
 */

interface Address extends Serializable {
    String getType();

    void setType(String newType);

    String getDescription();

    void setDescription(String description);

    String getStreet();

    void setStreet(String street);

    String getCity();

    void setCity(String city);

    String getState();

    void setState(String state);

    String getZipCode();

    void setZipCode(String zipCode);
}

class AddressImpl implements Address {
    private String type;

    private String description;

    private String street;

    private String city;

    private String state;

    private String zipCode;

    public AddressImpl() {
    }

    public AddressImpl(String description, String street, String city, String state, String zipCode) {
        this.description = description;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String newType) {
        type = newType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean equals(Object o) {
        if (!(o instanceof AddressImpl))
            return false;

        AddressImpl address = (AddressImpl) o;
        if (street.equals(address.street) && city.equals(address.city) && state.equals(address.state) && zipCode.equals(address.zipCode))
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "AddressImpl{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}

/**
 * Contact
 */

interface Contact extends Serializable {
    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getTitle();

    void setTitle(String title);

    String getOrganization();

    void setOrganization(String organization);

    Collection<Address> getAddresses();

    void addAddress(Address address);

    void removeAddress(Address address);
}

class ContactImpl implements Contact {
    private String firstName;

    private String lastName;

    private String title;

    private String organization;

    private Collection<Address> addresses = new ArrayList<>();

    public ContactImpl() {
    }

    public ContactImpl(String firstName, String lastName, String title, String organization, Collection<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.organization = organization;
        if (addresses != null) {
            this.addresses = addresses;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        if (!addresses.contains(address)) {
            addresses.add(address);
        }
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }

    public boolean equals(Object o) {
        if (!(o instanceof ContactImpl))
            return false;

        ContactImpl contact = (ContactImpl) o;
        if (firstName.equals(contact.firstName) && lastName.equals(contact.lastName) && organization.equals(contact.organization) && title.equals(contact.title))
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "ContactImpl{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", organization='" + organization + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}

/**
 * SessionException
 */

class SessionException extends Exception {
    public static final int CONTACT_BEING_EDITED = 1;

    public static final int SESSION_ID_REQUIRED = 2;

    public static final int CONTACT_SELECT_REQUIRED = 3;

    public static final int ADDRESS_DOES_NOT_EXIST = 4;

    private int errorCode;

    public SessionException(String cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public SessionException(String cause) {
        super(cause);
    }

    public int getErrorCode() {
        return errorCode;
    }
}

/**
 * Session Server
 */

interface SessionServer extends Remote {
    long addContact(Contact contact, long sessionID) throws RemoteException, SessionException;

    long addAddress(Address address, long sessionID) throws RemoteException, SessionException;

    long removeAddress(Address address, long sessionID) throws RemoteException, SessionException;

    long finalizeContact(long sessionID) throws RemoteException, SessionException;
}

class SessionServerImpl implements SessionServer {
    private static final String SESSION_SERVER_SERVICE_NAME = "sessionServer";

    public SessionServerImpl() {
        try {
            UnicastRemoteObject.exportObject(this);
            Naming.rebind(SESSION_SERVER_SERVICE_NAME, this);
        } catch (Exception e) {
            System.err.println("Error using RMI to register the SessionServerImpl" + e);
        }
    }

    @Override
    public long addContact(Contact contact, long sessionID) throws RemoteException, SessionException {
        return SessionServerDelegate.addContact(contact, sessionID);
    }

    @Override
    public long addAddress(Address address, long sessionID) throws RemoteException, SessionException {
        return SessionServerDelegate.addAddress(address, sessionID);
    }

    @Override
    public long removeAddress(Address address, long sessionID) throws RemoteException, SessionException {
        return SessionServerDelegate.removeAddress(address, sessionID);
    }

    @Override
    public long finalizeContact(long sessionID) throws RemoteException, SessionException {
        return SessionServerDelegate.finalizeContact(sessionID);
    }
}

/**
 * Session Server Delegate
 */

class SessionServerDelegate {
    private static final long NO_SESSION_ID = 0;
    private static long nextSessionID = 1;
    private static List<Contact> contacts = new ArrayList<>();
    private static List<Address> addresses = new ArrayList<>();
    private static Map<Long, Contact> editContacts = new HashMap<>();

    public static long addContact(Contact contact, long sessionID) throws SessionException {
        if (sessionID <= NO_SESSION_ID) {
            sessionID = getSessionID();
        }

        if (contacts.indexOf(contact) != -1) {
            if (!editContacts.containsValue(contact)) {
                editContacts.put(sessionID, contact);
            } else {
                throw new SessionException("This contact is currently being edited by another user.", SessionException.CONTACT_BEING_EDITED);
            }
        } else {
            contacts.add(contact);
            editContacts.put(sessionID, contact);
        }

        return sessionID;
    }

    public static long addAddress(Address address, long sessionID) throws SessionException {
        if (sessionID <= NO_SESSION_ID) {
            throw new SessionException("A valid session ID is required to add an address", SessionException.SESSION_ID_REQUIRED);
        }

        Contact contact = editContacts.get(sessionID);
        if (contact == null) {
            throw new SessionException("You must select a contact before adding an address", SessionException.CONTACT_SELECT_REQUIRED);
        }

        if (addresses.indexOf(address) == -1) {
            addresses.add(address);
        }

        contact.addAddress(address);

        return sessionID;
    }

    public static long removeAddress(Address address, long sessionID) throws SessionException {
        if (sessionID <= NO_SESSION_ID) {
            throw new SessionException("A valid session ID is required to remove an address", SessionException.SESSION_ID_REQUIRED);
        }

        Contact contact = editContacts.get(sessionID);
        if (contact == null) {
            throw new SessionException("You must select a contact before removing an address", SessionException.CONTACT_SELECT_REQUIRED);
        }

        if (addresses.indexOf(address) == -1) {
            throw new SessionException("There is no record of this address", SessionException.ADDRESS_DOES_NOT_EXIST);
        }

        contact.removeAddress(address);

        return sessionID;
    }

    public static long finalizeContact(long sessionID) throws SessionException {
        if (sessionID <= NO_SESSION_ID) {
            throw new SessionException("A valid session ID is required to finalize a contact", SessionException.SESSION_ID_REQUIRED);
        }

        Contact contact = editContacts.get(sessionID);

        if (contact == null) {
            throw new SessionException("You must select and edit a contact before committing changes", SessionException.CONTACT_SELECT_REQUIRED);
        }

        editContacts.remove(sessionID);

        return NO_SESSION_ID;
    }

    private static long getSessionID() {
        return nextSessionID++;
    }

    public static Collection<Contact> getContacts() {
        return contacts;
    }

    public static Collection<Address> getAddresses() {
        return addresses;
    }

    public static Collection<Contact> getEditContacts() {
        Collection<Contact> contacts = editContacts.values();

        return contacts;
    }
}

/**
 * Session Client
 */

class SessionClient {
    private static final String SESSION_SERVER_SERVICE_NAME = "sessionServer";
    private static final String SESSION_SERVER_MACHINE_NAME = "localhost";
    private long sessionID;
    private SessionServer sessionServer;

    public SessionClient() {
        try {
            String url = "//" + SESSION_SERVER_MACHINE_NAME + "/" + SESSION_SERVER_SERVICE_NAME;
            sessionServer = (SessionServer) Naming.lookup(url);
        } catch (RemoteException e) {
        } catch (NotBoundException e) {
        } catch (MalformedURLException e) {
        } catch (ClassCastException e) {
        }
    }

    public void addContact(Contact contact) throws SessionException {
        try {
            sessionID = sessionServer.addContact(contact, 0);
        } catch (RemoteException e) {
        }
    }

    public void addAddress(Address address) throws SessionException {
        try {
            sessionServer.addAddress(address, sessionID);
        } catch (RemoteException e) {
        }
    }

    public void removeAddress(Address address) throws SessionException {
        try {
            sessionServer.removeAddress(address, sessionID);
        } catch (RemoteException e) {
        }
    }

    public void commitChanges() throws SessionException {
        try {
            sessionID = sessionServer.finalizeContact(sessionID);
        } catch (RemoteException e) {
        }
    }
}
