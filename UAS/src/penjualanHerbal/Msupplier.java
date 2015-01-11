package penjualanHerbal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class Msupplier extends JFrame {
	private JTable table;
	private JTextField IDsupplier;
	private JTextField NamaSupplier;
	private DefaultTableModel tabelModel;
	private static final int LEBAR = 925;
	private static final int TINGGI = 350;
	private static final int POS_X = 200;
	private static final int POS_Y = 200;

	/**
	 * Create the frame.
	 */
	public Msupplier() {
		setTitle("Toko Obat Herbal");
		// mengatur lebar tampilan
		setSize(LEBAR, TINGGI);
		setLocation(POS_X, POS_Y);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(550, 50, 350, 250);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();

				if (a == -1) {
					return;
				}

				String ids = (String) tabelModel.getValueAt(a, 0);
				IDsupplier.setText(ids);
				String nama = (String) tabelModel.getValueAt(a, 1);
				NamaSupplier.setText(nama);
			}
		});

		tabelModel = new DefaultTableModel();
		tabelModel.addColumn("ID Supplier");
		tabelModel.addColumn("Nama Supplier");
		table.setModel(tabelModel);
		tampilTabel();

		JLabel lblNewLabel = new JLabel("Maintenance Supplier");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel.setBounds(20, 20, 250, 31);
		getContentPane().add(lblNewLabel);

		JLabel IDSupplier = new JLabel("ID Supplier          :");
		IDSupplier.setBounds(38, 121, 103, 14);
		getContentPane().add(IDSupplier);

		JLabel lblNamaSupplier = new JLabel("Nama Supplier   :");
		lblNamaSupplier.setBounds(38, 169, 119, 14);
		getContentPane().add(lblNamaSupplier);

		IDsupplier = new JTextField();
		IDsupplier.setBounds(140, 118, 197, 20);
		getContentPane().add(IDsupplier);
		IDsupplier.setColumns(10);

		NamaSupplier = new JTextField();
		NamaSupplier.setColumns(10);
		NamaSupplier.setBounds(140, 166, 286, 20);
		getContentPane().add(NamaSupplier);

		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection konek = conn.getCon();
					String query = "INSERT INTO supp VALUES(?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);

					prepare.setString(1, IDsupplier.getText());
					prepare.setString(2, NamaSupplier.getText());

					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,
							"Data berhasil disimpan");
					prepare.close();
				}

				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Data gagal disimpan");
					System.out.println(ex);
				} finally {
					tampilTabel();
					// refresh();
				}
			}
		});
		btnTambah.setBounds(140, 244, 89, 36);
		getContentPane().add(btnTambah);

		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection konek = conn.getCon();
					String query = "DELETE FROM supp WHERE id = ?";
					PreparedStatement prepare = konek.prepareStatement(query);

					prepare.setString(1, IDsupplier.getText());
					prepare.executeUpdate();
					JOptionPane
							.showMessageDialog(null, "Data berhasil dihapus");
					prepare.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Data gagal dihapus");
					System.out.println(ex);
				} finally {
					tampilTabel();
					IDsupplier.setEnabled(true);
					// refresh();
				}
			}
		});
		btnHapus.setBounds(284, 244, 89, 36);
		getContentPane().add(btnHapus);

		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Connection konek = conn.getCon();
					String query = "UPDATE supp SET  nama = ? WHERE id = ?";
					PreparedStatement prepare = konek.prepareStatement(query);

					prepare.setString(1, NamaSupplier.getText());
					prepare.setString(2, IDsupplier.getText());

					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data berhasil diubah");
					prepare.close();
				}

				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Data gagal diubah");
					System.out.println(ex);
				} finally {
					tampilTabel();
					IDsupplier.setEnabled(true);
					// refresh();
				}
			}
		});
		btnUbah.setBounds(426, 244, 89, 36);
		getContentPane().add(btnUbah);
	}

	public void tampilTabel() {
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
		try {
			Connection konek = conn.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT * FROM supp";

			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				Object obj[] = new Object[2];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);

				tabelModel.addRow(obj);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// menjalankan aplikasi
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Msupplier frame = new Msupplier();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
