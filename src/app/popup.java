package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class popup extends JFrame {

	private JPanel contentPane;
	private CreateHospitalWindow hospital;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					popup frame = new popup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public popup() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 281, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel completed = new JLabel("Isleminiz Basariyla Gerceklestirildi.");
		contentPane.add(completed, BorderLayout.CENTER);
		completed.setHorizontalAlignment(SwingConstants.CENTER);
		completed.setVerticalAlignment(SwingConstants.CENTER);
		
		JButton close = new JButton("New button");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				hospital.setVisible(false);
				
				
			}
		});
		contentPane.add(close, BorderLayout.SOUTH);
		close.setText("Kapat");
		
	
	}

}
