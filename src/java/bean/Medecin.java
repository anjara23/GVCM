/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author LIANTSOA ANJARA
 */
public class Medecin {
    private int codemed;
    private String nom;
    private String prenom;
    private String grade;
    
    public Medecin(){}
    
    public Medecin(int codemed, String nom, String prenom, String grade){
        super();
        this.codemed = codemed;
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }

    
    public int getCodemed(){
        return codemed;
    }
    
    public void setCodemed(int codemed){
        this.codemed = codemed;
    }
    
    
    public String getNom(){
        return nom;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    
    public String getPrenom(){
        return prenom;
    }
    
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    
    
    public String getGrade(){
        return grade;
    }
    
    public void setGrade(String grade){
        this.grade = grade;
    }
    
    
    @Override
    public String toString(){
        return "Medecin [codemed=" + codemed + ", nom=" + nom + ", prenom=" + prenom + ", grade=" + grade + "]";
    }
    
}
