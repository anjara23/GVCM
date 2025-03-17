/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import org.hibernate.Session;

import bean.Patient;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Query;

import util.HibernateUtil;

/**
 *
 * @author LIANTSOA ANJARA
 */
public class PatientManager {
    
    public void ajouterPatient(String nom, String prenom, String sexe, String adresse){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setSexe(sexe);
        patient.setAdresse(adresse);
        
        session.save(patient);
        session.getTransaction().commit();
    }
    
    
    public List<Patient> getAllPat(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Patient> patient = new ArrayList<>();

        try {
            tx = session.beginTransaction();

            // HQL pur récupérer tus les médecins
            Query query = session.createQuery("FROM Patient ORDER BY codepat ASC");
            patient = query.list();

            // Commit the transaction
            tx.commit();

            System.out.println("Nombre de patients récupérés : " + patient);
            return patient;
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

   
        return patient;
    }
    
    
    public void supprimerPatient(int codepat){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
       
        
        session.getTransaction().commit();
        
        try {
            tx = session.beginTransaction();
            
            VisiterManager visiterManager = new VisiterManager();
            visiterManager.supprimerViByPat(codepat);
            
            Patient pat = (Patient) session.load(Patient.class, codepat);
            if(pat != null){
                session.delete(pat);
                System.out.println("Patient supprimé avec succès");
            }else{
                System.out.println("Patient introuvable");
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
    
    
    public void modifierPatient(int codepat, String nouveauNom, String nouveauPrenom, String nouveauSexe, String nouveauAdresse) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();

      
            Patient patient = (Patient) session.get(Patient.class, codepat);

            if (patient != null) {
                
                patient.setNom(nouveauNom);
                patient.setPrenom(nouveauPrenom);
                patient.setSexe(nouveauSexe);
                patient.setAdresse(nouveauAdresse);

                // Enregistrer les modifications dans la base de données
                session.update(patient);
                tx.commit();
            } else {
                System.out.println("Le patient avec le codepat " + codepat + " n'a pas été trouvé.");
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
    
    
    public List<Patient> rechercherPatient(String searchQuery) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Patient> listePatients = new ArrayList<>();

        try {
            tx = session.beginTransaction();

            // Utiliser HQL pour rechercher les professeurs par code ou nom
            Query query = session.createQuery("FROM Patient WHERE codepat = :params OR nom LIKE :paramsN");
            query.setParameter("paramsN", "%" + searchQuery + "%");
            try {
                int codepat = Integer.parseInt(searchQuery);
                query.setParameter("params", codepat); // Recherche par codeprof (convertir en entier)
            } catch (NumberFormatException e) {
                query.setParameter("params", 0);
            }
            listePatients = query.list();

            tx.commit();

            System.out.println("Nombre de patients trouvés : " + listePatients.size());
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

        return listePatients;
    }
    
}
