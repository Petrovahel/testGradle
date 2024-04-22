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

public class CompanyRepository {
    private SessionFactory sessionFactory;

    public void addCompany(Company company) {
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(company);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteCompany(int id){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = session.get(Company.class, id);
            if (company != null){
                session.remove(company);
                System.out.println(company + "was deleted");}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Company searchCompanyByName (String companyName){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = session.get(Company.class, companyName);
            if (company != null){
                System.out.println(company);
                return company;}

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCompany (Company company){
        sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Company companyById = session.get(Company.class, company.getId());
            if (companyById != null){
                session.merge(company); }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Company> getCompanyList (){
        Session session = sessionFactory.openSession();

        session.get(Company.class, 1L);

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Company.class);

        Root<User> root = cq.from(Company.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<Company> companiesList = query.getResultList();

        session.close();

        return companiesList;
    }

}
