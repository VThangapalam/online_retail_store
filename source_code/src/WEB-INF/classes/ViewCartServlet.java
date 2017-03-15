import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	

public class ViewCartServlet extends HttpServlet {  // JDK 6 and above only
 
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
			
		
			   
	  HttpSession session = request.getSession();
	  
    // Whether or not the customer changed the order, show
    // order status.
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Status of Your Order";
  
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
"            <li><p id='numberOfitems' style=\"color:black;padding:30px 10px;\">0</p></li>"+
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
"        <h2 style=\"align:center\">Your Order Status</h2>"+
""+
"        <section id=\"content\" style=\"min-height:500px\">";
	
out.println(myvar);
  	  
    ShoppingCart cart =null;
    
      synchronized(session) {
      cart = (ShoppingCart)session.getAttribute("shoppingCart");
      if (cart == null) {
        cart = new ShoppingCart();
        session.setAttribute("shoppingCart", cart);
      }
      }
    synchronized(session) {
      List itemsOrdered = cart.getItemsOrdered();
      if (itemsOrdered.size() == 0) {
        out.println("<H2><I>No items in your cart...</I></H2>");
      } else {	
        // If there is at least one item in cart, show table
        // of items ordered.
        out.println
          ("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
           "<TR>\n" +
           "  <TH>Item Name<TH>Brand\n" +
           "  <TH>Unit Cost<TH>Number<TH>Total Cost");
        ItemOrder order;
        // Rounds to two decimal places, inserts dollar
        // sign (or other currency symbol), etc., as
        // appropriate in current Locale.
       // NumberFormat formatter = NumberFormat.getCurrencyInstance();
        // For each entry in shopping cart, make
        // table row showing ID, description, per-item
        // cost, number ordered, and total cost.
        // Put number ordered in textfield that user
        // can change, with "Update Order" button next
        // to it, which resubmits to this same page
        // but specifying a different number of items.
        for(int i=0; i<itemsOrdered.size(); i++) {
          order = (ItemOrder)itemsOrdered.get(i);
          out.println
         ("<TR>\n" +
             "  <TD>" + order.getItem().getName() + "\n" +
             "  <TD>" + order.getItem().getBrand()+" " + "\n" +
             "  <TD>" +
               (order.getUnitCost()) + "\n" +
             "  <TD>" +
             "<FORM method=\"post\" action=\"http://localhost:8999/A2/total\">\n" +  // Submit to current URL
             "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"\n" +
             "       VALUE=\"" + order.getItemID() + "\">\n" +
             "<INPUT TYPE=\"TEXT\" NAME=\"numItems\"\n" +
             "       SIZE=3 VALUE=\"" + 
             order.getNumItems() + "\">\n" +
             "<SMALL>\n" +
			 "<INPUT TYPE=\"SUBMIT\"\n "+
             "       VALUE=\"Update Order\">\n" +
             "</SMALL>\n" +
             "</FORM>\n" +
             "  <TD>" +
             (order.getTotalCost()));
        }
        String checkoutURL =
          response.encodeURL("http://localhost:8999/A2/showorder");
        // "Proceed to Checkout" button below table
        out.println
          ("</TABLE>\n" +
           "<FORM METHOD =\"post\" ACTION=\"" + checkoutURL + "\">\n" +
           "<BIG><CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Review Order\">\n" +
           "</CENTER></BIG></FORM>");
      }
    
	  
	  
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
   	 
   } 