/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Ayoze Gil
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vista
     */
    public Vista() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_gastos = new javax.swing.JTable();
        panel_gasto = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtfield_concepto_gasto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtfield_importe_gasto = new javax.swing.JTextField();
        btn_añadir_gasto = new javax.swing.JButton();
        panel_fichero = new javax.swing.JPanel();
        txtfield_nombre_fichero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_abrir_fichero = new javax.swing.JButton();
        btn_seleccionar_fichero = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        label_mensaje = new javax.swing.JLabel();
        btn_guardar_cambios = new javax.swing.JButton();
        panel_dia = new javax.swing.JPanel();
        date_picker = new org.jdesktop.swingx.JXDatePicker();
        btn_seleccionar_dia = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tabla_gastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Importe", "Concepto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_gastos);

        panel_gasto.setBorder(javax.swing.BorderFactory.createTitledBorder("Gasto/Ingreso"));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Concepto:");

        jLabel5.setText("€");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Importe:");

        btn_añadir_gasto.setText("Añadir");
        btn_añadir_gasto.setActionCommand("AñadirMovimiento");

        javax.swing.GroupLayout panel_gastoLayout = new javax.swing.GroupLayout(panel_gasto);
        panel_gasto.setLayout(panel_gastoLayout);
        panel_gastoLayout.setHorizontalGroup(
            panel_gastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_gastoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_gastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_gastoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtfield_concepto_gasto))
                    .addGroup(panel_gastoLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtfield_importe_gasto, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_añadir_gasto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel_gastoLayout.setVerticalGroup(
            panel_gastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_gastoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_gastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtfield_concepto_gasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(panel_gastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_gastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtfield_importe_gasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_gastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_añadir_gasto)
                        .addComponent(jLabel5)))
                .addContainerGap())
        );

        panel_fichero.setBorder(javax.swing.BorderFactory.createTitledBorder("Fichero"));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText(".xml");

        btn_abrir_fichero.setText("Abrir");
        btn_abrir_fichero.setActionCommand("AbrirFichero");

        btn_seleccionar_fichero.setText("+");
        btn_seleccionar_fichero.setActionCommand("SeleccionarFichero");
        btn_seleccionar_fichero.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_seleccionar_fichero.setMaximumSize(new java.awt.Dimension(23, 23));
        btn_seleccionar_fichero.setMinimumSize(new java.awt.Dimension(23, 23));
        btn_seleccionar_fichero.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout panel_ficheroLayout = new javax.swing.GroupLayout(panel_fichero);
        panel_fichero.setLayout(panel_ficheroLayout);
        panel_ficheroLayout.setHorizontalGroup(
            panel_ficheroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ficheroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_seleccionar_fichero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfield_nombre_fichero, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_abrir_fichero)
                .addContainerGap())
        );
        panel_ficheroLayout.setVerticalGroup(
            panel_ficheroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ficheroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ficheroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfield_nombre_fichero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btn_abrir_fichero)
                    .addComponent(btn_seleccionar_fichero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("CONTROL DE GASTOS");

        label_mensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_mensaje.setText("Selecciona un fichero xml");

        btn_guardar_cambios.setText("Guardar");
        btn_guardar_cambios.setActionCommand("GuardarCambios");

        panel_dia.setBorder(javax.swing.BorderFactory.createTitledBorder("Dia"));

        btn_seleccionar_dia.setText("Seleccionar");
        btn_seleccionar_dia.setActionCommand("SeleccionarDia");

        javax.swing.GroupLayout panel_diaLayout = new javax.swing.GroupLayout(panel_dia);
        panel_dia.setLayout(panel_diaLayout);
        panel_diaLayout.setHorizontalGroup(
            panel_diaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_diaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(date_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_seleccionar_dia)
                .addGap(39, 39, 39))
        );
        panel_diaLayout.setVerticalGroup(
            panel_diaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_diaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_diaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_seleccionar_dia))
                .addContainerGap())
        );

        btn_Salir.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btn_Salir.setForeground(new java.awt.Color(153, 0, 51));
        btn_Salir.setText("x");
        btn_Salir.setActionCommand("Salir");
        btn_Salir.setMargin(new java.awt.Insets(0, 0, 3, 0));
        btn_Salir.setMaximumSize(new java.awt.Dimension(40, 40));
        btn_Salir.setMinimumSize(new java.awt.Dimension(40, 40));
        btn_Salir.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(panel_dia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panel_gasto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panel_fichero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(btn_guardar_cambios)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_fichero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel_gasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_guardar_cambios)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton btn_abrir_fichero;
    private javax.swing.JButton btn_añadir_gasto;
    private javax.swing.JButton btn_guardar_cambios;
    private javax.swing.JButton btn_seleccionar_dia;
    private javax.swing.JButton btn_seleccionar_fichero;
    private org.jdesktop.swingx.JXDatePicker date_picker;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_mensaje;
    private javax.swing.JPanel panel_dia;
    private javax.swing.JPanel panel_fichero;
    private javax.swing.JPanel panel_gasto;
    private javax.swing.JTable tabla_gastos;
    private javax.swing.JTextField txtfield_concepto_gasto;
    private javax.swing.JTextField txtfield_importe_gasto;
    private javax.swing.JTextField txtfield_nombre_fichero;
    // End of variables declaration//GEN-END:variables

    public JButton getBtn_abrir_fichero() {
        return btn_abrir_fichero;
    }

    public void setBtn_abrir_fichero(JButton btn_abrir_fichero) {
        this.btn_abrir_fichero = btn_abrir_fichero;
    }

    public JButton getBtn_seleccionar_fichero() {
        return btn_seleccionar_fichero;
    }

    public void setBtn_seleccionar_fichero(JButton btn_seleccionar_fichero) {
        this.btn_seleccionar_fichero = btn_seleccionar_fichero;
    }

    public JButton getBtn_seleccionar_dia() {
        return btn_seleccionar_dia;
    }

    public void setBtn_seleccionar_dia(JButton btn_seleccionar_dia) {
        this.btn_seleccionar_dia = btn_seleccionar_dia;
    }

    public JButton getBtn_añadir_gasto() {
        return btn_añadir_gasto;
    }

    public void setBtn_añadir_gasto(JButton btn_añadir_gasto) {
        this.btn_añadir_gasto = btn_añadir_gasto;
    }

    public JButton getBtn_guardar_cambios() {
        return btn_guardar_cambios;
    }

    public void setBtn_guardar_cambios(JButton btn_guardar_cambios) {
        this.btn_guardar_cambios = btn_guardar_cambios;
    }

    public JButton getBtn_Salir() {
        return btn_Salir;
    }

    public void setBtn_Salir(JButton btn_Salir) {
        this.btn_Salir = btn_Salir;
    }
    
    public JTable getTabla_gastos() {
        return tabla_gastos;
    }

    public void setTabla_gastos(JTable tabla_gastos) {
        this.tabla_gastos = tabla_gastos;
    }

    public JTextField getTxtfield_concepto_gasto() {
        return txtfield_concepto_gasto;
    }

    public void setTxtfield_concepto_gasto(JTextField txtfield_concepto_gasto) {
        this.txtfield_concepto_gasto = txtfield_concepto_gasto;
    }

    public JTextField getTxtfield_importe_gasto() {
        return txtfield_importe_gasto;
    }

    public void setTxtfield_importe_gasto(JTextField txtfield_importe_gasto) {
        this.txtfield_importe_gasto = txtfield_importe_gasto;
    }

    public JTextField getTxtfield_nombre_fichero() {
        return txtfield_nombre_fichero;
    }

    public void setTxtfield_nombre_fichero(JTextField txtfield_nombre_fichero) {
        this.txtfield_nombre_fichero = txtfield_nombre_fichero;
    }

    public JLabel getLabel_mensaje() {
        return label_mensaje;
    }

    public void setLabel_mensaje(JLabel label_mensaje) {
        this.label_mensaje = label_mensaje;
    }

    public JXDatePicker getDate_picker() {
        return date_picker;
    }
}
