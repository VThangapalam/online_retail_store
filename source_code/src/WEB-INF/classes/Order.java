import java.util.List;
import java.util.Date;
public class Order implements java.io.Serializable{
	
	public Order(String orderNum, List<ItemOrder> itemsOrdered,String user,String status,String deliveryDate) {
		super();
		this.orderNum = orderNum;
		this.itemsOrdered = itemsOrdered;
		this.user=user;
	}
	
	private String orderNum;
	private List itemsOrdered;
		private String user;
			private String status;
			private String deliveryDate;

	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
		public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
		public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
		
			public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public List getItemsOrdered() {
		return itemsOrdered;
	}
	public void setItemsOrdered(List itemsOrdered) {
		this.itemsOrdered = itemsOrdered;
	}

}
