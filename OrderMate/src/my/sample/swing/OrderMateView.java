package my.sample.swing;

import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class OrderMateView extends JFrame {

	OrderMateController controller;
	OrderMateModel model;
	OrderMateDataSource dataSource;
	public JTextField qtyTextField;
	public JTable orderTable;
	public OrderTableModel orderTableModel;
	public ReportTableModel reportTableModel;
	public JFrame frmOrdermateSample;
	public JTextField totalTextField;
	public JTable reportTable;
	public DecimalFormat priceFormat;
	public JTabbedPane tabbedPane;
	public JPanel orderPanel;
	public JLabel itemLabel;
	public JComboBox<String> itemCombo;
	public JLabel qtyLabel;
	public JButton addButton;
	public JScrollPane orderTableScrollPane;
	public JLabel totalLabel;
	public JButton orderButton;
	public GroupLayout orderPanelGroupLayout;
	public JPanel reportPanel;
	public JLabel lblTotal;
	public JTextField totalReportTextField;
	private JLabel salesReportLabel;

	public OrderMateView() {
		 model = new OrderMateModel();
		controller = new OrderMateController(this, model);
		dataSource = new OrderMateDataSource();
		initView();
	}

	public OrderMateView(OrderMateModel model) {
		this.model = model;
		initView();
	}

	/**
	 * Creates User Interface for the Order and Report Page.
	 */
	public void initView() {
		frmOrdermateSample = new JFrame();
		frmOrdermateSample.setTitle("Joey\u2019s Hamburger Barn");
		frmOrdermateSample.setBounds(100, 100, 500, 500);
		frmOrdermateSample.setLocationRelativeTo(null);
		frmOrdermateSample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		priceFormat = new DecimalFormat("###,##0.00");
		orderTableModel = new OrderTableModel();
		reportTableModel = new ReportTableModel();
		orderTable = new JTable();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		GroupLayout groupLayout = new GroupLayout(frmOrdermateSample.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE));
		{
			orderPanel = new JPanel();
			tabbedPane.addTab("Order", null, orderPanel, null);
			itemLabel = new JLabel("Item:");
			itemLabel.setFont(new Font("Arial", Font.BOLD, 13));
			itemCombo = new JComboBox<String>();
			itemCombo.setModel(new DefaultComboBoxModel<String>(OrderMateConstants.ITEM_LIST));
			qtyTextField = new JTextField();
			qtyTextField.setText("1");
			qtyTextField.setColumns(10);

			qtyLabel = new JLabel("Quantity:");
			qtyLabel.setFont(new Font("Arial", Font.BOLD, 13));
			addButton = new JButton("Add");
			
			
			
			/*addButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// new JDialog(frame, "You are Selected").setVisible(true);
					orderTableModel.setStrItem(itemCombo.getSelectedItem().toString());

					orderTableModel.setStrQty(qtyTextField.getText());
					orderTableModel.setStrUnitPrice(orderTableModel.getPrice());
					orderTableModel.itemTotal();
					orderTableModel.setStrTotal(orderTableModel.getStrTotal());
					orderTableModel.setTableRow();
					orderTableModel.addRow(orderTableModel.ROW);
					orderTableModel.fireTableDataChanged();
					orderTable.setModel(orderTableModel);
					totalTextField.setText(orderTableModel.calculate());

				}
			});*/
			orderTableScrollPane = new JScrollPane();

			totalLabel = new JLabel("Total");
			totalLabel.setFont(new Font("Arial", Font.BOLD, 13));

			totalTextField = new JTextField();
			totalTextField.setHorizontalAlignment(SwingConstants.RIGHT);
			totalTextField.setEditable(false);
			totalTextField.setColumns(10);

			orderButton = new JButton("Order");
			
			
			addButton.addMouseListener(controller);
			qtyTextField.addKeyListener(controller);
			orderButton.addMouseListener(controller);
			
			orderPanelGroupLayout = new GroupLayout(orderPanel);
			orderPanelGroupLayout.setHorizontalGroup(
				orderPanelGroupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(orderPanelGroupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(orderPanelGroupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(orderPanelGroupLayout.createSequentialGroup()
								.addComponent(itemLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(itemCombo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(qtyLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(qtyTextField, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(addButton)
								.addGap(6))
							.addComponent(orderTableScrollPane, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
							.addGroup(orderPanelGroupLayout.createSequentialGroup()
								.addComponent(totalLabel)
								.addGap(35)
								.addComponent(totalTextField, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
							.addComponent(orderButton))
						.addContainerGap(92, Short.MAX_VALUE))
			);
			orderPanelGroupLayout.setVerticalGroup(
				orderPanelGroupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(orderPanelGroupLayout.createSequentialGroup()
						.addGap(26)
						.addGroup(orderPanelGroupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(itemLabel)
							.addComponent(itemCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(qtyTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(qtyLabel)
							.addComponent(addButton))
						.addGap(37)
						.addComponent(orderTableScrollPane, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(orderPanelGroupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(totalTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(totalLabel))
						.addGap(18)
						.addComponent(orderButton)
						.addContainerGap(86, Short.MAX_VALUE))
			);

			orderTableScrollPane.setViewportView(orderTable);
			orderTable.setModel(orderTableModel);

			orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			orderTable.getColumnModel().getColumn(0).setPreferredWidth(200);
			orderTable.getColumnModel().getColumn(1).setPreferredWidth(100);
			orderTable.getColumnModel().getColumn(2).setPreferredWidth(100);
			orderTable.getColumnModel().getColumn(3).setPreferredWidth(100);
			orderTable.getColumn("Unit Price").setCellRenderer(new PriceRenderer(priceFormat));
			orderTable.getColumn("Total").setCellRenderer(new PriceRenderer(priceFormat));
			orderPanel.setLayout(orderPanelGroupLayout);
		}

		reportPanel = new JPanel();
		reportPanel.setName("Report");
		tabbedPane.addTab("Report", null, reportPanel, null);
		tabbedPane.addChangeListener(controller);
		JScrollPane reportScrollPane = new JScrollPane();
		
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Arial", Font.BOLD, 13));
		
		totalReportTextField = new JTextField();
		totalReportTextField.setEditable(false);
		totalReportTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		totalReportTextField.setColumns(10);
		totalReportTextField.setText(model.getdTotal().toString());
		
		salesReportLabel = new JLabel("Sales Report");
		salesReportLabel.setFont(new Font("Arial", Font.BOLD, 13));
		GroupLayout gl_reportPanel = new GroupLayout(reportPanel);
		gl_reportPanel.setHorizontalGroup(
			gl_reportPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_reportPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(reportScrollPane, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(gl_reportPanel.createSequentialGroup()
					.addContainerGap(316, Short.MAX_VALUE)
					.addComponent(lblTotal)
					.addGap(18)
					.addComponent(totalReportTextField, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(Alignment.LEADING, gl_reportPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(salesReportLabel)
					.addContainerGap(409, Short.MAX_VALUE))
		);
		gl_reportPanel.setVerticalGroup(
			gl_reportPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_reportPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(salesReportLabel)
					.addGap(13)
					.addComponent(reportScrollPane, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_reportPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal)
						.addComponent(totalReportTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(44, Short.MAX_VALUE))
		);

		reportTable = new JTable();
		reportScrollPane.setViewportView(reportTable);
		
		reportTable.setModel(reportTableModel);

		reportTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reportTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		reportTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		reportTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		reportTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		reportTable.getColumn("Unit Price").setCellRenderer(new PriceRenderer(priceFormat));
		reportTable.getColumn("Total").setCellRenderer(new PriceRenderer(priceFormat));
		
		reportPanel.setLayout(gl_reportPanel);
		frmOrdermateSample.getContentPane().setLayout(groupLayout);
		frmOrdermateSample.setVisible(true);
	}
}

@SuppressWarnings("serial")
class PriceRenderer extends DefaultTableCellRenderer {
	DecimalFormat formatter;

	PriceRenderer(String pattern) {
		this(new DecimalFormat(pattern));
	}

	PriceRenderer(DecimalFormat formatter) {
		this.formatter = formatter;
		setHorizontalAlignment(JLabel.RIGHT);
	}

	public void setValue(Object value) {
		setText((value == null) ? "" : formatter.format(((Double) value).doubleValue()));
	}
}
