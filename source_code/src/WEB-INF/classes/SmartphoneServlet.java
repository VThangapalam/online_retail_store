import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class SmartphoneServlet extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }


  static List<Product> products;
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
				     System.out.println("in gogeet meth!!!");
					  Product product;
                 
				   
				   
				   System.out.println("into customerServlet");

				   PrintWriter out = response.getWriter();
               			//    response.setContentType("text/html"); 
							
				//out.println("<H1>Hello from a Servlet!!!!!!! 4</h2>"); 
     HttpSession session = request.getSession();
	 ShoppingCart shoppingcartNum=null;
	 String snum = "0";
	 
    synchronized(session) {
      try {
	  shoppingcartNum = (ShoppingCart)session.getAttribute("shoppingCart");
	  snum = Integer.toString(shoppingcartNum.getItemsOrdered().size());
	  }
	  catch (Exception e) {
		 
	  }
	}
	
	
		
	 HashMap<String, Product> res = BestDealSerializedDataStore.getSmartphones();
	 
	 if (res.size() == 0) {
	      response.sendError(response.SC_NOT_FOUND,
	                         "Missing Items.");
	      return;
	    }
	 
	 
	 
	 
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
"        <h1><a href=\"\">Best<span>Deal</span></a></h1>"+
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
""+
"            </div>"+
"        </ul>"+
"            "+
"    </nav>"+
"    <div id=\"body\" stlye=\"height: 100%;\">"+
""+
"        <form action=\"http://localhost:8999/A2/home\">"+
"            <input type=\"submit\" name=\"submit1\" value=\"Go back\">"+
"        </form>"+
""+
"        <section id=\"content\" style=\"min-height:500px\">";
	

	
	
	out.println(myvar);
	  

	    
	    Product item;
	    
	    
	    
	   for (Product val : res.values()) {
	       
	        item = val;
	        // Show error message if subclass lists item ID
	        // that's not in the catalog.
	        if (item == null) {
	          out.println("Unknown item");
	        } else {
				String url;
				try {
				url = item.getImage();
				System.out.println(url);
				if(url.equals("null"))
				{
					url="default.jpg";
				}
				}
catch (Exception e) {
	url="default.jpg";
}				
			

System.out.println("url !!!"+url);


			
	          out.println("<form method=\"post\" action =\"http://localhost:8999/A2/addtocart\">"+
"<img  style=\"width:100px;height:100px\" src=\""+url+"\">"+
"</br>"+
"<span name=\"prod\">"+item.getName()+"</span>"+
"</br>"+
"<span name=\"brand\">Brand: "+item.getBrand()+"</span>"+
"</br>"+
"<span name=\"price\">Price $"+item.getPrice()+"</span>"+
"</br>"+
"<INPUT TYPE=\"HIDDEN\" NAME=\"id\" " +
	             "       VALUE=\"" + item.getId() + "\">\n" +
"<button type=\"submit\">Addtocart</button>"+

"</form>");
out.println("<form method=\"get\" action=\"http://localhost:8999/A2/reviewForm\">");
out.println("<input type=\"hidden\" name =\"product\""+
"value =\""+item.getName()+"\">");
out.println("<input type=\"hidden\" name =\"category\""+
"value =\""+item.getCategory()+"\">");
out.println("<input type=\"hidden\" name =\"price\""+
"value =\""+item.getPrice()+"\">");
out.println("<input type=\"hidden\" name =\"brand\""+
"value =\""+item.getBrand()+"\">");
out.println("<input type=\"hidden\" name =\"brand_rebate\""+
"value =\""+item.getBrandRebate()+"\">");
out.println("<button type=\"submit\">Write A Review</button>");
out.println("</form>");
out.println("<form method =\"post\" action=\"http://localhost:8999/A2/viewReview\">");
out.println("<input type=\"hidden\" name =\"product\""+
"value =\""+item.getName()+"\">");
out.println("<input type=\"hidden\" name =\"category\""+
"value =\""+item.getCategory()+"\">");
out.println("<input type=\"hidden\" name =\"price\""+
"value =\""+item.getPrice()+"\">");
out.println("<input type=\"hidden\" name =\"brand\""+
"value =\""+item.getBrand()+"\">");
out.println("<input type=\"hidden\" name =\"brand_rebate\""+
"value =\""+item.getBrandRebate()+"\">");
out.println("<button type=\"submit\">View Reviews</button>");
out.println("</form></br></br></br>");
	        }
	      }
	   
	   
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