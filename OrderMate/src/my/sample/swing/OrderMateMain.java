package my.sample.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class OrderMateMain extends JFrame {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new OrderMateMain();
				} catch (Exception e) {
					System.out.println("Unexpected Error Occured");
				}
			}
		});
	}

	/**
	 * Call OrderMateView
	 */
	public OrderMateMain() throws Exception{
		new OrderMateView();
	}
}