package OldTestView;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import Logic.IFachadaLogica;
import Logic.SystemProperties;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

public class JIFrameMantenerParticipantes extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IFachadaLogica iFachada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFrameMantenerParticipantes frame = new JIFrameMantenerParticipantes();

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
	public JIFrameMantenerParticipantes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 350);

		JPanelDatosParticipantes panelDatosParticipantes = new JPanelDatosParticipantes();
		getContentPane().add(panelDatosParticipantes, BorderLayout.NORTH);

		JpanelFotografia panelFotografiaParticipante = new JpanelFotografia();
		getContentPane().add(panelFotografiaParticipante, BorderLayout.CENTER);
		panelFotografiaParticipante.setLayout(new BoxLayout(panelFotografiaParticipante, BoxLayout.X_AXIS));

		JPanelGrillaParticipantes panelGrillaParticipantes = new JPanelGrillaParticipantes();
		getContentPane().add(panelGrillaParticipantes, BorderLayout.SOUTH);

		try {
			SystemProperties sp = new SystemProperties();
			String ip = sp.getIpServidor();
			String puerto = sp.getPuertoServidor();
			String nombreAPublicar = sp.getNombreAPublicar();
			String ruta = "//" + ip + ":" + puerto + "/" + nombreAPublicar;

			// accedo remotamente a la cuenta bancaria publicada en el servidor
			iFachada = (IFachadaLogica) Naming.lookup(ruta);

		} catch (Exception e) // si la ruta no esta bien formada
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
		}

		try {

			String NombreArtisitco = iFachada.TestPersistenceRMI();
			panelDatosParticipantes.setTxtNombreArtistico(NombreArtisitco);

		} catch (Exception ev) {
			JOptionPane.showMessageDialog(null, ev.getMessage(), "Alerta", JOptionPane.ERROR_MESSAGE);
		}

		JButton btnS = panelDatosParticipantes.getBtnSalir();
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});

	}

}
