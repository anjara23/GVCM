package servlet;



import bean.Patient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import manager.PatientManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LIANTSOA ANJARA
 */
@WebServlet(name = "PatientServlet", urlPatterns = {"/Patient"})
public class PatientServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("La méthode doGet() de PatientServlet est appelée.");
        PatientManager patientManager = new PatientManager();

        String searchQuery = request.getParameter("search");
        List<Patient> listePatients;

        
        if (searchQuery != null && !searchQuery.isEmpty()) {
           listePatients = patientManager.rechercherPatient(searchQuery);
        } else {           
            listePatients = patientManager.getAllPat();
        }

        request.setAttribute("listePatients",listePatients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Patient.jsp");
        dispatcher.forward(request, response);
    }
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("La méthode doPost() de PatientServlet est appelée.");

        PatientManager patientManager = new PatientManager();
        
        String action = request.getParameter("action");
        String codepatStr = request.getParameter("codepat");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String sexe = request.getParameter("sexe");
        String adresse = request.getParameter("adresse");

        
        try{
            if("supprimer".equals(action) && codepatStr != null && !codepatStr.isEmpty()){
                int codepatToDelete = Integer.parseInt(codepatStr);
                //int codeMedToDelete = Integer.parseInt(request.getParameter("codemedToDelete")); ça dépend de html qui renvoie les donnés 
                
                patientManager.supprimerPatient(codepatToDelete);
                System.out.println("Patient" + codepatToDelete + "et ses visites supprimés");
                
            } else if(nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty()){
                if(codepatStr == null || codepatStr.isEmpty()){
                    patientManager.ajouterPatient(nom, prenom, sexe, adresse);
                    System.out.println("Nouveau patient ajouté");
                } else{
                    int codepat = Integer.parseInt(codepatStr);
                    patientManager.modifierPatient(codepat, prenom, prenom, sexe, adresse);
                    System.out.println("Médecin modifié: "+codepat);
                }
            } else{
                System.out.println("Aucune action effectuée");
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Erreur dans le traitement de la requête");
        }
        
       
        // Rediriger l'utilisateur vers la page Med
        List<Patient> listePatients = patientManager.getAllPat();
        request.setAttribute("listePatients", listePatients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Patient.jsp");
        dispatcher.forward(request, response);
    } 
}
