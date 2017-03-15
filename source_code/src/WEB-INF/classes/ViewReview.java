  import java.io.*;
  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class ViewReview extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }
		     
		  

   // The doPost() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
   
               throws ServletException, IOException {
				   System.out.println("into view review");
				   
				   PrintWriter out = response.getWriter();
             response.setContentType("text/html"); 
			 String productName = request.getParameter("product");
       String category = request.getParameter("category");
       String price =request.getParameter("price");
       String manufacturerName = request.getParameter("brand");
       String manufacturerRebate = request.getParameter("brand_rebate");
		


String myvar4 = "<html>"+
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
out.println(myvar4);	
String myvar = "<pre>"+
"Product             : <span>"+productName+"</span><br>"+
"Category            : <span>"+category+"</span><br>"+
"Price               : <span>"+price+"</span><br>"+
"Manufacturer Name   : <span>"+manufacturerName+"</span><br>"+
"Manufacturer Rebate : <span>"+manufacturerRebate+"</span><br>";
	
out.println(myvar);

String myvar2 = "<table BORDER=1 ALIGN=\"CENTER\">";
	

	out.println(myvar2);
	
String product = request.getParameter("product");
System.out.println(product+"******");
 List<Review> rev = MongoDBDataStoreUtilities.getProductReview(product);
 if(rev.size()>0){
 for (Review r : rev){
	 
out.println("<tr><td><strong>Rating      :</strong></td><td><span>"+r.getRating()+"</span></td></tr>"+
"<tr><td><strong>Review Text :</strong></td><td><span>"+r.getReviewText()+"</span></td><tr>"+
"<tr><td><strong>User        :</strong></td><td><span>"+r.getUserId()+"</span></td></tr>");
	

 }
 }
 else{
	 out.println("<tr>No reviews for this product</tr>");
 }
 String myvar3 = "</table>                     "+
"<body>"+
"</html>";
 out.println(myvar3);
				 	
	}
	

      
    
	 
   } 