
package bean;

/**
 *
 * @author LIANTSOA ANJARA
 */
public class Visiter {
   
   private int codevi; 
   private int codemed;
   private int codepat;
   private String date;
   
   public Visiter() {}
   
   public Visiter(int codeVi, int codemed, int codepat, String date){
       this.codevi = codeVi; 
       this.codemed = codemed;
       this.codepat = codepat;
       this.date = date;          
   }
   
   public int getCodevi(){
       return codevi;
   }
   
   public void setCodevi(int codevi){
       this.codevi = codevi;
   }
   
   
   public int getCodemed(){
        return codemed;
   }

   public void setCodemed(int codemed){
       this.codemed = codemed;
   }
   
   public int getCodepat(){
       return codepat;
   }
   
   public void setCodepat(int codepat){
       this.codepat = codepat;
   }
   
   public String getDate(){
       return date;
   }
   
   public void setDate(String date){
       this.date = date;
   }
   
    @Override
    public String toString(){
        return "Visiter [codevi=" + codevi + ", codemed=" + codemed + ", codepat=" + codepat + ", date=" + date + "]";
    }
    
}
