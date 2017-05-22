package my.sample.swing;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class OrderTableModel extends DefaultTableModel {

	public static Object[][] DATA = { { "", "", null, null } };
	
	public static String[] TABLE_HEADER = { "Item", "Quantity", "Unit Price", "Total" };
	public String strItem;
	public String strQty;
	public Double dUnitPrice;
	public Double dUnitTotal;
	public Double dTotal;

	public OrderTableModel() {
		// super(OrderMateConstants.DATA, OrderMateConstants.TABLE_HEADER);
		super(TABLE_HEADER, 0);
	}

	

	public String calculate() {
		double total = 0.0;
		int iRowCount = this.getRowCount();
		for (int i = 0; i < iRowCount; i++) {
			total += ((Double) this.getValueAt(i, 3)).doubleValue();
		}
		return total + "";
	}

	
}