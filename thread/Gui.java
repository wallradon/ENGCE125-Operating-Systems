package thread;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Gui extends javax.swing.JFrame {

    private ArrayList<String> list = new ArrayList<>(List.of("Dog", "Cat", "ante"));
    private SearchALG salg = new SearchALG();

    private volatile String words = "";
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Gui.class.getName());

    public Gui() {
        initComponents();

        Thread t1 = new Thread(() -> {
            String lastText = "";
            while (true) {
                String text = EText.getText().trim();
                if (!text.equals(lastText) && !text.isEmpty()) {
                    System.out.println(text);
                    words = text;
                    lastText = text;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            String lastProcessedWord = "";

            while (true) {

                if (!words.equals(lastProcessedWord)) {

                    lastProcessedWord = words;

                    if (!words.isEmpty()) {

                        String result = salg.Search(list, words);

                        if (!result.isEmpty()) {
                            System.out.println("Match found : " + result);
                        }
                    } else {

                    }
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            int temp = 0;
            while (true) {
                int count = list.size();

                if (count != temp) {
                    SwingUtilities.invokeLater(() -> {
                        totalWord.setText("The dictionary contains " + count + " words");
                    });
                    System.out.println("Update size dictionary");
                    System.out.println("Word in Dictionary: " + list);
                    temp = count;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
//                NewText.setText("");
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        EnText = new javax.swing.JLabel();
        Header = new javax.swing.JLabel();
        EnText1 = new javax.swing.JLabel();
        NewText = new javax.swing.JTextField();
        EText = new javax.swing.JTextField();
        totalWord = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        EnText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        EnText.setText("Enter Text ");

        Header.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        Header.setText("Dictionary");

        EnText1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        EnText1.setText("Add Text ");

        NewText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewTextActionPerformed(evt);
            }
        });

        EText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ETextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EnText)
                            .addComponent(EnText1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NewText, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EText, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(totalWord))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Header)
                .addGap(184, 184, 184))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(Header)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnText)
                    .addComponent(EText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnText1)
                    .addComponent(NewText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(totalWord, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewTextActionPerformed
        String addText = NewText.getText().trim();
        if (addText.isEmpty()) {
            System.err.println("Unable to save the space.");
            NewText.setText("");
            return;
        }
        String addresult = salg.Search(list, addText);
        if (addresult.isEmpty()) {
            list.add(addText);
            System.out.println("New word added: " + addText);
        } else {
            System.err.println("This word already exists.");
        }

        NewText.setText("");
    }//GEN-LAST:event_NewTextActionPerformed

    private void ETextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ETextActionPerformed

    }//GEN-LAST:event_ETextActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Gui().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EText;
    private javax.swing.JLabel EnText;
    private javax.swing.JLabel EnText1;
    private javax.swing.JLabel Header;
    private javax.swing.JTextField NewText;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel totalWord;
    // End of variables declaration//GEN-END:variables
}
