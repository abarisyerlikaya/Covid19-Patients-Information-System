package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class UpdateDoctorWindow extends JFrame {

	private JPanel contentPane;
	private JTextField ssn;
	private JTextField firstName;
	private JTextField lastName;
	private JLabel title;
	private JLabel ssnLabel;
	private JLabel firstNameLabel;
	private JLabel hospitalLabel;
	private JLabel lastNameLabel;
	private JComboBox<String> hospital;
	private JButton submit;

	private String defaultSsn;
	private String defaultFirstName;
	private String defaultLastName;
	private String defaultHospital;

	public UpdateDoctorWindow(String defaultSsn, String defaultFirstName, String defaultLastName,
			String defaultHospital) throws SQLException {
		this.defaultSsn = defaultSsn;
		this.defaultFirstName = defaultFirstName;
		this.defaultLastName = defaultLastName;
		this.defaultHospital = defaultHospital;
		initialize();
	}

	public void initialize() throws SQLException {
		// Configure frame
		setResizable(false);
		setBounds(150, 150, 230, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Create components
		lastName = new JTextField();
		lastNameLabel = new JLabel("Soyadi:");
		firstNameLabel = new JLabel("Adi:");
		hospitalLabel = new JLabel("Calistigi:");
		firstName = new JTextField();
		ssn = new JTextField();
		hospital = new JComboBox<String>();
		ssnLabel = new JLabel("Sosyal Guvenlik Numarasi:");
		title = new JLabel("DOKTOR BILGILERI");
		submit = new JButton("Guncelle");

		// Configure components
		firstNameLabel.setBounds(10, 98, 194, 14);
		lastName.setColumns(10);
		lastName.setBounds(10, 179, 194, 20);
		lastNameLabel.setBounds(10, 154, 194, 14);
		hospitalLabel.setBounds(10, 210, 194, 14);
		hospital.setBounds(10, 235, 194, 22);
		submit.setBounds(10, 268, 194, 23);
		title.setFont(new Font("Calibri", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 194, 20);
		ssnLabel.setBounds(10, 42, 314, 14);
		ssn.setBounds(10, 67, 194, 20);
		ssn.setColumns(10);
		ssn.setEditable(false);
		firstName.setColumns(10);
		firstName.setBounds(10, 123, 194, 20);

		// Add components to panel
		contentPane.add(firstNameLabel);
		contentPane.add(hospital);
		contentPane.add(lastName);
		contentPane.add(lastNameLabel);
		contentPane.add(hospitalLabel);
		contentPane.add(title);
		contentPane.add(ssn);
		contentPane.add(ssnLabel);
		contentPane.add(firstName);
		contentPane.add(submit);

		// Add hospital names as options
		DbConnection.connect();
		ResultSet rs = DbConnection.select("SELECT name FROM hospital");
		while (rs.next())
			hospital.addItem(rs.getString("name"));
		DbConnection.disconnect();

		// Set initial values in fields
		ssn.setText(defaultSsn);
		firstName.setText(defaultFirstName);
		lastName.setText(defaultLastName);
		hospital.setSelectedItem(defaultHospital);

		// Add action listeners
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateDoctor();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void updateDoctor() throws SQLException {
		String ssn_pkey = ssn.getText();
		String first_name = firstName.getText();
		String last_name = lastName.getText();
		String hospital_name = (String) hospital.getSelectedItem();
		String hospital_id;

		DbConnection.connect();

		ResultSet rs = DbConnection.select("SELECT id FROM hospital WHERE name = '" + hospital_name + "'");
		rs.next();
		hospital_id = rs.getString("id");

		DbConnection.update("UPDATE doctor SET first_name = '" + first_name + "', last_name = '" + last_name
				+ "', hospital_id = '" + hospital_id + "' WHERE ssn = '" + ssn_pkey + "'");

		DbConnection.disconnect();

		setVisible(false);
	}
}
