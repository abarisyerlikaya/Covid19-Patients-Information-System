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
public class CreateDoctorWindow extends JFrame {

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

	public CreateDoctorWindow() throws SQLException {
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
		hospitalLabel = new JLabel("Hastane:");
		firstName = new JTextField();
		ssn = new JTextField();
		hospital = new JComboBox<String>();
		ssnLabel = new JLabel("Sosyal Guvenlik Numarasi:");
		title = new JLabel("YENI DOKTOR");
		submit = new JButton("Ekle");

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

		// Add action listeners
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createDoctor();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void createDoctor() throws SQLException{
		String ssn_pkey = ssn.getText();
		String first_name = firstName.getText();
		String last_name = lastName.getText();
		String hospital_name = (String) hospital.getSelectedItem();
		String hospital_id;

		DbConnection.connect();

		ResultSet rs = DbConnection.select("SELECT id FROM hospital WHERE name = '" + hospital_name + "'");
		rs.next();
		hospital_id = rs.getString("id");

		DbConnection.update("INSERT INTO doctor VALUES('" + ssn_pkey + "', '" + first_name + "', '" + last_name + "', "
				+ hospital_id + ")");

		DbConnection.disconnect();
		
		setVisible(false);
	}
}