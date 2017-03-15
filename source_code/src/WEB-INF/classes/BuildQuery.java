  import java.io.*;
  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class BuildQuery extends HttpServlet {  // JDK 6 and above only
	
		  
		  

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   System.out.println("into add %%%% review servlet!!!");
				 	
	String product = request.getParameter("product");
       String category = request.getParameter("category");
       String price =request.getParameter("price");
       String manufacturerName = request.getParameter("manufacturer");
       String manufacturerRebate = request.getParameter("rebate");
       String retailerName = request.getParameter("retailerName");
       String retailerZip = request.getParameter("retailerZip");
       String retailerCity = request.getParameter("retailerCity");
       String retailerState = request.getParameter("retailerState");
       String productOnSale = request.getParameter("productOnSale");
       String userid = request.getParameter("userid");
       String userage = request.getParameter("age");
       String usergender = request.getParameter("gender");
       String useroccupation = request.getParameter("useroccupation");
       String reviewrating = (request.getParameter("rating"));
       String reviewdate = request.getParameter("reviewdate");
       String reviewtext = request.getParameter("reviewtext");
       
	   
	   
	   System.out.println(product);
	   System.out.println(category);
	   System.out.println(price);
	   System.out.println(manufacturerName);
	   System.out.println(manufacturerRebate);
	   System.out.println(retailerName);
	   System.out.println(retailerZip);
	   System.out.println(retailerCity);
	   System.out.println(retailerState);
	   System.out.println(productOnSale);
	   System.out.println(userid);
	   System.out.println(userage);
	   System.out.println(usergender);
	   System.out.println(useroccupation);
	   System.out.println(reviewrating);
	   System.out.println(reviewdate);
	   System.out.println(reviewtext);
	   boolean sale = false;
	   if(productOnSale.equals(true))
	   {
		   sale=true;
	   }
	   else {
		   sale=false;
	   }
	   
	   Review rev = new Review(product,category, price,retailerName,
			retailerZip,retailerCity, retailerState,sale,
			manufacturerName,manufacturerRebate, userid, userage, usergender,useroccupation, reviewrating, reviewdate, reviewtext);
	   MongoDBDataStoreUtilities.dataAnalytics(rev);
					  
	}
	

      
    
	 
   } 