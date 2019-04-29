package OldTestView;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import View.JIFrame_a_InicioProgramaListParticipantes;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class frmContenedorPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JDesktopPane escritorio;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmContenedorPrincipal frame = new frmContenedorPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmContenedorPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(JFrameContenedorPrincipal.class.getResource("/imagenes/Diapositiva1.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Abrir Ventana Principal");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {

				try {
					JIFrame_a_InicioProgramaListParticipantes ventana_a;
					ventana_a = new JIFrame_a_InicioProgramaListParticipantes();
					ventana_a.setLocation(10, 10);

					escritorio.add(ventana_a);
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

			}
		});

		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		escritorio = new JDesktopPane();
		escritorio.setBounds(0, 0, 800, 600);
		contentPane.add(escritorio);
	}
}
