package bpkd;


import javax.swing.JFrame;

public class menuUtama extends JFrame {

    private javax.swing.JMenu jMenu1;

    private javax.swing.JMenuBar jMenuBar1;

    private javax.swing.JMenuItem jMenuItem1;

    private javax.swing.JMenuItem jMenuItem2;

    private javax.swing.JMenuItem jMenuItem3;

    public menuUtama() {
    initComponents();
     setLocationRelativeTo(null);
    }

    private void initComponents() {
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
     new loginPV().setVisible(true);
        dispose();
    }

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
     new login().setVisible(true);
 dispose();
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
   new lihatSurat().setVisible(true);
        dispose();
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
     setExtendedState(forgot.MAXIMIZED_BOTH);
    }

    public static void main(String args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuUtama().setVisible(true);
            }
        });
   
    }
}
