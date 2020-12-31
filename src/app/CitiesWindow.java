package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

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
		title = new JLabel("SEHIR BILGISI");
		back = new JButton("< Geri");
		filterByExaminationCount = new JLabel("Muayene sayisina gore filtrele:");
		minExaminationCount = new JTextField();
		maxExaminationCount = new JTextField();
		dash1 = new JLabel("-");
		filterByPositiveTestCount = new JLabel("Pozitif sonuc sayisina gore filtrele:");
		minPositiveTestCount = new JTextField();
		maxPositiveTestCount = new JTextField();
		dash3 = new JLabel("-");
		showResults = new JButton("Sonuclari Goster");
		
		
		
		
		
		
		
		showResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Showresults();
				
			}
			
			
			
			
			
			public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

				ResultSetMetaData metaData = rs.getMetaData();

				// names of columns
				Vector<String> columnNames = new Vector<String>();
				int columnCount = metaData.getColumnCount();

				columnNames.add("Sehir");
				

				// data of the table
				Vector<Vector<Object>> data = new Vector<Vector<Object>>();
				while (rs.next()) {
					Vector<Object> vector = new Vector<Object>();
					for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
						vector.add(rs.getObject(columnIndex));
					}
					data.add(vector);
				}

				return new DefaultTableModel(data, columnNames);

			}
			
			
			public void Showresults() {
				DbConnection.connect();
				String query;
				query = "select h.city from hospital h,examination e where h.id= e.hospital_id group by h.city ";
				if(maxExaminationCount.getText().length()>0) {
					int maxexam = Integer.parseInt(maxExaminationCount.getText());
					String add = "Select h.city from hospital h, examination e where h.id=e.hospital_id  group by h.city having count(*) <"+maxexam;
					query+= "intersect "+add;
				}
				if(minExaminationCount.getText().length()>0) {
					int minexam = Integer.parseInt(minExaminationCount.getText());
					String add = "select h.city from hospital h, examination e  where h.id=e.hospital_id group by h.city having count(*) > "+minexam;
					query+= " intersect "+add;
				}
				if(minPositiveTestCount.getText().length()>0) {
					int minpositive = Integer.parseInt(minPositiveTestCount.getText());
					String add = "select h.city from hospital h,examination e where h.id = e.hospital_id and e.test_result= true group by h.city having count(*)>"+minpositive;
					query+= " intersect "+add;
				}
				if(maxPositiveTestCount.getText().length()>0) {
					int maxpositive = Integer.parseInt(maxPositiveTestCount.getText());
					String add = "select h.city from hospital h, examination e where h.id = e.hospital_id and e.test_result = true group by h.city having count(*)<"+maxpositive;
					query+= " intersect "+add;
				}
				
				if(sortBy.getSelectedIndex()==1) {
					String add = "select h.city from hospital h, examination e where h.id=e.hospital_id and e.test_result != true group by h.city ";
				
					query+= " intersect "+add;
				}
				System.out.println(query);
				ResultSet rs = DbConnection.select(query);
				try {
					table.setModel(buildTableModel(rs));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DbConnection.disconnect();
				
			}
			
		});
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

		contentPane.add(filterByPositiveTestCount);
		contentPane.add(minPositiveTestCount);
		contentPane.add(maxPositiveTestCount);
		contentPane.add(dash3);

		contentPane.add(showResults);
		contentPane.add(sortBy);
		
		//Add components to ComboBox
		sortBy.addItem("Lutfen Bir Siralama Olcutu Seciniz");
		sortBy.addItem("Hic Vaka Olmayan Sehirler");

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
