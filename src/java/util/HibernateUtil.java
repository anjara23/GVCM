package util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author LIANTSOA ANJARA
 */
public class HibernateUtil{
    
    public static final SessionFactory sessionFactory;
    
    static{
        try{
            sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        }catch(HibernateException ex){
            System.err.println("SessionFactory creation failed: " +ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
