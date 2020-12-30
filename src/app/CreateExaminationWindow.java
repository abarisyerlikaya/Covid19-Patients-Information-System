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
		ssnLabel = new JLabel("Yapan Doktorun SGK No'su:");
		title = new JLabel("YENI MUAYENE");
		submit = new JButton("Ekle");
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
	}
}
