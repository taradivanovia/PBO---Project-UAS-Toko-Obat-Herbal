package penjualanHerbal;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class produk extends JFrame {
	private JTable table;
	private JTextField IDProduk;
	private JTextField NamaProduk;
	private JTextField IDSupplier;
	private JTextField Harga;
	private DefaultTableModel tabelModel;
	private DefaultComboBoxModel model = new DefaultComboBoxModel(); 
	private static final int LEBAR = 925;
	private static final int TINGGI = 400;
	private static final int POS_X = 200;
	private static final int POS_Y = 200;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					produk frame = new produk();
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
	public produk() {
		setTitle("Toko Obat Herbal");
		// mengatur lebar tampilan
		setSize(LEBAR, TINGGI);
		setLocation(POS_X, POS_Y);
		getContentPane().setLayout(null);
		
		JSpinner stok = new JSpinner();
		stok.setBounds(140, 270, 44, 20);
		getContentPane().add(stok);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(450, 50, 450, 250);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int a = table.getSelectedRow();

				if (a == -1) {
					return;
				}

				String idp = (String) tabelModel.getValueAt(a, 0);
				IDProduk.setText(idp);
				String nps = (String) tabelModel.getValueAt(a, 1);
				NamaProduk.setText(nps);
				String ids = (String) tabelModel.getValueAt(a, 2);
				IDSupplier.setText(ids);
				int hrg = (Integer) tabelModel.getValueAt(a, 3);
				Harga.setText("" + hrg);
				int stk = (Integer) tabelModel.getValueAt(a, 4);
				stok.setValue(stk);
				IDProduk.setEnabled(true);
				NamaProduk.setEnabled(true);
				IDSupplier.setEnabled(true);
			}
		});
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("ID Supplier");
        tabelModel.addColumn("Harga Produk");
        tabelModel.addColumn("Stok");
		table.setModel(tabelModel);	
		showTable();
		//label
        JLabel lblNewLabel = new JLabel("Maintenance Products");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel.setBounds(20, 20, 250, 31);
		getContentPane().add(lblNewLabel);
		
		JLabel lblIdp = new JLabel("ID Produk            :");
		lblIdp.setBounds(43, 70, 96, 14);
		getContentPane().add(lblIdp);
		
		JLabel lblIdsupplier = new JLabel("Nama Produk     :");
		lblIdsupplier.setBounds(43, 120, 96, 14);
		getContentPane().add(lblIdsupplier);
		
		JLabel lblNamaProduk = new JLabel("ID Supplier    		      :");
		lblNamaProduk.setBounds(43, 170, 96, 14);
		getContentPane().add(lblNamaProduk);
		
		JLabel lblHarga = new JLabel("Harga                   :");
		lblHarga.setBounds(43, 220, 96, 14);
		getContentPane().add(lblHarga);
		
		JLabel lblStok = new JLabel("Stok                      :");
		lblStok.setBounds(43, 270, 96, 14);
		getContentPane().add(lblStok);
		
		//text field
		IDProduk = new JTextField();
		IDProduk.setBounds(140, 70, 100, 18);
		getContentPane().add(IDProduk);
		IDProduk.setColumns(10);
		
		NamaProduk = new JTextField();
		NamaProduk.setColumns(10);
		NamaProduk.setBounds(140, 120, 270, 18);
		getContentPane().add(NamaProduk);
		
		IDSupplier = new JTextField();
		IDSupplier.setColumns(10);
		IDSupplier.setBounds(140, 170, 70, 18);
		getContentPane().add(IDSupplier);
		
		Harga = new JTextField();
		Harga.setColumns(10);
		Harga.setBounds(140, 220, 78, 18);
		getContentPane().add(Harga);
		
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			        Connection konek = conn.getCon();
			        String query = "INSERT INTO products VALUES(?,?,?,?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, IDProduk.getText());
			        prepare.setString(2, NamaProduk.getText());
			        prepare.setString(3, IDSupplier.getText());
			        prepare.setInt(4, Integer.parseInt(Harga.getText()));
			        prepare.setInt(5, (Integer) stok.getValue());


			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data Disimpan");
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
			        }
			}
		});
		btnTambah.setBounds(128, 320, 89, 32);
		getContentPane().add(btnTambah);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = conn.getCon();
			        String query = "UPDATE products SET  harga = ? WHERE id = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, Harga.getText());
			        prepare.setInt(2, (Integer) stok.getValue());
			        prepare.setString(3, IDProduk.getText());
			        
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
			            IDProduk.setEnabled(true);          
			        }
			}
		});
		btnUbah.setBounds(261, 320, 89, 32);
		getContentPane().add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus ?", "Confirmasi", JOptionPane.YES_NO_OPTION);
				if (opcion == 0) {
					try
			        {
			            Connection konek = conn.getCon();
			            String query = "DELETE FROM products WHERE id = ?";
			            PreparedStatement prepare = konek.prepareStatement(query);
			            
			            prepare.setString(1, IDProduk.getText());
			            
			            prepare.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Data Dihapus");
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
			            IDProduk.setEnabled(true);
			        }
				} else {
				   System.out.print("Data Tidak Terhapus");
				}
				
				
			}
		});
		btnHapus.setBounds(382, 320, 89, 32);
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
            String query = "SELECT * FROM products";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	
	
}
