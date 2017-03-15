  import java.io.*;
  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class AddReview extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }
		  
		  

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   System.out.println("into add %%%% review servlet!!!");
				 	
					       String product = request.getParameter("product");
       String category = request.getParameter("category");
       String price =request.getParameter("price");
       String manufacturerName = request.getParameter("manufacturerName");
       String manufacturerRebate = request.getParameter("manufacturerRebate");
       String retailerName = request.getParameter("retailerName");
       String retailerZip = request.getParameter("retailerZip");
       String retailerCity = request.getParameter("retailerCity");
       String retailerState = request.getParameter("retailerState");
       String productOnSale = request.getParameter("productOnSale");
       String userid = request.getParameter("userid");
       String userage = request.getParameter("userage");
       String usergender = request.getParameter("usergender");
       String useroccupation = request.getParameter("useroccupation");
       String reviewrating = (request.getParameter("reviewrating"));
       String reviewdate = request.getParameter("reviewdate");
       String reviewtext = request.getParameter("reviewtext");
       boolean sale =false;
       if(productOnSale.equalsIgnoreCase("yes")){
    	  sale = true ;
       }
       
       

      
		Review rev = new Review(product,category,  price, retailerName,
   			 retailerZip,  retailerCity,  retailerState,  sale,
   			 manufacturerName,  manufacturerRebate,  userid,  userage,  usergender,
   			useroccupation,  reviewrating,  reviewdate,  reviewtext);
			
			
			
			MongoDBDataStoreUtilities.addReview(rev);
if(category.equalsIgnoreCase("Smartphone")){
						  ServletContext sc = getServletContext();
                          sc.getRequestDispatcher("/tablet").forward(request, response);
}
	

if(category.equalsIgnoreCase("Laptop")){
						  ServletContext sc = getServletContext();
                          sc.getRequestDispatcher("/laptop").forward(request, response);
}


if(category.equalsIgnoreCase("Tablet")){
						  ServletContext sc = getServletContext();
                          sc.getRequestDispatcher("/tablet").forward(request, response);
}


if(category.equalsIgnoreCase("TV")){
						  ServletContext sc = getServletContext();
                          sc.getRequestDispatcher("/tv").forward(request, response);
}	
					  
	}
	

      
    
	 
   } 