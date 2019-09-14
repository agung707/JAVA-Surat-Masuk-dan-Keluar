package bpkd;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class viewSuratMasuk extends JFrame {

    private javax.swing.JButton btnBack;

    private javax.swing.JButton btnCari;

    private javax.swing.JButton btnMenuUtama;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JPopupMenu jPop;

    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JTable jTable1;

    private javax.swing.JMenuItem jmenSK;

    private javax.swing.JTextField txtcari;

    public viewSuratMasuk() {
    initComponents();
     setLocationRelativeTo(null);
    }

    private void initComponents() {
     
        setTable();
   
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
 new lihatSurat().setVisible(true);
        dispose();
    }

    private void btnMenuUtamaActionPerformed(java.awt.event.ActionEvent evt) {
    new menuUtama().setVisible(true);
        dispose();
    }

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {
    if (evt.isPopupTrigger()){
            JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            int column = source.columnAtPoint( evt.getPoint() );
 
            if (! source.isRowSelected(row))
                source.changeSelection(row, column, false, false);
 
            jPop.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }

    private void jmenSKActionPerformed(java.awt.event.ActionEvent evt) {
    try {
      
            int  j=JOptionPane.showConfirmDialog(null,"Apakah Anda Yakin Mau Membuka File?");
            if(j==0){
            
                int  i =jTable1.getSelectedRow();
            String no = (String) jTable1.getValueAt(i, 9);
            Desktop.getDesktop().open(new File(no));           
            
            }
        } catch (IOException ex) {
            Logger.getLogger(suratMasuk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
   try
        {
            Connection c = KoneksiDatabase.getKoneksi();
            Statement st = c.createStatement();
            String search = txtcari.getText();
            ResultSet rs = st.executeQuery("SELECT * FROM suratmasuk WHERE no like '%"+search+"%' OR asalsurat like '%"+search+
                    "%'OR nosurat like '%"+search+"%' OR tanggalsurat like '%"+search+
                    "%'OR perihal like '%"+search+"%' OR tanggalditerima like '%"+search+
                    "%'OR disposisi like '%"+search+"%' OR kepada like '%"+search+"%'OR keterangan like '%"+search+"%' ");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

            dtm.setRowCount(0);
            String [] data = new String[10];
            int i = 1;

            while(rs.next())
            {
                data[0] = rs.getString("no");
                data[1] = rs.getString("asalsurat");
                data[2] = rs.getString("nosurat");
                data[3] = rs.getString("tanggalsurat");
                data[4] = rs.getString("perihal");
                data[5] = rs.getString("tanggalditerima");
                data[6] = rs.getString("disposisi");
                data[7] = rs.getString("kepada");
                data[8] = rs.getString("keterangan");
                data[9] = rs.getString("lokasi");
                dtm.addRow(data);
                i++;
            }
            rs.close();
            txtcari.setText("");
        }
        catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,"Data yang Anda cari Tidak dapat Ditemukan");

        }
    }

 

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
 setExtendedState(viewSuratMasuk.MAXIMIZED_BOTH);
   
    }

    public static void main(String args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewSuratMasuk().setVisible(true);
            }
        });
    }

    private void setTable() {
    int a = 10000;
    Object[][] data = new Object[a][10];
    try {
        Connection c = KoneksiDatabase.getKoneksi();
        Statement st = c.createStatement();
        String sql = "select *from suratmasuk";
        ResultSet  rs= st.executeQuery(sql);
        
        int i = 0;
        while(rs.next())
        {
            data[i] [0] =rs.getString("no");
            data[i] [1] =rs.getString("asalsurat");
            data[i] [2] =rs.getString("nosurat");
            data[i] [3] =rs.getString("tanggalsurat");
            data[i] [4] =rs.getString("perihal");
            data[i] [5] =rs.getString("tanggalditerima");
            data[i] [6] =rs.getString("disposisi");
            data[i] [7] =rs.getString("kepada");
            data[i] [8] =rs.getString("keterangan");
            data[i] [9] =rs.getString("lokasi");
            i++;
        }
        
        String [] setJudul = {"No","Asal Surat","No Surat","Tanggal Surat","Perihal","Tanggal DIterima","Disposisi","Kepada","Keterangan","Lokasi"};  
        jTable1.setModel(new DefaultTableModel (data,setJudul));
        
        
        
        
        data = new Object [i][20];
            }
            catch (Exception sa){
                System.out.println("Gagal Di Tampilkan"+sa.toString());
            }
    }
}
