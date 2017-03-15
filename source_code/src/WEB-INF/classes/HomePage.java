  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class HomePage extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }


  static List<Product> products;
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
				    HttpSession session = request.getSession();
    String user="user";
	ShoppingCart shoppingcartNum;
		
	    ShoppingCart cart =null;
    
      synchronized(session) {
      cart = (ShoppingCart)session.getAttribute("shoppingCart");
      if (cart == null) {
        cart = new ShoppingCart();
        session.setAttribute("shoppingCart", cart);
      }
      }
      int shoppingitems=0;
    synchronized(session) {
      List itemsOrdered = cart.getItemsOrdered();
      shoppingitems=itemsOrdered.size();
    }
	
    synchronized(session) {
      user = session.getAttribute("username").toString();
	}
	
		
				     Product product;
                 
				   
				   
				   System.out.println("into home page post!!");

				   PrintWriter out = response.getWriter();
               			//    response.setContentType("text/html"); 
							System.out.println("Welcome user from session par "+user);
				//out.println("<H1>Hello from a Servlet!!!!!!! 4</h2>"); 
    
	 
	 HashMap<String, Product> res = BestDealSerializedDataStore.getProducts();
	 if (res.size() == 0) {
	      response.sendError(response.SC_NOT_FOUND,
	                         "Missing Items.");
	      return;
	    }
	 
	 
	  response.setContentType("text/html");
	  // response.setContentType("image/jpeg");
	  
	  

String myvar = "<!doctype html>"+
"<html>"+
"<head>"+
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
"<title>BestDeal</title>"+
"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
"<script src=\"myScript.js\"></script>"+
"</head>"+
"<body onload=\"init()\">"+
"<div id=\"container\">"+
"    <header>"+
"    	<h1><a href=\"/\">Best<span>Deal</span></a></h1>"+
"        <h2>Get the best products for the best price</h2>"+
"    </header>"+
"    <nav>"+
"    	<ul>"+
"        	<li class=\"start selected\"><a href=\"index.html\">Home</a></li>  "+
"            <li class=\"end\"><a href=\"http://localhost:8999/A2/trending\">Trending</a></li>"+
"            <li class=\"end\"><a href=\"ViewOrder.html\">View Order</a></li>"+
"            <li class=\"search\" style=\"color:black;\"> "+
"                Search site"+
"                    <ul>"+
"                        <li class=\"text\">"+

                             
"                                    <input type=\"text\" size=\"32\" value=\"\" name=\"searchId\" id=\"searchId\" onkeyup=\"doCompletion()\"/>"+
"<div id=\"auto-row\">"+
"<table id=\"complete-table\" style=\"background-color:#648ed1\"></table>"+
"</div>"+                               
"                        </li>"+
"                    </ul>"+
"            </li>"+
"            <div style=\"float:right\">"+
"            <li class=\"username\" ><a href=\"account.html\" style=\"color:black;\" id=\"username\"><b>User</b></a></li>"+
"            <script type=\"text/javascript\">"+
"                document.getElementById('username').text = \""+user+"\";"+
"            </script>"+
"            <li class=\"shoppingcart\" ><a href=\"http://localhost:8999/A2/viewcart\" style=\"color:black;\" id=\"shoppingcart\"><img src=\"shopping-cart.jpg\"/>"+"  "+shoppingitems+"</a></li><li class=\"logout\" ><a href=\"index.html\" style=\"color:black;\" id=\"logout\"><b>Logout</b></a></li>"+

"            </div>"+
"        </ul>"+
"            "+
"    </nav>"+
"    <div id=\"body\" stlye=\"height: 100%;\">";
	


	
	
String footer = "<aside class=\"sidebar\">"+
"	"+
"            <ul>	"+
"               <li>"+
"                    <h4>Categories</h4>"+
"                    <ul>"+
"                        <li><a href=\"http://localhost:8999/A2/smartphone\" onclick=\"document.getElementById('inputField').style.display = 'block';\">Smart Phones</a></li>"+
"                        <li><a href=\"http://localhost:8999/A2/tablet\">Tablets</a></li>"+
"                        <li><a href=\"http://localhost:8999/A2/laptop\">Laptop</a></li>"+
"                        <li><a href=\"http://localhost:8999/A2/tv\">TV</a></li>"+
"                    </ul>"+
"                </li>"+
"                </br></br></br></br></br></br></br></br></br></br></br></br></br></br>"+
"                "+
"               "+
"                "+
"            </ul>"+
"		"+
"        </aside>"+
"    	<div class=\"clear\"></div>"+
"    </div>"+
"    <footer>"+
"       "+
"        <div class=\"footer-bottom\">"+
"            <p>© BestDeal 2013. <a href=\"http://zypopwebtemplates.com/\">www.bestdeal.com</a></p>"+
"         </div>"+
"    </footer>"+
"</div>"+
"</body>"+
"</html>";
	
	out.println(myvar);
	System.out.println("Hey!!!!!33333333443");
	out.println(footer);
	
	
	
	    /*   String docType =
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
	            "http://localhost:8999/A2/addtocart";
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
	 

	 out.println("</table>"); */
	}
	

        @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
					    HttpSession session = request.getSession();
    String user="user";
	ShoppingCart shoppingcartNum;
    synchronized(session) {
      user = session.getAttribute("username").toString();
	}
	
		
				     Product product;
                 
				   
				   
				   System.out.println("into home page get!!");

				   PrintWriter out = response.getWriter();
               			//    response.setContentType("text/html"); 
							System.out.println("Welcome user from session par "+user);
				//out.println("<H1>Hello from a Servlet!!!!!!! 4</h2>"); 
    
	 
	 HashMap<String, Product> res = BestDealSerializedDataStore.getProducts();
	 if (res.size() == 0) {
	      response.sendError(response.SC_NOT_FOUND,
	                         "Missing Items.");
	      return;
	    }
	 
	 
	  response.setContentType("text/html");
	  // response.setContentType("image/jpeg");
	  
	System.out.println("Hey!!!!! 11111");  

