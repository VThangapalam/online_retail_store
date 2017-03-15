  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class RegisterFormSales extends HttpServlet {  // JDK 6 and above only
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
                 
				   
				   
				   System.out.println("into register form");

				   PrintWriter out = response.getWriter();
				   String mess="Please enter details";
     try {         		
	 mess = request.getAttribute("message").toString();
	System.out.println("message is "+mess);
	if(mess.equals("null"))
	{
		mess="Please enter details";
	}
	 }
	 catch (Exception e) {
		 System.out.println(e.getMessage());
	 }
	 
	  response.setContentType("text/html");
	     

String myvar = "<!doctype html>"+
"<html>"+
"<head>"+
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
"<title>BestDeal</title>"+
"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
""+
"</head>"+
"<body>"+
"<div id=\"container\">"+
"    <header>"+
"    	<h1><a href=\"/\">Best<span>Deal</span></a></h1>"+
"        <h2>Get the best products for the best price</h2>"+
"    </header>"+
"    <nav>"+
"    	<ul>"+
"        	<li class=\"start selected\"><a href=\"index.html\">Home</a></li>  "+
"            "+
"        </ul>"+
"            "+
"    </nav>"+
""+
"	"+
""+
"    <div id=\"body\" style=\"overflow: auto;padding: 100px 150px 100px 50px;\">"+
"<p id=\"p1\">Please enter the details and register</p>"+
"<script>"+
"document.getElementById(\"p1\").innerHTML = \""+mess+"\";"+
"</script>"+
""+
"		<form method=\"post\" action=\"http://localhost:8999/A2/register\">"+
"		<div class=\"registerform\">"+
"<h1>Create a Customer Account</h1>"+
"<input class=\"inputfield\"  type=\"text\" name=\"uname\" placeholder=\"Username\"><br>"+
"<input class=\"inputfield\" type=\"text\" name=\"password\" placeholder=\"Password\"><br>"+
"<input class=\"inputfield\" type=\"text\" name=\"Confirmpassword\" placeholder=\"Confirm Password\"><br>"+
"<input class=\"submit\" type=\"submit\" value=\"Submit\">"+
"        </div>"+
""+
"</form>"+
"		"+
"</br>		"+
"</br>"+
"</br>	"+
"<a href=\"index.html\">Return to home</a>	"+
"		"+
"       "+
"        "+
"        "+
"    	<div class=\"clear\"></div>"+
"    </div>"+
"    <footer>"+
"        <div class=\"footer-content\">"+
"            <ul>"+
"            	<li><h4>Returns</h4></li>"+
"                <li><a href=\"#\">Return Policy</a></li>"+
"                <li><a href=\"#\">Return Product</a></li>"+
"            </ul>"+
"            "+
"            <ul>"+
"            	<li><h4>Subscribe</h4></li>"+
"                <li><a href=\"#\">Latest Deals</a></li>"+
"                <li><a href=\"#\">Latest Products </a></li>"+
"            </ul>"+
"            "+
"            <ul class=\"endfooter\">"+
"            	<li><h4>Support and Services</h4></li>"+
"                <li><a href=\"#\">Call Customer Support </a></li>"+
"                <li><a href=\"#\">Feedback</a></li>"+
"            </ul>"+
"            "+
"            <div class=\"clear\"></div>"+
"        </div>"+
"        <div class=\"footer-bottom\">"+
"            <p>© BestDeal 2013. <a href=\"http://zypopwebtemplates.com/\">www.bestdeal.com</a></p>"+
"         </div>"+
"    </footer>"+
"</div>"+
"</body>"+
"</html>";
	


	

	    out.println(myvar);
		
		

	}
	

	
	@Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
				     System.out.println("in gogeet meth!!!");
					  Product product;
                 
				   
				   
				   System.out.println("into register form");

				   PrintWriter out = response.getWriter();
				   String mess="Please enter details";
     try {         		
	 mess = request.getAttribute("message").toString();
	System.out.println("message is "+mess);
	if(mess.equals("null"))
	{
		mess="Please enter details";
	}
	 }
	 catch (Exception e) {
		 System.out.println(e.getMessage());
	 }
	 
	  response.setContentType("text/html");
	     

String myvar = "<!doctype html>"+
"<html>"+
"<head>"+
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
"<title>BestDeal</title>"+
"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
""+
"</head>"+
"<body>"+
"<div id=\"container\">"+
"    <header>"+
"    	<h1><a href=\"/\">Best<span>Deal</span></a></h1>"+
"        <h2>Get the best products for the best price</h2>"+
"    </header>"+
"    <nav>"+
"    	<ul>"+
"        	<li class=\"start selected\"><a href=\"index.html\">Home</a></li>  "+
"            "+
"        </ul>"+
"            "+
"    </nav>"+
""+
"	"+
""+
"    <div id=\"body\" style=\"overflow: auto;padding: 100px 150px 100px 50px;\">"+
"<p id=\"p1\"></p>"+
"<script>"+
"document.getElementById(\"p1\").innerHTML = \""+mess+"\";"+
"</script>"+
""+
"		<form method=\"post\" action=\"http://localhost:8999/A0/register\">"+
"		<div class=\"registerform\">"+
"<h1>Create account for Customer</h1>"+
"<input class=\"inputfield\"  type=\"text\" name=\"uname\" placeholder=\"Username\"><br>"+
"<input class=\"inputfield\" type=\"password\" name=\"password\" placeholder=\"Password\"><br>"+
"<input class=\"inputfield\" type=\"password\" name=\"Confirmpassword\" placeholder=\"Confirm Password\"><br>"+
"<input class=\"submit\" type=\"submit\" value=\"Submit\">"+
"        </div>"+
""+
"</form>"+
"		"+
"</br>		"+
"</br>"+
"</br>	"+
"<a href=\"sales.html\">Return to home</a>	"+
"		"+
"       "+
"        "+
"        "+
"    	<div class=\"clear\"></div>"+
"    </div>"+
"    <footer>"+
"        <div class=\"footer-content\">"+
"            <ul>"+
"            	<li><h4>Returns</h4></li>"+
"                <li><a href=\"#\">Return Policy</a></li>"+
"                <li><a href=\"#\">Return Product</a></li>"+
"            </ul>"+
"            "+
"            <ul>"+
"            	<li><h4>Subscribe</h4></li>"+
"                <li><a href=\"#\">Latest Deals</a></li>"+
"                <li><a href=\"#\">Latest Products </a></li>"+
"            </ul>"+
"            "+
"            <ul class=\"endfooter\">"+
"            	<li><h4>Support and Services</h4></li>"+
"                <li><a href=\"#\">Call Customer Support </a></li>"+
"                <li><a href=\"#\">Feedback</a></li>"+
"            </ul>"+
"            "+
"            <div class=\"clear\"></div>"+
"        </div>"+
"        <div class=\"footer-bottom\">"+
"            <p>© BestDeal 2013. <a href=\"http://zypopwebtemplates.com/\">www.bestdeal.com</a></p>"+
"         </div>"+
"    </footer>"+
"</div>"+
"</body>"+
"</html>";
	


	

	    out.println(myvar);
		
		

	}
      
    
	 
   } 