package com.entityRelationshipExampleOneToOne;

import com.helpers.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserProfileRepository {
    private SessionFactory sessionFactory;

    public void addUserProfile(UserProfile userProfile) {

        for (UserProfile profile : getUserProfileList()) {
            if (userProfile.getUser().equals(profile.getUser())) {
                System.out.println("The profile cannot be added. Another profile already contains this user" + userProfile.getUser());
                return;
            }
        }
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            userProfile.getUser().getId();
            session.persist(userProfile);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteUserProfile(int id) {
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UserProfile userProfile = session.get(UserProfile.class, id);
            if (userProfile != null) {
                session.remove(userProfile);
                System.out.println(userProfile + "was deleted");
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserProfile searchUserProfileByUserName(String userProfileName) {
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UserProfile userProfile = session.get(UserProfile.class, userProfileName);
            if (userProfile != null) {
                System.out.println(userProfile);
                return userProfile;
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUserProfile(UserProfile userProfile) {
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UserProfile userProfileById = session.get(UserProfile.class, userProfile.getId());
            if (userProfileById != null) {
                session.merge(userProfile);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserProfile> getUserProfileList() {
        Session session = sessionFactory.openSession();

        session.get(UserProfile.class, 1L);

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(UserProfile.class);

        Root<UserProfile> root = cq.from(UserProfile.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<UserProfile> usersProfileList = query.getResultList();

        session.close();

        return usersProfileList;
    }

}
