  import java.io.*;
  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;
import java.util.*;
public class TrendingView extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }
		     
		  

   // The doPost() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   
               throws ServletException, IOException {
				   System.out.println("into view review");
				   
				   PrintWriter out = response.getWriter();
             response.setContentType("text/html"); 
			 
String myvar = "<html>"+
"<head>"+
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
"<title>BestDeal</title>"+
"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
"</head>"+
"<body>"+
"<div id=\"container\" >"+
"    <header>"+
"        <h1><a href=\"/\">Best<span>Deal</span></a></h1>"+
"        <h2>Get the best products for the best price</h2>"+
"    </header>"+
"    <nav>"+
"        <ul>"+
"            <li class=\"start selected\"><a href=\"index.html\">Home</a></li>  "+
"            <li class=\"end\"><a href=\"contact.html\">Trending</a></li>"+
"            <div style=\"float:right\">"+
"            <li class=\"username\" ><a href=\"account.html\" style=\"color:black;\" id=\"username\"><b>User</b></a></li>"+
"            <li class=\"shoppingcart\" ><a href=\"shoppingcart.html\" style=\"color:black;\" id=\"shoppingcart\"><img src=\"shopping-cart.jpg\"/>0</a></li>"+
"            </div>"+
"        </ul>"+
"            "+
"    </nav>";
	

			 out.println(myvar);
			 out.println("<div id=\"body\"       style=\"min-height:510px;\">");
			 
			 List<String> topliked =MongoDBDataStoreUtilities.getTopFiveLiked();
			 
			  List<String> topzip =MongoDBDataStoreUtilities.getTopZip();
			  
			  List<String> topsold=MySQLDataStoreUtilities.getMostSoldProducts();
			 
			 out.println("<h3>Top five most liked products</h3>");
			 
String myvar2 = "<table BORDER=1 ALIGN=\"CENTER\">"+
"<thead><td><strong>Product</strong></td><td><strong>Rating</strong></td></thead>";
	

			 out.println(myvar2);
			 

for (String entry : topliked) {
	 String[] values = entry.split(",");
    ;
	String rating =   values[1].substring(0, 3);
   out.println("<tr><td>"+values[0]+"</td><td>"+rating+"</td></tr>");
}
			 out.println("</table>");
	


out.println("<h3>Top five zip-codes where maximum number of products sold</h3>");
String myvar3 = "<table BORDER=1 ALIGN=\"CENTER\">"+
"<thead><td><strong>ZipCode</strong></td><td><strong>Number of Products</strong></td></thead>";
	

			 out.println(myvar2);
			 

for (String entry : topzip) {
	 String[] values = entry.split(",");
    
   out.println("<tr><td>"+values[0]+"</td><td>"+values[1]+"</td></tr>");
}
			 out.println("</table>");
		
			 
		out.println("<h3>Top five most sold products</h3>");
String myvar4 = "<table BORDER=1 ALIGN=\"CENTER\">"+
"<thead><td><strong>Product</strong></td><td><strong>Number of Products</strong></td></thead>";

			 out.println(myvar2);
			 

for (String entry : topsold) {
	 String[] values = entry.split(",");
    
   out.println("<tr><td>"+values[0]+"</td><td>"+values[1]+"</td></tr>");
}
			 out.println("</table></body></html>");
			
			 	
	}
	

      
    
	 
   } 