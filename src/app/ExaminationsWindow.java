package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

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
	private JTextField status;
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
		title = new JLabel("MUAYENE BÝLGÝSÝ");
		back = new JButton("< Geri");
		filterByName = new JLabel("Hastanýn adý ve soyadýna göre filtrele");
		firstName = new JTextField();
		lastName = new JTextField();
		filterByGender = new JLabel("Hastanýn cinsiyetine göre filtrele:");
		filterByTckn = new JLabel("Hastanýn TCKN'sine göre filtrele:");
		tckn = new JTextField();
		showResults = new JButton("Sonuçlarý Göster");
		sortBy = new JComboBox<String>();
		table = new JTable();
		minAge = new JTextField();
		dash1 = new JLabel("-");
		filterByAge = new JLabel("Hastanýn yaþa göre filtrele:");
		filterByBloodType = new JLabel("Hastanýn kan grubuna göre filtrele:");
		deleteExamination = new JButton("Muayeneyi sil");
		createExamination = new JButton("Muayene ekle");
		maxAge = new JTextField();
		updateExamination = new JButton("Muayene bilgilerini güncelle");
		filterByCronicalIllnes = new JLabel("Hastanýn kronik hastalýða göre filtrele:");
		filterByExaminationDate = new JLabel("Muayene tarihine göre filtrele");
		examinationMonth = new JTextField();
		examinationDay = new JTextField();
		cronicalIllnes = new JTextField();
		filterByTestResult = new JLabel("Test sonucuna göre filtrele:");
		filterByDoctorName = new JLabel("Doktor adýna göre filtrele:");
		filterByStatus = new JLabel("Hastanýn durumuna göre filtrele:");
		status = new JTextField();
		hospital = new JTextField();
		filterByHospital = new JLabel("Hastaneye göre filtrele:");
		slash1 = new JLabel("/");
		examinationYear = new JTextField();
		slash2 = new JLabel("/");
		city = new JTextField();
		filterByCity = new JLabel("Þehre göre filtrele:");
		bloodType = new JComboBox<String>();
		gender = new JComboBox<String>();
		testResult = new JComboBox<String>();
		doctorLastName = new JTextField();
		doctorFirstName = new JTextField();

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
		status.setColumns(10);
		status.setBounds(10, 259, 88, 20);
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
		table.setBounds(10, 327, 712, 267);
		minAge.setColumns(10);
		minAge.setBounds(10, 135, 32, 20);
		filterByAge.setBounds(10, 104, 221, 20);
		dash1.setBounds(52, 138, 4, 14);
		maxAge.setColumns(10);
		maxAge.setBounds(66, 135, 32, 20);

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
		contentPane.add(table);
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
}
