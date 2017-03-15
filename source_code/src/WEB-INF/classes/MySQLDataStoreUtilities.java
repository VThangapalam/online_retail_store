
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class MySQLDataStoreUtilities{


static Connection conn = null;
static Statement stmt = null;

public static void createDatabaseandTables()
{
	
	System.out.println("Create database and tables");
	    
		 
      try {
		  Class.forName("com.mysql.jdbc.Driver");
         // Step 1: Allocate a database Connection object
         conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306?useSSL=false", "root", "root"); // <== Check!
            // database-URL(hostname, port, default database), username, password
 
     
      stmt = conn.createStatement();
      
      String sql = "CREATE DATABASE IF NOT EXISTS BestDealDB";
      stmt.executeUpdate(sql);
      System.out.println("Database created successfully...!!");
	  
	  String sqlUser = "create table IF NOT EXISTS bestdealdb.Users (username "+
	  "varchar(50),role varchar (50),"+
	  "passwrd varchar(50));";
	  stmt.executeUpdate(sqlUser);
      System.out.println("User table created successfully...");
	  
	  String sqlOrder = "create table IF NOT EXISTS bestdealdb.Orders("+
"orderid varchar(50),"+
"username varchar(50),"+
"total int(40),"+
"status varchar(20),"+
"orderdate Date"+
")";

	  stmt.executeUpdate(sqlOrder);
      System.out.println("Order table created successfully...");
	  
	  String sqlItem ="create table IF NOT EXISTS bestdealdb.OrderItems("+
"orderid varchar(50),"+
"itemid varchar(50),"+
"itemname varchar(200),"+
"itemprice varchar(50),"+
"quantity int(20),"+
"itemtotal int(50)"+
")";

  stmt.executeUpdate(sqlItem);
      System.out.println("Item table created successfully...");

	  
	  //create product table 
	  String productSQl="create table IF NOT EXISTS bestdealdb.product ("+
"id varchar(100) primary key,"+
"category varchar(100),"+
"name varchar(200),"+
"image varchar(500),"+
"brand varchar(200),"+
"brand_rebate varchar(200),"+
"price int(20)"+
")";


stmt.executeUpdate(productSQl);
      System.out.println("product table  created successfully...");



	
         
        } catch (Exception ex) {
           ex.printStackTrace();
		   System.out.println(ex.getMessage());
        }
		finally{
			 closeConnection() ;
		}

}

public static void addUser(String user,String password) {
	try {
		System.out.println("users !!");
		String usersql ="insert into bestdealdb.Users values('"+user+"','customer','"+password+"')";
		System.out.println("query !"+usersql);
		getConnectionStatement().executeUpdate(usersql);
		System.out.println("successfully inserted user into db");
		
		getUsers();
		
	} catch(Exception e) {
		System.out.println("Exception when adding the user "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

}


public static void addOrder(String orderid,String user,int total,String status,String dateStr){
	try {
		 
		String orderSql ="insert into bestdealdb.orders "+
		"values('"+orderid+"','"+user+"',"+total+",'"+status+"','"+dateStr+"')";
		System.out.println(orderSql);
		
		getConnectionStatement().executeUpdate(orderSql);
		
	}catch(Exception e) {
		System.out.println("Exception adding order "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

} 




public static void addOrderItem(String orderid,String itemid,String itemname,int price,int qty,int total){
	try {
		System.out.println("orderid "+ orderid+" itemid "+itemid+" itemname");
		String orderItemSql ="insert into bestdealdb.orderitems values"+
		"('"+orderid+"','"+itemid+"','"+itemname+"',"+price+","+qty+","+total+")";
		
		System.out.println(orderItemSql);
		
		getConnectionStatement().executeUpdate(orderItemSql);
	
	}catch(Exception e) {
		System.out.println("Exception adding orderItem "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

} 



public static void deleteOrder(String orderid){
	try {
		 
		String orderSql ="delete from bestdealdb.orders where orderid = '"+orderid+"'";
		System.out.println(orderSql);
		
		getConnectionStatement().executeUpdate(orderSql);
		
	}catch(Exception e) {
		System.out.println("Exception deleting order "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

} 


public static void deleteProductsTable(){
	try {
		 System.out.println("delete from bestdealdb.products");
		String productSql ="delete from bestdealdb.product";
		getConnectionStatement().executeUpdate(productSql);
		
		
	}catch(Exception e) {
		System.out.println("Exception deleting products "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

} 



public static void deleteOrderItems(String orderid){
	try {
		 
		String orderSql ="delete from bestdealdb.orderitems where orderid  "+
		"= '"+orderid+"'";
		System.out.println(orderSql);
		
		getConnectionStatement().executeUpdate(orderSql);
		
	}catch(Exception e) {
		System.out.println("Exception deleting orderitems "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

} 

public static Statement getConnectionStatement() {
	try {
				  Class.forName("com.mysql.jdbc.Driver");
         // Step 1: Allocate a database Connection object
         conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306?useSSL=false", "root", "root"); // <== Check!
            // database-URL(hostname, port, default database), username, password
 
     
      stmt = conn.createStatement();
	  return stmt;
	  
	} catch(Exception e) {
		System.out.println("Exception occured "+e.getMessage());
	}
	
	return null;
}

public static void closeConnection () {
	try {
		try{stmt.close();
		}catch(Exception e){
			
		}
		try {
			
			conn.close();}
		catch(Exception e){}
	} catch (Exception e){
		System.out.println("Except "+e.getMessage());
	}
}

public static void getUsers() {
	try {
		System.out.println("the users from table");
		String getUsersSQl = "select * from bestdealdb.users";
		  ResultSet rs = getConnectionStatement().executeQuery(getUsersSQl);
		  
		   while(rs.next()){
         //Retrieve by column name
         String uname  = rs.getString("username");
         String pword  = rs.getString("passwrd");
        

         //Display values
         System.out.print("uname: " + uname);
         System.out.println(", pword: " + pword);
		 User tempuser = new User(uname,pword);
		
		   BestDealSerializedDataStore.users.put(uname,tempuser);
		
        
      }
   
	}catch (Exception e) {
		System.out.println("exception "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

}

public static List<String> getMostSoldProducts() {
	  List<String> topsold =  new ArrayList<>();
	try {
		
		System.out.println("the users from table");
		String getUsersSQl = "select itemname, SUM(quantity) as qty from bestdealdb.orderitems group by(itemname) ORDER BY qty DESC limit 5";
		  ResultSet rs = getConnectionStatement().executeQuery(getUsersSQl);
		  
		   while(rs.next()){
			   
			   String temp="";
         //Retrieve by column name
         String itemname  = rs.getString("itemname");
         String rating  = rs.getString("qty");
         temp = temp+itemname+","+rating;
		 topsold.add(temp);

		 
        
      }
      
        rs.close();
		closeConnection() ;
		
	}catch (Exception e) {
		System.out.println("exception "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

	return topsold;
}



public static boolean checkIfUserCorrect(String user , String password) {
	System.out.println("into check user correct ");
	boolean  res =false;
	try {
		
		System.out.println("checking if user is correct..!");
		String getUsersSQl = "select * from bestdealdb.users where username='"+user+"' and passwrd ='"+password+"'";
		  ResultSet rs = getConnectionStatement().executeQuery(getUsersSQl);
		  
		   while(rs.next()){
			   res=true;
			   System.out.println("The user is correct");
         //Retrieve by column name
         String uname  = rs.getString("username");
         String pword  = rs.getString("passwrd");
        

         //Display values
         System.out.print("uname: " + uname);
         System.out.println(", pword: " + pword);
		 
      }
	 
      rs.close();
	  closeConnection() ;
	  return res;
		
	}catch (Exception e) {
		System.out.println("exception "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

	
	return res;
}


public static List<Product> getProductsFromDb() {
	  List<Product> products =  new ArrayList<>();
	try {
		
		System.out.println("the product from table");
		String getProductSQl = "select * from bestdealdb.product";
		  ResultSet rs = getConnectionStatement().executeQuery(getProductSQl);
		  
		   while(rs.next()){
			 
         //Retrieve by column name
         String id  = rs.getString("id");
         String category  = rs.getString("category");
		 String name  = rs.getString("name");
		 String image  = rs.getString("image");
		 String brand  = rs.getString("brand");
		 String brand_rebate  = rs.getString("brand_rebate");
		 int price  = rs.getInt("price");
         
		
		 System.out.println("name "+name);
		 Product temp = new Product(id,category,name,image,brand,price,brand_rebate);
		 
		 products.add(temp);
   rs.close();
  
      }
      
		
	}catch (Exception e) {
		System.out.println("exception "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

	return products;
}



public static Product getProductFromDb(String itemid) {
	 	   Product temp=null;
	try {
		
		System.out.println("the product from table");
		String getProductSQl = "select * from product where id ='"+itemid+"'";
		  ResultSet rs = getConnectionStatement().executeQuery(getProductSQl);
		  
		   while(rs.next()){
			 
         //Retrieve by column name
         String id  = rs.getString("id");
         String category  = rs.getString("category");
		 String name  = rs.getString("name");
		 String image  = rs.getString("image");
		 String brand  = rs.getString("brand");
		 String brand_rebate  = rs.getString("brand_rebate");
		 int price  = rs.getInt("price");
         
		
		 System.out.println("name "+name);
		  temp = new Product(id,category,name,image,brand,price,brand_rebate);
rs.close();
		 
		 
		
   
      }
     
		
	}catch (Exception e) {
		System.out.println("exception "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

	return temp;
}


public static void addProductsToDB(List<Product> products){
	System.out.println("into add prod in DB");
	try {
		for (Product prod : products) {
			try {
			String id = prod.getId();
			String name = prod.getName();
			String category = prod.getCategory();
			String image = prod.getImage();
			String brand = prod.getBrand();
			String brand_rebate = prod.getBrandRebate();
			int price = prod.getPrice();
			System.out.println(price);
			
				String productSQL ="insert into bestdealdb.product values('"+id+"','"+category+"','"+name+"','"+image+"','"+brand+"','"+brand_rebate+"','"+price+"')";
				System.out.println(productSQL);
		getConnectionStatement().executeUpdate(productSQL);
		System.out.println("added to db "+name);
		;
		
			}catch(Exception e){
				
			}
				finally{
			 closeConnection() ;
		}

		}
		
		
	}catch(Exception e) {
		System.out.println("Exception adding product "+e.getMessage());
	}
} 


public static boolean checkIfOrderUser(String user , String orderid) {
	System.out.println("into check user order correct ");
	boolean  res =false;
	try {
		
		System.out.println("checking if order is correct..!");
		String getUsersSQl = "select * from orders where orderid = '"+orderid+"' and username = '"+user+"'";
		  ResultSet rs = getConnectionStatement().executeQuery(getUsersSQl);
		  
		   while(rs.next()){
			   res=true;
	System.out.println("user is right");
		rs.close(); 
		closeConnection() ;
      }
      
	  return res;
		
	}catch (Exception e) {
		System.out.println("exception "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

	return res;
}


public static void addProduct(String id,String category,String name,String image,String brand,String brand_rebate,int price) {
	try {
		System.out.println("pr "+price);
		System.out.println("adding the product !!");
		String productSQL ="insert into bestdealdb.product values('"+id+"','"+category+"','"+name+"','"+image+"','"+brand+"','"+brand_rebate+"','"+price+"')";
		
		System.out.println(" "+productSQL);
		getConnectionStatement().executeUpdate(productSQL);
		System.out.println("successfully inserted product into db");
		closeConnection() ;
		getUsers();
		
	} catch(Exception e) {
		System.out.println("Exception when adding the user "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

}
	
	
	
	public static void updateProduct(String id,String category,String name,String image,String brand,String brand_rebate,int price) {
	try {
		System.out.println("pr "+price);
		System.out.println("updating the product !!");
		String productSQL ="update bestdealdb.product set category='"+category+"',name='"+name+"',image='"+image+"',brand='"+brand+"',price='"+price+"' where id = '"+id+"'";
		
		System.out.println(" "+productSQL);
		getConnectionStatement().executeUpdate(productSQL);
		System.out.println("successfully updated product into db");
		closeConnection() ;
		getUsers();
		
	} catch(Exception e) {
		System.out.println("Exception when adding the user "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

}
	

	public static void deleteProduct(String id) {
	try {
			String productSQL ="delete from bestdealdb.product where id = '"+id+"'";
		
		System.out.println(" "+productSQL);
		getConnectionStatement().executeUpdate(productSQL);
		System.out.println("successfully deleted product into db");
		closeConnection() ;
		getUsers();
		
	} catch(Exception e) {
		System.out.println("Exception when adding the user "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

}
	
	
	
	public static void loadOrderFromDBtoHM(){
		List<String> orderids = new ArrayList();
		List<String> userids = new ArrayList();
	try {
		 
		String orderSql ="select * from bestdealdb.orders ";
		System.out.println(orderSql);
		
	  ResultSet rs = getConnectionStatement().executeQuery(orderSql);
		  String user=null;
		  String id =null;
		  String time =null;
		  
		   while(rs.next()){
		    id = rs.getString("orderid");
		   user= rs.getString("username");
		   time = rs.getString("orderdate");
		   orderids.add(id);
		   userids.add(user);
		   
		   }
	closeConnection() ;
	    for (int j=0;j<orderids.size();j++) {
			List<ItemOrder> items = new ArrayList();
			String orderSqlItem = "select * from bestdealdb.orderitems where orderid='"+orderids.get(j)+"'";
			 rs = getConnectionStatement().executeQuery(orderSqlItem);
			   while(rs.next()){
			       String itemid = rs.getString("itemid");
				   String name = rs.getString("itemname");
				   int price = rs.getInt("itemprice") ;
				   Product prod = new Product(itemid,name,price);
				   int qty = rs.getInt("quantity");
				   ItemOrder item = new ItemOrder(prod,qty);
				   items.add(item);
				   
			   }
			   
			     Order orderObj = new Order(orderids.get(j),items,user,"Ordered",time);
   
			   BestDealSerializedDataStore.orders.put(orderids.get(j),orderObj);
		  closeConnection() ;
		}
		System.out.println(BestDealSerializedDataStore.orders.size());
		
	}catch(Exception e) {
		System.out.println("Exception adding order "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

} 


public static void loadUserFromDBtoHM(){
		List<String> orderids = new ArrayList();
		List<String> userids = new ArrayList();
	try {
		 
		String orderSql ="select * from bestdealdb.user ";
		System.out.println(orderSql);
		
	  ResultSet rs = getConnectionStatement().executeQuery(orderSql);
		  String user=null;
		  String id =null;
		  String time =null;
		  
		   while(rs.next()){
		    id = rs.getString("orderid");
		   user= rs.getString("username");
		   time = rs.getString("orderdate");
		   orderids.add(id);
		   userids.add(user);
		   
		   }
	closeConnection() ;
	    for (int j=0;j<orderids.size();j++) {
			List<ItemOrder> items = new ArrayList();
			String orderSqlItem = "select * from bestdealdb.orderitems where orderid='"+orderids.get(j)+"'";
			 rs = getConnectionStatement().executeQuery(orderSqlItem);
			   while(rs.next()){
			       String itemid = rs.getString("itemid");
				   String name = rs.getString("itemname");
				   int price = rs.getInt("itemprice") ;
				   Product prod = new Product(itemid,name,price);
				   int qty = rs.getInt("quantity");
				   ItemOrder item = new ItemOrder(prod,qty);
				   items.add(item);
				   
			   }
			   
			     Order orderObj = new Order(orderids.get(j),items,user,"Ordered",time);
   
			   BestDealSerializedDataStore.orders.put(orderids.get(j),orderObj);
		  closeConnection() ;
		}
		System.out.println(BestDealSerializedDataStore.orders.size());
		
	}catch(Exception e) {
		System.out.println("Exception adding order "+e.getMessage());
	}
		finally{
			 closeConnection() ;
		}

} 


}
	