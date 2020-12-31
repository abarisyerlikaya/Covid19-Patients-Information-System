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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CreatePatientWindow extends JFrame {

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
	private JLabel bloodTypeLabel;
	private JComboBox<String> bloodType;
	private JLabel birthDateLabel_1;
	private JTextField birthMonth;
	private JLabel birthDateLabel_2;
	private JTextField birthYear;
	private JComboBox<String> status;

	public CreatePatientWindow() {
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
		cronicalIlnessesLabel = new JLabel("Varsa Kronik Rahatsizligi:");
		firstNameLabel = new JLabel("Adi:");
		genderLabel = new JLabel("Cinsiyet:");
		firstName = new JTextField();
		tckn = new JTextField();
		gender = new JComboBox<String>();
		tcknLabel = new JLabel("TCKN:");
		title = new JLabel("YENI HASTA");
		submit = new JButton("Ekle");
		birthDateLabel = new JLabel("Do�um Tarihi:");
		statusLabel = new JLabel("Su Anki Durumu:");
		bloodTypeLabel = new JLabel("Kan Grubu:");
		lastNameLabel = new JLabel("Soyadi:");
		birthDay = new JTextField();
		lastName = new JTextField();
		bloodType = new JComboBox<String>();
		birthDateLabel_1 = new JLabel("/");
		birthMonth = new JTextField();
		birthDateLabel_2 = new JLabel("/");
		birthYear = new JTextField();
		status = new JComboBox();

		// Configure components
		firstNameLabel.setBounds(10, 98, 194, 14);
		cronicalIllnesses.setColumns(10);
		cronicalIllnesses.setBounds(10, 237, 194, 20);
		cronicalIlnessesLabel.setBounds(10, 212, 194, 14);
		genderLabel.setBounds(10, 154, 194, 14);
		gender.setBounds(10, 179, 194, 22);
		gender.addItem("Erkek");
		gender.addItem("Kadin");
		gender.addItem("Diger");
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
		bloodTypeLabel.setBounds(220, 154, 194, 14);
		bloodType.setBounds(220, 179, 194, 22);
		bloodType.addItem("0-");
		bloodType.addItem("0+");
		bloodType.addItem("A-");
		bloodType.addItem("A+");
		bloodType.addItem("B-");
		bloodType.addItem("B+");
		bloodType.addItem("AB-");
		bloodType.addItem("AB+");
		birthDateLabel_1.setBounds(250, 70, 4, 14);
		birthMonth.setColumns(10);
		birthMonth.setBounds(264, 67, 20, 20);
		birthDateLabel_2.setBounds(294, 70, 4, 14);
		birthYear.setColumns(10);
		birthYear.setBounds(308, 67, 36, 20);
		status.setBounds(220, 236, 194, 22);
		status.addItem("Test sonucu bekleniyor");
		status.addItem("Evde karantinada");
		status.addItem("Yurtta karantinada");
		

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
		contentPane.add(bloodTypeLabel);
		contentPane.add(bloodType);
		contentPane.add(birthDateLabel_1);
		contentPane.add(birthMonth);
		contentPane.add(birthDateLabel_2);
		contentPane.add(birthYear);
		contentPane.add(status);

		// Add action listeners
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPatient();
			}
		});
	}

	public void createPatient() {
		String tckn_pkey = tckn.getText();
		String first_name = firstName.getText();
		String last_name = lastName.getText();
		String sex;
		String birth_date = birthYear.getText() + "-" + birthMonth.getText() + "-" + birthDay.getText();
		String blood_type = (String) bloodType.getSelectedItem();
		String cronical_illnesses = cronicalIllnesses.getText();
		String status_text = (String) status.getSelectedItem();

		if (gender.getSelectedIndex() == 0)
			sex = "M";
		else if (gender.getSelectedIndex() == 1)
			sex = "F";
		else
			sex = "O";

		String query = "INSERT INTO patient VALUES(" + tckn_pkey + ", '" + first_name + "', '" + last_name + "', '"
				+ sex + "', '" + birth_date + "', '" + blood_type + "', "
				+ (cronical_illnesses.length() > 0 ? "'" + cronical_illnesses + "'" : "null") + ", '" + status_text
				+ "')";

		DbConnection.connect();
		DbConnection.update(query);
		DbConnection.disconnect();
		setVisible(false);
	}
}
