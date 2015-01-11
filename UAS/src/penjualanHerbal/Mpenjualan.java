package penjualanHerbal;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Mpenjualan extends JFrame {

	// private JTextField namabeli;
	private JLabel lblNewLabel;
	private JLabel lblIdTransaksi;
	private JLabel lblNamaProduct;
	private JTextField namaproduk;
	private JLabel lblHarga;
	private JTextField harga;
	private JLabel lblJumlah;
	private JLabel lblTotal;
	private JLabel label_1;
	private JLabel lblJumlahBarang;
	private JLabel label_2;
	private JLabel lblBayar;
	private JTextField textField_1;
	private JLabel lblRp;
	private JButton btnBayar;
	private JTable table;
	private DefaultTableModel tabelModel;
	private JLabel label;
	private JLabel lblNamaPembeli;
	private JButton btnBeli;
	private JLabel lblIdProduct;
	private JSpinner spinner;
	private JButton btnTambah;
	private static String dtrans;
	private static int jumlahBeli = 0;
	private JComboBox cmbKasir;
	private DefaultComboBoxModel model = new DefaultComboBoxModel();
	private JTextField namabeli;
	private static final int LEBAR = 820;
	private static final int TINGGI = 500;
	private static final int POS_X = 200;
	private static final int POS_Y = 150;

	/**
	 * Create the frame.
	 */
	public void disable() {
		lblNamaPembeli.setEnabled(false);
		namabeli.setEnabled(false);
		btnBeli.setEnabled(false);
	}

	public void enabled() {
		table.setEnabled(true);
		lblIdProduct.setEnabled(true);
		cmbKasir.setEnabled(true);
		lblNamaProduct.setEnabled(true);
		lblHarga.setEnabled(true);
		lblJumlah.setEnabled(true);
		spinner.setEnabled(true);
		btnTambah.setEnabled(true);
		lblJumlahBarang.setEnabled(true);
		lblTotal.setEnabled(true);
		lblBayar.setEnabled(true);
		textField_1.setEnabled(true);
		btnBayar.setEnabled(true);
	}

	public void setTanggal() {
		java.util.Date skrng = new java.util.Date();
		java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		lblNewLabel.setText(kal.format(skrng));
	}

	public void autoNumber() {
		try {
			Connection konek = conn.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM trans ";
			ResultSet rs = state.executeQuery(query);
			if (rs.next() == false) {
				label.setText("T001");
			} else {
				rs.last();
				int IDPesan = rs.getInt(1) + 1;
				label.setText("T00" + IDPesan);

			}
			rs.close();
			state.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void autoDtrans() {
		try {
			Connection konek = conn.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM dTrans ";

			ResultSet rs = state.executeQuery(query);
			if (rs.next() == false) {
				dtrans = "dt001";
			} else {
				rs.last();
				int IDPesan = rs.getInt(1) + 1;
				String IDFix = "00" + IDPesan;
				dtrans = "dt" + IDFix;
			}
			rs.close();
			state.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void tampilTotal() {
		String kpesan = label.getText();
		try {
			Connection konek = conn.getCon();
			Statement st = konek.createStatement();
			ResultSet rs = st
					.executeQuery("select SUM(subtotal) from dTrans where idtrans='"
							+ kpesan + "'");
			while (rs.next()) {
				label_1.setText("" + rs.getInt(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Create the frame.
	 */
	public Mpenjualan() {
		setTitle("Toko Obat Herbal");
		// mengatur lebar tampilan
		setSize(LEBAR, TINGGI);
		setLocation(POS_X, POS_Y);
		getContentPane().setLayout(null);

		autoDtrans();
// membuat Label
		JLabel lblPenj = new JLabel("Penjualan");
		lblPenj.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblPenj.setBounds(20, 20, 250, 31);
		getContentPane().add(lblPenj);

		JLabel lblDt = new JLabel("Data Pelanggan");
		lblDt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDt.setBounds(20, 50, 250, 31);
		getContentPane().add(lblDt);
		
		getContentPane().setLayout(null);
		lblNamaPembeli = new JLabel("Nama Pembeli ");
		lblNamaPembeli.setBounds(20, 80, 90, 14);
		getContentPane().add(lblNamaPembeli);

		namabeli = new JTextField();
		namabeli.setBounds(121, 80, 170, 20);
		getContentPane().add(namabeli);
		namabeli.setColumns(10);

		btnBeli = new JButton("OK");
		btnBeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection konek = conn.getCon();
					String query = "INSERT INTO trans VALUES(?,?,?,?,0)";
					PreparedStatement prepare = konek.prepareStatement(query);

					prepare.setString(1, label.getText());
					prepare.setString(2, lblNewLabel.getText());
					prepare.setString(3, Login.nama);
					prepare.setString(4, namabeli.getText());

					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,
							"Data berhasil disimpan");
					prepare.close();
					tampilTabel();
					disable();
					enabled();
				}

				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Data gagal disimpan");
					System.out.println(ex);
				} finally {
				}
			}
		});
		btnBeli.setBounds(300, 80, 89, 23);
		getContentPane().add(btnBeli);

		lblNewLabel = new JLabel("-");
		lblNewLabel.setBounds(200, 424, 83, 23);
		getContentPane().add(lblNewLabel);
		setTanggal();

		lblIdTransaksi = new JLabel("ID transaksi ");
		lblIdTransaksi.setBounds(579, 11, 100, 14);
		getContentPane().add(lblIdTransaksi);

		Panel panel = new Panel();
		panel.setBounds(10, 43, 782, 370);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblIdProduct = new JLabel("ID Product ");
		lblIdProduct.setEnabled(false);
		lblIdProduct.setBounds(20, 120, 69, 20);
		panel.add(lblIdProduct);

		lblNamaProduct = new JLabel("Nama Product");
		lblNamaProduct.setEnabled(false);
		lblNamaProduct.setBounds(20, 150, 95, 14);
		panel.add(lblNamaProduct);

		namaproduk = new JTextField();
		namaproduk.setEnabled(false);
		namaproduk.setBounds(132, 150, 275, 20);
		panel.add(namaproduk);
		namaproduk.setColumns(10);

		lblHarga = new JLabel("Harga :");
		lblHarga.setEnabled(false);
		lblHarga.setBounds(20, 180, 46, 14);
		panel.add(lblHarga);

		harga = new JTextField();
		harga.setEnabled(false);
		harga.setBounds(132, 180, 275, 20);
		panel.add(harga);
		harga.setColumns(10);

		lblJumlah = new JLabel("Jumlah");
		lblJumlah.setEnabled(false);
		lblJumlah.setBounds(20, 210, 46, 14);
		panel.add(lblJumlah);

		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setBounds(132, 210, 46, 20);
		panel.add(spinner);

		btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection konek = conn.getCon();
					String query = "INSERT INTO dTrans VALUES(?,?,?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);

					prepare.setString(1, dtrans);
					prepare.setString(2, label.getText());
					prepare.setString(3, (String) cmbKasir.getSelectedItem());
					prepare.setInt(4, (Integer) spinner.getValue());
					prepare.setInt(5, Integer.valueOf(harga.getText())
							* (Integer) spinner.getValue());

					prepare.executeUpdate();
					jumlahBeli += 1;
					label_2.setText("" + jumlahBeli);
					JOptionPane.showMessageDialog(null,
							"Data pembelian disimpan");
					prepare.close();
				}

				catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Data pembelian gagal disimpan");
					System.out.println(ex);
				} finally {
				}

				// tampil total
				autoDtrans();
				tampilTotal();

				// update total database
				try {
					Connection konek = conn.getCon();
					String query = "UPDATE trans SET total = ? WHERE idtrans = ?";
					PreparedStatement prepare = konek.prepareStatement(query);

					prepare.setInt(1, Integer.parseInt(label_1.getText()));
					prepare.setString(2, label.getText());

					prepare.executeUpdate();
					prepare.close();
				}

				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Data gagal diubah");
					System.out.println(ex);
				} finally {
				}

				// kurang stok
				try {
					Connection konek = conn.getCon();
					String query = "UPDATE products SET stok = stok-? WHERE id = ?";
					PreparedStatement prepare = konek.prepareStatement(query);

					prepare.setInt(1, (Integer) spinner.getValue());
					prepare.setString(2, (String) cmbKasir.getSelectedItem());

					prepare.executeUpdate();
					tampilTabel();
					table.setModel(tabelModel);
					prepare.close();
				}

				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Stok gagal dikurangi");
					System.out.println(ex);
				} finally {
				}
			}
		});
		btnTambah.setEnabled(false);
		btnTambah.setBounds(329, 240, 89, 23);
		panel.add(btnTambah);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(450, 50, 320, 250);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
		tabelModel.addColumn("ID Product");
		tabelModel.addColumn("Nama Product");
		tabelModel.addColumn("Harga");
		tabelModel.addColumn("Jumlah");
		tabelModel.addColumn("Total Harga");
		table.setModel(tabelModel);
		tampilTabel();
		table.setEnabled(false);

		lblTotal = new JLabel("Total ");
		lblTotal.setEnabled(false);
		lblTotal.setBounds(20, 270, 63, 14);
		panel.add(lblTotal);

		label_1 = new JLabel("-");
		label_1.setBounds(250, 270, 46, 14);
		panel.add(label_1);

		lblJumlahBarang = new JLabel("Jumlah Barang ");
		lblJumlahBarang.setEnabled(false);
		lblJumlahBarang.setBounds(20, 300, 79, 14);
		panel.add(lblJumlahBarang);

		label_2 = new JLabel("-");
		label_2.setBounds(250, 300, 46, 14);
		panel.add(label_2);

		lblBayar = new JLabel("Sub Total ");
		lblBayar.setEnabled(false);
		lblBayar.setBounds(20, 330, 63, 14);
		panel.add(lblBayar);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(280, 330, 95, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		lblRp = new JLabel("Rp.");
		lblRp.setBounds(250, 330, 23, 14);
		panel.add(lblRp);

		btnBayar = new JButton("Bayar");
		btnBayar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kembalian =Integer.parseInt(textField_1.getText())-Integer.parseInt(label_1.getText());
	            JOptionPane.showMessageDialog(null, kembalian);
				autoNumber();
				tampilTabel();
				jumlahBeli=0;
			}
		});
		btnBayar.setEnabled(false);
		btnBayar.setBounds(667, 324, 89, 23);
		panel.add(btnBayar);

		cmbKasir = new JComboBox();
		cmbKasir.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				String nama = cmbKasir.getSelectedItem().toString();
				try {
					Connection konek = conn.getCon();
					Statement st = konek.createStatement();
					ResultSet rs = st
							.executeQuery("select nama,harga from products where id='"
									+ nama + "'");
					while (rs.next()) {
						namaproduk.setText(rs.getString(1));
						harga.setText("" + rs.getInt(2));
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		cmbKasir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		cmbKasir.setBounds(132, 127, 174, 20);
		panel.add(cmbKasir);

		label = new JLabel("-");
		label.setBounds(702, 11, 46, 14);
		getContentPane().add(label);

		autoNumber();
		// akses method isiSupplier
		isiProduk();
		// model comboBox di set sesuai comboBoxModel pada method isiSupplier
		cmbKasir.setModel(model);
	}

	public void tampilTabel() {
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
		try {
			Connection konek = conn.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT dt.idbarang,p.nama,p.harga,dt.jumlah,dt.subtotal FROM trans t join dTrans dt on t.idtrans=dt.idtrans join products p on dt.idbarang=p.id where dt.idtrans='"
					+ label.getText() + "'";

			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				Object obj[] = new Object[5];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getInt(3);
				obj[3] = rs.getInt(4);
				obj[4] = rs.getInt(5);

				tabelModel.addRow(obj);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void isiProduk() {
		try {
			Connection konek = conn.getCon();
			Statement st = konek.createStatement();
			ResultSet rs = st.executeQuery("select id from products");
			while (rs.next()) {
				model.addElement(rs.getString(1));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
