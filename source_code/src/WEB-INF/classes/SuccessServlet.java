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

public class SuccessServlet extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }
     

	static List itemsOrdered = new ArrayList <>();
	static double totalcost=0;
  
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
		// String orderNum = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
         String orderNum = ""+((int)(Math.random()*9000)+1000);
		 System.out.println(orderNum);
	     System.out.println("into SuccessServlet");
		  HttpSession session = request.getSession();
    ShoppingCart cart;
	String user;
    synchronized(session) {
	cart = (ShoppingCart)session.getAttribute("shoppingCart");
	user = session.getAttribute("username").toString();
	}
	int ordertotal =0;
	System.out.println("username in success page "+user);
	      List itemsOrdered = cart.getItemsOrdered();
		 for(int i=0; i<itemsOrdered.size(); i++) {
            ItemOrder order = (ItemOrder)itemsOrdered.get(i);
			Product pr = order.getItem();
			MySQLDataStoreUtilities.addOrderItem(orderNum,pr.getId(),pr.getName(),pr.getPrice(),order.getNumItems(),pr.getPrice()*order.getNumItems());
			ordertotal = ordertotal + pr.getPrice()*order.getNumItems();
			
			
		 }

		 
		 
		 Date mydate = new Date();
		  java.text.SimpleDateFormat sdf = 
        	     new java.text.SimpleDateFormat("YYYY-MM-dd");
        
        String currentTime = sdf.format(mydate);
		 Order orderObj = new Order(orderNum,itemsOrdered,user,"Ordered",currentTime);
		 MySQLDataStoreUtilities.addOrder(orderNum,user,ordertotal,"Ordered",currentTime);
		 
		 
		 
		 
		 	BestDealSerializedDataStore.orders.put(orderNum, orderObj);
 
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
"            <li class=\"end\"><a href=\"vieworder.html\">View Order</a></li>"+
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
""+
"        <section id=\"content\" style=\"min-height:500px\">";
	
out.println(myvar);

		
		out.println("<h2>Your order has been placed successful</h2>");
		out.println("<h3>Order Num "+orderNum+"</h3>");
		out.println("<h3>Delivery date "+getDeliveryDate()+"</h3>");
		
		out.println("</br>");
		out.println("</br>");
		out.println("</br>");
		
		out.println("<a href=\"http://localhost:8999/A2/home\">Continue Shopping</a>");


		  
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
	
	public String getDeliveryDate() {
		   String[] suffixes =
					  //    0     1     2     3     4     5     6     7     8     9
					     { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
					  //    10    11    12    13    14    15    16    17    18    19
					       "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
					  //    20    21    22    23    24    25    26    27    28    29
					       "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
					  //    30    31
					       "th", "st" };
			
			   Date today=new Date();
			   long ltime=today.getTime()+12*24*60*60*1000;
			   Date today8=new Date(ltime);
			   
			
			   
			   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			   //get current date time with Date()
			   
			   
			   
			   Date date = today8;
			    
			   DateFormat year= new SimpleDateFormat("YYYY");
			   //System.out.println(year.format(date));
			   
			   DateFormat week= new SimpleDateFormat("EEEE");
			   //System.out.println(week.format(date));
			   
			   DateFormat month= new SimpleDateFormat("MMMM");
			   //System.out.println(month.format(date));
			   
			   DateFormat day= new SimpleDateFormat("d");
			   //System.out.println(day.format(date));
			   int day3 = Integer.parseInt(day.format(date));
			   String day1 = day.format(date);
			   
			   
			   String dayStr = day1 + suffixes[day3];
			  
			  // System.out.println("daystr "+dayStr);
			
			   
			   String deliveryDate = week.format(date)+", "+month.format(date)+dayStr+", "+ year.format(date);
			   System.out.println(deliveryDate);
			   return deliveryDate;
	}
      
    
	 
   } 