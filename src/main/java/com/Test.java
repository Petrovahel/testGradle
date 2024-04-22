package com;

import com.entityRelationshipExampleOneToOne.User;
import com.entityRelationshipExampleOneToOne.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setUsername("petrovahel");
            user.setEmail("petrovahel.com");

            UserProfile profile = new UserProfile();
            profile.setFirstName("Olena");
            profile.setLastName("Petrova");
            profile.setAddress("123 Sadovskogo");

            user.setUserProfile(profile);
            profile.setUser(user);

            session.persist(user);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
