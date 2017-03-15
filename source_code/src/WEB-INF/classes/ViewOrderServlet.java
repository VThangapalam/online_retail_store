  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class ViewOrderServlet extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }


  static List<Product> products;
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
								System.out.println("View order servlet post");   
				    HttpSession session = request.getSession();
    String user="user";
    synchronized(session) {
      user = session.getAttribute("username").toString();
	}
	
	PrintWriter out = response.getWriter();
    response.setContentType("text/html"); 	
								 String myvar = "<html>"+
"<head>"+
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
"<title>BestDeal</title>"+
"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
"</head> 	"+
""+
"<body>"+
"<div id=\"container\" >"+
"    <header>"+
"        <h1><a href=\"/\">Best<span>Deal</span></a></h1>"+
"        <h2>Get the best products for the best price</h2>"+
"    </header>"+
"<div id=\"orderUpdate\" style=\"min-height:500px\">"+
"        <form action=\"index.html\">"+
"            <input type=\"submit\" name=\"submit1\" value=\"Go back\">"+
"        </form>";
out.println(myvar);

				
	String orderid = request.getParameter("orderid");
System.out.println("orderid "+orderid);	
		
		HashMap<String,Order> placed = BestDealSerializedDataStore.getOrders();
		if(placed.containsKey(orderid)) 
		{
			System.out.println("order present checking user");
			System.out.println("user checking is "+user);
			String orderowner= placed.get(orderid).getUser();
			if(orderowner.equals(user)) {
				System.out.println("correct user");
				Order ordObj = placed.get(orderid);
				List itemsOrdered = ordObj.getItemsOrdered();
				if(itemsOrdered.size()>0)
				{
				 


out.println("<div id =\"message\">Order Details : </div>"+
"<table style=\"align:center\">"+
"<TH>Item</TH>"+
"<TH>Quantity</TH>");

	

				 for(int i=0; i<itemsOrdered.size(); i++) {
					ItemOrder order = (ItemOrder)itemsOrdered.get(i);
                     System.out.println(order.getItem().getName());
					 System.out.println(order.getItem().getPrice());
					 System.out.println(order.getNumItems());
					
out.println ("<tr>"+
"<td><h3>"+order.getItem().getName()+"</h3></td>"+
"<td><form method=\"post\" action=\"http://localhost:8999/A2/vieworderupdate\">"+
"<input type=\"text\" name=\"qty\" value=\""+order.getNumItems()+"\">"+
"<input type=\"hidden\" name=\"itemId\" value=\""+order.getItemID()+"\"></input>"+
"<input type=\"hidden\" name=\"orderId\" value=\""+orderid+"\"></input>"+
"<input name=\"update\" type=\"submit\" value=\"Update Item\"></input></form></td>"+
"<td><form method=\"post\" action=\"http://localhost:8999/A2/vieworderdelete\">"+
"<input type=\"hidden\"  name=\"itemId\" value=\""+order.getItemID()+"\"></input>"+
"<input type=\"hidden\" name=\"orderId\" value=\""+orderid+"\"></input>"+
"<input name=\"delete\" type=\"submit\" value=\"Delete Item\"></input></form></td>"+
"</tr>");

				 
					}
					
					out.println("</table>");
					
				}//size > 0	
				else {
					
					
				}
			}
			
			else {
				out.println("<div id =\"message\">The user does not own this order</div>");
				System.out.println("The user does not own this order");
			}
		}
		else {
			System.out.println("No such order id");
			out.println("<div id =\"message\">The user does not own this order</div>");
		}
			
out.println("</div>"+
"</div>"+
"</body>"+
"</html>");			
					
	}

	
	
	@Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				System.out.println("View order servlet post");   
				    HttpSession session = request.getSession();
    String user="user";
    synchronized(session) {
      user = session.getAttribute("username").toString();
	}
	
	PrintWriter out = response.getWriter();
    response.setContentType("text/html"); 	
								 String myvar = "<html>"+
"<head>"+
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
"<title>BestDeal</title>"+
"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />"+
"</head> 	"+
""+
"<body>"+
"<div id=\"container\" >"+
"    <header>"+
"        <h1><a href=\"/\">Best<span>Deal</span></a></h1>"+
"        <h2>Get the best products for the best price</h2>"+
"    </header>"+
"<div id=\"orderUpdate\" style=\"min-height:500px\">"+
"        <form action=\"index.html\">"+
"            <input type=\"submit\" name=\"submit1\" value=\"Go back\">"+
"        </form>";
out.println(myvar);

				
	String orderid = request.getParameter("orderid");
System.out.println("orderid "+orderid);	
		
		HashMap<String,Order> placed = BestDealSerializedDataStore.getOrders();
		if(placed.containsKey(orderid)) 
		{
			System.out.println("order present checking user");
			System.out.println("user checking is "+user);
			String orderowner= placed.get(orderid).getUser();
			if(orderowner.equals(user)) {
				System.out.println("correct user");
				Order ordObj = placed.get(orderid);
				List itemsOrdered = ordObj.getItemsOrdered();
				if(itemsOrdered.size()>0)
				{
				 


out.println("<div id =\"message\">Order Details : </div>"+
"<table style=\"align:center\">"+
"<TH>Item</TH>"+
"<TH>Quantity</TH>");

	

				 for(int i=0; i<itemsOrdered.size(); i++) {
					ItemOrder order = (ItemOrder)itemsOrdered.get(i);
                     System.out.println(order.getItem().getName());
					 System.out.println(order.getItem().getPrice());
					 System.out.println(order.getNumItems());
					
out.println ("<tr>"+
"<td><h3>"+order.getItem().getName()+"</h3></td>"+
"<td><form method=\"post\" action=\"http://localhost:8999/A2/vieworderupdate\">"+
"<input type=\"text\" name=\"qty\" value=\""+order.getNumItems()+"\">"+
"<input type=\"hidden\" name=\"itemId\" value=\""+order.getItemID()+"\"></input>"+
"<input type=\"hidden\" name=\"orderId\" value=\""+orderid+"\"></input>"+
"<input name=\"update\" type=\"submit\" value=\"Update Item\"></input></form></td>"+
"<td><form method=\"post\" action=\"http://localhost:8999/A2/vieworderdelete\">"+
"<input type=\"hidden\"  name=\"itemId\" value=\""+order.getItemID()+"\"></input>"+
"<input type=\"hidden\" name=\"orderId\" value=\""+orderid+"\"></input>"+
"<input name=\"delete\" type=\"submit\" value=\"Delete Item\"></input></form></td>"+
"</tr>");

				 
					}
					
					out.println("</table>");
					
				}//size > 0	
				else {
					
					
				}
			}
			
			else {
				out.println("<div id =\"message\">No order with this id</div>");
				System.out.println("The user does not own this order");
			}
		}
		else {
			System.out.println("No such order id");
			out.println("<div id =\"message\">No order with this id</div>");
		}
			
out.println("</div>"+
"</div>"+
"</body>"+
"</html>");			
					
	}

    
	 
   } 