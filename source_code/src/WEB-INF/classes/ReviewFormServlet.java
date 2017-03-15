import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class ReviewFormServlet extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }



   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
				     System.out.println("in review product");
					  Product product;
                 
				   
				   
				   System.out.println("into customerServlet");

				   PrintWriter out = response.getWriter();
	 
	  response.setContentType("text/html");
	  String myvar1 = "<html>"+
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
	
	out.println(myvar1);
	  String product_name = request.getParameter("product");
	   String category = request.getParameter("category");
	   String price = request.getParameter("price");
	    String brand = request.getParameter("brand");
		String brand_rebate = request.getParameter("brand_rebate");
System.out.println("trying to  post of add review");
String myvar = "<form method=\"get\""+
"action=\"http://localhost:8999/A2/addReview\">"+
"<pre>"+
"Product             : <span>"+product_name+"</span><br>"+
"Category            : <span>"+category+"</span><br>"+
"Price               : <span>"+price+"</span><br>"+
"Manufacturer Name   : <span>"+brand+"</span><br>"+
"Manufacturer Rebate : <span>"+brand_rebate+"</span><br>"+
"Retailer Name       : <input type=\"text\" name=\"retailerName\" placeholder=\"retailerName\"><br>"+
"Retailer Zip        : <input type=\"text\" name=\"retailerZip\" placeholder=\"retailerZip\"><br>"+
"Retailer City       : <input type=\"text\" name=\"retailerCity\" placeholder=\"City\"><br>"+
"Retailer State      : <input type=\"text\" name=\"retailerState\" placeholder=\"retailerState\"><br>"+
"Product On Sale     : <input type=\"text\" name=\"productOnSale\" placeholder=\"productOnSale\"><br>"+
"User Id             : <input type=\"text\" name=\"userid\" placeholder=\"userid\"><br>"+
"Age                 : <input type=\"text\" name=\"userage\" placeholder=\"userage\"><br>"+
"Gender              : <input type=\"text\" name=\"usergender\" placeholder=\"usergender\"><br>"+
"Occupation          : <input type=\"text\" name=\"useroccupation\" placeholder=\"useroccupation\"><br>"+
"Rating              : <input type=\"text\" name=\"reviewrating\" placeholder=\"reviewrating\"><br>"+
"Review Date         : <input type=\"text\" name=\"reviewdate\" placeholder=\"MM/DD/YYYY\"><br>"+
"Review Text         : <input type=\"text\" name=\"reviewtext\" placeholder=\"reviewtext\"><br>"+
"<input type=\"hidden\" name =\"product\""+
"value =\""+product_name+"\">"+
"<input type=\"hidden\" name =\"category\""+
"value =\""+category+"\">"+
"<input type=\"hidden\" name =\"price\""+
"value =\""+price+"\">"+
"<input type=\"hidden\" name =\"brand\""+
"value =\""+brand+"\">"+
"<input type=\"hidden\" name =\"brand_rebate\""+
"value =\""+brand_rebate+"\">"+

"<input class=\"submit\" type=\"submit\" value=\"Submit\">"+


"</body>"+
"</html>";
	
out.println(myvar);
	   
	}
	

      
    
	 
   } 