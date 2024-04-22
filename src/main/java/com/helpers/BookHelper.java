package com.helpers;

import com.entity.Author;
import com.entity.Book;
import com.helpers.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BookHelper {
    private SessionFactory sessionFactory;

    public BookHelper(){sessionFactory = HibernateUtil.getSessionFactory();}

    public List<Book> getBookList(){

        Session session = sessionFactory.openSession();

        session.get(Book.class, 1L);

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Book.class);

        Root<Author> root = cq.from(Book.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<Book> bookList = query.getResultList();

        session.close();

        return bookList;
    }

    public Author getAuthor(String name){ return null; }
}

