package app;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppWindow {
	private JFrame frame;
	private CitiesWindow citiesWindow;
	private HospitalsWindow hospitalsWindow;
	private DoctorsWindow doctorsWindow;
	private PatientsWindow patientsWindow;
	private ExaminationsWindow examinationsWindow;
	private JLabel title;
	private JButton cities;
	private JButton hospitals;
	private JButton doctors;
	private JButton patients;
	private JButton examinations;

	public JFrame getFrame() {
		return frame;
	}

	public AppWindow() {
		initialize();
	}

	private void initialize() {
		// Create child windows
		citiesWindow = new CitiesWindow(this);
		hospitalsWindow = new HospitalsWindow(this);
		doctorsWindow = new DoctorsWindow(this);
		patientsWindow = new PatientsWindow(this);
		examinationsWindow = new ExaminationsWindow(this);

		// Configure frame
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 328);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		// Create components
		title = new JLabel("COVID-19 HASTALARI B�LG� S�STEM�");
		cities = new JButton("�ehir Bilgileri");
		hospitals = new JButton("Hastane Bilgileri");
		doctors = new JButton("Doktor Bilgileri");
		patients = new JButton("Hasta Bilgileri");
		examinations = new JButton("Muayene Bilgileri");

		// Configure components
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Calibri", Font.BOLD, 16));
		title.setBounds(10, 11, 414, 43);
		cities.setBounds(10, 54, 414, 36);
		hospitals.setBounds(10, 101, 414, 36);
		doctors.setBounds(10, 148, 414, 36);
		patients.setBounds(10, 195, 414, 36);
		examinations.setBounds(10, 242, 414, 36);

		// Add components to the panel
		frame.getContentPane().add(title);
		frame.getContentPane().add(cities);
		frame.getContentPane().add(hospitals);
		frame.getContentPane().add(doctors);
		frame.getContentPane().add(patients);
		frame.getContentPane().add(examinations);

		// Button actions
		cities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getFrame().setVisible(false);
					citiesWindow.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		hospitals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getFrame().setVisible(false);
					hospitalsWindow.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		doctors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getFrame().setVisible(false);
					doctorsWindow.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		patients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getFrame().setVisible(false);
					patientsWindow.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		examinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getFrame().setVisible(false);
					examinationsWindow.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "İşlem başarısız!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
