/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException;
import contexto.Año;
import contexto.Dia;
import contexto.LibroCuentas;
import contexto.Mes;
import contexto.Movimiento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import vista.Vista;

/**
 *
 * @author Ayoze Gil
 */
public final class Controlador implements ActionListener{
 
    //Atributo para controlar el flujo del programa
    int estado = 0;
    //Atributos para cargar datos a la vista
    Vista vista_principal;
    Tabla tabla;
    Dia dia;
    //Atributos para el mapeo de ficheros xml.
    LibroCuentas libroCuentas;
    File fichero;
    JAXBContext contexto;
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    
    public Controlador() {
        
        try {
            //Definimos un contexto de objetos de negocio a partir de la clase 
            //LibroCuentas en una instancia de la clase JAXBContext
            contexto = JAXBContext.newInstance( LibroCuentas.class );
            //Inicializamos la vista
            vista_principal = new Vista();
            tabla = new Tabla();
            vista_principal.getTabla_gastos().setModel(tabla);
            vista_principal.setVisible(true);
            iniciarEventos();
            estado = 0;
        } catch (JAXBException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Método que prepara los manejadores de eventos.
    public void iniciarEventos(){
        vista_principal.getBtn_seleccionar_fichero().addActionListener(this);
        vista_principal.getBtn_abrir_fichero().addActionListener(this);
        vista_principal.getBtn_añadir_gasto().addActionListener(this);
        vista_principal.getBtn_guardar_cambios().addActionListener(this);
        vista_principal.getBtn_seleccionar_dia().addActionListener(this);
        vista_principal.getBtn_Salir().addActionListener(this);
    }
    
    //Metodo para seleccionar desde el gestor de ficheros
    //un fichero xml ubicado en la carpeta "ficheros" del proyecto
    public void seleccionarFichero(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new java.io.File("./ficheros"));
        int seleccion = fileChooser.showOpenDialog(vista_principal);
        if (seleccion == JFileChooser.APPROVE_OPTION){
           fichero = fileChooser.getSelectedFile();
           vista_principal.getTxtfield_nombre_fichero().
                   setText(fichero.getName().substring(0, fichero.getName().length()-4));
        }
    }
    
    //Método que realiza el mapeo de un fichero a objetos Java siguiendo el
    //contexto definido
    public void abrirFichero(){
        try {
            tabla.getDataVector().clear();
            tabla.fireTableDataChanged();
            vista_principal.getTxtfield_concepto_gasto().setText("");
            vista_principal.getTxtfield_importe_gasto().setText("");
            vista_principal.getDate_picker().setDate(null);
            fichero = new File("./ficheros/"+
                    vista_principal.getTxtfield_nombre_fichero().getText()+".xml");
            
            // Si el fichero no existe creamos una nueva instancia de LibroCuentas
            if(!fichero.exists() || fichero.length() == 0){
                vista_principal.getLabel_mensaje().
                        setText("Fichero no existe o está vacío. Seleccione día");
                libroCuentas = new LibroCuentas();
            }
            else {
                
                // A partir del contexto creado en el constructor instanciamos un
                // objeto Unmarshaller que mapea el fichero XML y lo deserializa
                // en un objeto de clase LibroCuentas.
                unmarshaller = contexto.createUnmarshaller();
                libroCuentas = (LibroCuentas)unmarshaller.unmarshal(fichero);
                
                
                tabla.getDataVector().clear();
                libroCuentas.getAños().forEach((A) -> {
                    A.getMeses().forEach((M) -> {
                        M.getDias().forEach((D) -> {
                            D.getMovimientos().forEach((Mv) -> {
                                tabla.getDataVector().add(
                                        new String[]{String.valueOf(
                                                Mv.getImporte()),Mv.getConcepto()});
                            });
                        });
                    });
                });
                tabla.fireTableDataChanged();
                vista_principal.getLabel_mensaje().
                        setText("Fichero cargado. Selecciona día");
            }
            estado = 1;
        } catch (ClassCastException ex) {
            vista_principal.getLabel_mensaje().
                    setText("La estructura no coincide. Revise fichero xml");
        } catch (UnmarshalException | IllegalAnnotationsException ex) {
            vista_principal.getLabel_mensaje().
                    setText("Sintaxis incorrecta. Revise fichero xml");
        } catch (JAXBException ex) {
            vista_principal.getLabel_mensaje().
                    setText("Se ha producido un error al leer el fichero");
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Método que obtiene los objetos que representan la fecha seleccionada
    public void diaSeleccionado(){
        
        Año año;
        Mes mes;

        if (estado == 0){
            vista_principal.getLabel_mensaje().
                    setText("El fichero no está listo. Cargue o Revise fichero xml");
        }
        else{
            if (vista_principal.getDate_picker().getDate() != null){
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = formato.format(vista_principal.getDate_picker().getDate());
                
                //Algoritmo que comprueba de forma ordenada si hay instanciados objetos
                //Año, Mes y Dia en sus respectivos ArrayList. Si no existen se crea una 
                //nueva instancia.
                año = null;
                for (Año A : libroCuentas.getAños()){
                    if(A.getAño().equals(fecha.substring(6, 10)))
                        año = A;
                }
                if (año == null){
                    libroCuentas.getAños().add(new Año(fecha.substring(6, 10), new ArrayList<>()));
                    año = libroCuentas.getAños().get(libroCuentas.getAños().size()-1);
                }
                mes = null;
                for (Mes M : año.getMeses()){
                    if(M.getMes().equals(fecha.substring(3, 5)))
                        mes = M;     
                }
                if (mes == null){
                    año.getMeses().add(new Mes(fecha.substring(3, 5), new ArrayList<>()));
                    mes = año.getMeses().get(año.getMeses().size()-1);
                }
                dia = null;
                for (Dia D : mes.getDias()){
                    if(D.getDia().equals(fecha.substring(0, 2)))
                        dia = D;     
                }
                if (null == dia){
                    mes.getDias().add(new Dia(fecha.substring(0, 2), new ArrayList<>()));
                    dia = mes.getDias().get(mes.getDias().size()-1);
                }
                
                //Se muestra en la tabla los movimientos que hubiese en el Dia instanciado
                tabla.getDataVector().clear();
                dia.getMovimientos().forEach((Mv) -> {
                    tabla.getDataVector().add(
                            new String[]{String.valueOf(
                                    Mv.getImporte()),Mv.getConcepto()});
                });
                tabla.fireTableDataChanged();
                vista_principal.getLabel_mensaje().
                    setText("Fecha seleccionada correctamente");
                estado = 2;
            }
            else
                vista_principal.getLabel_mensaje().
                    setText("No ha seleccionado una fecha");
        }
        
        
    }
    
    //Método que añade un nuevo Movimiento en el día seleccionado
    public void añadirMovimiento(){
        
        String concepto;
        int importe;
        
        if (estado < 2){
            vista_principal.getLabel_mensaje().
                    setText("No ha seleccionado una fecha para añadir movimiento");
        }
        else{
            if ((!"".equals(vista_principal.getTxtfield_concepto_gasto().getText()))){
                try{
                    concepto = vista_principal.getTxtfield_concepto_gasto().getText();    
                    importe = Integer.parseInt(vista_principal.getTxtfield_importe_gasto().getText());

                    dia.getMovimientos().add(new Movimiento(concepto, importe));   
                    tabla.getDataVector().add(new String[]{String.valueOf(importe),concepto});    
                    tabla.fireTableDataChanged();
                } catch(NumberFormatException e){
                    vista_principal.getLabel_mensaje().
                            setText("El importe no es correcto");
                }
            }
            else
                vista_principal.getLabel_mensaje().
                            setText("No ha rellenado el campo \"Concepto");
        }
    }
    
    //Método que serializa en un fichero xml
    public void guardarCambios(){
        try {
            vista_principal.getTxtfield_concepto_gasto().setText("");
            vista_principal.getTxtfield_importe_gasto().setText("");
            vista_principal.getDate_picker().setDate(null);
            tabla.getDataVector().clear();
            tabla.fireTableDataChanged();
            
            fichero = new File("./ficheros/"+
                    vista_principal.getTxtfield_nombre_fichero().getText()+".xml");
            //A partir del contexto se usa un objeto marshaller para serializar
            //el objeto libroCuentas y guardarlo en formato xml en el fichero.
            marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(libroCuentas, fichero);
            
            vista_principal.getLabel_mensaje().
                            setText("Guardado en fichero. Puede continuar editando"); 
        } catch (IllegalArgumentException ex) {
            vista_principal.getLabel_mensaje().
                            setText("No existe modelo para convertir a xml"); 
        } catch (JAXBException ex) {
            vista_principal.getLabel_mensaje().
                            setText("Se ha producido un error al leer los datos"); 
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case "SeleccionarFichero":
                seleccionarFichero();
                break;
            case "AbrirFichero":
                abrirFichero();
                break;
            case "SeleccionarDia":
                diaSeleccionado();
                break;
            case "AñadirMovimiento":
                añadirMovimiento();
                break;
            case "GuardarCambios":
                guardarCambios();
                break;
            case "Salir":
                System.exit(0);
            default:  
        }
    }
}