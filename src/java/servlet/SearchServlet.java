/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import bean.Visiter;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet{
    
    private VisiterManager visiterManager = new VisiterManager();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        
        List<Visiter> searchResults = null;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            searchResults = visiterManager.rechercheVisite(searchQuery);
        } else {
            searchResults = visiterManager.getAllVi();
        }

        request.setAttribute("searchResults", searchResults);
        RequestDispatcher dispatcher = request.getRequestDispatcher("searchResults.jsp");
        dispatcher.forward(request, response);
    }
}
