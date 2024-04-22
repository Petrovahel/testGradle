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

public class StudentRepository {
    private SessionFactory sessionFactory;


    public void addStudent(Student student) {
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();
//        for (Student studentFromList : getStudentList()){
//            if (student.getName().equals(studentFromList.getName())){
//                System.out.println("The student cannot be added. Another student already contains this userName" + student.getName());
//                return;
//            }
//        }
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteStudent(int id){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null){
                session.remove(student);
                System.out.println(student + "was deleted");}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student searchStudentByUserName (String studentName){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentName);
            if (student != null){
                return student;}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStudent (Student student){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student studentById = session.get(Student.class, student.getId());
            if (studentById != null){
                session.merge(student); }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudentList (){
        Session session = sessionFactory.openSession();

        session.get(Student.class, 1L);

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Student.class);

        Root<User> root = cq.from(Student.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<Student> studentsList = query.getResultList();

        session.close();

        return studentsList;
    }
}
