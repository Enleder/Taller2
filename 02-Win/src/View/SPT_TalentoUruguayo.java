package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SPT_TalentoUruguayo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JIFrame_a_InicioProgramaListParticipantes ventana_a;
	private JIFrame_b_AltaParticipante ventana_b;
	private JIFrame_c_ViewParticipanteListPerformances ventana_c;
	private JIFrame_d_AltaPerformance ventana_d;
	public static JDesktopPane desktopPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SPT_TalentoUruguayo frame = new SPT_TalentoUruguayo();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SPT_TalentoUruguayo() {
		// this.setLocationRelativeTo(null);
		try {
			setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SPT_TalentoUruguayo.class.getResource("/imagenes/Diapositiva1.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar Concurso = new JMenuBar();
		setJMenuBar(Concurso);

		JMenu mnInicio = new JMenu("Inicio");
		Concurso.add(mnInicio);
		getContentPane().setLayout(new CardLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new java.awt.Color(2, 49, 75));
		getContentPane().add(desktopPane, "name_256800534823722");
		desktopPane.setLayout(new GridLayout(0, 1, 0, 0));
		desktopPane.setBounds(100, 100, 780, 535);

		JPanelImageBg panelImageBg = new JPanelImageBg();
		desktopPane.add(panelImageBg);
		panelImageBg.setBorder(null);
		panelImageBg.setBackground(new Color(2, 49, 75));
		panelImageBg.setLayout(new CardLayout(0, 0));

		this.setTitle("Talento Uruguayo - SPT (Sistema de Participantes de TalentoUruguayo)");

		JMenuItem mntmPantallaPrincipalParticipantes = new JMenuItem("Pantalla Principal - Participantes");
		mntmPantallaPrincipalParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelImageBg.setVisible(false);
				desktopPane.remove(panelImageBg);
				cerrarTodoSiEstaInstanciadoYVisible();

				// if (ventana_a == null) {
				try {
					ventana_a = new JIFrame_a_InicioProgramaListParticipantes();
					ventana_a.setLocation(1, 1);
					desktopPane.add(ventana_a);
					// contentPane.add(ventana_a);
					ventana_a.show();
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				// } else {
				// ventana_a.show();
				// }
			}
		});
		mnInicio.add(mntmPantallaPrincipalParticipantes);

		JSeparator separator = new JSeparator();
		// separator.setBackground(new java.awt.Color(2, 49, 75));
		// separator.setForeground(new java.awt.Color(2, 49, 75));
		mnInicio.add(separator); // Una rayita separadora.
		JMenuItem mntmCloseAll = new JMenuItem("Volver a la pantalla inicial");
		mntmCloseAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// cerrarTodoSiEstaInstanciadoYVisible();
				desktopPane.removeAll();
				desktopPane.add(panelImageBg);
				panelImageBg.setVisible(true);

			}
		});
		mnInicio.add(mntmCloseAll);

	}

	public void imgSetVisible() {
		// lblNewLabel.setVisible(true);

	}

	public void cerrarTodoSiEstaInstanciadoYVisible() {

		if (ventana_a != null && ventana_a.isVisible()) {
			// ventana_a.dispose();// .setVisible(false);
			ventana_a = null;
		}
		if (ventana_b != null && ventana_b.isVisible()) {
			// ventana_b.dispose();// setVisible(false);
			ventana_b = null;
		}
		if (ventana_c != null && ventana_c.isVisible()) {
			// ventana_c.dispose();// setVisible(false);
			ventana_c = null;
		}
		if (ventana_d != null && ventana_d.isVisible()) {
			// ventana_d.dispose();// setVisible(false);
			ventana_d = null;
		}
		desktopPane.removeAll();
	}
}
