package penjualanHerbal;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class Mlaporan extends JFrame {
	private JTable table;
	private DefaultTableModel tabelModel;
	private JLabel lblOmset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mlaporan frame = new Mlaporan();
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
	public Mlaporan() {
		this.setTitle("Toko Obat Herbal");
		setBounds(300, 200,750, 400);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 60, 700, 250);
		getContentPane().add(scrollPane);
		
		JLabel lblPenj = new JLabel("Laporan Transaksi");
		lblPenj.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblPenj.setBounds(20, 20, 250, 31);
		getContentPane().add(lblPenj);

		table = new JTable();
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Transaksi");
        tabelModel.addColumn("Tanggal");
        tabelModel.addColumn("Nama Kasir");
        tabelModel.addColumn("Nama Pelanggan");
        tabelModel.addColumn("Total");
		table.setModel(tabelModel);
		
		tampilTabel();
	}
	public void tampilTotalOmset()
    {
	try{
        Connection Con=conn.getCon();
        String sql = "Select sum(total) from trans";
        Statement st=Con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()){
        	lblOmset.setText(""+rs.getInt(1));
        }
        }catch (Exception ex){

        }
    }
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = conn.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM trans";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getString(4);
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
