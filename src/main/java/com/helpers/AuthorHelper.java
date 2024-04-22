package com.helpers;

import com.entity.Author;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper(){sessionFactory = HibernateUtil.getSessionFactory();}

    public List<Author> getAuthorList(){

        Session session = sessionFactory.openSession();

        session.get(Author.class, 1L);

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Author.class);

        Root<Author> root = cq.from(Author.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<Author> authorList = query.getResultList();

        session.close();

        return authorList;
    }

    public Author getAuthor(String name){ return null; }
}

