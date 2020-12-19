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
		title = new JLabel("DOKTOR BÝLGÝSÝ");
		back = new JButton("< Geri");
		filterByName = new JLabel("Ad ve soyada göre filtrele");
		firstName = new JTextField();
		lastName = new JTextField();
		filterByCity = new JLabel("Þehre göre filtrele:");
		city = new JTextField();
		filterByHospital = new JLabel("Çalýþtýðý hastaneye göre filtrele:");
		hospital = new JTextField();
		filterByExaminationCount = new JLabel("Toplam muayene sayýsýna göre filtrele:");
		minExaminationCount = new JTextField();
		maxExaminationCount = new JTextField();
		dash1 = new JLabel("-");
		filterByPositiveTestCount = new JLabel("Pozitif sonuçlu muayene sayýsýna göre filtrele:");
		minPositiveTestCount = new JTextField();
		maxPositiveTestCount = new JTextField();
		dash2 = new JLabel("-");
		showResults = new JButton("Sonuçlarý Göster");
		sortBy = new JComboBox<String>();
		table = new JTable();
		createDoctor = new JButton("Doktor ekle");
		updateDoctor = new JButton("Doktor bilgilerini güncelle");
		deleteDoctor = new JButton("Doktoru sil");

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
		sortBy.setBounds(10, 198, 350, 26);		table.setBounds(10, 251, 712, 267);
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
		contentPane.add(table);
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

		// Add button actions
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				app.getFrame().setVisible(true);
			}
		});
		
		createDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDoctorWindow frame = new CreateDoctorWindow();
				frame.setVisible(true);
			}
		});
	}
}
