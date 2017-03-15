  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class testServlet extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }


  static List<Product> products;
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
				     System.out.println("in gogeet meth!!!");
					  Product product;
                 
				   
				   
				   System.out.println("into customerServlet");

				   PrintWriter out = response.getWriter();
               			//    response.setContentType("text/html"); 
							out.println("Welcome "+request.getAttribute("Name"));
				//out.println("<H1>Hello from a Servlet!!!!!!! 4</h2>"); 
    
	 
	 HashMap<String, Product> res = BestDealSerializedDataStore.getSmartphones();
	 System.out.println("size of smart phone");
	 if (res.size() == 0) {
	      response.sendError(response.SC_NOT_FOUND,
	                         "Missing Items.");
	      return;
	    }
	 
	 
	  response.setContentType("text/html");
	       String docType =
	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	      "Transitional//EN\">\n";
	    out.println(docType +
	                "<HTML>\n" +
	                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	              
	                "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
	    
	    Product item;
	    
	    
	    
	   for (Product val : res.values()) {
	        out.println("<HR>");
	        item = val;
	        // Show error message if subclass lists item ID
	        // that's not in the catalog.
	        if (item == null) {
	          out.println("<FONT COLOR=\"RED\">" +
	                      "Unknown item ID "+
	                      "</FONT>");
	        } else {
	          out.println();
	          String formURL =
	            "http://localhost:8999/A0/addtocart";
	          // Pass URLs that reference own site through encodeURL.
	          formURL = response.encodeURL(formURL);
	          out.println
	            ("<FORM METHOD=\"post\" ACTION=\"" + formURL + "\">\n" +
	             "<INPUT TYPE=\"HIDDEN\" NAME=\"id\" " +
	             "       VALUE=\"" + item.getId() + "\">\n" +
	             "<H2>" + item.getName() +
	             " ($" + item.getPrice() + ")</H2>\n" +
	             item.getBrand() + "\n" +
	             "<P>\n<CENTER>\n" +
	             "<INPUT TYPE=\"SUBMIT\" " +
	             "VALUE=\"Add to Shopping Cart\">\n" +
	             "</CENTER>\n<P>\n</FORM>");
	        }
	      }
	      out.println("<HR>\n</BODY></HTML>");
	 

	 out.println("</table>");
	}
	

      
    
	 
   } 