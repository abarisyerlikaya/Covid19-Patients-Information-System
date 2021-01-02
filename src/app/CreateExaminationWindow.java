package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
public class CreateExaminationWindow extends JFrame {

	private JPanel contentPane;
	private JTextField ssn;
	private JTextField tckn;
	private JLabel title;
	private JLabel ssnLabel;
	private JLabel tcknLabel;
	private JLabel hospitalLabel;
	private JLabel testResultLabel;
	private JComboBox<String> hospital;
	private JButton submit;
	private JComboBox<String> testResult;

	public CreateExaminationWindow() {
		initialize();
	}

	public void initialize() {
		// Configure frame
		setResizable(false);
		setBounds(150, 150, 230, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Create components
		testResultLabel = new JLabel("Test Sonucu:");
		tcknLabel = new JLabel("Hastanin TCKN'si:");
		hospitalLabel = new JLabel("Yapildigi Hastane:");
		tckn = new JTextField();
		ssn = new JTextField();
		hospital = new JComboBox<String>();
		DbConnection.connect();
		ResultSet rs = DbConnection.select("Select name from hospital");
		try {
			while(rs.next()) {
				try {
					hospital.addItem(rs.getString("name"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbConnection.disconnect();
		ssnLabel = new JLabel("Yapan Doktorun SGK No'su:");
		title = new JLabel("YENI MUAYENE");
		submit = new JButton("Ekle");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createExamination();
			}
		});
		testResult = new JComboBox<String>();
		testResult.addItem("Bekleniyor");
		testResult.addItem("Negatif");
		testResult.addItem("Pozitif");
		// Configure components
		tcknLabel.setBounds(10, 98, 194, 14);
		testResultLabel.setBounds(10, 154, 194, 14);
		hospitalLabel.setBounds(10, 210, 194, 14);
		hospital.setBounds(10, 235, 194, 22);
		submit.setBounds(10, 268, 194, 23);
		title.setFont(new Font("Calibri", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 194, 20);
		ssnLabel.setBounds(10, 42, 194, 14);
		ssn.setBounds(10, 67, 194, 20);
		ssn.setColumns(10);
		tckn.setColumns(10);
		tckn.setBounds(10, 123, 194, 20);
		testResult.setBounds(10, 179, 194, 22);

		// Add components to panel
		contentPane.add(tcknLabel);
		contentPane.add(hospital);
		contentPane.add(testResultLabel);
		contentPane.add(hospitalLabel);
		contentPane.add(title);
		contentPane.add(ssn);
		contentPane.add(ssnLabel);
		contentPane.add(tckn);
		contentPane.add(submit);
		contentPane.add(testResult);
	}
	public void createExamination() {
		
		String patient_tckn= tckn.getText();
		String doc_ssn = ssn.getText();
		String hospital_name = (String)hospital.getSelectedItem();
		int test_result= testResult.getSelectedIndex();
		String hos_id="(Select id from hospital where name='"+hospital_name+"')";
		
		String query = "INSERT INTO examination VALUES(" + patient_tckn + ",nextval('exam_id'), now(),'" + doc_ssn + "',"
				+(test_result == 0 ? "null" : test_result == 1 ? "false" : "true")+","+ hos_id+")";
		DbConnection.connect();
		DbConnection.update(query);
		DbConnection.disconnect();
		setVisible(false);
	}
}
