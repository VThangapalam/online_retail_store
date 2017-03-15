import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;

public class loginServlet extends HttpServlet {  // JDK 6 and above only
 
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   System.out.println("into login Servlet");
				   String uname =null;
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
 
      try {
		
		uname = request.getParameter("uname");
		String pword = request.getParameter("pword");
		ServletContext sc = getServletContext();
		
		
		if(uname.equals("sales") && pword.equals("sales")){
			
			
		System.out.println("in the login Servlet redirecting to  jsp");
		sc.getRequestDispatcher("/sales.html").forward(request, response);
		
		}
		
		if(uname.equals("manager") && pword.equals("manager")){
		System.out.println("in the login Servlet redirecting to manager html");
		
		
		sc.getRequestDispatcher("/manager.html").forward(request, response);
		
		}
		
		
		
  HashMap<String, User>  registeredUsers = BestDealSerializedDataStore.getUsers();
		 if (registeredUsers.containsKey(uname)) {
             //key exists
             System.out.println("User exists checking password");
			 String actualpass= registeredUsers.get(uname).getPassword();
			 if(actualpass.equals(pword)) 
			 {
				 System.out.println("correct username and password");
				   HttpSession session = request.getSession();
    
					synchronized(session) {
					session.setAttribute("username",uname);
						}
				 sc.getRequestDispatcher("/home").forward(request, response);
			 }
			 else {
				 System.out.println("Incorrect password!!");
				 request.setAttribute("mes1","Check UserName/Password");
				 sc.getRequestDispatcher("/loginform").forward(request, response);
				 
			 }
			 
		 }
		else {
			System.out.println("User does not exist please check username/ register");
			  
		}
		
		
			

   
         // Step 4: Process the query result set
        
         
        } catch (Exception ex) {
           ex.printStackTrace();
		   System.out.println(ex.getMessage());
        }
     }
	 
	 
	   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   System.out.println("into login Servlet");
				   String uname =null;
				   
				
				   
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
 
      try {
		
		uname = request.getParameter("uname");
		String pword = request.getParameter("pword");
		ServletContext sc = getServletContext();
		   if(uname.equals("sales") && pword.equals("sales")){
		System.out.println("in the login Servlet redirecting to  jsp");
		sc.getRequestDispatcher("/sales.html").forward(request, response);
		
		}
		
		if(uname.equals("manager") && pword.equals("manager")){
		System.out.println("in the login Servlet redirecting to manager jsp");
		sc.getRequestDispatcher("/manager.html").forward(request, response);
		
		}
  HashMap<String, User>  registeredUsers = BestDealSerializedDataStore.getUsers();
		 if (registeredUsers.containsKey(uname)) {
             //key exists
             System.out.println("User exists checking password");
			 String actualpass= registeredUsers.get(uname).getPassword();
			 if(actualpass.equals(pword)) 
			 {
				 System.out.println("correct username and password");
				 request.setAttribute("user",uname);
				 sc.getRequestDispatcher("/home").forward(request, response);
			 }
			 else {
				 System.out.println("Incorrect password!!");
				 request.setAttribute("mes1","Check UserName/Password");
				 sc.getRequestDispatcher("/Login.html").forward(request, response);
				 
			 }
			 
		 }
		else {
			System.out.println("User does not exist please check username/ register");
			request.setAttribute("mes1","Check UserName/Password");
			 sc.getRequestDispatcher("/Login.html").forward(request, response);
		}
		/*

		ServletContext sc = getServletContext();
		if(uname.equals("c1") && pword.equals("c1")){
		request.setAttribute("Name", uname );
		System.out.println(request.getAttribute("Name"));
		System.out.println("in the login Servlet redirecting to customer servlet  111 html file !!!");
		sc.getRequestDispatcher("/home").forward(request, response);
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/test2.html");
        //  dispatcher.forward( request, response ) ;

		
		}
		
		*/
		
			
		
		
   
         // Step 4: Process the query result set
        
         
        } catch (Exception ex) {
           ex.printStackTrace();
		   System.out.println(ex.getMessage());
        }
     }
	 
	 
   }
