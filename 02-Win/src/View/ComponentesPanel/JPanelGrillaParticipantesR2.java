package View.ComponentesPanel;

import javax.swing.*;
import javax.swing.table.*;
import Controller.ControllerInicioProgramaListParticipantes;
import Exceptions.ParticipantesExceptions;
import Logic.Mensajes;
import ValueObjects.VOParticipante;
import View.JIFrame_c_ViewParticipanteListPerformances;
import View.SPT_TalentoUruguayo;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

public class JPanelGrillaParticipantesR2 extends JPanel {

	private static final long serialVersionUID = 1L;

	public JTable gridParticipantes;

	private static Object[][] datosFilas = {};

	private final static String nombresColumnas[] = { "Nombre Artisitco", "Edad", "Especialidad" };

	private TableModel modelo;

	public JPanelGrillaParticipantesR2() {
		setLayout(new BorderLayout(5, 5));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado simple de Participantes",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		cargarGrilla();

		// creamos la Table basados en el modelo de datos que hemos creado
		JTable table = new JTable(modelo);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (table.getSelectedRow() != -1) {
					// System.out.println( table.getValueAt(table.getSelectedRow(), 0) );
					String idAPasar = table.getValueAt(table.getSelectedRow(), 0).toString().trim();
					// System.out.println( idAPasar );
					// JOptionPane.showMessageDialog(null, table.getValueAt(table.getSelectedRow(),
					// 0), "Alerta",
					// JOptionPane.ERROR_MESSAGE);

					try {
						JIFrame_c_ViewParticipanteListPerformances ventana_c;
						ventana_c = new JIFrame_c_ViewParticipanteListPerformances(idAPasar);
						ventana_c.setLocation(0, 0);
						// frmContenedorPrincipal.escritorio.add(v2);
						SPT_TalentoUruguayo.desktopPane.removeAll();
						SPT_TalentoUruguayo.desktopPane.add(ventana_c);
						ventana_c.show();
						setVisible(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		// ordenacion de filas (por defecto, al ser tipos primitivos)
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);

		JLabel lblNewLabel = new JLabel(
				"*Haga clic en una linea de la grilla para ver detalle del Participante y/o agregarle una nueva Performance");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNewLabel, BorderLayout.SOUTH);
		table.setRowSorter(sorter);

		// creamos un scroll y le añadomos la tabla
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);

		this.add(scrollPane);
		this.setSize(200, 150);
		this.setVisible(true);
	}

	private void cargarGrilla() {

		try {
			ControllerInicioProgramaListParticipantes controllerlistar = new ControllerInicioProgramaListParticipantes();
			List<VOParticipante> lista = new ArrayList<VOParticipante>();
			lista = controllerlistar.ListarTodosLosParticipantes();
			if (lista.size() > 0) {

				datosFilas = new Object[lista.size()][3];

				for (int i = 0; i < lista.size(); i++) {

					datosFilas[i][0] = String.valueOf(lista.get(i).getNombreArtistico());
					datosFilas[i][1] = String.valueOf(lista.get(i).getEdad());
					datosFilas[i][2] = String.valueOf(lista.get(i).getEspecialidadArtistica());

				}

				// creamos el modelo con los datos
				modelo = new DefaultTableModel(datosFilas, nombresColumnas) {
					private static final long serialVersionUID = 1L;

					public Class<?> getColumnClass(int column) {
						return getValueAt(0, column).getClass();
					}
				};
			} else {
				JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTES_NOHAYNINGUNO, "Lista de Participantes",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (ParticipantesExceptions e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			// JOptionPane.showMessageDialog(null, Mensajes.M_PARTICIPANTE_ERROR, "Alerta",
			// JOptionPane.ERROR_MESSAGE);

		}

	}

}
