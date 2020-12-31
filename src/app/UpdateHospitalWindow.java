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
public class UpdateHospitalWindow extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JLabel title;
	private JLabel nameLabel;
	private JLabel cityLabel;
	private JComboBox<String> city;
	private JButton submit;
	
	private String defaultName;
	private String defaultCity;

	public UpdateHospitalWindow(String defaultName, String defaultCity) {
		this.defaultName = defaultName;
		this.defaultCity = defaultCity;
		initialize();
	}

	public void initialize() {
		// Configure frame
		setBounds(150, 150, 230, 224);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cityLabel = new JLabel("Bulunduðu Þehir:");
		name = new JTextField();
		city = new JComboBox<String>();
		nameLabel = new JLabel("Adý:");
		title = new JLabel("YENÝ HASTANE");
		submit = new JButton("Ekle");
		cityLabel.setBounds(10, 98, 194, 14);
		city.setBounds(10, 123, 194, 22);
		submit.setBounds(10, 156, 194, 23);
		title.setFont(new Font("Calibri", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 194, 20);
		nameLabel.setBounds(10, 42, 194, 14);
		name.setBounds(10, 67, 194, 20);
		name.setColumns(10);
		contentPane.add(city);
		contentPane.add(cityLabel);
		contentPane.add(title);
		contentPane.add(name);
		contentPane.add(nameLabel);
		contentPane.add(submit);
		
	
		
		
		
	}
	
	
	

}
