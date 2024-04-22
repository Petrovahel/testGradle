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

public class UserRepository {
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();
        for (User userFromList : getUserList()){
            if (user.getUsername().equals(userFromList.getUsername())){
                System.out.println("The profile cannot be added. Another profile already contains this userName" + user.getUsername());
                if (user.getEmail().equals(userFromList.getEmail())){
                    System.out.println("The profile cannot be added. Another profile already contains this email" + user.getEmail());
                }
                return;
            }
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteUser(int id){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null){
            session.remove(user);
                System.out.println(user + "was deleted");}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User searchUserByUserName (String username){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, username);
            if (user != null){
                System.out.println(user);
            return user;}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser (User user){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User userById = session.get(User.class, user.getId());
            if (userById != null){
                session.merge(user); }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getUserList (){
        Session session = sessionFactory.openSession();

        session.get(User.class, 1L);

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<User> usersList = query.getResultList();

        session.close();

        return usersList;
    }


}
