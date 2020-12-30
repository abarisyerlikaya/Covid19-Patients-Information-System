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
import java.math.BigDecimal;
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
public class PatientsWindow extends JFrame {

	private AppWindow app;
	private JPanel contentPane;
	private JLabel title;
	private JLabel filterByName;
	private JTextField firstName;
	private JTextField lastName;
	private JLabel filterByGender;
	private JLabel filterByTckn;
	private JTextField tckn;
	private JComboBox<String> sortBy;
	private JButton showResults;
	private JButton back;
	private JTable table;
	private JTextField minAge;
	private JTextField maxAge;
	private JButton updatePatient;
	private JLabel filterByCronicalIllnes;
	private JTextField cronicalIllnes;
	private JTextField examinationDay;
	private JLabel filterByExaminationDate;
	private JTextField examinationMonth;
	private JLabel filterByTestResult;
	private JLabel filterByDoctorName;
	private JComboBox<String> status;
	private JLabel filterByStatus;
	private JTextField hospital;
	private JLabel filterByHospital;
	private JLabel slash1;
	private JTextField examinationYear;
	private JLabel slash2;
	private JTextField city;
	private JLabel filterByCity;
	private JLabel dash1;
	private JLabel filterByAge;
	private JLabel filterByBloodType;
	private JButton deletePatient;
	private JButton createPatient;
	private JComboBox<String> bloodType;
	private JComboBox<String> gender;
	private JComboBox<String> testResult;
	private JTextField doctorLastName;
	private JTextField doctorFirstName;
	private JScrollPane scrollPane;

	public PatientsWindow(AppWindow app) {
		this.app = app;
		initialize();
	}

