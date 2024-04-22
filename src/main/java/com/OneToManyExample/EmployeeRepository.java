package com.OneToManyExample;

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

public class EmployeeRepository {
    private SessionFactory sessionFactory;

    public void addEmployee(Employee employee) {
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(employee);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteEmployee(int id){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null){
                session.remove(employee);
                System.out.println(employee + "was deleted");}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee searchEmployeeByName (String employeeName){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeName);
            if (employee != null){
                System.out.println(employee);
                return employee;}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateEmployee (Employee employee){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employeeById = session.get(Employee.class, employee.getId());
            if (employeeById != null){
                session.merge(employee); }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployeesList (){
        Session session = sessionFactory.openSession();

        session.get(Employee.class, 1L);

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Employee.class);

        Root<User> root = cq.from(Employee.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<Employee> employeesList = query.getResultList();

        session.close();

        return employeesList;
    }

}
