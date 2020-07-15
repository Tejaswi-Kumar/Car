/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
/**
 *
 * @author tejpa
 */
public class database extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        MongoClient mongoClient = null;
        DBCursor cursor = null;
    mongoClient = new MongoClient( "localhost" , 27017 );
    DB db = mongoClient.getDB( "car_show" );
    DBCollection coll = db.getCollection("scrape1");
    cursor = coll.find();
     double a[][] = {       
                        {1.0, 0.0, 0.0},    
                        {0.0, 1.0, 0.0},    
                        {0.0, 0.0, 1.0}    
                    };    
     if(Integer.parseInt(request.getParameter("combo1"))==1)
     {a[0][1]=Double.parseDouble(request.getParameter("ques1"));
    a[1][0]=1.0/Double.parseDouble(request.getParameter("ques1"));}
     else
     {
    a[1][0]=Double.parseDouble(request.getParameter("ques1"));
    a[0][1]=1.0/Double.parseDouble(request.getParameter("ques1"));
     }
     if(Integer.parseInt(request.getParameter("combo2"))==1){
    a[1][2]=Double.parseDouble(request.getParameter("ques2"));
    a[2][1]=1.0/Double.parseDouble(request.getParameter("ques2"));}
     else{
    a[2][1]=Double.parseDouble(request.getParameter("ques2"));
    a[1][2]=1.0/Double.parseDouble(request.getParameter("ques2"));
     }
     if(Integer.parseInt(request.getParameter("combo3"))==1){
    a[2][0]=Double.parseDouble(request.getParameter("ques3"));
    a[0][2]=1.0/Double.parseDouble(request.getParameter("ques3"));}
     else{
    a[0][2]=Double.parseDouble(request.getParameter("ques3"));
    a[2][0]=1.0/Double.parseDouble(request.getParameter("ques3"));
     }
    int i,j,n,n1;
    String s=request.getParameter("combo4");
    n=Integer.parseInt(request.getParameter("combo5"));
    double s1=0.0,s2=0.0,s3=0.0,c1,c2,c3;
    s1=a[0][0]+a[1][0]+a[2][0];
    s2=a[0][1]+a[1][1]+a[2][1];
    s3=a[0][2]+a[1][2]+a[2][2];
    for(i=0;i<3;i++)
    for(j=0;j<3;j++)
    {
    if(i==0)
     a[j][i]/=s1;
    if(i==1)
     a[j][i]/=s2;
    if(i==2)
     a[j][i]/=s3;
    }
     c1=(a[0][0]+a[0][1]+a[0][2])/3;
    c2=(a[1][0]+a[1][1]+a[1][2])/3;
    c3=(a[2][0]+a[2][1]+a[2][2])/3;
    
        try (PrintWriter out = response.getWriter()) {
            String f=request.getParameter("fname");
            String l=request.getParameter("lname");
            if(request.getParameter("bname").equals("")==false)
            n1=Integer.parseInt(request.getParameter("bname"));
            else
            n1=1000000000;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Car database</title>");            
            out.println("</head>");
            out.println("<body style=\"background-color:#ffff4d;\"><center>");
            out.println("<h2>Hello "+f+" "+l+"</h2><br><p align=\"right\"><a href='questions.html'>CHANGE YOUR PREFERENCES</a></p>");
            out.println("<br><h3>Based on the given filters CRS has listed the following Models:</h3><br><br>");
            out.println("<table border='1' cellpadding=\"15\">");
            out.println("<tr><th>INDEX</th>");
            out.println("<th>CAR MODEL</th>");
            out.println("<th>PRICE</th>");
            out.println("<th>FUEL TYPE</th>");
            out.println("<th>ENGINE</th>");
            out.println("<th>MILEAGE</th>");
            out.println("<th>CAR TYPE</th>"); 
            out.println("<th>SEATING</th>"); 
            out.println("<th>CRS SCORE</th></tr>");
           int index=0;
           ArrayList<car> c = new ArrayList<>();
           ArrayList<car> y1 = new ArrayList<>();
           double w1=0.0,w2=0.0,w3=0.0;
    while(cursor.hasNext()) {
        DBObject obj = cursor.next();
        String mod = (String)obj.get("CAR MODEL");
        double mil = (double)obj.get("MILEAGE");
        String fuel = (String)obj.get("FUEL TYPE");
        double price = (double)obj.get("PRICE");
        int eng = (int)obj.get("ENGINE");
        int seat=(int)obj.get("SEATING");
        String type=(String)obj.get("CAR TYPE");
        c.add(new car(mod,price,eng,fuel,mil,type,seat,0.0));
        w1+=price*1.0;
        w2+=eng;
        w3+=mil;
        index++;
    }
    w1/=index;
    w2/=index;
    w3/=index;
    double p,e,m;
    for(car str: c){
        p=(str.price*1.0)/w1;
        e=str.eng/w2;
        m=str.mil/w3;
        double score=c2*e-c1*p+c3*m;
        y1.add(new car(str.mod,str.price,str.eng,str.fuel,str.mil,str.type,str.seat,score));
    }
    Collections.sort(y1,new scorecompare());
    index=0;
DecimalFormat numberFormat = new DecimalFormat("#.0000");
            for(car str: y1){
        if(((str.score)*10)<=0)
        break;
        if(n!=0)
            if(n!=str.seat)
                continue;
        if(s.equals("Choose an option")==false)
            if(s.equals(str.type)==false)
                continue;
        if(n1<str.price)
        continue;
        out.print("<tr><td>"+(index+1)+"</td>");
        out.println("<td>"+str.mod+"</td>");
        out.println("<td>"+"Rs. "+str.price+"</td>");
        out.println("<td>"+str.fuel+"</td>");
        out.println("<td>"+str.eng+" cc"+"</td>");
        out.println("<td>"+str.mil+" kmpl"+"</td>");
        out.println("<td>"+str.type+"</td>");
        out.println("<td>"+str.seat+"</td>");
        out.println("<td>"+numberFormat.format((str.score)*10)+"</td></tr>");
        index++;
	   }
            out.println("</table>"+(index)+" Results Found</center>");
            out.println("</body></html>");
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null,e);  
        }
        cursor.close(); 
    mongoClient.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
