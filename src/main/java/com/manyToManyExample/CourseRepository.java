package com.manyToManyExample;

import com.entityRelationshipExampleOneToOne.User;
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

public class CourseRepository {
    private SessionFactory sessionFactory;

    public void addCourse(Course course) {
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();
//        for (Course courseFromList : getCourseList()){
//            if (course.getName().equals(courseFromList.getUsername())){
//                System.out.println("The profile cannot be added. Another profile already contains this userName" + user.getUsername());
//            }
//        }
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(course);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteCourse(int id){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            if (course != null){
                session.remove(course);
                System.out.println(course + "was deleted");}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Course searchCourseByUserName (String name){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, name);
            if (course != null){
                System.out.println(course);
                return course;}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCourse (Course course){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Course courseById = session.get(Course.class, course.getId());
            if (courseById != null){
                session.merge(course); }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Course> getCourseList (){
        Session session = sessionFactory.openSession();

        session.get(Course.class, 1L);

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Course.class);

        Root<User> root = cq.from(Course.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<Course> coursesList = query.getResultList();

        session.close();

        return coursesList;
    }

}