String myvar = "<!doctype html>"+
"<html>"+
"<head>"+
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
"<title>BestDeal</title>"+
"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
"<script>"+
"function myFunction() {"+
"    document.getElementById(\"demo\").innerHTML = \"Paragraph changed.\";"+
"}"+
"</script>"+
"<script src=\"myScript.js\"></script>"+
"</head>"+
"<body onload=\"init()\">"+
"<div id=\"container\">"+
"    <header>"+
"    	<h1><a href=\"/\">Best<span>Deal</span></a></h1>"+
"        <h2>Get the best products for the best price</h2>"+
"    </header>"+
"    <nav>"+
"    	<ul>"+
"        	<li class=\"start selected\"><a href=\"index.html\">Home</a></li>  "+
"            <li class=\"end\"><a href=\"http://localhost:8999/A2/trending\">Trending</a></li>"+
"            <li class=\"end\"><a href=\"ViewOrder.html\">View Order</a></li>"+
"            <li class=\"search\" style=\"color:black;\"> "+
"                Search site"+
"                    <ul>"+
"                        <li class=\"text\">"+
"                            <form method=\"get\" class=\"searchform\" action=\"#\" >"+
                             
"                                    <input type=\"text\" size=\"32\" value=\"\" name=\"searchId\" id=\"searchId\" onkeyup=\"doCompletion()\"/>"+
"<div id=\"auto-row\">"+
"<table id=\"complete-table\"  style=\"background-color:#648ed1\"></table>"+
"</div>"+
                                 
"                            </form> "+
"                        </li>"+
"                    </ul>"+
"            </li>"+
"            <div style=\"float:right\">"+
"            <li class=\"username\" ><a href=\"account.html\" style=\"color:black;\" id=\"username\"><b>User</b></a></li>"+
"            <script type=\"text/javascript\">"+
"                document.getElementById('username').text = \""+user+"\";"+
"            </script>"+
"            <li class=\"shoppingcart\" ><a href=\"shoppingcart.html\" style=\"color:black;\" id=\"shoppingcart\"><img src=\"shopping-cart.jpg\"/>0</a></li><li class=\"logout\" ><a href=\"index.html\" style=\"color:black;\" id=\"logout\"><b>Logout</b></a></li>"+

"            </div>"+
"        </ul>"+
"            "+
"    </nav>"+
"    <div id=\"body\" stlye=\"height: 100%;\">";
	
System.out.println("Hey!!!!!22222");

	
	
String footer = "<aside class=\"sidebar\">"+
"	"+
"            <ul>	"+
"               <li>"+
"                    <h4>Categories</h4>"+
"                    <ul>"+
"                        <li><a href=\"http://localhost:8999/A2/smartphone\" onclick=\"document.getElementById('inputField').style.display = 'block';\">Smart Phones</a></li>"+
"                        <li><a href=\"http://localhost:8999/A2/tablet\">Tablets</a></li>"+
"                        <li><a href=\"http://localhost:8999/A2/laptop\">Laptop</a></li>"+
"                        <li><a href=\"http://localhost:8999/A2/tv\">TV</a></li>"+
"                    </ul>"+
"                </li>"+
"                </br></br></br></br></br></br></br></br></br></br></br></br></br></br>"+
"                "+
"               "+
"                "+
"            </ul>"+
"		"+
"        </aside>"+
"    	<div class=\"clear\"></div>"+
"    </div>"+
"    <footer>"+
"       "+
"        <div class=\"footer-bottom\">"+
"            <p>© BestDeal 2013. <a href=\"http://zypopwebtemplates.com/\">www.bestdeal.com</a></p>"+
"         </div>"+
"    </footer>"+
"</div>"+
"</body>"+
"</html>";
	
	out.println(myvar);
	
	out.println(footer);
	
	
	
	    /*   String docType =
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
	            "http://localhost:8999/A2/addtocart";
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
	 

	 out.println("</table>"); */
	}
	

    
	 
   } 