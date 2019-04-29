package OldTestView;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.awt.BorderLayout;
import javax.swing.text.NumberFormatter;
import javax.swing.JButton;

public class testSoloNumeros extends JInternalFrame {

	private JFormattedTextField formattedTextField_Edad;
	private int edad = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testSoloNumeros frame = new testSoloNumeros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public testSoloNumeros() throws ParseException {

		setBounds(100, 100, 450, 300);

		NumberFormat formato = NumberFormat.getInstance();
		NumberFormatter formatear = new NumberFormatter(formato);
		formatear.setValueClass(Integer.class);
		formatear.setMinimum(1); // valor mínimo
		formatear.setMaximum(Integer.MAX_VALUE); // valor máximo
		formatear.setAllowsInvalid(false);
		// Si quieres comprobar que sea válido, cada vez que se pulse una tecla
		formatear.setCommitsOnValidEdit(true);

		// JFormattedTextField field = new JFormattedTextField(formatear);

		formattedTextField_Edad = new JFormattedTextField(formatear);
		// formattedTextField_Edad.setText("for");

		formattedTextField_Edad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// JFormattedTextField source = (JFormattedTextField) arg0.getSource();
				// Object value = source.getValue();
				// edad = (int) value;
			}
		});
		getContentPane().add(formattedTextField_Edad, BorderLayout.CENTER);

		JButton btnMostrarDatoFormatted = new JButton("MOSTRAR DATO FORMATTED");
		btnMostrarDatoFormatted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edad = 0;

				// JOptionPane.showMessageDialog(null, formattedTextField_Edad.getText(),
				// "error", JOptionPane.ERROR_MESSAGE);

				try {

					edad = (int) formattedTextField_Edad.getValue();
					mostrarDato();

				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "ingrese un numero", "error", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		getContentPane().add(btnMostrarDatoFormatted, BorderLayout.SOUTH);

	}

	public void mostrarDato() {
		int numeroAGuardar = (edad + 0);
		String textoAMostrar = "el valor del coso es= " + numeroAGuardar + " ?";
		getToolkit().beep();
		JOptionPane.showMessageDialog(null, textoAMostrar, "testing formatted", JOptionPane.INFORMATION_MESSAGE);
		getToolkit().beep();
		/////
	}
}
