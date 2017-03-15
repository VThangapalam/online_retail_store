import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class DataAnalytics extends HttpServlet {  // JDK 6 and above only
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
	
String myvar = "<html>"+
"<body>"+
"<form method=\"post\" action=\"http://localhost:8999/A2/buildQuery\">"+
"<pre>"+
"<lablel>Product             :</label> <select name=\"product\">"+
"  <option value=\"all\" selected=\"selected\">all</option>";
	
 HashMap<String, Product> res = BestDealSerializedDataStore.getSmartphones();
	
	out.println(myvar);
  for (Product val : res.values()) {
  out.println(" <option value=\""+val.getName()+"\">"+val.getName()+"</option>");
	       }
		   
	
String myvar2 = "</select>"+
"<label>Category            : </lablel>"+
"<select name=\"category\">"+
"  <option value=\"all\" selected=\"selected\">all</option>"+
"  <option value=\"Smartphone\">SmartPhone</option>"+
"  <option value=\"Laptop\">Laptop</option>"+
"  <option value=\"TV\">TV</option>"+
"   <option value=\"Tablet\">Tablet</option>"+
"</select>"+
"<br>"+
""+
"Price               : <input type=\"text\" name=\"price\" placeholder=\"price\"><br>"+
"Manufacturer Name   : <input type=\"text\" name=\"manufacturer\" placeholder=\"manufacturer\"><br>"+
"Manufacturer Rebate : "+
"<select name=\"rebate\">"+
"  <option value=\"all\" selected=\"selected\"></option>"+
"  <option value=\"yes\">Yes</option>"+
"  <option value=\"no\">No</option>"+
"</select>"+
"Retailer Name       : <input type=\"text\" name=\"retailerName\" placeholder=\"retailerName\"><br>"+
"Retailer Zip        : <input type=\"text\" name=\"retailerZip\" placeholder=\"retailerZip\"><br>"+
"Retailer City       : <input type=\"text\" name=\"retailerCity\" placeholder=\"City\"><br>"+
"Retailer State      : <input type=\"text\" name=\"retailerState\" placeholder=\"retailerState\"><br>"+
"Product On Sale     : <input type=\"text\" name=\"productOnSale\" placeholder=\"productOnSale\"><br>"+
"User Id             : <input type=\"text\" name=\"userid\" placeholder=\"userid\"><br>"+
"Age                 : "+
"<select name=\"age\">"+
"  <option value=\"all\" selected=\"selected\"></option>"+
"  <option value=\"greater\">greater than</option>"+
"  <option value=\"equal\">equal to</option>"+
"  <option value=\"lesser\">less than</option>"+
"</select>"+
"<br>"+
"Gender              : "+
"<select name=\"gender\">"+
"  <option value=\"all\" selected=\"selected\"></option>"+
"  <option value=\"Male\">Male</option>"+
"  <option value=\"Female\">Female</option>"+
"</select>"+
"<br>"+
"Occupation          : <input type=\"text\" name=\"useroccupation\" placeholder=\"useroccupation\"><br>"+
"Rating              : "+
"<select name=\"rating\">"+
"  <option value=\"all\" selected=\"selected\"></option>"+
"  <option value=\"greater\">greater than</option>"+
"  <option value=\"equal\">equal to</option>"+
"   <option value=\"less than\">less than</option>"+
"</select>"+
"<br>"+
"Review Date         : <input type=\"text\" name=\"reviewdate\" placeholder=\"MM/DD/YYYY\"><br>"+
"Review Text         : <input type=\"text\" name=\"reviewtext\" placeholder=\"reviewtext\"><br>"+
"                      <input class=\"submit\" type=\"submit\" value=\"Find Reviews\">"+
"<body>"+
"</html>";
	
	  out.println(myvar2); 
		   
	   
	}
	

      
    
	 
   } 