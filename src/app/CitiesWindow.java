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
public class CitiesWindow extends JFrame {

	private AppWindow app;
	private JPanel contentPane;
	private JLabel title;
	private JLabel filterByExaminationCount;
	private JTextField minExaminationCount;
	private JTextField maxExaminationCount;
	private JLabel dash1;
	private JLabel filterByPositiveTestCount;
	private JTextField maxPositiveTestCount;
	private JTextField minPositiveTestCount;
	private JLabel dash2;
	private JLabel filterByPositiveTestPercentage;
	private JTextField maxPositiveTestPercentage;
	private JTextField minPositiveTestPercentage;
	private JLabel dash3;
	private JComboBox<String> sortBy;
	private JButton showResults;
	private JButton back;
	private JTable table;

	public CitiesWindow(AppWindow app) {
		this.app = app;
		initialize();
	}

	private void initialize() {
		// Configure frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		// Create components
		title = new JLabel("ÞEHÝR BÝLGÝSÝ");
		back = new JButton("< Geri");
		filterByExaminationCount = new JLabel("Muayene sayýsýna göre filtrele:");
		minExaminationCount = new JTextField();
		maxExaminationCount = new JTextField();
		dash1 = new JLabel("-");
		filterByPositiveTestCount = new JLabel("Pozitif sonuç sayýsýna göre filtrele:");
		minPositiveTestCount = new JTextField();
		maxPositiveTestCount = new JTextField();
		dash2 = new JLabel("-");
		filterByPositiveTestPercentage = new JLabel("Pozitif sonuç yüzdesine göre filtrele:");
		minPositiveTestPercentage = new JTextField();
		maxPositiveTestPercentage = new JTextField();
		dash3 = new JLabel("-");
		showResults = new JButton("Sonuçlarý Göster");
		sortBy = new JComboBox<String>();

		// Configure components
		title.setFont(new Font("Calibri", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 712, 20);
		back.setBounds(9, 8, 69, 23);
		filterByExaminationCount.setBounds(10, 42, 200, 20);
		minExaminationCount.setBounds(10, 73, 32, 20);
		minExaminationCount.setColumns(10);
		maxExaminationCount.setColumns(10);
		maxExaminationCount.setBounds(66, 73, 32, 20);
		dash1.setBounds(52, 76, 4, 14);
		filterByPositiveTestPercentage.setBounds(266, 42, 212, 20);
		minPositiveTestPercentage.setColumns(10);
		minPositiveTestPercentage.setBounds(266, 73, 32, 20);
		maxPositiveTestPercentage.setColumns(10);
		maxPositiveTestPercentage.setBounds(322, 73, 32, 20);
		dash2.setBounds(308, 76, 4, 14);
		filterByPositiveTestCount.setBounds(522, 42, 200, 20);
		minPositiveTestCount.setColumns(10);
		minPositiveTestCount.setBounds(522, 73, 32, 20);
		maxPositiveTestCount.setColumns(10);
		maxPositiveTestCount.setBounds(578, 73, 32, 20);
		dash3.setBounds(564, 76, 4, 14);
		showResults.setBounds(372, 116, 350, 26);
		sortBy.setBounds(10, 116, 350, 26);

		// Add components to panel
		contentPane.add(title);
		contentPane.add(back);

		contentPane.add(filterByExaminationCount);
		contentPane.add(minExaminationCount);
		contentPane.add(maxExaminationCount);
		contentPane.add(dash1);

		contentPane.add(filterByPositiveTestPercentage);
		contentPane.add(minPositiveTestPercentage);
		contentPane.add(maxPositiveTestPercentage);
		contentPane.add(dash2);

		contentPane.add(filterByPositiveTestCount);
		contentPane.add(minPositiveTestCount);
		contentPane.add(maxPositiveTestCount);
		contentPane.add(dash3);

		contentPane.add(showResults);
		contentPane.add(sortBy);

		table = new JTable();
		table.setBounds(10, 167, 712, 267);
		contentPane.add(table);

		// Add button actions
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				app.getFrame().setVisible(true);
			}
		});
	}
}
