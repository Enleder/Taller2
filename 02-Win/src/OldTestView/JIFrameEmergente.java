package OldTestView;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class JIFrameEmergente extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFrameEmergente frame = new JIFrameEmergente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JIFrameEmergente() {
		setBounds(100, 100, 450, 300);

		JLabel lblNewLabel = new JLabel("EMERGENTE");
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);

	}

}
