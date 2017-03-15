  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateOrderServlet extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }
     


	static double totalcost=0;
  
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
System.out.println("int order update page");
			   
              String itemId = request.getParameter("itemId");
			     String orderId = request.getParameter("orderId");
				 //String qty1 = request.getParameter("qty");
				 int qty=Integer.parseInt(request.getParameter("qty"));
			  System.out.println("the product id is "+itemId);
			    System.out.println("the order id is "+orderId);
				System.out.println("the  qty is "+qty);
			  
			  
			  
			  HashMap<String,Order> placed = BestDealSerializedDataStore.getOrders();
			  
			  
			  
			  Order orderObj = placed.get(orderId);
			  List itemsOrdered = orderObj.getItemsOrdered();
			  List newList = new ArrayList <>(); 
			  //int qty = 5;
			   for(int i=0; i<itemsOrdered.size(); i++) {
                  ItemOrder item = (ItemOrder)itemsOrdered.get(i);	
				  ItemOrder newItem = null;
				  if((item.getItemID()).equals(itemId)) {
	                           if(qty>0) {
								  newItem = new ItemOrder(SaxParser4BestDealXMLdataStore.getItem(itemId),qty);
								  
							   }
							   
							   
                   }
				   else {
								   newItem = item;
					    }
			        newList.add(newItem);
					
			   
			   }
			  
			    String id = orderId;
				String user = orderObj.getUser();
				String status = orderObj.getStatus();
				String del = orderObj.getDeliveryDate();
				Order newOrderobj = new Order(id,newList,user,status,del);
				BestDealSerializedDataStore.orders.put(id, newOrderobj);
				
				try {
				
				MySQLDataStoreUtilities.deleteOrder(id);
				MySQLDataStoreUtilities.deleteOrderItems(id);
				int ordertotal =0;
					      
		 for(int i=0; i<newList.size(); i++) {
            ItemOrder order = (ItemOrder)newList.get(i);
			Product pr = order.getItem();
			MySQLDataStoreUtilities.addOrderItem(id,pr.getId(),pr.getName(),pr.getPrice(),order.getNumItems(),pr.getPrice()*order.getNumItems());
			ordertotal = ordertotal + pr.getPrice()*order.getNumItems();
				
		 }

		 
		 
		 
		 
		 Date mydate = new Date();
		  java.text.SimpleDateFormat sdf = 
        	     new java.text.SimpleDateFormat("YYYY-MM-dd");
        
        String currentTime = sdf.format(mydate);
	   MySQLDataStoreUtilities.addOrder(id,user,ordertotal,"Ordered",currentTime);
		 
			    }
				catch(Exception e) {
					System.out.println("Except "+e.getMessage());
				}

				
					
 ServletContext servletContext = getServletContext();
RequestDispatcher requestDispatcher = servletContext
.getRequestDispatcher("/ViewOrder.html");
requestDispatcher.forward(request, response);		
				

   }
	

      
    
	 
   } 