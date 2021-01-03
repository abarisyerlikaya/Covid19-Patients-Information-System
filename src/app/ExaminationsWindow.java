package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class ExaminationsWindow extends JFrame {

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
	private JButton updateExamination;
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
	private JButton deleteExamination;
	private JButton createExamination;
	private JComboBox<String> bloodType;
	private JComboBox<String> gender;
	private JComboBox<String> testResult;
	private JTextField doctorLastName;
	private JTextField doctorFirstName;
	private JLabel positivityRatio;

	public ExaminationsWindow(AppWindow app) {
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
		title = new JLabel("MUAYENE BILGISI");
		back = new JButton("< Geri");
		filterByName = new JLabel("Hastanin adi ve soyadina gore filtrele");
		firstName = new JTextField();
		lastName = new JTextField();
		filterByGender = new JLabel("Hastanin cinsiyetine gore filtrele:");
		filterByTckn = new JLabel("Hastanin TCKN'sine gore filtrele:");
		tckn = new JTextField();
		showResults = new JButton("Sonuclari Goster");
		showResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					printTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Sorgu başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		sortBy = new JComboBox<String>();
		table = new JTable();
		minAge = new JTextField();
		dash1 = new JLabel("-");
		filterByAge = new JLabel("Hastanin yasina gore filtrele:");
		filterByBloodType = new JLabel("Hastanin kan grubuna gore filtrele:");
		deleteExamination = new JButton("Muayeneyi sil");
		deleteExamination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletePatient((BigDecimal) table.getModel().getValueAt(table.getSelectedRow(), 0));
			}
		});
		createExamination = new JButton("Muayene ekle");
		maxAge = new JTextField();
		updateExamination = new JButton("Muayene bilgilerini guncelle");
		updateExamination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String patient_tckn = (String) table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				String exam_id = (String) table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
				String test_date = (String) table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
				String doc_ssn = (String) table.getModel().getValueAt(table.getSelectedRow(), 3).toString();
				String test_result = "";
				String hospital_id = (String) table.getModel().getValueAt(table.getSelectedRow(), 5).toString();
				if ((table.getModel().getValueAt(table.getSelectedRow(), 4)) == null) {
					test_result = "";
				} else {
					test_result = (String) table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
				}

				UpdateExaminationWindow frame = new UpdateExaminationWindow(patient_tckn, exam_id, doc_ssn, test_result,
						hospital_id);
				frame.setVisible(true);
			}
		});
		filterByCronicalIllnes = new JLabel("Hastanin kronik hastaliga gore filtrele:");
		filterByExaminationDate = new JLabel("Muayene tarihine gore filtrele");
		examinationMonth = new JTextField();
		examinationDay = new JTextField();
		cronicalIllnes = new JTextField();
		filterByTestResult = new JLabel("Test sonucuna gore filtrele:");
		filterByDoctorName = new JLabel("Doktor adina gore filtrele:");
		filterByStatus = new JLabel("Hastanin durumuna gore filtrele:");
		status = new JComboBox();
		hospital = new JTextField();
		filterByHospital = new JLabel("Hastaneye gore filtrele:");
		slash1 = new JLabel("/");
		examinationYear = new JTextField();
		slash2 = new JLabel("/");
		city = new JTextField();
		filterByCity = new JLabel("Sehre gore filtrele:");
		bloodType = new JComboBox<String>();
		gender = new JComboBox<String>();
		testResult = new JComboBox<String>();
		doctorLastName = new JTextField();
		doctorFirstName = new JTextField();
		status.addItem("Tumu");
		status.addItem("Test sonucu bekleniyor");
		status.addItem("Evde karantinada");
		status.addItem("Yurtta karantinada");
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
		filterByBloodType.setBounds(266, 104, 245, 20);
		deleteExamination.setBounds(494, 605, 228, 26);
		createExamination.setBounds(10, 605, 228, 26);
		updateExamination.setBounds(250, 605, 228, 26);
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

		status.setBounds(10, 259, 109, 20);
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
		gender.setBounds(522, 72, 69, 22);
		testResult.setBounds(266, 196, 69, 22);
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
		sortBy.addItem("Hasta TCKN'ye gore sirala");
		sortBy.addItem("Test tarihine gore sirala");
		sortBy.addItem("Doktor SSN'e g�re sirala");
		sortBy.addItem("Test sonucuna gore sirala");
		sortBy.addItem("Hastane adina gore sirala");

		gender.addItem("Tumu");
		gender.addItem("Erkek");
		gender.addItem("Kadin");
		gender.addItem("Diger");
		testResult.addItem("Tumu");
		testResult.addItem("Negatif");
		testResult.addItem("Pozitif");
		bloodType.addItem("Tumu");
		bloodType.addItem("0-");
		bloodType.addItem("0+");
		bloodType.addItem("A-");
		bloodType.addItem("A+");
		bloodType.addItem("B-");
		bloodType.addItem("B+");
		bloodType.addItem("AB-");
		bloodType.addItem("AB+");
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
		contentPane.add(deleteExamination);
		contentPane.add(updateExamination);
		contentPane.add(filterByCronicalIllnes);
		contentPane.add(cronicalIllnes);
		contentPane.add(createExamination);
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

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 327, 712, 267);
		contentPane.add(scrollPane);

		positivityRatio = new JLabel("");
		positivityRatio.setBounds(522, 12, 151, 14);
		contentPane.add(positivityRatio);

		// Add button actions
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				app.getFrame().setVisible(true);
			}
		});

		createExamination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateExaminationWindow frame = new CreateExaminationWindow();
				frame.setVisible(true);
			}
		});
	}

	public void printTable() {
		DbConnection.connect();

		String query = generateQuery();
		ResultSet rs = DbConnection.select(query);
		try {
			table.setModel(buildTableModel(rs));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
		}

		ResultSet ratio = DbConnection.select("SELECT get_positive_ratio('" + query.replaceAll("'", "''") + "')");
		try {
			ratio.next();
			positivityRatio.setText("Pozitif yüzdesi: %" + (int) (ratio.getFloat(1) * 100));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ErrorMsg", "Failure", JOptionPane.ERROR_MESSAGE);
		}

		DbConnection.disconnect();

	}

	public String generateQuery() {
		String query = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e";
		// isim
		if (firstName.getText().length() > 0) {

			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM patient pt,examination e WHERE e.patient_tckn=pt.tckn AND pt.first_name = '"
					+ firstName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// soyisim
		if (lastName.getText().length() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM patient pt,examination e WHERE e.patient_tckn=pt.tckn AND pt.last_name = '"
					+ lastName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// tckn
		if (tckn.getText().length() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM patient pt,examination e WHERE e.patient_tckn = '"
					+ tckn.getText() + "' AND e.patient_tckn=pt.tckn";
			query = query + " INTERSECT " + newQuery;
		}
		// cinsiyet
		if (gender.getSelectedIndex() == 1) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e,patient p WHERE e.patient_tckn=p.tckn and p.sex='M'";
			query = query + " INTERSECT " + newQuery;
		} else if (gender.getSelectedIndex() == 2) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e,patient p WHERE e.patient_tckn=p.tckn and p.sex='F'";
			query = query + " INTERSECT " + newQuery;
		} else if (gender.getSelectedIndex() == 3) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e,patient p WHERE e.patient_tckn=p.tckn and p.sex!='F' and p.sex!='M'";
			query = query + " INTERSECT " + newQuery;
		}
		// kan grubu
		if (bloodType.getSelectedIndex() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e,patient p WHERE e.patient_tckn=p.tckn and p.blood_type='"
					+ (String) bloodType.getSelectedItem() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// kronik hastalık
		if (cronicalIllnes.getText().length() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e,patient p WHERE e.patient_tckn=p.tckn and p.cronic_illnesses ='"
					+ cronicalIllnes.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Test sonucu
		if (testResult.getSelectedIndex() == 1) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e WHERE test_result = false";
			query = query + " INTERSECT " + newQuery;
		} else if (testResult.getSelectedIndex() == 2) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e where test_result=true";
			query = query + " INTERSECT " + newQuery;
		}
		// Doktor ismi
		if (doctorFirstName.getText().length() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM doctor d,examination e WHERE e.doc_ssn=d.ssn AND d.first_name='"
					+ doctorFirstName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Doktor soyismi
		if (doctorLastName.getText().length() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM doctor d,examination e WHERE e.doc_ssn=d.ssn AND d.last_name='"
					+ doctorLastName.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Durum
		if (status.getSelectedIndex() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e,patient p WHERE e.patient_tckn=p.tckn and p.status = '"
					+ (String) status.getSelectedItem() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// hastane
		if (hospital.getText().length() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e,hospital h WHERE e.hospital_id=h.id and h.name='"
					+ hospital.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// şehir
		if (city.getText().length() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e,hospital h WHERE e.hospital_id=h.id and h.city='"
					+ city.getText() + "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Tarih
		if (examinationDay.getText().length() > 0 && examinationMonth.getText().length() > 0
				&& examinationYear.getText().length() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e WHERE test_date='"
					+ examinationYear.getText() + "-" + examinationMonth.getText() + "-" + examinationDay.getText()
					+ "'";
			query = query + " INTERSECT " + newQuery;
		}
		// Yaş
		if (minAge.getText().length() > 0 && maxAge.getText().length() > 0) {
			String newQuery = "SELECT e.patient_tckn, e.exam_id, e.test_date, e.doc_ssn, e.test_result, get_hospital_name(e.hospital_id) FROM examination e,patient p WHERE e.patient_tckn=p.tckn and "
					+ minAge.getText() + "<=(now()::date-birth_date)/365 AND (now()::date-birth_date)/365<="
					+ maxAge.getText();
			query = query + " INTERSECT " + newQuery;
		}
		// Sirala
		if (sortBy.getSelectedIndex() == 0)
			query += " ORDER BY patient_tckn";
		else if (sortBy.getSelectedIndex() == 1)
			query += " ORDER BY test_date";
		else if (sortBy.getSelectedIndex() == 2)
			query += " ORDER BY doc_ssn";
		else if (sortBy.getSelectedIndex() == 3)
			query += " ORDER BY test_result";
		else if (sortBy.getSelectedIndex() == 4)
			query += " ORDER BY hospital_id";

		return query;
	}

	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();

		columnNames.add("Hasta TCKN");
		columnNames.add("ID");
		columnNames.add("Test Tarihi");
		columnNames.add("Doktor SSN");
		columnNames.add("Test Sonucu");
		columnNames.add("Hastane Adi");

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
		DbConnection.update("DELETE FROM examination WHERE patient_tckn = '" + tckn.toString() + "'");
		DbConnection.disconnect();
	}
}
