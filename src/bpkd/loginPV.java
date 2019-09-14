package bpkd;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class loginPV extends JFrame  {

    private javax.swing.JButton btnVerifikasi;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JPasswordField txtpass;

    public loginPV() {
    initComponents();
     setLocationRelativeTo(null);
    }

    private void initComponents() {
    }

    private void btnVerifikasiActionPerformed(java.awt.event.ActionEvent evt) {
    try {
            Connection c = KoneksiDatabase.getKoneksi();
            Statement st = c.createStatement();
           String sql = "SELECT * FROM loginpv WHERE password='"+txtpass.getText()+" ' ";
            ResultSet rs = st.executeQuery(sql);
           
            if(rs.next()){
                if(txtpass.getText().equals(rs.getString("password"))){
                JOptionPane.showMessageDialog(null, "Login Berhasil!");
                new inputSurat().setVisible(true);
                dispose();
                }
            }else{
                    JOptionPane.showMessageDialog(null, "username atau password salah");
                }
        }catch(SQLException e){
        
        }
    }

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {
    new forgotPV().setVisible(true);
        dispose();
    }

    private void txtpassKeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == evt.VK_ENTER)
btnVerifikasi.doClick();
    }

    
    public static void main(String args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginPV().setVisible(true);
            }
        });
    }
}
