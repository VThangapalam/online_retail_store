import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	

public class LoginForm extends HttpServlet  {  // JDK 6 and above only
 
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   PrintWriter out = response.getWriter();
				    response.setContentType("text/html");
					String mess="";
					  try {         		
	 mess = request.getAttribute("mes1").toString();
	System.out.println("message is "+mess);
	if(mess.equals("null"))
	{
		mess="Please enter details";
	}
					  } catch(Exception e){}
					
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
""+
"		"+
""+
"	    	<H3>Login</H3>"+
"        <p id =\"mess\">"+mess+"<p>"+
"            "+
"			<form method=\"post\" action=\"http://localhost:8999/A2/login\">"+
"       "+
"            <input type=\"text\" name=\"uname\" placeholder=\"Username\"> </br>"+
"            <input type=\"password\" name=\"pword\"  placeholder=\"Password\"></br>"+
"            <input class=\"submit\" type=\"submit\" value=\"Submit\"></br>"+
""+
""+
"       "+
"        </form>"+
"		"+
"        </br>"+
"        <a href=\"http://localhost:8999/A2/registerform\" >Register</a>"+
"	"+
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
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   PrintWriter out = response.getWriter();
				    response.setContentType("text/html");
				String mess="";
					  try {         		
	 mess = request.getAttribute("mes1").toString();
	System.out.println("message is "+mess);
	if(mess.equals("null"))
	{
		mess="Please enter details";
	}  } catch(Exception e){}
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
""+
"		"+
""+
"	    	<H3>Login</H3>"+
"        <p id =\"mess\">"+mess+"<p>"+
"            "+
"			<form method=\"post\" action=\"http://localhost:8999/A2/login\">"+
"       "+
"            <input type=\"text\" name=\"uname\" placeholder=\"Username\"> </br>"+
"            <input type=\"password\" name=\"pword\"  placeholder=\"Password\"></br>"+
"            <input class=\"submit\" type=\"submit\" value=\"Submit\"></br>"+
""+
""+
"       "+
"        </form>"+
"		"+
"        </br>"+
"        <a href=\"http://localhost:8999/A2/registerform\" >Register</a>"+
"	"+
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