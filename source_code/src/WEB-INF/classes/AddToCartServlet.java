import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	

public class AddToCartServlet extends HttpServlet {  // JDK 6 and above only
 
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
	  HttpSession session = request.getSession();
	  
	  
	  
 	
		
System.out.println("In add to cart Servlet!!");
System.out.println("product !"+request.getParameter("productname"));
System.out.println("price !"+request.getParameter("price"));
	System.out.println("id !"+request.getParameter("id"));	
  System.out.println("id !"+request.getParameter("cat"));  		 
	  //HttpSession session = request.getSession();
	  
	  
	  
    ShoppingCart cart;
    synchronized(session) {
      cart = (ShoppingCart)session.getAttribute("shoppingCart");
      if (cart == null) {
        cart = new ShoppingCart();
        session.setAttribute("shoppingCart", cart);
      }
      String itemID = request.getParameter("id");
      if (itemID != null) {
      String numItemsString =null;
        try{ 
          numItemsString =
          request.getParameter("numItems");
          }
          catch(Exception e) {

          }
        if (numItemsString == null) {

          cart.addItem(itemID);
        } else {
              int numItems;
          try {
            numItems = Integer.parseInt(numItemsString);
          } catch(NumberFormatException nfe) {
            numItems = 1;
          }
          cart.setNumOrdered(itemID, numItems);
        }
      }
    }

request.setAttribute("cat",request.getParameter("cat"));
RequestDispatcher dispatch=request.getRequestDispatcher("/home");
dispatch.forward(request,response);
    
  }
   	 
   } 