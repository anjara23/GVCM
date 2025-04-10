/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;


import org.hibernate.Session;

import bean.Visiter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Query;

import util.HibernateUtil;
/**
 *
 * @author LIANTSOA ANJARA
 */
public class VisiterManager {
    
    public void ajoutVisite(int codemed, int codepat, String date){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Visiter vi = new Visiter();
        vi.setCodemed(codemed);
        vi.setCodepat(codepat);
        vi.setDate(date);
        
        session.save(vi);
        session.getTransaction().commit();
    }
    
    public List<Visiter> getAllVi(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Visiter> visite = new ArrayList<>();

        try {
            tx = session.beginTransaction();

            // HQL pur récupérer tus les médecins
            Query query = session.createQuery("FROM Visiter ORDER BY codevi ASC");
            visite = query.list();

            // Commit the transaction
            tx.commit();

            System.out.println("Nombre de visites : " + visite);
            return visite;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace(); // Log or handle the exception appropriately
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

   
        return visite;
    }
    
    public void supprimerVi(int codevi){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            VisiterManager visiterManager = new VisiterManager();
            visiterManager.supprimerViByPat(codevi);
            
            Visiter visite = (Visiter) session.get(Visiter.class, codevi);
            if(visite != null){
                session.delete(visite);
                System.out.println("Visite supprimé avec succès");
            }else{
                System.out.println("Visite introuvable");
            }
            tx.commit();
        }catch(Exception e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            if(session != null && session.isOpen()){
                session.close();
            }
        }

    }
    
    public void modifierVi (int codevi, int codemed, int codepat, String nouvelleDate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;  
        
        try{
            tx = session.beginTransaction();

            Visiter visite = (Visiter) session.get(Visiter.class, codevi);

            if (visite != null) {
                
                visite.setCodemed(codemed);
                visite.setCodepat(codepat);
                visite.setDate(nouvelleDate);

                // Enregistrer les modifications dans la base de données
                session.update(visite);
                tx.commit();
            } else {
                System.out.println("Visite avec le codepat " + codemed + "et codemed "+codepat+" n'a pas été trouvé.");
            }
        }catch(Exception e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }
    
    public List<Visiter> rechercheVisite(String searchQuery) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Visiter> listeVisites = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            
            boolean isNumeric = searchQuery.matches("\\d+");
            
            String hql;
            Query query;
            
            if(isNumeric){
                hql = "FROM Visiter v WHERE v.codemed = :param OR v.codepat = :param";
                query = session.createQuery(hql);
                query.setParameter("param", Integer.parseInt(searchQuery));
            } else{
                hql = "FROM Visiter v WHERE v.date LIKE : paramN";
                query = session.createQuery(hql);
                query.setParameter("paramN", "%" + searchQuery + "%");
            }
            
            listeVisites = query.list();
            tx.commit();

            System.out.println("Nombre de visites trouvés : " + listeVisites.size());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return listeVisites;
    }
    
    public void supprimerViByMed(int codemed){
    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            Query query =  session.createQuery(" DELETE FROM Visiter v WHERE v.codemed = :codemed");
            query.setParameter("codemed",codemed);
            
            int result = query.executeUpdate();
            
            tx.commit();
            
            System.out.println(result + "Visites concernant un medecin sont suprimées");
            
        }catch(Exception e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }
    
    public void supprimerViByPat(int codepat){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            Query query =  session.createQuery(" DELETE FROM Visiter v WHERE v.codepat = :codepat");
            query.setParameter("codepat",codepat);
    
            int result = query.executeUpdate();
            
            tx.commit();
            
            System.out.println(result + "Visites concernant un patient sont suprimées");
            
        }catch(Exception e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }
    
    public Integer totalVi() {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction tx = null;
        Integer total = 0;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("SELECT COUNT(v) FROM Visiter v "); 
            List results = query.list();

            if (!results.isEmpty()) {
                Long result = (Long) results.get(0);
                total = result.intValue();
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return total;
    }
    
    public Integer viAuj() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer total = 0;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(
                "SELECT COUNT(v) FROM Visiter v WHERE v.date = :today"
            );
            query.setParameter("today", LocalDate.now().toString());

            Long result = (Long) query.uniqueResult();
            total = result.intValue();

            tx.commit();
            System.out.println("Date d'aujourd'hui : " + LocalDate.now());
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return total;
    }

}
