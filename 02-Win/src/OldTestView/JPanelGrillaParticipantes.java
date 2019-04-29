package OldTestView;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.border.CompoundBorder;

public class JPanelGrillaParticipantes extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable gridParticipantes;

	private Object[][] datosFilas = {

			{ "prueba", "prueba", "prueba", "prueba", "prueba" }, { "prueba", "prueba", "prueba", "prueba", "prueba" },
			{ "prueba", "prueba", "prueba", "prueba", "prueba" },
			{ "prueba", "prueba", "prueba", "prueba", "prueba" } };

	private String[] nombresColumnas = { "Nombre Artisitco", "Edad", "Nombre y Apellido", "Especialidad",
			"Departamento" };

	public JPanelGrillaParticipantes() {
		setLayout(new BorderLayout(0, 0));

		gridParticipantes = new JTable(datosFilas, nombresColumnas);

		// DefaultTableModel modelo = new DefaultTableModel();
		// JTable gridParticipantes = new JTable(datosFilas, nombresColumnas);

		gridParticipantes.setBorder(new CompoundBorder());

		add(gridParticipantes);

		// gridParticipantes.setModel(new DefaultTableModel(datosFilas,
		// nombresColumnas);

	}

}
