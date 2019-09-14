package bpkd;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class suratKeluar extends JFrame  {

    public String TGL;

    SimpleDateFormat format;

    private javax.swing.JLabel Perihal;

    private javax.swing.JButton btnBack;

    private javax.swing.JButton btnEdit;

    private javax.swing.JButton btnMenuUtama;

    private javax.swing.JButton btncari1;

    private javax.swing.JButton btncarifile;

    private javax.swing.JButton btnhapus;

    private javax.swing.JButton btnsimpan;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JLabel jLabel8;

    private javax.swing.JPopupMenu jPop;

    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JTable jTable1;

    private javax.swing.JMenuItem jmen;

    private javax.swing.JTextField txtcari;

    private javax.swing.JFormattedTextField txtkdsurat;

    private javax.swing.JTextField txtketerangan;

    private javax.swing.JTextField txtkirimkpd;

    private javax.swing.JTextField txtno;

    private javax.swing.JTextField txtperihal;

    private com.toedter.calendar.JDateChooser txttglsurat;

    private String lokasi;

    public suratKeluar() {
   initComponents();
     setLocationRelativeTo(null);
    }

    private void bersih() {
       txtno.setText(null);
    txtkirimkpd.setText(null);
    txtkdsurat.setText(null);
    txttglsurat.setDate(null);
    txtperihal.setText(null);
    txtketerangan.setText(null);
    jLabel7.setText(null);
    }

    private void ubah() {
     try {       
           Connection c = KoneksiDatabase.getKoneksi();
            String sql = "UPDATE suratkeluar SET dikirimkepada=?,kodesurat=?,tanggalsurat=?,perihal=?,keterangan=?,lokasi=? WHERE no=?";
            PreparedStatement ps =c.prepareStatement(sql); 
            
            ps.setString(1, txtkirimkpd.getText());
            ps.setString(2, txtkdsurat.getText());
            ps.setString(3, TGL);
            ps.setString(4, txtperihal.getText());
            ps.setString(5, txtketerangan.getText());
            ps.setString(6, lokasi);
            ps.setString(7, txtno.getText());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil DiEdit !");
            
            
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Data Gagal Diubah !");
    
    }
 setTable();
 bersih();
    }

    private void initComponents() {
        setLocationRelativeTo(null);
        txttglsurat.setDateFormatString(TGL);
        this.setTable();
    }

    private void btnMenuUtamaActionPerformed(java.awt.event.ActionEvent evt) {
     menuUtama mu = new menuUtama();
        mu.setVisible(true);
        this.dispose();
    }

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {
     try {
          Connection c = KoneksiDatabase.getKoneksi();
            Statement st = c.createStatement();
            String sql = "insert  into suratkeluar(no,dikirimkepada,kodesurat,tanggalsurat,perihal,keterangan,lokasi)values (?,?,?,?,?,?,?) ";
            PreparedStatement ps =c.prepareStatement(sql);
            ps.setString(1, txtno.getText());
            ps.setString(2, txtkirimkpd.getText());
            ps.setString(3, txtkdsurat.getText());
            ps.setString(4, TGL);
            ps.setString(5, txtperihal.getText());
            ps.setString(6, txtketerangan.getText());
            ps.setString(7, lokasi);
            ps.execute();
            
        }
        catch (SQLException ex) {
            Logger.getLogger(suratMasuk.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setTable();
        bersih();
    }

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {
    int i = jTable1.getSelectedRow();
      if (i== -1){
          return;
      }
        String no = (String) jTable1.getValueAt(i, 0);
        try{
        Connection c = KoneksiDatabase.getKoneksi();
        String sql = "DELETE FROM suratkeluar WHERE no = ?";
        
        PreparedStatement p = c.prepareStatement(sql);
        p.setString(1, no);
        
        p.executeUpdate();
        JOptionPane.showConfirmDialog(null,"Apakah Anda Yakin Mau Menghapus File Ini?");
        JOptionPane.showMessageDialog(null, "Data Berhasil DI Hapus", "Title : Pesan Informasi ", JOptionPane.INFORMATION_MESSAGE);
        p.close();
        }catch (SQLException e) {
                System.out.println("Terjadi Kesalahan");
            }finally {
                               this.setTable();
           }    
    }

    private void btncarifileActionPerformed(java.awt.event.ActionEvent evt) {
    JFileChooser j = new JFileChooser("This PC");
        j.showOpenDialog(null);
        File nosurat = j.getSelectedFile();
        lokasi = nosurat.getAbsolutePath();
        jLabel7.setText(lokasi);
    }

    private void jmenActionPerformed(java.awt.event.ActionEvent evt) {
     try {
      
            int  j=JOptionPane.showConfirmDialog(null,"Apakah Anda Yakin Mau Membuka File?");
            if(j==0){
            
                int  i =jTable1.getSelectedRow();
            String nosurat = (String) jTable1.getValueAt(i, 6);
            Desktop.getDesktop().open(new File(nosurat));           
            
            }
        } catch (IOException ex) {
            Logger.getLogger(suratMasuk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {
    if (evt.isPopupTrigger()){
            JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            int column = source.columnAtPoint( evt.getPoint() );
 
            if ( source.isRowSelected(row))
                source.changeSelection(row, column, false, false);
 
            jPop.show(evt.getComponent(), evt.getX(), evt.getY());
        }           
    }

    private void btncari1ActionPerformed(java.awt.event.ActionEvent evt) {
     try
        {
            Connection c = KoneksiDatabase.getKoneksi();
            Statement st = c.createStatement();
            String search = txtcari.getText();
            ResultSet rs = st.executeQuery("SELECT * FROM suratkeluar WHERE no like '%"+search+"%' OR dikirimkepada like '%"+search+
                    "%'OR kodesurat like '%"+search+"%' OR tanggalsurat like '%"+search+
                    "%'OR perihal like '%"+search+"%' OR keterangan like '%"+search+"%'");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

            dtm.setRowCount(0);
            String [] data = new String[10];
            int i = 1;

            while(rs.next())
            {
                data[0] = rs.getString("no");
                data[1] = rs.getString("dikirimkepada");
                data[2] = rs.getString("kodesurat");
                data[3] = rs.getString("tanggalsurat");
                data[4] = rs.getString("perihal");
                data[5] = rs.getString("keterangan");
                data[6] = rs.getString("lokasi");
                dtm.addRow(data);
                i++;
            }
            
            txtcari.setText("");
        }
        catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,"Data yang Anda cari Tidak dapat Ditemukan");

        } 
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
     new inputSurat().setVisible(true);
        dispose();
    }

    private void txtnoKeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == evt.VK_ENTER)
txtkirimkpd.requestFocus(); 
    }

    private void txtkirimkpdKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == evt.VK_ENTER)
txtkdsurat.requestFocus();  
    }

    private void txtkdsuratKeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == evt.VK_ENTER)
