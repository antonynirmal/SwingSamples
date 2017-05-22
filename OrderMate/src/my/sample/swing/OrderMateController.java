package my.sample.swing;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OrderMateController implements ActionListener, KeyListener, MouseListener, ChangeListener {

	private JTextField textField = new JTextField(10);
	private JButton button = new JButton();
	OrderMateView view;
	OrderMateModel model;
	OrderTableModel tablemodel;

	public OrderMateController(OrderMateView view, OrderMateModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		textField = (JTextField) e.getSource();
		if ((key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9) || (key >= KeyEvent.VK_NUMPAD0 && key <= KeyEvent.VK_NUMPAD9)
				|| key == KeyEvent.VK_BACK_SPACE) {
			this.updateView();

		} else {
			JOptionPane.showMessageDialog(null, "Please enter a valid Quantity!");
			textField.setText("");
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void updateView() {

		if (view.qtyTextField.getText() != null) {
			model.setStrQty(view.qtyTextField.getText());
		}

	}

	@Override @SuppressWarnings({ "rawtypes", "unchecked" })
	public void mouseClicked(MouseEvent e) {
		Object object = e.getSource();
		button = (JButton) object;
		if (button.getText().equals("Add")) {
			// new JDialog(frame, "You are Selected").setVisible(true);
			model.setStrItem(view.itemCombo.getSelectedItem().toString());
			if(view.qtyTextField.getText().length()> 0 && !view.qtyTextField.getText().equals("0")) {
				model.setStrQty(view.qtyTextField.getText());
				model.setStrUnitPrice(model.getPrice());
				model.itemTotal();
				model.setStrTotal(model.getStrTotal());
				model.setTableRow();
				view.orderTableModel.addRow(model.getROW());
				view.orderTableModel.fireTableDataChanged();
				view.orderTable.setModel(view.orderTableModel);
				view.totalTextField.setText(view.orderTableModel.calculate());
				view.qtyTextField.setText("1");
			} else{
				this.setWarningMsg(OrderMateConstants.INVALID_QUANTITY);
				textField.setText("");
			}
			
		} else if (button.getText().equals("Order")) {
			int iRowCount = view.orderTable.getRowCount();
			ArrayList orderList = new ArrayList(iRowCount);
			if (iRowCount > 0) {
				for (int row = 0; row < iRowCount; row++) {
					ArrayList order = new ArrayList<>();
					for (int column = 0; column < view.orderTable.getColumnCount(); column++) {
						order.add(view.orderTable.getValueAt(row, column));
					}
					orderList.add(order);
				}
				view.dataSource.addOrder(orderList);
				this.setWarningMsg("Your Order is Placed Successfully!");
				view.orderTableModel.setRowCount(0);
				view.itemCombo.setSelectedIndex(0);
				view.qtyTextField.setText("1");
				view.totalTextField.setText("0.0");
			} else {
				this.setWarningMsg(OrderMateConstants.ORDER_WARNING);
			}
		} 
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setWarningMsg(String text) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane optionPane = new JOptionPane(text, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog("Info!");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void stateChanged(ChangeEvent e) {
		JPanel reportPanel = new JPanel();
		JTabbedPane reportPane = new JTabbedPane();
		reportPane = (JTabbedPane) e.getSource();
		reportPanel = (JPanel) reportPane.getSelectedComponent();
		if (reportPane != null && reportPanel.getName() != null && reportPanel.getName().equals("Report")) {
			ArrayList data = (ArrayList) view.dataSource.getOrders();
			ArrayList row;
			int i = 0;
			// this.setWarningMsg(data.size()+"");
			for (i = 0; i < data.size(); i++) {
				row = (ArrayList) data.get(i);
				model.strItem = (String) row.get(0);
				model.strQty = (String) row.get(1);
				model.dUnitPrice = (Double) row.get(2);
				model.dUnitTotal = (Double) row.get(3);
				model.setTableRow();
				view.reportTableModel.addRow(model.getROW());
			}
			model.setdTotal(Double.valueOf(view.reportTableModel.calculate()));
			// model.setdTotal((Double.valueOf(view.reportTableModel.calculate())));
			view.totalReportTextField.setText(model.getdTotal().toString());
			 data.clear();
		}

	}

}
