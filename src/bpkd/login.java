/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpkd;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
/**
 *
 * @author agung707
 */
public class login extends JFrame{

    private javax.swing.JCheckBox Show;

    private javax.swing.JButton btnlogin;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JPasswordField txtpass;

    private javax.swing.JTextField txtuser;
    
    
    public login(){
     initComponents();
     setLocationRelativeTo(null);
    }
    
     private void initComponents() {
    
    }
    
    public void aksi() {
         if(Show.isSelected()){
              txtpass.setEchoChar((char)0);
              Show.setText("Tampilkan");
          }else{
            txtpass.setEchoChar('*');
          Show.setText("Sembunyikan ");
          }
    }
        

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {
    try {
            Connection c = KoneksiDatabase.getKoneksi();
            Statement st = c.createStatement();
           String sql = "SELECT * FROM login WHERE username='"+txtuser.getText()+"' AND password='"+txtpass.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
           
            if(rs.next()){
                if(txtuser.getText().equals(rs.getString("username")) && txtpass.getText().equals(rs.getString("password"))){
                new menuUtama().setVisible(true);
                dispose();
                }
            }else{
                    JOptionPane.showMessageDialog(null, "username atau password salah");
                }
        }catch(SQLException e){
        
        }
    }

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {
  new forgot().setVisible(true);
        dispose();
    }

    private void txtpassKeyPressed(java.awt.event.KeyEvent evt) {
    if(evt.getKeyCode() == evt.VK_ENTER)
btnlogin.doClick();
    }

    private void ShowActionPerformed(java.awt.event.ActionEvent evt) {
  aksi();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
        UIManager.setLookAndFeel(new McWinLookAndFeel());
    } catch (Exception e) {
    }
                new login().setVisible(true);
            }
        });
 
    }
    
}
