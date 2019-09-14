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

public class suratMasuk extends JFrame  {

    public String TGL,tgl;

    SimpleDateFormat format;

    private javax.swing.JButton btnBack;

    private javax.swing.JButton btnCari;

    private javax.swing.JButton btnCariFile;

    private javax.swing.JButton btnEdit;

    private javax.swing.JButton btnHapus;

    private javax.swing.JButton btnMenuUtama;

    private javax.swing.JButton btnSimpan;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel10;

    private javax.swing.JLabel jLabel11;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JLabel jLabel8;

    private javax.swing.JLabel jLabel9;

    private javax.swing.JPopupMenu jPop;

    private javax.swing.JScrollPane jScrollPane2;

    private javax.swing.JTable jTable1;

    private javax.swing.JMenuItem jmen;

    private javax.swing.JTextField txtasal;

    private javax.swing.JTextField txtcari;

    private javax.swing.JTextField txtdisposisi;

    private javax.swing.JTextField txtkepada;

    private javax.swing.JTextField txtket;

    private javax.swing.JTextField txtno;

    private javax.swing.JTextField txtnosurat;

    private javax.swing.JTextField txtperihal;

    private com.toedter.calendar.JDateChooser txttglditerima;

    private com.toedter.calendar.JDateChooser txttglsurat;

    private String lokasi;

    public suratMasuk() {
    initComponents();
     setLocationRelativeTo(null);
    }

    private void ubah() {
    try {       
           Connection c = KoneksiDatabase.getKoneksi();
            String sql = "UPDATE suratmasuk SET asalsurat=?,nosurat=?,tanggalsurat=?,perihal=?,tanggalditerima=?,disposisi=?,kepada=?,keterangan=?,lokasi=? WHERE no=?";
            PreparedStatement ps =c.prepareStatement(sql); 
            
            ps.setString(1, txtasal.getText());
            ps.setString(2, txtnosurat.getText());
            ps.setString(3, TGL);
            ps.setString(4, txtperihal.getText());
            ps.setString(5, tgl);
            ps.setString(6, txtdisposisi.getText());
            ps.setString(7, txtkepada.getText());
            ps.setString(8, txtket.getText());
            ps.setString(9, jLabel8.getText());
            ps.setString(10, txtno.getText());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil DiEdit !");
            
            
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Data Gagal Diubah !");
    
    }
 setTable();
 bersih();
    }

    private void bersih() {
           txtno.setText(null);
       txtasal.setText(null);
       txtnosurat.setText(null);
       txttglsurat.setDate(null);
       txtperihal.setText(null);
       txttglditerima.setDate(null);
       txtdisposisi.setText(null);
       txtkepada.setText(null);
       txtket.setText(null);
       jLabel8.setText(null);

    }

    private void initComponents() {
    setLocationRelativeTo(null);
        txttglsurat.setDateFormatString("dd-MM-yyyy");
        txttglditerima.setDateFormatString("dd-MM-yyyy");
        this.setTable();
    }


