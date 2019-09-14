package bpkd;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class forgotPV extends JFrame{

    private javax.swing.JButton btnReset;

    private javax.swing.JButton btnVerfikasi;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JTextField txtemail;

    private javax.swing.JTextField txtnewpas1;

    public forgotPV() {
  initComponents();
     setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLocationRelativeTo(null);
        txtnewpas1.setEditable(false);
    }

    private void btnVerfikasiActionPerformed(java.awt.event.ActionEvent evt) {
    try {
            Connection c = KoneksiDatabase.getKoneksi();
            Statement st = c.createStatement();
           String sql = "SELECT * FROM loginpv WHERE email='"+txtemail.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
           
            if(rs.next()){
                if(txtemail.getText().equals(rs.getString("email"))){
                JOptionPane.showMessageDialog(null, "Silahkan Isi Password Baru!");
                txtnewpas1.setEditable(true);
                }
            }else{
                txtnewpas1.setEditable(false);
                    JOptionPane.showMessageDialog(null, "Email Salah!");
                }
        }catch(SQLException e){
        
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
     String newpass=txtnewpas1.getText();
        try{
            Connection c = KoneksiDatabase.getKoneksi();
             String sql = "UPDATE loginpv SET password =?";
              PreparedStatement p = c.prepareStatement(sql);
              
              
            p.setString(1,newpass);
            p.executeUpdate();
    
            p.close();
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Reset Passord Gagal !");
        }   
        JOptionPane.showMessageDialog(null,"Reset Password Berhasil!");
        
        loginPV obj6 = new loginPV();
        obj6.setVisible(true);
        this.dispose();
    }


    public static void main(String args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new forgotPV().setVisible(true);
            }
        });
    }
}
