
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class AjaxUtilities{

public static HashMap<String , Product>  getData() {

HashMap<String,Product> hm = new HashMap<String,Product>();
 
 try {
	 String prodQuery = "select * from bestdealdb.product";
	 ResultSet rs =  MySQLDataStoreUtilities.getConnectionStatement().executeQuery(prodQuery);
 
 while(rs.next()){
         //Retrieve by column name
         String id  = rs.getString("id");
         String category  = rs.getString("category");
        String name  = rs.getString("name");
String image  = rs.getString("image");
String brand  = rs.getString("brand");
String brand_rebate  = rs.getString("brand_rebate");

int price = rs.getInt("price");

		 Product prod = new Product(id,category,name,image,brand,price,brand_rebate);
		
		   hm.put(id,prod);
		
        
      }
   
 
 }catch(Exception e) {
	System.out.println("Exception "+e.getMessage());
 }
 return hm;
}


public static StringBuffer readData(String targetId)
{
	StringBuffer sb = new StringBuffer();
	HashMap<String,Product> products = getData();
	try{
		
		 Iterator it = products.keySet().iterator();

                while (it.hasNext()) {
                    String id = (String) it.next();
                    Product product = (Product) products.get(id);

                    if ( // targetId matches first name
                         product.getName().toLowerCase().startsWith(targetId)) {

                        sb.append("<product>");
                        sb.append("<id>" + product.getId() + "</id>");
                        sb.append("<productName>" + product.getName() + "</productName>");
                        sb.append("</product>");
                       
                    }
                }
		return sb;
		
	}catch (Exception e) {
	
	}
	return sb;	
	
}


}