    private void btnMenuUtamaActionPerformed(java.awt.event.ActionEvent evt) {
    menuUtama mu = new menuUtama();
        mu.setVisible(true);
        this.dispose();
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
    try {
            String informasi = "Apa Anda Yakin Akan Menyimpan Data Ini?";
            int  j=JOptionPane.showConfirmDialog(null,informasi,"PERHATIAN!",JOptionPane.YES_NO_OPTION);
            if(j==0){
          Connection c = KoneksiDatabase.getKoneksi();
            Statement st = c.createStatement();
            String sql = "insert  into suratmasuk(no,asalsurat,nosurat,tanggalsurat,perihal,tanggalditerima,disposisi,kepada,keterangan,lokasi)values (?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps =c.prepareStatement(sql);
            ps.setString(1, txtno.getText());
            ps.setString(2, txtasal.getText());
            ps.setString(3, txtnosurat.getText());
            ps.setString(4, TGL);
            ps.setString(5, txtperihal.getText());
            ps.setString(6, tgl);
            ps.setString(7, txtdisposisi.getText());
            ps.setString(8, txtkepada.getText());
            ps.setString(9, txtket.getText());
            ps.setString(10, lokasi);
            ps.execute();
        
        
       }
            
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Harus Di Isi Semua !");
               
        }
        this.setTable();
        bersih();
  
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        int i = jTable1.getSelectedRow();
      if (i== -1){
          return;
      }
        String no = (String) jTable1.getValueAt(i, 0);
        try{
        Connection c = KoneksiDatabase.getKoneksi();
        String sql = "DELETE FROM suratmasuk WHERE no = ?";
        
        PreparedStatement p = c.prepareStatement(sql);
        p.setString(1, no);
        
        p.executeUpdate();
        int Pilih = JOptionPane.showConfirmDialog(null,"Apakah Anda Yakin Ingin Menimpan Data Ini?","INFORMASI!!!",JOptionPane.YES_NO_OPTION);
        if(Pilih == JOptionPane.OK_OPTION)
            JOptionPane.showMessageDialog(null, "Data Berhasil DiHapus!");
        else if(Pilih == JOptionPane.NO_OPTION)
            JOptionPane.showMessageDialog(null, "Data Gagal DiHapus!");
        }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Pilih Data Yang Akan DiHapus!");
            }finally {
                               this.setTable();
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

    private void btnCariFileActionPerformed(java.awt.event.ActionEvent evt) {
      JFileChooser j = new JFileChooser("This PC");
        j.showOpenDialog(null);
        File no = j.getSelectedFile();
        lokasi = no.getAbsolutePath();
        jLabel8.setText(lokasi);
    }

    private void jmenActionPerformed(java.awt.event.ActionEvent evt) {
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

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
     new inputSurat().setVisible(true);
        dispose();
    }

    private void txtnoKeyPressed(java.awt.event.KeyEvent evt) {
         if(evt.getKeyCode() == evt.VK_ENTER)
txtasal.requestFocus();
    }

    private void txtasalKeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == evt.VK_ENTER)
txtnosurat.requestFocus();
    }

    private void txtnosuratKeyPressed(java.awt.event.KeyEvent evt) {
       if(evt.getKeyCode() == evt.VK_ENTER)
txttglsurat.requestFocus();  
   
    }

    private void txtperihalKeyPressed(java.awt.event.KeyEvent evt) {
         if(evt.getKeyCode() == evt.VK_ENTER)
txttglditerima.requestFocus();  
   
    }

    private void txtdisposisiKeyPressed(java.awt.event.KeyEvent evt) {
         if(evt.getKeyCode() == evt.VK_ENTER)
txtkepada.requestFocus();  
   
    }

    private void txtkepadaKeyPressed(java.awt.event.KeyEvent evt) {
         if(evt.getKeyCode() == evt.VK_ENTER)
txtket.requestFocus();  
   
    }

    private void txtketKeyPressed(java.awt.event.KeyEvent evt) {
         if(evt.getKeyCode() == evt.VK_ENTER)
btnSimpan.doClick();
   
    }

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {
         if(evt.getKeyCode() == evt.VK_ENTER)
btnCari.doClick();
   
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

    private void txttglsuratPropertyChange(java.beans.PropertyChangeEvent evt) {
         if(txttglsurat.getDate()!=null){
        TGL = format.format(txttglsurat.getDate());
    }
    }

    private void txttglditerimaPropertyChange(java.beans.PropertyChangeEvent evt) {
      if(txttglditerima.getDate()!=null){
        tgl = format.format(txttglditerima.getDate());   
        
    }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
    ubah();
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
       try{
        int pilih= jTable1.getSelectedRow();
        String a = (String)jTable1.getModel().getValueAt(pilih, 3);
        String b = (String)jTable1.getModel().getValueAt(pilih, 5);
        SimpleDateFormat f = new SimpleDateFormat ("yyyy-MM-dd");
        SimpleDateFormat g = new SimpleDateFormat ("yyyy-MM-dd");
        Date d =f.parse(a);
        Date e =g.parse(b);
        
    txtno.setText(jTable1.getValueAt(pilih, 0).toString());
    txtasal.setText(jTable1.getValueAt(pilih, 1).toString());
    txtnosurat.setText(jTable1.getValueAt(pilih, 2).toString());
    txttglsurat.setDate(d);
    txtperihal.setText(jTable1.getValueAt(pilih, 4).toString());
    txttglditerima.setDate(e);
    txtdisposisi.setText(jTable1.getValueAt(pilih, 6).toString());
    txtkepada.setText(jTable1.getValueAt(pilih, 7).toString());
    txtket.setText(jTable1.getValueAt(pilih, 8).toString());
    jLabel8.setText(jTable1.getValueAt(pilih, 9).toString());
        
   
    }catch(Exception e){
    
    }
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
       setExtendedState(suratMasuk.MAXIMIZED_BOTH);
    }

    public static void main(String args) {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new suratMasuk().setVisible(true);
           
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
        jTable1.setModel(new DefaultTableModel (data, setJudul));
  
        
        data = new Object [i][20];
            }
            catch (Exception sa){
                System.out.println("Gagal Di Tampilkan"+sa.toString());
            }
   
    }
}
