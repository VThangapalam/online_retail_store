import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	

public class TotalServlet extends HttpServlet  {  // JDK 6 and above only
 
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
	  HttpSession session = request.getSession();
    ShoppingCart cart;
    synchronized(session) {
      cart = (ShoppingCart)session.getAttribute("shoppingCart");			 
    //calculate totalPrice
	System.out.println("Total num of items"+ request.getParameter("numItems"));
	  cart.setNumOrdered(request.getParameter("itemID"), Integer.parseInt(request.getParameter("numItems")));
	}
	ServletContext servletContext = getServletContext();
RequestDispatcher requestDispatcher = servletContext
.getRequestDispatcher("/addtocart");
requestDispatcher.forward(request, response);
  }
   	 
   } 