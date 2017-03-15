import java.util.*;
import java.io.*;
public class HashMapStore{


// The known platforms

	public static HashMap<String, Product> products = new HashMap<String, Product>();

	public static HashMap<String, Product> tablets= new HashMap<String, Product>();
	public static HashMap<String, Product> smartphones = new HashMap<String, Product>();
	public static HashMap<String, Product> laptops = new HashMap<String, Product>();
	public static HashMap<String, Product> tvs = new HashMap<String, Product>();
	
	
    public static HashMap<String, User> users = new HashMap<String,User>();
    public static HashMap<String,Order> orders = new  HashMap<String,Order>();


	


// polulate the data for the different platforms into HashMaps

    public static void populateSerializedDataStore(){
 
	populateProductTablet();

    }



// Polulate the data for Microsoft into HashMap

static void populateProductTablet(){
System.out.println("into products poulate*****");
	List<Product> res = SaxParser4BestDealXMLdataStore.getProducts();
	for (Product p:res) {
		products.put(p.getId(), p);
		if(p.category.equals("Smartphone"))
		{
		    	smartphones.put(p.getId(),p);
		}
		if(p.category.equals("Tablet"))
		{
		    	tablets.put(p.getId(),p);
		}
		
		if(p.category.equals("Laptop"))
		{
		    	laptops.put(p.getId(),p);
		}
		
		if(p.category.equals("TV"))
		{
		    	tvs.put(p.getId(),p);
		}
		
		
	}


	
	
}




public static HashMap<String, Product> getProducts() {
	return products;
}


public static HashMap<String, User> getUsers() {
	return users;
}

	
public static HashMap<String, Order> getOrders() {
	return orders;
}


public static HashMap<String, Product> getSmartphones() {
	return smartphones;
}


public static HashMap<String, Product> getTablets() {
	return tablets;
}



public static HashMap<String, Product> getLaptops() {
	return laptops;
}



public static HashMap<String, Product> getTvs() {
	return tvs;
}

  public static Product getItem(String itemID) {
    	Product res = products.get(itemID);
		return res;
    	
         
    }
    

public static void setProducts(HashMap<String, Product> products) {
	BestDealSerializedDataStore.products = products;
}


static void addProduct(Product p) {
	products.put(p.getId(), p);
	
}




// Write the HashMaps into the File GameSpeedDataStore

static void  writeBestDealDataStore(){
//System.out.println("into witesBestDealDataStore");
    try{
/*File bestDealDataStore = new File("BestDealDataStoreOrder");
bestDealDataStore.createNewFile(); // if file already exists will do nothing 
FileOutputStream fos = new FileOutputStream(bestDealDataStore, false);
   // File bestDealDataStore=new File("BestDealDataStore");
	System.out.println("f.getAbsolutePath() = " + bestDealDataStore.getAbsolutePath());
    //FileOutputStream fos=new FileOutputStream(bestDealDataStore);
        ObjectOutputStream oos=new ObjectOutputStream(fos);

	
        oos.writeObject(orders);
        oos.flush();
        oos.close();
        fos.close();
		*/
	
    }catch(Exception e){
		System.out.println("Could NOT Write microsoft to BestDealDataStore ..."+e.getMessage());
	}

}


// Read the HashMaps from the File GameSpeedDataStore

static HashMap<String,Order> readBestDealDataStore() {

   
    try{
		System.out.println("into readBestDealDataStore");
 
        File bestDealDataStore=new File("BestDealDataStoreOrder");
        FileInputStream fis=new FileInputStream(bestDealDataStore);
        ObjectInputStream ois=new ObjectInputStream(fis);

        HashMap<String,Order> mapInFile=(HashMap<String,Order>)ois.readObject();

        ois.close();
        fis.close();
        
        for(Map.Entry<String,Order> m :mapInFile.entrySet()){
            	System.out.println(m.getKey());
				System.out.println("order id!!!! "+m.getKey());
				System.out.println("adding to hashmap ");
				orders.put(m.getKey(),m.getValue());
		Order c = m.getValue();
		System.out.println("\t Ordernum : "+c.getOrderNum());
		//System.out.println("\t Price : "+c.getPrice());
		//System.out.println("\t Accessories : ");
		List orderedItems = c.getItemsOrdered();
		 for(int i=0; i<orderedItems.size(); i++) {
			 ItemOrder item =  (ItemOrder)orderedItems.get(i);
System.out.println("name"+item.getProductName());
			 
		 }
                 
        }
    }catch(Exception e){}
return null;
}

//for User 
static void  writeBestDealDataStoreUser(){
System.out.println("into witesBestDealDataStore Users");
    try{
File bestDealDataStoreUser = new File("BestDealDataStoreUser");
bestDealDataStoreUser.createNewFile(); // if file already exists will do nothing 
FileOutputStream fos = new FileOutputStream(bestDealDataStoreUser, false);
   // File bestDealDataStore=new File("BestDealDataStore");
	System.out.println("f.getAbsolutePath() = " + bestDealDataStoreUser.getAbsolutePath());
    //FileOutputStream fos=new FileOutputStream(bestDealDataStore);
        ObjectOutputStream oos=new ObjectOutputStream(fos);

	
        oos.writeObject(users);
        oos.flush();
        oos.close();
        fos.close();
	
    }catch(Exception e){
		System.out.println("Could NOT Write microsoft to BestDealDataStore ..."+e.getMessage());
	}

}




static HashMap<String,User> readBestDealDataStoreUser() {

   
    try{
		System.out.println("into readBestDealDataStoreUser");
 
        File bestDealDataStore=new File("BestDealDataStoreUser");
        FileInputStream fis=new FileInputStream(bestDealDataStore);
        ObjectInputStream ois=new ObjectInputStream(fis);

        HashMap<String,User> mapInFile=(HashMap<String,User>)ois.readObject();

        ois.close();
        fis.close();
        
        for(Map.Entry<String,User> m :mapInFile.entrySet()){
            	System.out.println(m.getKey());
				System.out.println("username!!!! "+m.getKey());
				System.out.println("\t password : "+m.getValue());
				users.put(m.getKey(),m.getValue());
		         
        }
    }catch(Exception e){
		System.out.println("Could NOT Write  BestDealDataStoreUser ..."+e.getMessage());
	}
return null;
}


}