/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;


import bean.Visiter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import manager.VisiterManager;

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

        
        try{
            if("supprimer".equals(action) && codeviStr != null && !codeviStr.isEmpty()){
                int codeviToDelete = Integer.parseInt(codeviStr);
                //int codeMedToDelete = Integer.parseInt(request.getParameter("codemedToDelete")); ça dépend de html qui renvoie les donnés 
                
                visiterManager.supprimerVi(codeviToDelete);
                System.out.println(" La visite avec codeviToDelete :" + codeviToDelete + "est supprimée");
                
            } else if(date != null && !date.isEmpty() && codemedStr == null && !codemedStr.isEmpty() && codepatStr == null && codepatStr.isEmpty()){
                    int codemed = Integer.parseInt(codemedStr);
                    int codepat = Integer.parseInt(codepatStr);
                    
                    visiterManager.ajoutVisite(codemed, codepat, date);
                    System.out.println(" La visite avec codemed :" + codemed + "et codepat : " + codepat +" est ajoutée");
             
            } else{
                System.out.println("Aucune action effectuée");
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Erreur dans le traitement de la requête");
        }
        
       
        // Rediriger l'utilisateur vers la page Med
        List<Visiter> listeVisiter = visiterManager.getAllVi();
        request.setAttribute("listeVisiter", listeVisiter);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Visiter.jsp");
        dispatcher.forward(request, response);
    }
    
}
