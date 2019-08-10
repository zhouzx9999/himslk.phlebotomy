/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smapling;

import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JOptionPane;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author buddh
 */
public class Sampling extends javax.swing.JFrame {

    /**
     * Creates new form Sampling
     */
    public Sampling() {
        initComponents();
        this.getRootPane().setDefaultButton(btnPrintLabels);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBillNo = new javax.swing.JLabel();
        txtBillNo = new javax.swing.JTextField();
        btnPrintLabels = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblBillNo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        txtBillNo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        btnPrintLabels.setText("Print Labels");
        btnPrintLabels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintLabelsActionPerformed(evt);
            }
        });

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setText("Bill No");

        jLabel1.setText("Log");

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblBillNo)
                        .addGap(457, 457, 457))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(btnPrintLabels, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClear)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtBillNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnPrintLabels, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBillNo)
                    .addComponent(jLabel1)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String sendRestfulRequest(String url) {
        String output = "";
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource(url);

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            output = response.getEntity(String.class);

            return output;

        } catch (Exception e) {

            e.printStackTrace();

        }
        return "";
    }

    public String parseJsonAndGeneratePrintCommand(String json, String template) {
        /**
         * https://www.testingexcellence.com/how-to-parse-json-in-java/
         */
        String cmd = "";
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("Barcodes");
            for (int i = 0; i < arr.length(); i++) {
                String t = template;
                String name = arr.getJSONObject(i).getString("name");
                String insId = arr.getJSONObject(i).getString("insid");
                String tests = arr.getJSONObject(i).getString("tests");
                String barcode = arr.getJSONObject(i).getString("barcode");
                t = t.replace("[name]", name);
                t = t.replace("[insid]", insId);
                t = t.replace("[tests]", tests);
                t = t.replace("[barcode]", barcode);
                cmd += t;
            }
        } catch (JSONException ex) {
            Logger.getLogger(Sampling.class.getName()).log(Level.SEVERE, null, ex);
            return cmd;
        }
        return cmd;
    }

    private void btnPrintLabelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintLabelsActionPerformed
        if (txtBillNo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the Bill No", "Error", JOptionPane.ERROR_MESSAGE);
            txtLog.setText(txtLog.getText() + "\nBill Number Entered is Empty ");
            txtBillNo.requestFocus();
        }
        txtLog.setText(txtLog.getText() + "\nBill Number Entered = " + txtBillNo.getText());
        String url = Prefs.getUrlValue() + "api/lims/samples/[SampleId]/[UserName]/[Password]";
        url = url.replace("[SampleId]", txtBillNo.getText());
        url = url.replace("[UserName]", Prefs.getUsername());
        url = url.replace("[Password]", Prefs.getPassword());
        txtLog.setText(txtLog.getText() + "\nRequest = " + url);
        String res = sendRestfulRequest(url);
        txtLog.setText(txtLog.getText() + "\nResponse = " + res);
        String printCmd = parseJsonAndGeneratePrintCommand(res, Prefs.getPrintSample());
        txtLog.setText(txtLog.getText() + "\nPrint Command = " + printCmd);
        printZpl(printCmd);
    }//GEN-LAST:event_btnPrintLabelsActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.getRootPane().setDefaultButton(btnPrintLabels);
    }//GEN-LAST:event_formWindowOpened

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtLog.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    public void printZpl(String commands) {
        String printerName = Prefs.getPrinter().toLowerCase();
        PrintService service = null;
        // Get array of all print services
        PrintService[] services = PrinterJob.lookupPrintServices();

        // Retrieve a print service from the array
        for (int index = 0; service == null && index < services.length; index++) {

            if (services[index].getName().toLowerCase().indexOf(printerName) >= 0) {
                service = services[index];
            }
        }

        if (service != null) {

            InputStream is = null;
            try {
                is = new ByteArrayInputStream(commands.getBytes("UTF8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(PrinterSettings.class.getName()).log(Level.SEVERE, null, ex);
            }

            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(new Copies(1));

            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(is, flavor, null);
            DocPrintJob job = service.createPrintJob();

            PrintJobWatcher pjw = new PrintJobWatcher(job);
            try {
                job.print(doc, pras);
            } catch (PrintException ex) {
                Logger.getLogger(PrinterSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
            pjw.waitForDone();
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(PrinterSettings.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Printer NOT available", "printer Error", JOptionPane.ERROR);
        }
        txtBillNo.setText("");
    }

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
            java.util.logging.Logger.getLogger(Sampling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sampling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sampling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sampling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sampling().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnPrintLabels;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBillNo;
    private javax.swing.JTextField txtBillNo;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables
}