txttglsurat.requestFocus();
    }

    private void txtperihalKeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == evt.VK_ENTER)
txtketerangan.requestFocus();
    }

    private void txtketeranganKeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == evt.VK_ENTER)
btnsimpan.doClick();
    }

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {
            if(evt.getKeyCode() == evt.VK_ENTER)
btncari1.doClick(); 

    }

    private void txttglsuratPropertyChange(java.beans.PropertyChangeEvent evt) {
            if(txttglsurat.getDate()!=null){
        TGL = format.format(txttglsurat.getDate());
    }

    }


    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
          try{
        int pilih= jTable1.getSelectedRow();
        String a = (String)jTable1.getModel().getValueAt(pilih, 3);
        SimpleDateFormat f = new SimpleDateFormat ("yyyy-MM-dd");
        Date d =f.parse(a);  
        if(pilih==-1){
            return;
        }
    txtno.setText(jTable1.getValueAt(pilih, 0).toString());
    txtkirimkpd.setText(jTable1.getValueAt(pilih, 1).toString());
    txtkdsurat.setText(jTable1.getValueAt(pilih, 2).toString());
    txttglsurat.setDate(d);
    txtperihal.setText(jTable1.getValueAt(pilih, 4).toString());
    txtketerangan.setText(jTable1.getValueAt(pilih, 5).toString());
    jLabel7.setText(jTable1.getValueAt(pilih, 6).toString());
    }catch(Exception e){
    
    } 

    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
    ubah();
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
   setExtendedState(suratKeluar.MAXIMIZED_BOTH);
    }

    public static void main(String args) {
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new suratKeluar().setVisible(true);
            }
        });
    }

    private void setTable() {
     int a = 10000;
    Object[][] data = new Object[a][7];
    try {
        Connection c = KoneksiDatabase.getKoneksi();
        Statement st = c.createStatement();
        String sql = "select *from suratkeluar order by no asc";
        ResultSet  rs= st.executeQuery(sql);
        
        int i = 0;
        while(rs.next())
        {
            data[i] [0] =rs.getString("no");
            data[i] [1] =rs.getString("dikirimkepada");
            data[i] [2] =rs.getString("kodesurat");
            data[i] [3] =rs.getString("tanggalsurat");
            data[i] [4] =rs.getString("perihal");
            data[i] [5] =rs.getString("keterangan");
            data[i] [6] =rs.getString("lokasi");
            i++;
        }
        
        String [] setJudul = {"No","Dikirim Kepada","Kode Surat","Tanggal Surat","Perihal","Keterangan","Lokasi"};
        jTable1.setModel(new DefaultTableModel (data, setJudul));
        data = new Object [i][14];
            }
            catch (Exception sa){
                System.out.println("Gagal Di Tampilkan"+sa.toString());
            }
    }
}