	private void initialize() {
		// Configure frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 32, 748, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		// Create components
		title = new JLabel("HASTA B�LG�S�");
		back = new JButton("< Geri");
		filterByName = new JLabel("Ad ve soyada g�re filtrele");
		firstName = new JTextField();
		lastName = new JTextField();
		filterByGender = new JLabel("Cinsiyete g�re filtrele:");
		filterByTckn = new JLabel("TCKN'ye g�re filtrele:");
		tckn = new JTextField();
		showResults = new JButton("Sonu�lar� G�ster");

		sortBy = new JComboBox<String>();
		table = new JTable();
		minAge = new JTextField();
		dash1 = new JLabel("-");
		filterByAge = new JLabel("Ya�a g�re filtrele:");
		filterByBloodType = new JLabel("Kan grubuna g�re filtrele:");
		deletePatient = new JButton("Hastay� sil");
		createPatient = new JButton("Hasta ekle");
		maxAge = new JTextField();
		updatePatient = new JButton("Hasta bilgilerini g�ncelle");
		filterByCronicalIllnes = new JLabel("Kronik hastal��a g�re filtrele:");
		filterByExaminationDate = new JLabel("Muayene tarihine g�re filtrele");
		examinationMonth = new JTextField();
		examinationDay = new JTextField();
		cronicalIllnes = new JTextField();
		filterByTestResult = new JLabel("Test sonucuna g�re filtrele:");
		filterByDoctorName = new JLabel("Doktor ad�na g�re filtrele:");
		filterByStatus = new JLabel("Durumuna g�re filtrele");
		status = new JComboBox<String>();
		hospital = new JTextField();
		filterByHospital = new JLabel("Muayene oldu�u hastaneye g�re filtrele:");
		slash1 = new JLabel("/");
		examinationYear = new JTextField();
		slash2 = new JLabel("/");
		city = new JTextField();
		filterByCity = new JLabel("�ehre g�re filtrele:");
		bloodType = new JComboBox<String>();
		gender = new JComboBox<String>();
		testResult = new JComboBox<String>();
		doctorLastName = new JTextField();
		doctorFirstName = new JTextField();
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
		filterByTckn.setBounds(266, 42, 212, 20);
		tckn.setColumns(10);
		tckn.setBounds(266, 73, 109, 20);
		filterByGender.setBounds(522, 42, 200, 20);
		showResults.setBounds(372, 290, 350, 26);
		sortBy.setBounds(10, 290, 350, 26);
		sortBy.addItem("TCKN'ye gore sirala");
		sortBy.addItem("Ada gore sirala");
		sortBy.addItem("Soyada gore sirala");
		sortBy.addItem("Cinsiyete gore sirala");
		sortBy.addItem("Dogum tarihine gore sirala");
		sortBy.addItem("Kan grubuna gore sirala");
		sortBy.addItem("Kronik hastaliga gore sirala");
		sortBy.addItem("Durumuna gore sirala");
		filterByBloodType.setBounds(266, 104, 245, 20);

		deletePatient.setBounds(494, 605, 228, 26);
		createPatient.setBounds(10, 605, 228, 26);
		updatePatient.setBounds(250, 605, 228, 26);
		filterByCronicalIllnes.setBounds(522, 104, 200, 20);
		cronicalIllnes.setColumns(10);
		cronicalIllnes.setBounds(522, 135, 151, 20);
		examinationDay.setColumns(10);
		examinationDay.setBounds(10, 197, 20, 20);
		filterByExaminationDate.setBounds(10, 166, 200, 20);
		examinationMonth.setColumns(10);
		examinationMonth.setBounds(52, 197, 20, 20);
		filterByTestResult.setBounds(266, 166, 212, 20);
		filterByDoctorName.setBounds(522, 166, 200, 20);

		status.setBounds(10, 259, 88, 20);
		status.addItem("Tumu");
		status.addItem("Test sonucu bekleniyor");
		status.addItem("Evde karantinada");
		status.addItem("Yurtta karantinada");
		filterByStatus.setBounds(10, 228, 200, 20);
		hospital.setColumns(10);
		hospital.setBounds(266, 259, 109, 20);
		filterByHospital.setBounds(266, 228, 212, 20);
		slash1.setBounds(38, 200, 4, 14);
		examinationYear.setColumns(10);
		examinationYear.setBounds(96, 197, 40, 20);
		slash2.setBounds(82, 200, 4, 14);
		city.setColumns(10);
		city.setBounds(522, 259, 151, 20);
		filterByCity.setBounds(522, 228, 200, 20);
		bloodType.setBounds(266, 134, 69, 22);
		bloodType.addItem("Tumu");
		bloodType.addItem("0-");
		bloodType.addItem("0+");
		bloodType.addItem("A-");
		bloodType.addItem("A+");
		bloodType.addItem("B-");
		bloodType.addItem("B+");
		bloodType.addItem("AB-");
		bloodType.addItem("AB+");
		gender.setBounds(522, 72, 69, 22);
		gender.addItem("Tumu");
		gender.addItem("Erkek");
		gender.addItem("Kadin");
		gender.addItem("Diger");
		testResult.setBounds(266, 196, 69, 22);
		testResult.addItem("Tumu");
		testResult.addItem("Negatif");
		testResult.addItem("Pozitif");
		doctorLastName.setColumns(10);
		doctorLastName.setBounds(607, 197, 75, 20);
		doctorFirstName.setColumns(10);
		doctorFirstName.setBounds(522, 197, 75, 20);
		minAge.setColumns(10);
		minAge.setBounds(10, 135, 32, 20);
		filterByAge.setBounds(10, 104, 221, 20);
		dash1.setBounds(52, 138, 4, 14);
		maxAge.setColumns(10);
		maxAge.setBounds(66, 135, 32, 20);
		scrollPane.setBounds(10, 327, 712, 267);

		// Add components to panel
		contentPane.add(title);
		contentPane.add(back);
		contentPane.add(filterByName);
		contentPane.add(firstName);
		contentPane.add(lastName);
		contentPane.add(filterByTckn);
		contentPane.add(tckn);
		contentPane.add(filterByGender);
		contentPane.add(showResults);
		contentPane.add(sortBy);
		contentPane.add(maxAge);
		contentPane.add(dash1);
		contentPane.add(minAge);
		contentPane.add(filterByAge);
		contentPane.add(filterByBloodType);
		contentPane.add(deletePatient);
		contentPane.add(updatePatient);
		contentPane.add(filterByCronicalIllnes);
		contentPane.add(cronicalIllnes);
		contentPane.add(createPatient);
		contentPane.add(examinationMonth);
		contentPane.add(examinationDay);
		contentPane.add(filterByExaminationDate);
		contentPane.add(filterByTestResult);
		contentPane.add(filterByDoctorName);
		contentPane.add(status);
		contentPane.add(filterByStatus);
		contentPane.add(doctorFirstName);
		contentPane.add(hospital);
		contentPane.add(filterByHospital);
		contentPane.add(doctorLastName);
		contentPane.add(city);
		contentPane.add(gender);
		contentPane.add(testResult);
		contentPane.add(slash1);
		contentPane.add(bloodType);
		contentPane.add(slash2);
		contentPane.add(filterByCity);
		contentPane.add(examinationYear);
		contentPane.add(scrollPane);

		// Add button actions
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				app.getFrame().setVisible(true);
			}
		});

		createPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatePatientWindow frame = new CreatePatientWindow();
				frame.setVisible(true);
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

		deletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletePatient((BigDecimal) table.getModel().getValueAt(table.getSelectedRow(), 0));
			}
		});

		updatePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tckn_pkey = (String) table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				String first_name = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
				String last_name = (String) table.getModel().getValueAt(table.getSelectedRow(), 2);
				String sex = (String) table.getModel().getValueAt(table.getSelectedRow(), 3);
				String birth_date = (String) table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				String blood_type = (String) table.getModel().getValueAt(table.getSelectedRow(), 5);
				String cronical_illnesses = (String) table.getModel().getValueAt(table.getSelectedRow(), 6);
				String status_text = (String) table.getModel().getValueAt(table.getSelectedRow(), 7);

				UpdatePatientWindow frame = new UpdatePatientWindow(tckn_pkey, first_name, last_name, sex, birth_date,
						blood_type, cronical_illnesses, status_text);
				frame.setVisible(true);
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
		String query = "SELECT * FROM patient";
		// isim
		if (firstName.getText().length() > 0) {
			String newQuery = "SELECT * FROM patient WHERE first_name = '" + firstName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// soyisim
		if (lastName.getText().length() > 0) {
			String newQuery = "SELECT * FROM patient WHERE last_name = '" + lastName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// tckn
		if (tckn.getText().length() > 0) {
			String newQuery = "SELECT * FROM patient WHERE tckn='" + tckn.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// cinsiyet
		if (gender.getSelectedIndex() == 1) {
			String newQuery = "SELECT * FROM patient WHERE sex='M'";
			query = query + " INTERSECT " + newQuery;
		} else if (gender.getSelectedIndex() == 2) {
			String newQuery = "SELECT * FROM patient WHERE sex='F'";
			query = query + " INTERSECT " + newQuery;
		} else if (gender.getSelectedIndex() == 3) {
			String newQuery = "SELECT * FROM patient WHERE sex!='F' and sex!='M'";
			query = query + " INTERSECT " + newQuery;
		}
		// kan grubu
		if (bloodType.getSelectedIndex() > 0) {
			String newQuery = "SELECT * FROM patient WHERE blood_type='" + (String) bloodType.getSelectedItem() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// kronik hastalık
		if (cronicalIllnes.getText().length() > 0) {
			String newQuery = "SELECT * FROM patient WHERE cronic_illnesses='" + cronicalIllnes.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Test sonucu
		if (testResult.getSelectedIndex() == 1) {
			String newQuery = "SELECT patient.* FROM patient,examination WHERE tckn=patient_tckn and test_result=false";
			query = query + " INTERSECT " + newQuery;
		} else if (testResult.getSelectedIndex() == 2) {
			String newQuery = "SELECT patient.* FROM patient,examination WHERE tckn=patient_tckn and test_result=true";
			query = query + " INTERSECT " + newQuery;
		}
		// Doktor ismi
		if (doctorFirstName.getText().length() > 0) {
			String newQuery = "SELECT ptnt.* FROM patient ptnt,examination e,doctor d WHERE ptnt.tckn=e.patient_tckn and e.doc_ssn=d.ssn and d.first_name='"
					+ doctorFirstName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Doktor soyismi
		if (doctorLastName.getText().length() > 0) {
			String newQuery = "SELECT ptnt.* FROM patient ptnt,examination e,doctor d WHERE ptnt.tckn=e.patient_tckn and e.doc_ssn=d.ssn and d.last_name='"
					+ doctorLastName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Durum
		if (status.getSelectedIndex() > 0) {
			String newQuery = "SELECT * from patient WHERE status='" + (String) status.getSelectedItem() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// hastane
		if (hospital.getText().length() > 0) {
			String newQuery = "SELECT pt.* FROM patient pt,examination e,hospital h WHERE pt.tckn=e.patient_tckn and e.hospital_id=h.id and h.name='"
					+ hospital.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// şehir
		if (city.getText().length() > 0) {
			String newQuery = "SELECT pt.* FROM patient pt,examination e,hospital h WHERE pt.tckn=e.patient_tckn and e.hospital_id=h.id and h.city='"
					+ city.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Tarih
		if (examinationDay.getText().length() > 0 && examinationMonth.getText().length() > 0
				&& examinationYear.getText().length() > 0) {
			String newQuery = "SELECT patient.* FROM patient,examination WHERE tckn=patient_tckn and test_date='"
					+ examinationYear.getText() + "-" + examinationMonth.getText() + "-" + examinationDay.getText()
					+ "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Yaş
		if (minAge.getText().length() > 0 && maxAge.getText().length() > 0) {
			String newQuery = "SELECT * FROM patient WHERE " + minAge.getText()
					+ "<=(now()::date-birth_date)/365 AND (now()::date-birth_date)/365<=" + maxAge.getText();
			query = query + " INTERSECT " + newQuery;
		}
		// Sirala
		if (sortBy.getSelectedIndex() == 0)
			query += " ORDER BY tckn";
		else if (sortBy.getSelectedIndex() == 1)
			query += " ORDER BY first_name";
		else if (sortBy.getSelectedIndex() == 2)
			query += " ORDER BY last_name";
		else if (sortBy.getSelectedIndex() == 3)
			query += " ORDER BY sex";
		else if (sortBy.getSelectedIndex() == 4)
			query += " ORDER BY birth_date";
		else if (sortBy.getSelectedIndex() == 5)
			query += " ORDER BY blood_type";
		else if (sortBy.getSelectedIndex() == 6)
			query += " ORDER BY cronic_illnesses";
		else if (sortBy.getSelectedIndex() == 7)
			query += " ORDER BY status";

		return query;
	}

	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();

		columnNames.add("TCKN");
		columnNames.add("Adi");
		columnNames.add("Soyadi");
		columnNames.add("Cinsiyeti");
		columnNames.add("Dogum Tarihi");
		columnNames.add("Kan Grubu");
		columnNames.add("Kronik Hastaligi");
		columnNames.add("Durumu");

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

	public void deletePatient(BigDecimal tckn) {
		DbConnection.connect();
		DbConnection.update("DELETE FROM patient WHERE tckn = '" + tckn.toString() + "'");
		DbConnection.disconnect();
	}
}
