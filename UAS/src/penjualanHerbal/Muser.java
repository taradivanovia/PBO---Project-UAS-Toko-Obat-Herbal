package penjualanHerbal;

import java.awt.EventQueue;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Muser extends JFrame {
	private JTextField usr;
	private JTextField pass;
	private JTable tbUs;
	private DefaultTableModel tabelModel;
	private DefaultComboBoxModel model = new DefaultComboBoxModel(); 
	
	private static final int LEBAR = 925;
	private static final int TINGGI = 350;
	private static final int POS_X = 200;
	private static final int POS_Y = 200;

	public Muser() {
		setTitle("Toko Obat Herbal");
		// mengatur lebar tampilan
		setSize(LEBAR, TINGGI);
		setLocation(POS_X, POS_Y);
		getContentPane().setLayout(null);
		
		//membuat scroll
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(550, 50, 350, 250);
		getContentPane().add(scrollPane);
		
		//membuat combo box
		final JComboBox cS = new JComboBox();
		cS.setModel(new DefaultComboBoxModel(new String[] {"Admin","Kasir"}));
		cS.setBounds(127, 170, 108, 20);
		getContentPane().add(cS);
		
		//Membuat Tabel
		tbUs = new JTable();
		scrollPane.setViewportView(tbUs);
		//Memberikan reaksi klik kepada tabel
		tbUs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = tbUs.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		       
		        String user = (String) tabelModel.getValueAt(a, 0);
		        usr.setText(user);
		        String passw = (String) tabelModel.getValueAt(a, 1);
		        pass.setText(passw);
		        String cbn = (String) tabelModel.getValueAt(a, 2);
		        cS.setSelectedItem(cbn);
		        usr.setEnabled(false);
			}
		});
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("Username");
        tabelModel.addColumn("Password");
        tabelModel.addColumn("Jenis");
        tbUs.setModel(tabelModel);	
        tbUs.setSize(50, 50);
		showTable();
		
		//Membuat Label
		JLabel lblNewLabel = new JLabel("Maintenance User");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel.setBounds(20, 20, 250, 31);
		getContentPane().add(lblNewLabel);
		
		JLabel Username = new JLabel("Username  :");
		Username.setBounds(43, 70, 71, 14);
		getContentPane().add(Username);
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setBounds(43, 120, 71, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblJns = new JLabel("Jenis         :");
		lblJns.setBounds(43, 170, 71, 14);
		getContentPane().add(lblJns);
		
		//membuat text field
		usr = new JTextField();
		usr.setBounds(124, 70, 257, 20);
		getContentPane().add(usr);
		usr.setColumns(10);
		
		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(124, 120, 156, 20);
		getContentPane().add(pass);

		//Membuat Button
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = conn.getCon();
		        String query = "INSERT INTO pengguna VALUES(?,?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, usr.getText());
		        prepare.setString(2, pass.getText());
		        prepare.setString(3, (String) cS.getSelectedItem());


		        prepare.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        	showTable();
		            //refresh();
		        }
			}
		});
		btnTambah.setBounds(98, 220, 96, 38);
		getContentPane().add(btnTambah);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					try{
				        Connection konek = conn.getCon();
				        String query = "UPDATE pengguna SET pass = ?, jenis = ? WHERE usr = ?";
				        PreparedStatement prepare = konek.prepareStatement(query);
				       
				        prepare.setString(1, pass.getText());
				        prepare.setString(2, (String) cS.getSelectedItem());
				        prepare.setString(3, usr.getText());
				      
				        prepare.executeUpdate();
				        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
				        prepare.close();
				        }
				        
				        catch(Exception ex)
				        {
				            JOptionPane.showMessageDialog(null, "Data gagal diubah");
				            System.out.println(ex);
				        }
				        finally
				        {
				            showTable();
				            usr.setEnabled(true);
				            //refresh();            
				        }
			}
		});
		btnUbah.setBounds(260, 220, 96, 38);
		getContentPane().add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try
		        {
		            Connection konek = conn.getCon();
		            String query = "DELETE FROM pengguna WHERE usr = ?";
		            PreparedStatement prepare = konek.prepareStatement(query);
		            
		            prepare.setString(1, usr.getText());
		            prepare.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
		            prepare.close();
		        }
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
		            System.out.println(ex);
		        }
		        finally
		        {
		        	showTable();
		        	usr.setEnabled(true);
		            //refresh();
		        }
			}
		});
		btnHapus.setBounds(416, 220, 96, 38);
		getContentPane().add(btnHapus);
	}
	
	public void showTable()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = conn.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM pengguna";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	//menjalankan aplikasi
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Muser frame = new Muser();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}
