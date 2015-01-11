package penjualanHerbal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

public class utamaKasir extends JFrame {
	private static final int LEBAR = 500;
	private static final int TINGGI = 250;
	private static final int POS_X = 356;
	private static final int POS_Y = 241;
	public static javax.swing.JFrame f;
	public static javax.swing.JFrame h;

	// menjalankan aplikasi
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					utamaKasir frame = new utamaKasir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// mengatur layout tampilan
	public utamaKasir() {
		// mengatur lebar tampilan
		setSize(LEBAR, TINGGI);
		setLocation(POS_X, POS_Y);

		// memberi warna tampilan
		getContentPane().setBackground(new Color(238, 238, 238));

		// Membuat Button

		JButton btPj = new JButton("Penjualan");
		btPj.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				f = new Mpenjualan();
				f.show();
			}
		});
		btPj.setBounds(10, 50, 200, 31);
		getContentPane().add(btPj);

		JButton btUs = new JButton("Maintenance User");
		btUs.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				h = new Muser();
				h.show();
			}
		});
		btUs.setBounds(240, 50, 200, 31);
		getContentPane().add(btUs);

		JButton btKl = new JButton("Keluar");
		btKl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login Login = new Login();
				Login.setVisible(true);
				setVisible(false);
			}
		});
		btKl.setBounds(10, 150, 100, 31);
		getContentPane().add(btKl);

		// membuat panel
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Menu Utama - Kasir",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 82, 418, 175);
		getContentPane().add(panel);

		setForeground(Color.GRAY);
		setTitle("Toko Obat Herbal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.GRAY);
	}

}