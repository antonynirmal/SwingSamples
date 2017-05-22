package my.sample.swing;

import java.util.ArrayList;
import java.util.List;

public class OrderMateDataSource {

	//protected static OrderMateDataSource instance;
	@SuppressWarnings("rawtypes")
	public List orderList;
	public OrderMateModel model;
	
	@SuppressWarnings("rawtypes")
	public OrderMateDataSource() {
		this.orderList = new ArrayList();
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addOrder(ArrayList orderList){
	
		this.orderList.addAll(orderList);
	}

	@SuppressWarnings("rawtypes")
	public List getOrders(){
		return this.orderList;
	}
	/*public static OrderMateDataSource getInstance(){
		if (instance == null){
			instance = new OrderMateDataSource();			
		}
		return instance;
	}*/
	
}
