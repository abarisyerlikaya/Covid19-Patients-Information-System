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
public class UpdateExaminationWindow extends JFrame {

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
	private String defaultSsn;
	private String defaultTckn;
	private String defaultTestResult;
	private String defaultHospital;
	private String defaultId;

	public UpdateExaminationWindow(String defaultTckn,String defaultId,String defaultSsn, String defaultTestResult, String defaultHospital) {
		this.defaultSsn = defaultSsn;
		this.defaultTckn = defaultTckn;
		this.defaultTestResult = defaultTestResult;
		this.defaultHospital = defaultHospital;
		this.defaultId=defaultId;
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
		tcknLabel = new JLabel("Hastanýn TCKN'si:");
		hospitalLabel = new JLabel("Yapýldýðý Hastane:");
		tckn = new JTextField(defaultTckn);
		tckn.setEditable(false);
		ssn = new JTextField(defaultSsn);
		ssn.setEditable(false);
		hospital = new JComboBox<String>();
		hospital.setEnabled(false);
		
		ssnLabel = new JLabel("Yapan Doktorun SGK No'su:");
		title = new JLabel("MUAYENE BILGILERI");
		submit = new JButton("Guncelle");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateExamination();
				popup hey=new popup();
				hey.setVisible(true);
			}
		});
		testResult = new JComboBox<String>();

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
		testResult.addItem("Bekliyor");
		testResult.addItem("Negatif");
		testResult.addItem("Pozitif");
		DbConnection.connect();
		ResultSet rs = DbConnection.select("SELECT name FROM hospital");
		try {
			while (rs.next())
				hospital.addItem(rs.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hospital.setSelectedItem(defaultHospital);
		DbConnection.disconnect();
		testResult.setSelectedItem(defaultTestResult);
	}
	public void updateExamination() {
		String pt_tckn = tckn.getText();
		String doc_ssn = ssn.getText();
		int test_result_new=testResult.getSelectedIndex();
		String hospital_name = (String) hospital.getSelectedItem();
		
		String test_result="null";
		if(test_result_new==1) {
			test_result="false";
		}
		else if(test_result_new==2) {
			test_result="true";
		}
		
		DbConnection.connect();

		DbConnection.update("UPDATE examination SET test_result = "+ test_result +" WHERE exam_id = " + defaultId );

		DbConnection.disconnect();

		setVisible(false);
	}

}
