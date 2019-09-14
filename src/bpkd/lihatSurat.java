package bpkd;


import javax.swing.JFrame;

public class lihatSurat extends JFrame {

    private javax.swing.JButton btnMenuUtama;

    private javax.swing.JButton btnSuratKeluar;

    private javax.swing.JButton btnSuratMasuk;

    private javax.swing.JLabel jLabel1;

    public lihatSurat() {
    initComponents();
     setLocationRelativeTo(null);
    }

    private void initComponents() {
    
    }

    private void btnSuratMasukActionPerformed(java.awt.event.ActionEvent evt) {
      new viewSuratMasuk().setVisible(true);
        dispose();
    }

    private void btnSuratKeluarActionPerformed(java.awt.event.ActionEvent evt) {
      new viewSuratKeluar().setVisible(true);
        dispose();
    }

    private void btnMenuUtamaActionPerformed(java.awt.event.ActionEvent evt) {
      new menuUtama().setVisible(true);
        dispose();
    }

   
    public static void main(String args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lihatSurat().setVisible(true);
            }
        });
    }
}
