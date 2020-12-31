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
public class CreateHospitalWindow extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JLabel title;
	private JLabel nameLabel;
	private JLabel cityLabel;
	private JComboBox<String> city;
	private JButton submit;

	public CreateHospitalWindow() {
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
		cityLabel = new JLabel("Bulundugu Sehir:");
		name = new JTextField();
		city = new JComboBox<String>();
		nameLabel = new JLabel("Adi:");
		title = new JLabel("YENI HASTANE");
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
		
		
		String[] sehirler ={"Adana","Adiyaman", "Afyon", "Agri", "Amasya", "Ankara", "Antalya", "Artvin",
	            "Aydin", "Balikesir","Bilecik", "Bingol", "Bitlis", "Bolu", "Burdur", "Bursa", "Canakkale",
	            "Cankiri", "Corum","Denizli","Diyarbakir", "Edirne", "Elazig", "Erzincan", "Erzurum ", "Eskisehir",
	            "Gaziantep", "Giresun","Gumushane", "Hakkari", "Hatay", "Isparta", "Mersin", "Istanbul", "Izmir",
	            "Kars", "Kastamonu", "Kayseri","Kirklareli", "Kirsehir", "Kocaeli", "Konya", "Kutahya ", "Malatya",
	            "Manisa", "Kahramanmaras", "Mardin", "Mugla", "Mus", "Nevsehir", "Nigde", "Ordu", "Rize", "Sakarya",
	            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdag", "Tokat", "Trabzon  ", "Tunceli", "Sanliurfa", "Usak",
	            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt ", "Karaman", "Kirikkale", "Batman", "Sirnak",
	            "Bartin", "Ardahan", "Igdir", "Yalova", "Karabuk ", "Kilis", "Osmaniye ", "Duzce"};
		
		for(String cursor:sehirler) {
			city.addItem(cursor);
						
		}
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DbConnection.connect();
				String query;
				if(name.getText().length()>0) {
				query = "insert into hospital values(nextval('hostid'),"+ "'"+name.getText()+"'" +","+"'"+sehirler[city.getSelectedIndex()]+"'"+")"; 
				
				DbConnection.update(query);
				}
				popup frame = new popup();
				frame.setVisible(true);
				DbConnection.disconnect();
							
			}
		});
	}
}
