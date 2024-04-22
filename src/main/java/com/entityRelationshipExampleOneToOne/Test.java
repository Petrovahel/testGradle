package com.entityRelationshipExampleOneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {
//    private static SessionFactory sessionFactory;
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        UserProfileRepository userProfileRepository = new UserProfileRepository();

//        sessionFactory = new Configuration().configure().buildSessionFactory();

//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setUsername("petrovahel");
            user.setEmail("petrovahel.com");

            UserProfile profile = new UserProfile();
            profile.setFirstName("Olena");
            profile.setLastName("Petrova");
            profile.setAddress("123 Sadovskogo");
        User user1 = new User();
        user1.setUsername("petrovahel");
        user1.setEmail("petrovahel.com");

        UserProfile profile1 = new UserProfile();
        profile1.setFirstName("Olena");
        profile1.setLastName("Petrova");
        profile1.setAddress("123 Sadovskogo");

            user1.setUserProfile(profile1);
            profile1.setUser(user1);

            userRepository.addUser(user1);
//            userProfileRepository.addUserProfile(profile1);
//userProfileRepository.deleteUserProfile(6);
//        userRepository.deleteUser(6);
//            session.persist(user);
//            session.persist(profile);
//
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}

