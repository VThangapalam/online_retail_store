

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/** Servlet that displays a list of items being ordered.
 *  Accumulates them in an ArrayList with no attempt at
 *  detecting repeated items. Used to demonstrate basic
 *  session tracking. Updated to Java 5.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

@SuppressWarnings("unchecked")

public class ShowOrder extends HttpServlet {
	static List itemsOrdered = new ArrayList <>();
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
   
				    HttpSession session = request.getSession();
    ShoppingCart cart;
    synchronized(session) {
      cart = (ShoppingCart)session.getAttribute("shoppingCart");
	   itemsOrdered = cart.getItemsOrdered();
	  }
    if (itemsOrdered == null) {
     
    }
   
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
		
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Items Purchased";
    
	
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
"            <li class=\"username\" ><a href=\"account.html\" style=\"color:black;padding-left:-60px\" id=\"username\"><b>"+session.getAttribute("username").toString()+"</b></a></li>"+

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
"        <h2 style=\"align:center\">Review Your Order</h2>"+

""+
"        <section id=\"content\" style=\"min-height:500px\">";
	
	
	out.println(myvar);
	
	
    if (itemsOrdered.size() == 0) {
      out.println("<p id=\"message\">No Items</p>");
    } else {
      
       for(int i=0; i<itemsOrdered.size(); i++) {
		   ItemOrder order = (ItemOrder)itemsOrdered.get(i);
        out.println("  <h3> Item: " + order.getItem().getName()+"</h3>");
		out.println("<H> Quantity: "+ order.getNumItems()+"</h3>");
      }
      out.println("</br></br>");
    }
	out.println("<a href=\"http://localhost:8999/A2/checkout\">Proceed to checkout</a>");
    
	
		  
String myvar1 = "</section>"+
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
	
out.println(myvar1);
  }
}
