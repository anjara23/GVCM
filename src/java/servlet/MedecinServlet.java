/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import bean.Medecin;
import jakarta.servlet.RequestDispatcher;
import manager.MedecinManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.json.JSONObject;
/**
 *
 * @author LIANTSOA ANJARA
 */
@WebServlet(name = "MedecinServlet", urlPatterns = {"/Medecin"})
public class MedecinServlet extends HttpServlet{
    
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("La méthode doGet() de MedecinServlet est appelée.");
        MedecinManager medecinManager = new MedecinManager();

        String searchQuery = request.getParameter("search");
        List<Medecin> listeMedecins;

        
        if (searchQuery != null && !searchQuery.isEmpty()) {
           listeMedecins = medecinManager.rechercherMedecins(searchQuery);
        } else {           
            listeMedecins = medecinManager.getAllMed();
        }
        
        //cards
        Integer totalMed = medecinManager.totalMed();
        request.setAttribute("totalMed",totalMed);
        
        Integer medLib = medecinManager.medLibre();
        request.setAttribute("medLib", medLib);

        request.setAttribute("listeMedecins",listeMedecins);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Medecin.jsp");
        dispatcher.forward(request, response);
    }
    
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("La méthode doPost() de MedecinServlet est appelée.");

        MedecinManager medecinManager = new MedecinManager();
        
        String action = request.getParameter("action");
        String codemedStr = request.getParameter("codemed");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String grade = request.getParameter("grade");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
        
        try{
            if("supprimer".equals(action) && codemedStr != null && !codemedStr.isEmpty()){
                int codeMedToDelete = Integer.parseInt(codemedStr);            
                medecinManager.supprimerMedecin(codeMedToDelete);
                System.out.println("Médecin" + codeMedToDelete + "et ses visites supprimés");
                
                    json.put("status", "success");
                    json.put("message", "Medecin supprimé avec succès");                
                
            } else if(nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty()){
                if(codemedStr == null || codemedStr.isEmpty()){
                    medecinManager.ajouterMedecin(nom, prenom, grade);
                    System.out.println("Nouveau médecin ajouté");
                    
                    json.put("status", "success");
                    json.put("message", "Nouveau medecin ajouté avec succès");
                } else{
                    int codemed = Integer.parseInt(codemedStr);
                    medecinManager.modifierMedecin(codemed, nom, prenom, grade);
                    System.out.println("Médecin modifié: "+codemed);
                    
                    json.put("status", "success");
                    json.put("message", "Medecin modifié avec succès");
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
        List<Medecin> listeMedecins = medecinManager.getAllMed();
        request.setAttribute("listeMedecins", listeMedecins);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Medecin.jsp");
        dispatcher.forward(request, response);*/
    }
}
