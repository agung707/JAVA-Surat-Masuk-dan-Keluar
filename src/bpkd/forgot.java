package bpkd;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class forgot extends JFrame {

    private javax.swing.JButton btnReset;

    private javax.swing.JButton btnVerifikasi;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JTextField txtemail;

    private javax.swing.JTextField txtnewpass;

    private javax.swing.JTextField txtuser;

    public forgot() {
 initComponents();
     setLocationRelativeTo(null);
    }

    private void initComponents() {
  
    txtnewpass.setEditable(false);
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
    String  newpass = txtnewpass.getText();
       
        try{
            Connection c = KoneksiDatabase.getKoneksi();
             String sql = "UPDATE login SET password =?";
              PreparedStatement p = c.prepareStatement(sql);
              
              
            p.setString(1,newpass);
            p.executeUpdate();
    
            p.close();
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Reset Passord Gagal !");
        }   
        JOptionPane.showMessageDialog(null,"Reset Password Berhasil!");
        
    }

    private void btnVerifikasiActionPerformed(java.awt.event.ActionEvent evt) {
   try {
            Connection c = KoneksiDatabase.getKoneksi();
            Statement st = c.createStatement();
           String sql = "SELECT * FROM login WHERE email='"+txtemail.getText()+"' AND username='"+txtuser.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
           
            if(rs.next()){
                if(txtemail.getText().equals(rs.getString("email")) && txtuser.getText().equals(rs.getString("username"))){
                JOptionPane.showMessageDialog(null, "Silahkan Isi Password Baru!");
                txtnewpass.setEditable(true);
                }
            }else{
                txtnewpass.setEditable(false);
                    JOptionPane.showMessageDialog(null, "Email Atau Username Salah!");
                }
        }catch(SQLException e){
        
        }
    }

    private void txtuserKeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == evt.VK_ENTER)
btnVerifikasi.doClick();
    }

    private void txtnewpassKeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == evt.VK_ENTER)
btnReset.doClick();
    }

    public static void main(String args) {
     java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new forgot().setVisible(true);
            }
        });
    }
}
