package db.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;


public class SessionUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session sessionOpen(){
        session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    public static void sessionClose(){
        session.close();
    }
}