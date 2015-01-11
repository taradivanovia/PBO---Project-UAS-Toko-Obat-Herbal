package penjualanHerbal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import java.awt.SystemColor;
import java.sql.*;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;


public class Login extends JFrame {
	private JTextField textField_1;
	private JPasswordField passwordField;
	public static String nama;
	private static final int LEBAR=500;
	private static final int TINGGI=150;
	private static final int POS_X=356;
	private static final int POS_Y=241;
	public static javax.swing.JFrame f;
	public static javax.swing.JFrame h;
	
	//menjalankan aplikasi
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//mengatur layout tampilan
	public Login() {
		//mengatur lebar tampilan
		setSize(LEBAR,TINGGI);
		setLocation(POS_X,POS_Y);
		
		//button OK
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
			        Connection Con=conn.getCon();
			        String sql = "Select * from pengguna where usr='"+textField_1.getText()+"' and pass='" +passwordField.getText()+ "'";
			        Statement st=Con.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        if (rs.next()){
			        if (rs.getString(1).equalsIgnoreCase("admin"))
			        {
			            f = new utamaAdmin();
			        	f.show();
			        	setVisible(false);
			        }else{
			        	h = new utamaKasir();
			        	h.show();
			        	setVisible(false); 
			        }
			        }else{
			        JOptionPane.showMessageDialog(null, "Login gagal");
			        }
			        }catch (Exception ex){
			        JOptionPane.showMessageDialog(null, "Login gagal (koneksi)");
			        }
			}
		});
		btnNewButton.setBounds(50, 65, 67, 31);
		getContentPane().add(btnNewButton);
		
		//button Cancel
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {System.exit(0);
			}
		});
		btnCancel.setBounds(120, 65, 99, 31);
		getContentPane().add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 82, 418, 175);
		getContentPane().add(panel);
		
		JLabel lblUsr = new JLabel("Username");
		panel.add(lblUsr);
		lblUsr.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setBackground(SystemColor.text);
		textField_1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);
		lblPassword.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(passwordField);
		passwordField.setColumns(10);
		passwordField.setBackground(new Color(255, 255, 255));
		setForeground(Color.GRAY);
		setTitle("Toko Obat Herbal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.GRAY);
		setVisible(true);
	}
	


}