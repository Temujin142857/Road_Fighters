/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadfighters_richdesign;

/**
 *
 * @author root
 */
public class defeat extends javax.swing.JFrame {

    /**
     * Creates new form defeat
     */
    public defeat() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p1 = new javax.swing.JPanel();
        quit = new javax.swing.JButton();
        mm = new javax.swing.JButton();
        retry = new javax.swing.JButton();
        info = new javax.swing.JButton();
        l4 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        l1 = new javax.swing.JLabel();
        l5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        p1.setBackground(new java.awt.Color(0, 0, 0));

        quit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/roadfighters_richdesign/quit.png"))); // NOI18N
        quit.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 0, 0), null));
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });

        mm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/roadfighters_richdesign/menu.png"))); // NOI18N
        mm.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 102, 255), null));
        mm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmActionPerformed(evt);
            }
        });

        retry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/roadfighters_richdesign/retry.png"))); // NOI18N
        retry.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 255, 0), null));
        retry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retryActionPerformed(evt);
            }
        });

        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/roadfighters_richdesign/info.png"))); // NOI18N
        info.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 102, 255), null));
        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
            }
        });

        l4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/roadfighters_richdesign/defeat.gif"))); // NOI18N

        l3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/roadfighters_richdesign/rip.png"))); // NOI18N

        l2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/roadfighters_richdesign/download (6).png"))); // NOI18N

        l1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/roadfighters_richdesign/gameover.gif"))); // NOI18N

        l5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/roadfighters_richdesign/skull.gif"))); // NOI18N

        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p1Layout.createSequentialGroup()
                        .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p1Layout.createSequentialGroup()
                        .addComponent(l5)
                        .addGap(58, 58, 58)))
                .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(retry, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mm, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))))
            .addGroup(p1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(l1)
                .addGap(253, 253, 253)
                .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(p1Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(p1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(l5)
                        .addGap(18, 18, 18)
                        .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))))
            .addGroup(p1Layout.createSequentialGroup()
                .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mm, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(retry, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(383, 383, 383))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
        //Allows the user to exit the program
        System.exit(0);
    }//GEN-LAST:event_quitActionPerformed

    private void mmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmActionPerformed
        //Allows the user to access the main menu 
        Menu mm = new Menu();
        mm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mmActionPerformed

    private void retryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retryActionPerformed
        Combat_map2.main(null);
        this.dispose();
    }//GEN-LAST:event_retryActionPerformed

    private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed
        Info info = new Info();
        info.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_infoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(defeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(defeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(defeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(defeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new defeat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton info;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JButton mm;
    private javax.swing.JPanel p1;
    private javax.swing.JButton quit;
    private javax.swing.JButton retry;
    // End of variables declaration//GEN-END:variables
}
