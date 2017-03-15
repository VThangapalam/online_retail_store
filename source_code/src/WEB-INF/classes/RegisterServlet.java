  import java.io.*;
  import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;	
import java.util.*;
import java.io.*;

public class RegisterServlet extends HttpServlet {  // JDK 6 and above only
	private String title; 
	protected void setTitle(String title) {
		    this.title = title;
		  }
		  
		  

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
				   
				 HashMap<String, User>  registeredUsers = BestDealSerializedDataStore.getUsers();
				 	String uname = request.getParameter("uname");
		            String pword = request.getParameter("password");
					
					System.out.println("uname "+uname);
		System.out.println("pass "+pword);
				 if (registeredUsers.containsKey(uname)) {
             //key exists
             System.out.println("User already exists enter another username");
			              request.setAttribute("message","User already exists enter another username");
			              ServletContext sc = getServletContext();
						  sc.getRequestDispatcher("/registerform").forward(request, response);
						  
			 
                      }
					  
					  else {
						  System.out.println("adding user to mysql");
						  		
					System.out.println("sending uname "+uname);
		System.out.println("sending pass "+pword);
						  MySQLDataStoreUtilities.addUser(uname,pword);
						  System.out.println("  user in hashmap");
						  
						  User newuser = new User(uname,pword);	
						  
						  BestDealSerializedDataStore.users.put(uname,newuser);
						  System.out.println("User created successfully");
			              request.setAttribute("message","User created successfully"  );
						  ServletContext sc = getServletContext();
                          sc.getRequestDispatcher("/registerform").forward(request, response);
						  
						  
					  }
	}
	

      
    
	 
   } 