/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import manager.MedecinManager;
import manager.PatientManager;
import manager.VisiterManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import util.HibernateUtil;

/**
 *
 * @author LIANTSOA ANJARA
 */
public class Test_Main {
    public static void main(String[] args){
        
       /* MedecinManager med = new MedecinManager();
        med.ajouterMedecin("Nom2", "Prenom2", "Professeur");
        
        PatientManager pat = new PatientManager();
        pat.ajouterPatient("nomPat2", "prenomPat2", "Homme", "IIP138 Imerimanjaka");

        VisiterManager visite = new VisiterManager();
        visite.ajoutVisite(2, 3, "29-03-2025");
        HibernateUtil.sessionFactory.close();
       
        MedecinManager med = new MedecinManager();
        med.modifierMedecin(1, "ANDRIANARISON", "Jean", "Docteur");
        HibernateUtil.sessionFactory.close();
       
       PatientManager pat = new PatientManager();
       pat.modifierPatient(1, "ANDRIANILAINA", "Antsa", "Femme", "IIP138 Imerimanjaka");
       HibernateUtil.sessionFactory.close();
       
       VisiterManager visite = new VisiterManager();
       visite.modifierVi(2, 2, 2, "29-03-2025");
       HibernateUtil.sessionFactory.close();*/
       
       /*MedecinManager med = new MedecinManager();
       med.supprimerMedecin(1);
       HibernateUtil.sessionFactory.close();
       
       PatientManager pat = new PatientManager();
       pat.modifierPatient(1, "ZA", "ZA", "Femme", "IIP138 Imerimanjaka");
       HibernateUtil.sessionFactory.close();
       
       int total = new MedecinManager().medLibre();
       System.out.println("Nombre total de médecins libres : " + total);
       
       int total = new PatientManager().patVi();
       System.out.println("Nombre total de patients à visiter : " + total);
       */
       
       int total = new VisiterManager().viAuj();
       System.out.println("Nombre total de visite auj : " + total);

    }
}
