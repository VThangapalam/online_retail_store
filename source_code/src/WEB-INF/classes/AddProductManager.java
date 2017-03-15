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

public class AddProductManager extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }
     


	static double totalcost=0;
  
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
System.out.println("int add product page update page");
			   
           String category = request.getParameter("category_add");
		   String product_id = request.getParameter("id_add");
		   String product_name = request.getParameter("prod_add");
			String product_price = request.getParameter("price_add");
String product_brand = request.getParameter("brand_add");
String product_image = request.getParameter("image_add");	
			  
			  
			  System.out.println("category "+category);
			  System.out.println("id "+product_id);
			  System.out.println("ame "+product_name);
			  System.out.println("pr "+product_price);
			  System.out.println("br "+product_brand);
			  System.out.println("img "+product_image);
			  int pr = Integer.parseInt(product_price);
			  
			   HashMap<String,Product> products = BestDealSerializedDataStore.getProducts();
			  
			 
			
			  Product newProd = new Product(product_id ,category, product_name, product_image, product_brand,pr,"no");
			  
		BestDealSerializedDataStore.addProduct(newProd);
		MySQLDataStoreUtilities.addProduct(product_id,category,product_name,product_image,product_brand,"yes",pr);
			  
		BestDealSerializedDataStore.addProduct(newProd);
		if(category.equals("Smartphone"))
		{
		    	BestDealSerializedDataStore.smartphones.put(product_id,newProd);
				System.out.println("adding to smartphone");
		}
		if(category.equals("Tablet"))
		{
		    	BestDealSerializedDataStore.tablets.put(product_id,newProd);
				System.out.println("adding to tab");
		}
		
		if(category.equals("Laptop"))
		{
		    	BestDealSerializedDataStore.laptops.put(product_id,newProd);
				System.out.println("adding to lap");
		}
		
		if(category.equals("TV"))
		{
		    	BestDealSerializedDataStore.tvs.put(product_id,newProd);
				System.out.println("adding to tv");
		}
		
			
	for (Map.Entry<String, Product> entry : BestDealSerializedDataStore.products.entrySet()) {
	    String key = entry.getKey();
	    Object value = entry.getValue();
	    System.out.println("product id is "+ key);
		}
			  
			  
 ServletContext servletContext = getServletContext();
RequestDispatcher requestDispatcher = servletContext
.getRequestDispatcher("/manager.html");
requestDispatcher.forward(request, response);		
				

   }
	

      
    
	 
   } 