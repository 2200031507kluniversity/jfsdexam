package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure Hibernate
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Insert records
        insertClientRecords(sessionFactory);

        // Fetch and print all records
        fetchAndPrintAllRecords(sessionFactory);

        sessionFactory.close();
    }

    private static void insertClientRecords(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client1 = new Client();
        client1.setName("Alice");
        client1.setGender("Female");
        client1.setAge(25);
        client1.setLocation("New York");
        client1.setEmailAddress("alice@example.com");
        client1.setMobileNumber("1234567890");

        Client client2 = new Client();
        client2.setName("Bob");
        client2.setGender("Male");
        client2.setAge(30);
        client2.setLocation("San Francisco");
        client2.setEmailAddress("bob@example.com");
        client2.setMobileNumber("9876543210");

        session.save(client1);
        session.save(client2);

        transaction.commit();
        session.close();

        System.out.println("Records inserted successfully!");
    }

    private static void fetchAndPrintAllRecords(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).list();

        for (Client client : clients) {
            System.out.println("ID: " + client.getId() + ", Name: " + client.getName() +
                    ", Gender: " + client.getGender() + ", Age: " + client.getAge() +
                    ", Location: " + client.getLocation() + ", Email: " + client.getEmailAddress() +
                    ", Mobile: " + client.getMobileNumber());
        }

        session.close();
    }
}
