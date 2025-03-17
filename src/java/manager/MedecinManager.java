/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import org.hibernate.Session;

import bean.Medecin;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;

import util.HibernateUtil;
/**
 *
 * @author LIANTSOA ANJARA
 */
public class MedecinManager {
    
    public void ajouterMedecin(String nom, String prenom, String grade){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Medecin medecin = new Medecin();
        medecin.setNom(nom);
        medecin.setPrenom(prenom);
        medecin.setGrade(grade);
        
       session.save(medecin);
       session.getTransaction().commit();
    }
    
    
    public List<Medecin> getAllMed(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Medecin> med = new ArrayList<>();

        try {
            tx = session.beginTransaction();

            // HQL pur récupérer tus les médecins
            Query query = session.createQuery("FROM Medecin ORDER BY codemed ASC");
            med = query.list();

            // Commit the transaction
            tx.commit();

            System.out.println("Nombre de medecins récupérés : " + med);
            return med;
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

   
        return med;
    }
       
    
    public void supprimerMedecin(int codemed){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx= null;
        
        
        session.getTransaction().commit();
        try {
            tx = session.beginTransaction();
            
            VisiterManager visiterManager = new VisiterManager();
            visiterManager.supprimerViByMed(codemed);
            
            Medecin med = (Medecin) session.load(Medecin.class, codemed);
            if(med != null){
                session.delete(med);
                System.out.println("Médecin supprimé avec succès");
            } else{
                System.out.println("Médecin introuvable");
            }
            
            tx.commit();
      
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace(); // Log ou gérer l'exception de manière appropriée
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
             
    }
    
    
    public void modifierMedecin(int codemed, String nouveauNom, String nouveauPrenom, String nouveauGrade) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();

      
            Medecin medecin = (Medecin) session.get(Medecin.class, codemed);

            if (medecin != null) {
                
                medecin.setNom(nouveauNom);
                medecin.setPrenom(nouveauPrenom);
                medecin.setGrade(nouveauGrade);

                // Enregistrer les modifications dans la base de données
                session.update(medecin);
                tx.commit();
            } else {
                System.out.println("Le medecin avec le codemed " + codemed + " n'a pas été trouvé.");
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace(); // Log ou gérer l'exception de manière appropriée
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    
    public List<Medecin> rechercherMedecins(String searchQuery) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Medecin> listeMedecins = new ArrayList<>();

        try {
            tx = session.beginTransaction();

            // Utiliser HQL pour rechercher les medecins par code ou nom
            Query query = session.createQuery("FROM Medecin WHERE codemed = :params OR nom LIKE :paramsN");
            query.setParameter("paramsN", "%" + searchQuery + "%");
            try {
                int codemed = Integer.parseInt(searchQuery);
                query.setParameter("params", codemed); // Recherche par codemed (convertir en entier)
            } catch (NumberFormatException e) {
                query.setParameter("params", 0);
            }
            listeMedecins = query.list();

            tx.commit();

            System.out.println("Nombre de medecins trouvés : " + listeMedecins.size());
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

        return listeMedecins;
    }
    
}
