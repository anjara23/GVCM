package servlet;



import bean.Patient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import manager.PatientManager;
import org.json.JSONObject;

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

        //cards
        Integer totalPat = patientManager.totalPat();
        request.setAttribute("totalPat",totalPat);
        
        Integer patVi = patientManager.patVi();
        request.setAttribute("patVi",patVi);        
        
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
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();

        
        try{
            if("supprimer".equals(action) && codepatStr != null && !codepatStr.isEmpty()){
                int codepatToDelete = Integer.parseInt(codepatStr);
                //int codeMedToDelete = Integer.parseInt(request.getParameter("codemedToDelete")); ça dépend de html qui renvoie les donnés 
                
                patientManager.supprimerPatient(codepatToDelete);
                System.out.println("Patient" + codepatToDelete + "et ses visites supprimés");
                
                    json.put("status", "success");
                    json.put("message", "Patient supprimé avec succès");
                
                
            } else if(nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty() && sexe != null && !sexe.isEmpty() && adresse != null && !adresse.isEmpty()){
                if(codepatStr == null || codepatStr.isEmpty()){
                    patientManager.ajouterPatient(nom, prenom, sexe, adresse);
                    
                    json.put("status", "success");
                    json.put("message", "Nouveau patient ajouté avec succès");
                    
                } else{
                    int codepat = Integer.parseInt(codepatStr);
                    patientManager.modifierPatient(codepat, nom, prenom, sexe, adresse);
                    System.out.println("Médecin modifié: "+codepat);
                    
                    json.put("status", "success");
                    json.put("message", "Patient modifié avec succès");
                }
                

                
            } else{
                System.out.println("Aucune action effectuée");
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Erreur dans le traitement de la requête");
        }

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
        out.close();
                        
       
        /* Rediriger l'utilisateur vers la page Med
        List<Patient> listePatients = patientManager.getAllPat();
        request.setAttribute("listePatients", listePatients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Patient.jsp");
        dispatcher.forward(request, response);*/
    } 
}
