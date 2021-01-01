package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class HospitalsWindow extends JFrame {

	private AppWindow app;
	private JPanel contentPane;
	private JLabel title;
	private JLabel filterByExaminationCount;
	private JTextField minExaminationCount;
	private JTextField maxExaminationCount;
	private JLabel dash1;
	private JLabel filterByPositiveTestCount;
	private JTextField maxPositiveTestCount;
	private JTextField minPositiveTestCount;
	private JLabel dash3;
	private JComboBox<String> sortBy;
	private JButton showResults;
	private JButton back;
	private JTable table;
	private JButton createHospital;

	public HospitalsWindow(AppWindow app) {
		this.app = app;
		initialize();
	}

	private void initialize() {
		// Configure frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		// Create components
		title = new JLabel("HASTANE BILGISI");
		back = new JButton("< Geri");
		filterByExaminationCount = new JLabel("Muayene sayisina gore filtrele:");
		minExaminationCount = new JTextField();
		maxExaminationCount = new JTextField();
		dash1 = new JLabel("-");
		filterByPositiveTestCount = new JLabel("Pozitif sonuc sayisina gore filtrele:");
		minPositiveTestCount = new JTextField();
		maxPositiveTestCount = new JTextField();
		dash3 = new JLabel("-");
		showResults = new JButton("Sonuclari Goster");
		showResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Showtable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}

			public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

				ResultSetMetaData metaData = rs.getMetaData();

				// names of columns
				Vector<String> columnNames = new Vector<String>();
				int columnCount = metaData.getColumnCount();

				columnNames.add("ID");
				columnNames.add("Adi");
				columnNames.add("Sehir");

				// data of the table
				Vector<Vector<Object>> data = new Vector<Vector<Object>>();
				while (rs.next()) {
					Vector<Object> vector = new Vector<Object>();
					for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
						vector.add(rs.getObject(columnIndex));
					}
					data.add(vector);
				}

				return new DefaultTableModel(data, columnNames);

			}

			public void Showtable() throws SQLException {
				DbConnection.connect();

				String query = "select id,name,city from hospital ";

				if (maxExaminationCount.getText().length() > 0) {
					int maxexamination = Integer.parseInt(maxExaminationCount.getText());
					String query1 = "select h.id, h.name, h.city from examination e, hospital h where e.hospital_id = h.id group by h.id,h.name,h.city ";

					String addition = " having count(*) < " + maxexamination;
					query1 += addition;

					query = query + " intersect " + query1;
				}
				if (minExaminationCount.getText().length() > 0) {
					int minexamination = Integer.parseInt(minExaminationCount.getText());
					String query1 = "select h.id, h.name, h.city " + "from examination e, hospital h "
							+ "where e.hospital_id = h.id " + "group by h.id, h.name, h.city, e.hospital_id ";
					String addition = "having count(*) > " + minexamination;
					query1 += addition;

					query = query + " intersect " + query1;

				}
				if (minPositiveTestCount.getText().length() > 0) {
					int minpositive = Integer.parseInt(minPositiveTestCount.getText());
					String query1 = "select h.id, h.name, h.city  " + "from examination e, hospital h "
							+ "where e.hospital_id = h.id and e.test_result = true "
							+ "group by h.id, h.name, h.city, e.hospital_id having count(*) > " + minpositive;

					query = query + "intersect " + query1;

				}
				if (maxPositiveTestCount.getText().length() > 0) {
					int maxpositive = Integer.parseInt(maxPositiveTestCount.getText());
					String query1 = "select h.id, h.name, h.city  " + "from examination e, hospital h "
							+ "where e.hospital_id = h.id and e.test_result = true "
							+ "group by h.id, h.name, h.city, e.hospital_id having count(*) < " + maxpositive + " ";
					query = query + "intersect " + query1;
				}

				if ((minExaminationCount.getText().length() > 0
						&& Integer.parseInt(minExaminationCount.getText()) == 0)) {
					query += " UNION SELECT h.* FROM hospital h WHERE h.id NOT IN (SELECT hospital_id FROM examination)";
				}

				if ((minPositiveTestCount.getText().length() > 0
						&& Integer.parseInt(minPositiveTestCount.getText()) == 0)) {
					query += " UNION SELECT h.* FROM hospital h WHERE h.id NOT IN (SELECT hospital_id FROM examination WHERE test_result = true)";
				}

				switch (sortBy.getSelectedIndex()) {
				case 1:
					query += " order by id";
					break;
				case 2:
					query += " order by name";
					break;
				case 3:
					query += " order by city";
					break;
				}

				ResultSet rs = DbConnection.select(query);
				table.setModel(buildTableModel(rs));
				DbConnection.disconnect();
			}
		});
		sortBy = new JComboBox<String>();
		table = new JTable();
		createHospital = new JButton("Hastane ekle");
		JScrollPane scrollPane = new JScrollPane(table);

		// Configure components
		title.setFont(new Font("Calibri", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 712, 20);
		back.setBounds(9, 8, 69, 23);
		filterByExaminationCount.setBounds(10, 42, 200, 20);
		minExaminationCount.setBounds(10, 73, 32, 20);
		minExaminationCount.setColumns(10);
		maxExaminationCount.setColumns(10);
		maxExaminationCount.setBounds(66, 73, 32, 20);
		dash1.setBounds(52, 76, 4, 14);
		filterByPositiveTestCount.setBounds(522, 42, 200, 20);
		minPositiveTestCount.setColumns(10);
		minPositiveTestCount.setBounds(522, 73, 32, 20);
		maxPositiveTestCount.setColumns(10);
		maxPositiveTestCount.setBounds(578, 73, 32, 20);
		dash3.setBounds(564, 76, 4, 14);
		showResults.setBounds(372, 116, 350, 26);
		sortBy.setBounds(10, 116, 350, 26);
		scrollPane.setBounds(10, 167, 712, 267);
		createHospital.setBounds(10, 445, 712, 26);

		// Add components to panel
		contentPane.add(title);
		contentPane.add(back);
		contentPane.add(filterByExaminationCount);
		contentPane.add(minExaminationCount);
		contentPane.add(maxExaminationCount);
		contentPane.add(dash1);
		contentPane.add(filterByPositiveTestCount);
		contentPane.add(minPositiveTestCount);
		contentPane.add(maxPositiveTestCount);
		contentPane.add(dash3);
		contentPane.add(showResults);
		contentPane.add(sortBy);
		contentPane.add(scrollPane);
		contentPane.add(createHospital);

		// Add components to ComboBox
		sortBy.addItem("Lutfen Bir Siralama Olcutu Seciniz");
		sortBy.addItem("Hastane ID'sine Gore Sirala");
		sortBy.addItem("Hastane Ismine Gore Alfabetik Sirala ");
		sortBy.addItem("Sehre Gore Alfabetik Sirala");

		// Add button actions
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				app.getFrame().setVisible(true);
			}
		});

		createHospital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateHospitalWindow frame = new CreateHospitalWindow();
				frame.setVisible(true);
			}
		});
	}
}
