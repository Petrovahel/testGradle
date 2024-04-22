package com;

import com.entity.Book;
import com.helpers.BookHelper;
import com.helpers.HibernateUtil;
import org.hibernate.Session;

public class Start {
    public static void main(String[] args) {
//        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//        }catch (Exception e){
//            System.err.println(e);
//        }

//        Session session = HibernateUtil.getSessionFactory().openSession();
//        for (Author author:new AuthorHelper().getAuthorList()) {
//            System.out.println("author = " + author.getName());
//        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        for (Book book :new BookHelper().getBookList()) {
            System.out.println("book = " + book.getName());
        }
//        System.out.println(5);
    }
}
