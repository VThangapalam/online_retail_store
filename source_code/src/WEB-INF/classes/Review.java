
public class Review {

	private String productname;
	private String productCategory;
	private String productPrice;
	private String retailerName;
	private String retailerZip;
	private String retailerCity;
	private String retailerState;
	private boolean productOnsale;
	private String manufacturerName;
	private String manufacturerRebate;
	private String userId;
	private String userAge;
	private String gender;
	private String occupation;
	private String rating;
	private String Date;
	private String reviewText;
	public Review()
	{
		
	}
		public Review(String productname, String productCategory, String productPrice, String retailerName,
			String retailerZip, String retailerCity, String retailerState, boolean productOnsale,
			String manufacturerName, String manufacturerRebate, String userId, String userAge, String gender,
			String occupation, String rating, String date, String reviewText) {
		this.productname = productname;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.retailerName = retailerName;
		this.retailerZip = retailerZip;
		this.retailerCity = retailerCity;
		this.retailerState = retailerState;
		this.productOnsale = productOnsale;
		this.manufacturerName = manufacturerName;
		this.manufacturerRebate = manufacturerRebate;
		this.userId = userId;
		this.userAge = userAge;
		this.gender = gender;
		this.occupation = occupation;
		this.rating = rating;
		Date = date;
		this.reviewText = reviewText;
	}
	
	public Review( Review rev)
			{
		
		this.productname = rev.getProductname();
		this.productCategory = rev.getProductCategory();
		this.productPrice = rev.getProductPrice();
		this.retailerName = rev.getRetailerName();
		this.retailerZip = rev.getRetailerZip();
		this.retailerCity = rev.getRetailerCity();
		this.retailerState = rev.getRetailerState();
		this.productOnsale = rev.isProductOnsale();
		this.manufacturerName = rev.getManufacturerName();
		this.manufacturerRebate = rev.getManufacturerRebate();
		this.userId = rev.getUserId();
		this.userAge = rev.getUserAge();
		this.gender = rev.getGender();
		this.occupation = rev.getOccupation();
		this.rating = rev.getRating();
		Date = rev.getDate();
		this.reviewText = rev.getReviewText();
	}
	
	
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public String getRetailerZip() {
		return retailerZip;
	}
	public void setRetailerZip(String retailerZip) {
		this.retailerZip = retailerZip;
	}
	public String getRetailerCity() {
		return retailerCity;
	}
	public void setRetailerCity(String retailerCity) {
		this.retailerCity = retailerCity;
	}
	public String getRetailerState() {
		return retailerState;
	}
	public void setRetailerState(String retailerState) {
		this.retailerState = retailerState;
	}
	public boolean isProductOnsale() {
		return productOnsale;
	}
	public void setProductOnsale(boolean productOnsale) {
		this.productOnsale = productOnsale;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getManufacturerRebate() {
		return manufacturerRebate;
	}
	public void setManufacturerRebate(String manufacturerRebate) {
		this.manufacturerRebate = manufacturerRebate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	
}
