/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;


import bean.Medecin;
import bean.Patient;
import bean.Visiter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import manager.MedecinManager;
import manager.PatientManager;
import manager.VisiterManager;
import org.json.JSONObject;

/**
 *
 * @author LIANTSOA ANJARA
 */
@WebServlet(name = "VisiterServlet", urlPatterns = {"/Visiter"})
public class VisiterServelet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("La méthode doGet() de VisiterServlet est appelée.");
        VisiterManager visiterManager = new VisiterManager();

        String searchQuery = request.getParameter("search");
        List<Visiter> listeVisiter;

        
        if (searchQuery != null && !searchQuery.isEmpty()) {
           listeVisiter = visiterManager.rechercheVisite(searchQuery);
        } else {           
            listeVisiter = visiterManager.getAllVi();
        }

        //prendre liste patients pour visiter
        PatientManager patientManager = new PatientManager();
        MedecinManager medecinManager = new MedecinManager();
  
        List<Patient> listePatients = patientManager.getAllPat();
        request.setAttribute("listePatients1",listePatients);
        
        List<Medecin> listeMedecins = medecinManager.getAllMed();
        request.setAttribute("listeMedecins",listeMedecins);
        
        //cards
        Integer totalVi = visiterManager.totalVi();
        request.setAttribute("totalVi",totalVi);
        
        Integer viAuj = visiterManager.viAuj();
        request.setAttribute("viAuj",viAuj);
      
        request.setAttribute("listeVisiter",listeVisiter);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Visiter.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("La méthode doPost() de VisiterServlet est appelée.");

        VisiterManager visiterManager = new VisiterManager();
        
        String action = request.getParameter("action");
        String codeviStr = request.getParameter("codevi");
        String codemedStr = request.getParameter("codemed");
        String codepatStr = request.getParameter("codepat");
        String date = request.getParameter("date");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();

        
        try{
            if("supprimer".equals(action) && codeviStr != null && !codeviStr.isEmpty()){
                int codeviToDelete = Integer.parseInt(codeviStr);
                //int codeMedToDelete = Integer.parseInt(request.getParameter("codemedToDelete")); ça dépend de html qui renvoie les donnés 
                
                visiterManager.supprimerVi(codeviToDelete);
                System.out.println(" La visite avec codeviToDelete :" + codeviToDelete + "est supprimée");
                
                json.put("status", "success");
                json.put("message", "Visite supprimée avec succès");
                
            } else if(date != null && !date.isEmpty() && codemedStr != null && !codemedStr.isEmpty() && codepatStr != null && !codepatStr.isEmpty()){
     
                   int codemed = Integer.parseInt(codemedStr);
                   int codepat = Integer.parseInt(codepatStr);
                   
                   if(codeviStr == null || codeviStr.isEmpty()){
                        visiterManager.ajoutVisite(codemed, codepat, date);
                        System.out.println(" La visite avec codemed :" + codemed + "et codepat : " + codepat +" est ajoutée");

                        json.put("status", "success");
                        json.put("message", "Nouvelle visite ajoutée avec succès");
                    
                    } else{
                        int codevi = Integer.parseInt(codeviStr);

                        visiterManager.modifierVi(codevi, codemed, codepat, date);
                        System.out.println("La visite avec codevi :" + codevi + " est modifiée");

                        json.put("status", "success");
                        json.put("message", "Visite modifiée avec succès");
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
        
    }
    
}
