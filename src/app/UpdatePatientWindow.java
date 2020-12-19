package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.util.Calendar;

@SuppressWarnings("serial")
public class UpdatePatientWindow extends JFrame {

	private JPanel contentPane;
	private JTextField tckn;
	private JTextField firstName;
	private JTextField cronicalIllnesses;
	private JLabel title;
	private JLabel tcknLabel;
	private JLabel firstNameLabel;
	private JLabel genderLabel;
	private JLabel cronicalIlnessesLabel;
	private JComboBox<String> gender;
	private JButton submit;
	private JLabel birthDateLabel;
	private JTextField birthDay;
	private JLabel lastNameLabel;
	private JTextField lastName;
	private JLabel statusLabel;
	private JTextField status;
	private JLabel bloodTypeLabel;
	private JComboBox<String> bloodType;
	private JLabel birthDateLabel_1;
	private JTextField birthMonth;
	private JLabel birthDateLabel_2;
	private JTextField birthYear;
	private int defaultTckn;
	private Calendar defaultDate;
	private String defaultFirstName;
	private String defaultLastName;
	private char defaultGender;
	private String defaultBloodType;
	private String defaultCronicalIllnesses;
	private String defaultStatus;

	public UpdatePatientWindow(int defaultTckn, Calendar defaultDate, String defaultFirstName, String defaultLastName,
			char defaultGender, String defaultBloodType, String defaultCronicalIllnesses, String defaultStatus) {
		this.defaultTckn = defaultTckn;
		this.defaultDate = defaultDate;
		this.defaultFirstName = defaultFirstName;
		this.defaultLastName = defaultLastName;
		this.defaultGender = defaultGender;
		this.defaultBloodType = defaultBloodType;
		this.defaultCronicalIllnesses = defaultCronicalIllnesses;
		this.defaultStatus = defaultStatus;
		initialize();
	}

	public void initialize() {
		// Configure frame
		setResizable(false);
		setBounds(150, 150, 440, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Create components
		cronicalIllnesses = new JTextField();
		cronicalIlnessesLabel = new JLabel("Varsa Kronik Rahatsýzlýðý:");
		firstNameLabel = new JLabel("Adý:");
		genderLabel = new JLabel("Cinsiyet:");
		firstName = new JTextField();
		tckn = new JTextField();
		gender = new JComboBox<String>();
		tcknLabel = new JLabel("TCKN:");
		title = new JLabel("YENÝ HASTA");
		submit = new JButton("Ekle");
		birthDateLabel = new JLabel("Doðum Tarihi:");
		statusLabel = new JLabel("Gerkliyse Þu Anki Durumu:");
		bloodTypeLabel = new JLabel("Kan Grubu:");
		lastNameLabel = new JLabel("Soyadý:");
		birthDay = new JTextField();
		lastName = new JTextField();
		status = new JTextField();
		bloodType = new JComboBox<String>();
		birthDateLabel_1 = new JLabel("/");
		birthMonth = new JTextField();
		birthDateLabel_2 = new JLabel("/");
		birthYear = new JTextField();

		// Configure components
		firstNameLabel.setBounds(10, 98, 194, 14);
		cronicalIllnesses.setColumns(10);
		cronicalIllnesses.setBounds(10, 237, 194, 20);
		cronicalIlnessesLabel.setBounds(10, 212, 194, 14);
		genderLabel.setBounds(10, 154, 194, 14);
		gender.setBounds(10, 179, 194, 22);
		submit.setBounds(10, 268, 404, 23);
		title.setFont(new Font("Calibri", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 404, 20);
		tcknLabel.setBounds(10, 42, 194, 14);
		tckn.setBounds(10, 67, 194, 20);
		tckn.setColumns(10);
		firstName.setColumns(10);
		firstName.setBounds(10, 123, 194, 20);
		birthDateLabel.setBounds(220, 42, 194, 14);
		birthDay.setColumns(10);
		birthDay.setBounds(220, 67, 20, 20);
		lastNameLabel.setBounds(220, 98, 194, 14);
		lastName.setColumns(10);
		lastName.setBounds(220, 123, 194, 20);
		statusLabel.setBounds(220, 212, 194, 14);
		status.setColumns(10);
		status.setBounds(220, 237, 194, 20);
		bloodTypeLabel.setBounds(220, 154, 194, 14);
		bloodType.setBounds(220, 179, 194, 22);
		birthDateLabel_1.setBounds(250, 70, 4, 14);
		birthMonth.setColumns(10);
		birthMonth.setBounds(264, 67, 20, 20);
		birthDateLabel_2.setBounds(294, 70, 4, 14);
		birthYear.setColumns(10);
		birthYear.setBounds(308, 67, 36, 20);

		// Add components to panel
		contentPane.add(firstNameLabel);
		contentPane.add(gender);
		contentPane.add(cronicalIllnesses);
		contentPane.add(cronicalIlnessesLabel);
		contentPane.add(genderLabel);
		contentPane.add(title);
		contentPane.add(tckn);
		contentPane.add(tcknLabel);
		contentPane.add(firstName);
		contentPane.add(submit);
		contentPane.add(birthDateLabel);
		contentPane.add(birthDay);
		contentPane.add(lastNameLabel);
		contentPane.add(lastName);
		contentPane.add(statusLabel);
		contentPane.add(status);
		contentPane.add(bloodTypeLabel);
		contentPane.add(bloodType);
		contentPane.add(birthDateLabel_1);
		contentPane.add(birthMonth);
		contentPane.add(birthDateLabel_2);
		contentPane.add(birthYear);
	}
}
