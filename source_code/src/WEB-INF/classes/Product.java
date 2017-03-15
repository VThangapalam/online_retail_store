
import java.util.ArrayList;
import java.util.List;


public class Product implements java.io.Serializable{
    String category;
	String name;
    String id;
    String image;
    String brand;
	String brand_rebate;
    int price;
    List<String> accessories;
	
	
    public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public String getBrand() {
		return brand;
	}

		public String getBrandRebate() {
		return brand_rebate;
	}
	
	public int getPrice() {
		return price;
	}

	
	


    public Product(){
        accessories=new ArrayList<String>();
    }

void setId(String id) {
	this.id = id;
}

void setCategory(String category) {
	this.category = category;
}


void setImage(String image) {
	this.image = image;
}

void setBrand(String brand) {
	this.brand = brand;
}


void setBrandRebate(String brand_rebate) {
	this.brand_rebate= brand_rebate;
}

void setPrice(int price) {
	this.price = price;
}

List getAccessories() {
	return accessories;
}


void setName(String name) {
	this.name = name;
}

public Product(String id ,String category, String name,  String image, String brand, int price,
			List<String> accessories) {
		super();
		this.category = category;
		this.name = name;
		this.id = id;
		this.image = image;
		this.brand = brand;
		this.price = price;
		this.accessories = accessories;
	}
	
public Product(String id ,String category, String name,  String image, String brand, int price,List<String> accessories,String brand_rebate) {
		super();
		this.category = category;
		this.name = name;
		this.id = id;
		this.image = image;
		this.brand = brand;
		this.brand_rebate = brand_rebate;
		this.price = price;
		this.accessories = accessories;
	}	
	


public Product(String id ,String category, String name,  String image, String brand, int price,String brand_rebate) {
		super();
		this.category = category;
		this.name = name;
		this.id = id;
		this.image = image;
		this.brand = brand;
		this.brand_rebate = brand_rebate;
		this.price = price;
		
	}	
	

public Product(String id ,String name,int price) {
		super();
			this.name = name;
		this.id = id;
		
		this.price = price;
		
	}
	
}
