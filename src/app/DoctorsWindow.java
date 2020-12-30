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
public class DoctorsWindow extends JFrame {

	private AppWindow app;
	private JPanel contentPane;
	private JLabel title;
	private JButton back;
	private JLabel filterByName;
	private JTextField firstName;
	private JTextField lastName;
	private JLabel filterByCity;
	private JTextField city;
	private JLabel filterByHospital;
	private JTextField hospital;
	private JLabel filterByExaminationCount;
	private JTextField minExaminationCount;
	private JTextField maxExaminationCount;
	private JLabel dash1;
	private JLabel filterByPositiveTestCount;
	private JTextField minPositiveTestCount;
	private JTextField maxPositiveTestCount;
	private JLabel dash2;
	private JComboBox<String> sortBy;
	private JButton showResults;
	private JTable table;
	private JButton createDoctor;
	private JButton updateDoctor;
	private JButton deleteDoctor;
	private JScrollPane scrollPane;

	public DoctorsWindow(AppWindow app) {
		this.app = app;
		initialize();
	}

	private void initialize() {
		// Configure frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		// Create components
		title = new JLabel("DOKTOR BILGISI");
		back = new JButton("< Geri");
		filterByName = new JLabel("Ad ve soyada gore filtrele");
		firstName = new JTextField();
		lastName = new JTextField();
		filterByCity = new JLabel("Sehre gore filtrele:");
		city = new JTextField();
		filterByHospital = new JLabel("Hastaneye gore filtrele:");
		hospital = new JTextField();
		filterByExaminationCount = new JLabel("Toplam muayene sayisina gore filtrele:");
		minExaminationCount = new JTextField();
		maxExaminationCount = new JTextField();
		dash1 = new JLabel("-");
		filterByPositiveTestCount = new JLabel("Pozitif sonuclu muayene sayisina gore filtrele:");
		minPositiveTestCount = new JTextField();
		maxPositiveTestCount = new JTextField();
		dash2 = new JLabel("-");
		showResults = new JButton("Sonuclari Goster");
		sortBy = new JComboBox<String>();
		sortBy.addItem("SGK numarasina gore sirala");
		sortBy.addItem("Ada gore sirala");
		sortBy.addItem("Soyada gore sirala");
		table = new JTable();
		createDoctor = new JButton("Doktor ekle");
		updateDoctor = new JButton("Doktor bilgilerini g�ncelle");
		deleteDoctor = new JButton("Doktoru sil");
		scrollPane = new JScrollPane(table);

		// Configure components
		title.setFont(new Font("Calibri", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 712, 20);
		back.setBounds(9, 8, 69, 23);
		filterByName.setBounds(10, 42, 200, 20);
		firstName.setBounds(10, 73, 88, 20);
		firstName.setColumns(10);
		lastName.setColumns(10);
		lastName.setBounds(108, 73, 88, 20);
		filterByCity.setBounds(266, 42, 212, 20);
		city.setColumns(10);
		city.setBounds(266, 73, 109, 20);
		filterByHospital.setBounds(522, 42, 200, 20);
		hospital.setColumns(10);
		hospital.setBounds(522, 73, 151, 20);
		showResults.setBounds(372, 198, 350, 26);
		sortBy.setBounds(10, 198, 350, 26);
		minExaminationCount.setColumns(10);
		minExaminationCount.setBounds(10, 151, 32, 20);
		filterByExaminationCount.setBounds(10, 120, 221, 20);
		dash1.setBounds(52, 154, 4, 14);
		maxExaminationCount.setColumns(10);
		maxExaminationCount.setBounds(66, 151, 32, 20);
		minPositiveTestCount.setColumns(10);
		minPositiveTestCount.setBounds(266, 151, 32, 20);
		filterByPositiveTestCount.setBounds(266, 120, 245, 20);
		maxPositiveTestCount.setColumns(10);
		maxPositiveTestCount.setBounds(322, 151, 32, 20);
		dash2.setBounds(308, 154, 4, 14);
		deleteDoctor.setBounds(494, 529, 228, 26);
		createDoctor.setBounds(10, 529, 228, 26);
		updateDoctor.setBounds(250, 529, 228, 26);
		scrollPane.setBounds(10, 251, 712, 267);

		// Add components to panel
		contentPane.add(title);
		contentPane.add(back);
		contentPane.add(filterByName);
		contentPane.add(firstName);
		contentPane.add(lastName);
		contentPane.add(filterByCity);
		contentPane.add(city);
		contentPane.add(filterByHospital);
		contentPane.add(hospital);
		contentPane.add(showResults);
		contentPane.add(sortBy);
		contentPane.add(minExaminationCount);
		contentPane.add(filterByExaminationCount);
		contentPane.add(dash1);
		contentPane.add(minPositiveTestCount);
		contentPane.add(maxExaminationCount);
		contentPane.add(createDoctor);
		contentPane.add(updateDoctor);
		contentPane.add(maxPositiveTestCount);
		contentPane.add(dash2);
		contentPane.add(deleteDoctor);
		contentPane.add(filterByPositiveTestCount);
		contentPane.add(scrollPane);

		// Add button actions
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				app.getFrame().setVisible(true);
			}
		});

		createDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CreateDoctorWindow frame = new CreateDoctorWindow();
					frame.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		showResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showResults();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		updateDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ssn_pkey = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
				String first_name = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
				String last_name = (String) table.getModel().getValueAt(table.getSelectedRow(), 2);
				String hospital_name = (String) table.getModel().getValueAt(table.getSelectedRow(), 3);
				
				try {
					UpdateDoctorWindow frame = new UpdateDoctorWindow(ssn_pkey, first_name, last_name, hospital_name);
					frame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		deleteDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDoctor((String) table.getModel().getValueAt(table.getSelectedRow(), 0));
			}
		});
	}

	public void showResults() throws SQLException {
		DbConnection.connect();

		String query = generateQueryFromFields();
		ResultSet rs = DbConnection.select(query);
		table.setModel(buildTableModel(rs));

		DbConnection.disconnect();
	}

	public String generateQueryFromFields() {
		String query = "SELECT * FROM doctor";

		// First name
		if (firstName.getText().length() > 0) {
			String newQuery = "SELECT * FROM doctor WHERE first_name = '" + firstName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}

		// Last name
		if (lastName.getText().length() > 0) {
			String newQuery = "SELECT * FROM doctor WHERE last_name = '" + lastName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}

		// City
		if (city.getText().length() > 0) {
			String newQuery = "SELECT d.* FROM doctor d, hospital h WHERE h.id = d.hospital_id AND h.city = '"
					+ city.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}

		// Hospital
		if (hospital.getText().length() > 0) {
			String newQuery = "SELECT d.* FROM doctor d, hospital h WHERE h.id = d.hospital_id AND h.name = '"
					+ hospital.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}

		// Min examination count
		if (minExaminationCount.getText().length() > 0 && minExaminationCount.getText().compareTo("0") != 0) {
			String newQuery = "SELECT d.* FROM (SELECT doc_ssn, count(*) FROM examination GROUP BY doc_ssn HAVING count(*) >= "
					+ minExaminationCount.getText() + ") q, doctor d WHERE q.doc_ssn = d.ssn";
			query = query + " INTERSECT " + newQuery;
		}

		// Max examination count
		if (maxExaminationCount.getText().length() > 0) {
			String newQuery = "SELECT * FROM doctor EXCEPT SELECT d.* FROM (SELECT doc_ssn, count(*) FROM examination GROUP BY doc_ssn HAVING count(*) >="
					+ maxExaminationCount.getText() + ") q, doctor d WHERE q.doc_ssn = d.ssn";
			query = query + " INTERSECT " + newQuery;
		}

		// Min positive test count
		if (minPositiveTestCount.getText().length() > 0 && minPositiveTestCount.getText().compareTo("0") != 0) {
			String newQuery = "SELECT d.* FROM (SELECT doc_ssn, count(*) FROM examination WHERE test_result = TRUE GROUP BY doc_ssn HAVING count(*) >=  "
					+ minPositiveTestCount.getText() + ") q, doctor d WHERE q.doc_ssn = d.ssn";
			query = query + " INTERSECT " + newQuery;
		}

		// Max positive test count
		if (maxPositiveTestCount.getText().length() > 0) {
			String newQuery = "SELECT * FROM doctor EXCEPT SELECT d.* FROM (SELECT doc_ssn, count(*) FROM examination WHERE test_result = TRUE GROUP BY doc_ssn HAVING count(*) >="
					+ maxPositiveTestCount.getText() + ") q, doctor d WHERE q.doc_ssn = d.ssn";
			query = query + " INTERSECT " + newQuery;
		}

		// Order by ...
		// SSN
		if (sortBy.getSelectedIndex() == 0)
			query += " ORDER BY ssn";
		// First name
		else if (sortBy.getSelectedIndex() == 1)
			query += " ORDER BY first_name";
		// Last name
		else
			query += " ORDER BY last_name";

		return query;
	}

	public void deleteDoctor(String ssn) {
		DbConnection.connect();
		DbConnection.update("DELETE FROM doctor WHERE ssn = '" + ssn + "'");
		DbConnection.disconnect();
	}

	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();

		int columnCount = metaData.getColumnCount();

		columnNames.add("SGK Numarasi");
		columnNames.add("Adi");
		columnNames.add("Soyadi");
		columnNames.add("Hastane");

		// data of the table
		DbConnection.connect();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				// If column is hospital id, set hospital name instead of id
				if (columnIndex == 4) {

					ResultSet hospital = DbConnection
							.select("SELECT * FROM hospital WHERE id = " + rs.getString(columnIndex));
					hospital.next();
					vector.add(hospital.getString("name"));

				} else
					vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		DbConnection.disconnect();
		return new DefaultTableModel(data, columnNames);
	}
}
