import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SignInServlet extends HttpServlet {  // JDK 6 and above only
 
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
 
      Connection conn = null;
      Statement stmt = null;
      try {
		  Class.forName("com.mysql.jdbc.Driver");
         // Step 1: Allocate a database Connection object
         conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bestdeal?useSSL=false", "root", "root"); // <== Check!
            // database-URL(hostname, port, default database), username, password
 
         // Step 2: Allocate a Statement object within the Connection
         stmt = conn.createStatement();
 
         // Step 3: Execute a SQL SELECT query
         
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");	
		String pword = request.getParameter("password");
 
		String sqlStr = "INSERT INTO bestdeal.Users values('"+fname+"','"+lname+"','"+email+"','"+pword+"','customer')";
		stmt.executeUpdate(sqlStr);  // Send the query to the server
         // Print an HTML page as the output of the query
        out.println("<h3>Inserted into table check Db</h3>");
       
        System.out.println("<p>fname: " + fname+ "</p>");
        System.out.println("<p>lname: " + lname + "</p>");
		
 
         // Step 4: Process the query result set
        
         
        } catch (Exception ex) {
           ex.printStackTrace();
		   System.out.println(ex.getMessage());
        }
     }
	 @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response){
	   
   }
	 
   }
