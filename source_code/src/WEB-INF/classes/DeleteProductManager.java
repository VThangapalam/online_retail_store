  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DeleteProductManager extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }
     


	static double totalcost=0;
  
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
System.out.println("in product delete page");
			   
    
		   String product_id = request.getParameter("prod_del");
		
			  
			  
			  System.out.println("id "+product_id);
			  
			 HashMap<String,Product> products = BestDealSerializedDataStore.getProducts();
			  
			  Product proObj = products.get("product_id");
			  //String category = proObj.getCategory();
			  //System.out.println("cat !!!"+category);
			  
			  
			  BestDealSerializedDataStore.products.remove(product_id);
			  BestDealSerializedDataStore.smartphones.remove(product_id);
			  BestDealSerializedDataStore.tablets.remove(product_id);
			  BestDealSerializedDataStore.laptops.remove(product_id);
			  BestDealSerializedDataStore.tvs.remove(product_id);

	for (Map.Entry<String, Product> entry : BestDealSerializedDataStore.products.entrySet()) {
	    String key = entry.getKey();
	    Object value = entry.getValue();
	    System.out.println("product id is "+ key);
		}

	MySQLDataStoreUtilities.deleteProduct(product_id);
			  
			  
 ServletContext servletContext = getServletContext();
RequestDispatcher requestDispatcher = servletContext
.getRequestDispatcher("/manager.html");
requestDispatcher.forward(request, response);		
				

   }
	

      
    
	 
   } 