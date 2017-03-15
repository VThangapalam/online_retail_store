  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class CheckoutServlet extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }


	static List itemsOrdered = new ArrayList <>();
	static double totalcost=0;
  
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
	    HttpSession session = request.getSession();
	 ShoppingCart shoppingcartNum;
    synchronized(session) {
      
	  shoppingcartNum = (ShoppingCart)session.getAttribute("shoppingCart");
	}
	String snum = "0";
	if (shoppingcartNum.getItemsOrdered().size()==0 || shoppingcartNum.equals(null))
	{
		snum="0";
	}
	
	else 
	{
		snum = Integer.toString(shoppingcartNum.getItemsOrdered().size());
	}
				   
				   
				   System.out.println("into checkoutServlet");
				   
				  
    ShoppingCart cart;
    synchronized(session) {
      cart = (ShoppingCart)session.getAttribute("shoppingCart");
	   itemsOrdered = cart.getItemsOrdered();
	  }
    ItemOrder order;

    for(int i=0; i<itemsOrdered.size(); i++) {
        order = (ItemOrder)itemsOrdered.get(i);
        totalcost = totalcost + order.getTotalCost();
    }
    System.out.println("the total cost is "+totalcost);
    PrintWriter out = response.getWriter();
    response.setContentType("text/html"); 
						
	String myvar = "<!doctype html>"+
"<html>"+
"<head>"+
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
"<title>BestDeal</title>"+
"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
"</head>"+
"<body>"+
"<div id=\"container\">"+
"    <header>"+
"        <h1><a href=\"/\">Best<span>Deal</span></a></h1>"+
"        <h2>Get the best products for the best price</h2>"+
"    </header>"+
"    <nav>"+
"        <ul>"+
"            <li class=\"start selected\"><a href=\"index.html\">Home</a></li>  "+
"            <li class=\"end\"><a href=\"contact.html\">Contact</a></li>"+
"            <li class=\"end\"><a href=\"ViewOrder.html\">View Order</a></li>"+
"            <li class=\"search\" style=\"color:black;\"> "+
"                Search site"+
"                    <ul>"+
"                        <li class=\"text\">"+
"                            <form method=\"get\" class=\"searchform\" action=\"#\" >"+
"                                <p>"+
"                                    <input type=\"text\" size=\"32\" value=\"\" name=\"s\" class=\"s\" />"+
"                                    "+
"                                </p>"+
"                            </form> "+
"                        </li>"+
"                    </ul>"+
"            </li>"+
"            <div style=\"float:right\">"+
"            <li class=\"username\" ><a href=\"account.html\" style=\"color:black;padding-left:-60px\" id=\"username\"><b>"+session.getAttribute("username")+"</b></a></li>"+

"            <li class=\"shoppingcart\" ><a href=\"shoppingcart.html\" style=\"color:black; position: relative; \" id=\"shoppingcart\"><img src=\"shopping-cart.jpg\"/></a></li>"+
"            <li><p id='numberOfitems' style=\"color:black;padding:30px 10px;\">"+snum+"</p></li>"+
"            <li ><a href=\"index.html\">Logout</a></li>"+
""+
"            </div>"+
"        </ul>"+
"            "+
"    </nav>"+
"    <div id=\"body\" stlye=\"height: 100%;\">"+
""+
"        <form action=\"index.html\">"+
"            <input type=\"submit\" name=\"submit1\" value=\"Go back\">"+
"        </form>"+
"        <section id=\"content\" style=\"min-height:500px\">";
	
out.println(myvar);
out.println("<h4>Bill Ammount : "+totalcost+"<h4>");
out.println("</br></br></br>");
String myvar1=
"<form method=\"post\" style=\"text-align: center\" action=\"http://localhost:8999/A2/success\">"+
"<input type=\"text\" name=\"name\" placeholder=\"Full Name\">"+
"<input type=\"text\" name=\"address\" placeholder=\"Address\">"+
"<input type=\"number\" name=\"card\" placeholder=\"Credit Card No\">"+
"<input type=\"password\" name=\"card\" placeholder=\"CVV\">"+
"<input class=\"submit\" type=\"submit\" value=\"Make Payment\">"+
"</form>"+
"</body>"+
"</html>";
	

out.println(myvar1);
		  
String myvar2 = "</section>"+
"        "+
"     "+
"        <div class=\"clear\"></div>"+
"    </div>"+
"    <footer>"+
"       "+
"        <div class=\"footer-bottom\">"+
"            <p>© BestDeal 2013. <a href=\"http://zypopwebtemplates.com/\">www.bestdeal.com</a></p>"+
"         </div>"+
"    </footer>"+
"</div>"+
"</body>"+
""+
"</html>";
	
out.println(myvar2);

   }
	

      
    
	 
   } 