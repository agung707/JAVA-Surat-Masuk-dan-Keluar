package bpkd;


import javax.swing.JFrame;

public class inputSurat extends JFrame{

    private javax.swing.JButton btnMenuUtama;

    private javax.swing.JButton btnSuratKeluar;

    private javax.swing.JButton btnSuratMasuk;

    private javax.swing.JLabel jLabel1;

    public inputSurat() {
 initComponents();
     setLocationRelativeTo(null);
    }

    private void initComponents() {
    
    }

    private void btnSuratMasukActionPerformed(java.awt.event.ActionEvent evt) {
     new suratMasuk().setVisible(true);
        dispose();
    }

    private void btnSuratKeluarActionPerformed(java.awt.event.ActionEvent evt) {
     new suratKeluar().setVisible(true);
        dispose();
    }

    private void btnMenuUtamaActionPerformed(java.awt.event.ActionEvent evt) {
     new menuUtama().setVisible(true);
        dispose();
    }

    public static void main(String args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inputSurat().setVisible(true);
            }
        });
    }
}
