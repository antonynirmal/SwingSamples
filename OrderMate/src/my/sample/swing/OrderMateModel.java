package my.sample.swing;

public class OrderMateModel {
	
	public String strItem;
	public String strQty="0";
	public Double dUnitPrice;
	public Double dUnitTotal;
	public Double dTotal = 0.0;
	public static Object[] ROW = { "", "", new Double(0.0), new Double(0.0) };
	
	public Object[] getROW() {
		return ROW;
	}

	public void setROW(Object[] rOW) {
		ROW = rOW;
	}

	public Double getdTotal() {
		return dTotal;
	}

	public void setdTotal(Double dTotal) {
		this.dTotal = dTotal;
	}

	public String getStrItem() {
		return strItem;
	}

	public void setStrItem(String strItem) {
		this.strItem = strItem;
		this.setStrUnitPrice(getPrice());
	}

	public String getStrQty() {
		return strQty;
	}

	public void setStrQty(String strQty) {
		this.strQty = strQty;
	}

	public Double getStrUnitPrice() {
		return dUnitPrice;
	}

	public void setStrUnitPrice(Double dUnitPrice) {
		this.dUnitPrice = dUnitPrice;
	}

	public Double getStrTotal() {
		return dUnitTotal;
	}

	public void setStrTotal(Double dTotal) {
		this.dUnitTotal = dTotal;
	}

	public void itemTotal() {
		this.dUnitTotal = Integer.parseInt(this.strQty) * dUnitPrice;
		// calculate();
	}

	public void setTableRow() {
		ROW = new Object[] { strItem, strQty, dUnitPrice, dUnitTotal };
	}

	public Double getPrice() {
		Double dPrice = null;
		switch (strItem) {

		case OrderMateConstants.HAMBURGER:
			dPrice = OrderMateConstants.HAMBURGER_PRICE;
			break;
		case OrderMateConstants.HAMBURGER_NO_CHEESE:
			dPrice = OrderMateConstants.HAMBURGER_NO_CHEESE_PRICE;
			break;
		case OrderMateConstants.CHIPS:
			dPrice = OrderMateConstants.CHIPS_PRICE;
			break;
		case OrderMateConstants.COKES:
			dPrice = OrderMateConstants.COKES_PRICE;
		}
		return dPrice;
	}
	
}
