/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Negocio;
import modelo.Lista;
import modelo.Tabla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import modelo.Guerra;
import modelo.Pais;
import vista.Vista;

/**
 *
 * @author Grupo3
 */
public final class Controlador implements ActionListener{

    //Atributos
    Negocio negocio = new Negocio();
    Vista principal = new Vista();
    Guerra guerra = new Guerra();
    Pais pais = new Pais();
    Tabla tabla = new Tabla();
    Lista lista_guerras = new Lista();
    Lista lista_paises = new Lista();
    Lista lista_paises_neutrales = new Lista();
    Lista lista_guerras_neutral = new Lista();
    
    //Constructor
    public Controlador(){
        principal.setVisible(true);
        principal.getTabla().setModel(tabla);
        procesosInicio();
        inicializacionEventos();
    }
    
    //Activación de manejadores de eventos
    private void inicializacionEventos(){
        principal.getBtn_guerras().addActionListener(this);
        principal.getBtn_paises().addActionListener(this);
        principal.getBtn_participa().addActionListener(this);
        principal.getBtn_independencia().addActionListener(this);
        principal.getLista_guerras().addActionListener(this);
        principal.getLista_paises().addActionListener(this);
        principal.getAñadir_guerra().addActionListener(this);
        principal.getAñadir_pais().addActionListener(this);
        principal.getBtn_nueva_guerra().addActionListener(this);
        principal.getBtn_nuevo_pais().addActionListener(this);
        principal.getBtn_nueva_independencia().addActionListener(this);
        principal.getBtn_borrar_guerra().addActionListener(this);
        principal.getBtn_borrar_pais().addActionListener(this);
        principal.getBtn_borrar_periodo_independencia().addActionListener(this);
        principal.getBtn_modificar_relacion().addActionListener(this);
        principal.getBtn_borrar_relacion().addActionListener(this);
        principal.getMenu_participa_btn_aceptar().addActionListener(this);
        principal.getMenu_participa_btn_cancelar().addActionListener(this);
    }
    
    //Carga de datos al iniciar la vista de usuario
    private void procesosInicio(){
        try {
            lista_guerras.Actualizar(negocio.consulta("SELECT * FROM guerra ORDER BY nombre_guerra"));
            lista_guerras.aJComboBox(principal.getLista_guerras());
            lista_paises.Actualizar(negocio.consulta("SELECT * FROM pais ORDER BY nombre_pais"));
            lista_paises.aJComboBox(principal.getLista_paises()); 
        } catch (SQLException ex) {
            principal.getMensaje().setText("SQL Error "+ex.getErrorCode()+": "+ex.getMessage());  
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Actualiza el atributo tabla del controlador y la tabla del panel de usuario
    private void actualizar_tablas(String consulta) throws SQLException{
        tabla.Actualizar_Tabla(negocio.consulta(consulta));
        principal.getTabla().setModel(tabla);
    }
    
    /*
    * Se muestran en la lista inferior países que no aparecen como participantes 
    * y en la tabla los que si participaron junto con su información de interés
    */
    private void actualizar_menu_guerras() throws SQLException{
        if(principal.getLista_guerras().getSelectedIndex()>0){

            String consulta;
            int id = lista_guerras.get(principal.getLista_guerras().getSelectedIndex()).getId();

            //Consulta para rellenar modelo
            consulta = "SELECT * FROM guerra WHERE id_guerra = "+id;
            guerra.setGuerra(negocio.consulta(consulta));
            principal.getEti_id_guerra().setText("id: "+guerra.getId_guerra());
            principal.getEti_nombre_guerra().setText("nombre: "+guerra.getNombre_guerra());
            principal.getEti_fecha_inicio().setText("fecha inicio: "+guerra.getFecha_inicio());
            principal.getEti_fecha_fin().setText("fecha fin: "+guerra.getFecha_fin());
            principal.getTxtfield_nombre_guerra().setText(guerra.getNombre_guerra());
            principal.getTxtfield_fecha_inicio().setText(String.valueOf(guerra.getFecha_inicio()));
            principal.getTxtfield_fecha_fin().setText(String.valueOf(guerra.getFecha_fin()));
            principal.getBtn_nueva_guerra().setText("M");

            //Consulta para la lista de países neutrales
            consulta = "SELECT id_pais, nombre_pais FROM pais "
                    + "WHERE id_pais NOT IN ("
                    + "SELECT R.id_pais FROM guerra W INNER JOIN "
                    + "(SELECT C.*, P.id_guerra "
                    + "FROM participa P INNER JOIN pais C "
                    + "ON P.id_pais = C.id_pais) AS R "
                    + "ON W.id_guerra = R.id_guerra "
                    + "WHERE W.id_guerra = "
                    + id
                    + ") ORDER BY nombre_pais";
            lista_paises_neutrales.Actualizar(negocio.consulta(consulta));
            lista_paises_neutrales.aJList(principal.getLista_paises_neutrales());


            //Consulta para la tabla relaciones
            consulta = "SELECT W.nombre_guerra, R.nombre_pais, "
                    + "R.fecha_entrada, R.fecha_salida, R.vencedor "
                    + "FROM guerra W INNER JOIN "
                    + "(SELECT P.*, C.nombre_pais "
                    + "FROM participa P INNER JOIN pais C "
                    + "ON P.id_pais = C.id_pais) AS R "
                    + "ON W.id_guerra = R.id_guerra "
                    + "WHERE W.id_guerra = "
                    + id;
            actualizar_tablas(consulta);
        }
        else{
            guerra.setGuerra(0, null, null, null);
            principal.limpiarMenuGuerra();
        }
    }
    
    /*
    * Se muestran en la lista inferior las guerras en las que no aparece como 
    * participante y en la tabla las que sí junto con su información de interés
    */
    private void actualizar_menu_paises() throws SQLException{
        if(principal.getLista_paises().getSelectedIndex()>0){

            String consulta;
            int id = lista_paises.get(principal.getLista_paises().getSelectedIndex()).getId();

            //Consulta para rellenar modelo
            consulta = "SELECT * FROM pais WHERE id_pais = "+id;
            pais.setPais(negocio.consulta(consulta));
            principal.getEti_id_pais().setText("id: "+pais.getId_pais());
            principal.getEti_nombre_pais().setText("nombre: "+pais.getNombre_pais());
            principal.getTxtfield_nombre_pais().setText(pais.getNombre_pais());
            principal.getBtn_nuevo_pais().setText("M");
            principal.getTxtfield_inicio_independencia().setText("aaaa-mm-dd");
            principal.getTxtfield_fin_independencia().setText("aaaa-mm-dd");

            //Consulta para los periodos de independencia
            consulta = "SELECT fecha_inicio, fecha_fin FROM periodo_independencia"
                    + " WHERE id_pais = " + id + " ORDER BY fecha_inicio";
     
            DefaultListModel<String> modelo = new DefaultListModel<>();
            negocio.consulta(consulta);
            while (negocio.getRs().next()){
                pais.añadirPeriodoIndependencia(negocio.getRs().getDate(1), negocio.getRs().getDate(2));
                modelo.addElement(String.valueOf(negocio.getRs().getDate(1))+"  a  "+
                        String.valueOf(negocio.getRs().getDate(2)));
            }
            principal.getLista_periodos_independencia().setModel(modelo);

            //Consulta para la lista de guerra en las que no ha estado el país
            consulta = "SELECT id_guerra, nombre_guerra FROM guerra "
                    + "WHERE id_guerra NOT IN ("
                    + "SELECT R.id_guerra FROM pais C INNER JOIN "
                    + "(SELECT W.*, P.id_pais "
                    + "FROM participa P INNER JOIN guerra W "
                    + "ON P.id_guerra = W.id_guerra) AS R "
                    + "ON C.id_pais = R.id_pais "
                    + "WHERE C.id_pais = "
                    + id
                    + ") ORDER BY nombre_guerra";
            lista_guerras_neutral.Actualizar(negocio.consulta(consulta));
            lista_guerras_neutral.aJList(principal.getLista_guerras_neutral());
            principal.getLista_guerras_neutral().setSelectedIndex(-1);
            //Consulta para la tabla relaciones
            consulta = "SELECT C.nombre_pais, R.nombre_guerra, "
                    + "R.fecha_entrada, R.fecha_salida, R.vencedor "
                    + "FROM pais C INNER JOIN "
                    + "(SELECT P.*, W.nombre_guerra "
                    + "FROM participa P INNER JOIN guerra W "
                    + "ON P.id_guerra = W.id_guerra) AS R "
                    + "ON C.id_pais = R.id_pais "
                    + "WHERE C.id_pais = "
                    + id;
            actualizar_tablas(consulta);
        }
        else{
            pais.setPais(0, null);
            pais.LimpiarPeriodosIndependencia();
            principal.limpiarMenuPais();
        }
    }
    
    //AutoRellena menu el menú Participa con los modelos actuales
    private void autorellena_menu_participa(String modo) throws SQLException{
       
        principal.getMenu_participa_eti_nombre_pais().setText(
                pais.getNombre_pais());
        principal.getMenu_participa_field_id_pais().setText(
                String.valueOf(pais.getId_pais()));
        principal.getMenu_participa_eti_nombre_guerra().setText(
                guerra.getNombre_guerra());
        principal.getMenu_participa_field_id_guerra().setText(
                String.valueOf(guerra.getId_guerra()));
        switch(modo){
            case "Añadir":
                principal.getMenu_participa_btn_aceptar().setText("Añadir");
                principal.getMenu_participa().setTitle("Añadir relación");
                principal.getMenu_participa_txtfield_fecha_entrada().setText(
                        String.valueOf(guerra.getFecha_inicio()));
                principal.getMenu_participa_txtfield_fecha_salida().setText(
                        String.valueOf(guerra.getFecha_fin()));
                principal.getMenu_participa_cbox_bando().setSelectedIndex(0);
                break;
            case "Modificar":
                principal.getMenu_participa_btn_aceptar().setText("Modificar");
                principal.getMenu_participa().setTitle("Modificar relación");
                negocio.consulta("select * from participa where id_guerra = "
                        +guerra.getId_guerra()+" AND id_pais = "+pais.getId_pais()+";");
                negocio.getRs().next();             
                principal.getMenu_participa_txtfield_fecha_entrada().setText(
                        String.valueOf(negocio.getRs().getDate("fecha_entrada")));
                principal.getMenu_participa_txtfield_fecha_salida().setText(
                        String.valueOf(negocio.getRs().getDate("fecha_salida")));
                if (null == negocio.getRs().getString("vencedor"))
                    principal.getMenu_participa_cbox_bando().setSelectedIndex(0);
                else{
                    switch (negocio.getRs().getString("vencedor")){
                        case "0":
                            principal.getMenu_participa_cbox_bando().setSelectedIndex(2);
                            break;
                        case "1":
                            principal.getMenu_participa_cbox_bando().setSelectedIndex(1);
                            break;
                        default:
                            principal.getMenu_participa_cbox_bando().setSelectedIndex(0);
                            break;
                    }
                }
                break;
            default:
                
                break;
        }
        

        
        
}

    //Añade a la tabla una nueva guerra
    private void añadir_guerra() throws SQLException{
        String nombre = principal.getTxtfield_nombre_guerra().getText();
        String fecha_inicio = principal.getTxtfield_fecha_inicio().getText();
        String fecha_fin = principal.getTxtfield_fecha_fin().getText();

        if(!"null".equals(fecha_inicio) && !"".equals(fecha_inicio))
            fecha_inicio = "'"+fecha_inicio+"'";
        else
            fecha_inicio = null;

        if(!"null".equals(fecha_fin) && !"".equals(fecha_fin))
            fecha_fin = "'"+fecha_fin+"'";
        else
            fecha_fin = null;

        negocio.update("insert into guerra (nombre_guerra, fecha_inicio, fecha_fin) "
                + "values ('"+nombre+"', "+fecha_inicio+", "+fecha_fin+");");

        lista_guerras.Actualizar(negocio.consulta("SELECT * FROM guerra ORDER BY nombre_guerra"));
        lista_guerras.aJComboBox(principal.getLista_guerras());
        principal.getLista_guerras().setSelectedIndex(0);
        actualizar_menu_guerras();
        actualizar_tablas("Select * from guerra");
    }
    
    //Añade a la tabla una nueva guerra
    private void modificar_guerra() throws SQLException{
        String nombre = principal.getTxtfield_nombre_guerra().getText();
        String fecha_inicio = principal.getTxtfield_fecha_inicio().getText();
        String fecha_fin = principal.getTxtfield_fecha_fin().getText();

        if(!"null".equals(fecha_inicio) && !"".equals(fecha_inicio))
            fecha_inicio = "'"+fecha_inicio+"'";
        else
            fecha_inicio = null;

        if(!"null".equals(fecha_fin) && !"".equals(fecha_fin))
            fecha_fin = "'"+fecha_fin+"'";
        else
            fecha_fin = null;
        
        negocio.update("update guerra set nombre_guerra = '"+nombre
                + "', fecha_inicio = "+fecha_inicio+", fecha_fin = "+fecha_fin
                + " where id_guerra = "+guerra.getId_guerra()+";");
        
        lista_guerras.Actualizar(negocio.consulta("SELECT * FROM guerra ORDER BY nombre_guerra"));
        lista_guerras.aJComboBox(principal.getLista_guerras());
        principal.getLista_guerras().setSelectedIndex(0);
        actualizar_menu_guerras();
        actualizar_tablas("Select * from guerra");
    }
    
    //Borra de la tabla una guerra
    private void borrar_guerra() throws SQLException{
        negocio.update("delete from guerra where id_guerra = "+guerra.getId_guerra());
        lista_guerras.Actualizar(negocio.consulta("SELECT * FROM guerra ORDER BY nombre_guerra"));
        lista_guerras.aJComboBox(principal.getLista_guerras());
        principal.getLista_guerras().setSelectedIndex(0);
        actualizar_menu_guerras();
        actualizar_tablas("Select * from guerra");
    }
     
    //Añade a la tabla un nuevo país
    private void añadir_pais() throws SQLException{
        String nombre = principal.getTxtfield_nombre_pais().getText();
        negocio.update("insert into pais (nombre_pais) values ('"+nombre+"');");
        lista_paises.Actualizar(negocio.consulta("SELECT * FROM pais ORDER BY nombre_pais"));
        lista_paises.aJComboBox(principal.getLista_paises()); 
        principal.getLista_paises().setSelectedIndex(0);
        actualizar_menu_paises();
        actualizar_tablas("Select * from pais");
    }
    
    //Añade a la tabla un nuevo país
    private void modificar_pais() throws SQLException{
        String nombre = principal.getTxtfield_nombre_pais().getText();
        negocio.update("update pais set nombre_pais = '"+nombre+"' where id_pais ="+pais.getId_pais()+";");
        lista_paises.Actualizar(negocio.consulta("SELECT * FROM pais ORDER BY nombre_pais"));
        lista_paises.aJComboBox(principal.getLista_paises()); 
        principal.getLista_paises().setSelectedIndex(0);
        actualizar_menu_paises();
        actualizar_tablas("Select * from pais order by id_pais");
    }
    
    //Borra de la tabla una guerra
    private void borrar_pais() throws SQLException{
        negocio.update("delete from pais where id_pais = "+pais.getId_pais());
        lista_paises.Actualizar(negocio.consulta("SELECT * FROM pais ORDER BY nombre_pais"));
        lista_paises.aJComboBox(principal.getLista_paises()); 
        principal.getLista_paises().setSelectedIndex(0);
        actualizar_menu_paises();
        actualizar_tablas("Select * from pais order by id_pais");
    }

    //Añade a la tabla participa un nuevo registro con los datos del menú participa
    private void añadir_relacion() throws SQLException{
        String id_guerra = principal.getMenu_participa_field_id_guerra().getText();
        String id_pais = principal.getMenu_participa_field_id_pais().getText();
        String bando;
        String fecha_entrada = principal.getMenu_participa_txtfield_fecha_entrada().getText();
        String fecha_salida = principal.getMenu_participa_txtfield_fecha_salida().getText();

        if(!"null".equals(fecha_entrada) && !"".equals(fecha_entrada))
            fecha_entrada = "'"+fecha_entrada+"'";
        else
            fecha_entrada = null;

        if(!"null".equals(fecha_salida) && !"".equals(fecha_salida))
            fecha_salida = "'"+fecha_salida+"'";
        else
            fecha_salida = null;

        switch (principal.getMenu_participa_cbox_bando().getSelectedIndex()){
            case 1:
                bando = "1";
                break;
            case 2:
                bando = "0";
                break;
            case 0: default:
                bando = "null";
        }
        negocio.update("insert into participa (id_guerra, id_pais, "
                + "fecha_entrada, fecha_salida, vencedor)values ("+id_guerra+", "
                +id_pais+", "+fecha_entrada+", "+fecha_salida+", "+bando+");");
    }
    
    //Modifica la tabla participa con los datos del menú participa
    private void modificar_relacion() throws SQLException{
        String id_guerra = principal.getMenu_participa_field_id_guerra().getText();
        String id_pais = principal.getMenu_participa_field_id_pais().getText();
        String bando;
        String fecha_entrada = principal.getMenu_participa_txtfield_fecha_entrada().getText();
        String fecha_salida = principal.getMenu_participa_txtfield_fecha_salida().getText();

        if(!"null".equals(fecha_entrada) && !"".equals(fecha_entrada))
            fecha_entrada = "'"+fecha_entrada+"'";
        else
            fecha_entrada = null;

        if(!"null".equals(fecha_salida) && !"".equals(fecha_salida))
            fecha_salida = "'"+fecha_salida+"'";
        else
            fecha_salida = null;

        switch (principal.getMenu_participa_cbox_bando().getSelectedIndex()){
            case 1:
                bando = "1";
                break;
            case 2:
                bando = "0";
                break;
            case 0: default:
                bando = "null";
        }
        negocio.update("update participa set fecha_entrada = "+fecha_entrada+
                ", fecha_salida = "+fecha_salida+", vencedor = "+bando+" where"
                + " id_guerra = "+id_guerra+" AND id_pais = "+id_pais+";");
    }
    
    //Borra un registro de la tabla participa
    private void borrar_relacion() throws SQLException{
        negocio.update("delete from participa where id_guerra = "
                +guerra.getId_guerra()+" AND id_pais = "+pais.getId_pais()+";");
    }
    
    
    //Añade a la tabla periodo_independencia un nuevo registro
    private void añadir_periodo_independencia() throws SQLException{
        int id = pais.getId_pais();
        String fecha_inicio = principal.getTxtfield_inicio_independencia().getText();
        String fecha_fin = principal.getTxtfield_fin_independencia().getText();

        if(!"null".equals(fecha_inicio) && !"".equals(fecha_inicio))
            fecha_inicio = "'"+fecha_inicio+"'";
        else
            fecha_inicio = null;

        if(!"null".equals(fecha_fin) && !"".equals(fecha_fin))
            fecha_fin = "'"+fecha_fin+"'";
        else
            fecha_fin = null;
        negocio.update("insert into periodo_independencia (id_pais, fecha_inicio, fecha_fin) "
                + "values ('"+id+"', "+fecha_inicio+", "+fecha_fin+");");
        this.actualizar_menu_paises();
    }
    
    //Añade a la tabla periodo_independencia un nuevo registro
    private void borrar_periodo_independencia() throws SQLException{
        
        int id = pais.getId_pais();
        String periodo = principal.getLista_periodos_independencia().getSelectedValue();
        String fecha_inicio = periodo.substring(0, 10);
        String fecha_fin = periodo.substring(15);

        if(!"null".equals(fecha_inicio) && !"".equals(fecha_inicio))
            fecha_inicio = "'"+fecha_inicio+"'";
        else
            fecha_inicio = null;

        if(!"null".equals(fecha_fin) && !"".equals(fecha_fin))
            fecha_fin = "'"+fecha_fin+"'";
        else
            fecha_fin = null;
        negocio.update("delete from periodo_independencia where id_pais = "+ id +
                " AND fecha_inicio = " + fecha_inicio + " AND fecha_fin = " + fecha_fin +";");
        this.actualizar_menu_paises();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            principal.limpiarTabla();
            switch (e.getActionCommand()){
                //btn_nueva_guerra, añade una guerra a la tabla guerras
                case "Nueva_Guerra":
                    if (principal.getLista_guerras().getSelectedIndex()==0) { 
                        añadir_guerra();
                        principal.getMensaje().setText("Añadida Guerra: "+guerra.getNombre_guerra());
                    }
                    else {
                        modificar_guerra();
                        principal.getMensaje().setText("Modificada Guerra: "+guerra.getNombre_guerra());
                    }
                    break;
                    //btn_borrar_guerra, borra una guerra de la tabla guerras
                case "Borrar_Guerra":
                    borrar_guerra();
                    principal.getMensaje().setText("Borrada Guerra: "+guerra.getNombre_guerra());
                    break;
                    //btn_guerras, muestra la entidad GUERRA con sus atributos en la tabla
                case "Listar_Guerras":
                    actualizar_tablas("Select * from guerra");
                    principal.getMensaje().setText("Mostrada Tabla \"Guerra\"");
                    break;
                    //btn_nuevo_pais, añade un país a la tabla países
                case "Nuevo_Pais":
                    if (principal.getLista_paises().getSelectedIndex()==0) { 
                        añadir_pais();
                        principal.getMensaje().setText("Añadido País: "+pais.getNombre_pais());
                    }
                    else {
                        modificar_pais();
                        principal.getMensaje().setText("Modificado País: "+pais.getNombre_pais());
                    }
                    break;
                    //btn_borrar_pais, borra un país de la tabla países
                case "Borrar_Pais":
                    borrar_pais();
                    actualizar_tablas("Select * from pais");
                    principal.getMensaje().setText("Borrado País: "+pais.getNombre_pais());
                    break;
                    //btn_países, muestra la entidad PAIS con sus atributos en la tabla
                case "Listar_Países":
                    actualizar_tablas("Select * from pais order by id_pais");
                    principal.getMensaje().setText("Mostrada Tabla \"País\"");
                    break;
                case "Nueva_Independencia":
                    añadir_periodo_independencia();
                    actualizar_tablas("Select '"+this.pais.getNombre_pais()+"' as pais, "
                            + "fecha_inicio, fecha_fin from periodo_independencia "
                            + "where id_pais = "+this.pais.getId_pais());
                    this.actualizar_menu_paises();
                    principal.getMensaje().setText("Añadido periodo de independencia a: "+pais.getNombre_pais());
                    break;
                case "Borrar_Independencia":
                    if(!principal.getLista_periodos_independencia().isSelectionEmpty()){
                        borrar_periodo_independencia();
                        principal.getMensaje().setText("Borrado periodo de independencia de: "+pais.getNombre_pais());
                    }
                    
                    break;
                    //btn_países, muestra la entidad PAIS con sus atributos en la tabla
                case "Listar_Participa":
                    actualizar_tablas("Select * from participa order by id_guerra, id_pais");
                    principal.getMensaje().setText("Mostrada Tabla \"Participa\"");
                    break;
                    //btn_países, muestra la entidad PAIS con sus atributos en la tabla
                case "Listar_Independencias":
                    actualizar_tablas("Select * from periodo_independencia order by id_pais");
                    principal.getMensaje().setText("Mostrada Tabla \"Periodos de Independencia\"");
                    break;
                    //Jlist_guerras, actualiza el menú de guerras
                case "Guerra_Seleccionada":
                    actualizar_menu_guerras();
                    actualizar_menu_paises();
                    if (principal.getLista_guerras().getSelectedIndex()>0
                            && principal.getLista_paises().getSelectedIndex()>0){
                        actualizar_tablas("Select * from participa where id_guerra = "
                                +guerra.getId_guerra() +" AND id_pais = "+pais.getId_pais()+";");
                        if (principal.getTabla().getRowCount() == 1){
                            principal.getBtn_modificar_relacion().setVisible(true);
                            principal.getBtn_borrar_relacion().setVisible(true);   
                        }
                    }
                    principal.getMensaje().setText("Guerra Seleccionada: "+this.guerra.getNombre_guerra());
                    break;
                    //Jlist_paises, actualiza el menú de países
                case "Pais_Seleccionado":
                    actualizar_menu_guerras();
                    actualizar_menu_paises();
                    if (principal.getLista_guerras().getSelectedIndex()>0
                            && principal.getLista_paises().getSelectedIndex()>0){
                        actualizar_tablas("Select * from participa where id_guerra = "
                                +guerra.getId_guerra() +" AND id_pais = "+pais.getId_pais()+";");
                        if (principal.getTabla().getRowCount() == 1){
                            principal.getBtn_modificar_relacion().setVisible(true);
                            principal.getBtn_borrar_relacion().setVisible(true);   
                        } 
                    }
                    principal.getMensaje().setText("País Seleccionado: "+this.pais.getNombre_pais());
                    break;
                //Boton JList Paises
                case "Añadir_Paises":     
                    if (principal.getLista_paises_neutrales().getSelectedIndex()>=0){
                        int id_pais;
                        id_pais = this.lista_paises_neutrales.get(
                                principal.getLista_paises_neutrales().getSelectedIndex()+1).getId();
                        pais.setPais(negocio.consulta("SELECT * FROM pais WHERE id_pais = "+id_pais));
                        actualizar_menu_guerras();
                        autorellena_menu_participa("Añadir");
                        principal.getMenu_participa().setVisible(true);
                    }
                    break;
                //Boton JList Guerras
                case "Añadir_Guerras":
                    if (principal.getLista_guerras_neutral().getSelectedIndex()>=0){
                        int id_guerra;
                        id_guerra = this.lista_guerras_neutral.get(
                                principal.getLista_guerras_neutral().getSelectedIndex()+1).getId();
                        guerra.setGuerra(negocio.consulta("SELECT * FROM guerra WHERE id_guerra = "+id_guerra));
                        actualizar_menu_paises();
                        autorellena_menu_participa("Añadir");
                        principal.getMenu_participa().setVisible(true);
                    }
                    break;
                case "Modificar_Relacion":
                    this.autorellena_menu_participa("Modificar");
                    principal.getMenu_participa().setVisible(true);
                    break;
                case "Borrar_Relacion":
                    borrar_relacion();
                    principal.limpiarVista();
                    principal.getMensaje().setText("Relación borrada");
                    this.actualizar_tablas("select * from participa order by id_guerra, id_pais");
                    break;
                case "Aceptar_Participa":
                    if ("Añadir".equals(principal.getMenu_participa_btn_aceptar().getText())){
                        añadir_relacion();
                        principal.getMensaje().setText("Relacion añadida: "+this.pais.getNombre_pais()
                                +" a "+this.guerra.getNombre_guerra());
                        this.actualizar_menu_guerras();
                        this.actualizar_menu_paises();
                    this.actualizar_tablas("select * from participa order by id_guerra, id_pais");    
                    }
                    if ("Modificar".equals(principal.getMenu_participa_btn_aceptar().getText())){
                        modificar_relacion();
                        this.actualizar_menu_guerras();
                        this.actualizar_menu_paises();
                        principal.getMensaje().setText("Relacion modificada: "+this.pais.getNombre_pais()
                                +" en "+this.guerra.getNombre_guerra());
                        this.actualizar_tablas("select * from participa order by id_guerra, id_pais");   
                    }
                    principal.getMenu_participa().dispose();
                    
                    
                    break;
                case "Cancelar_Participa":
                    principal.getMenu_participa().dispose();
                    principal.getMensaje().setText("Operación cancelada");
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            principal.getMensaje().setText("SQL Error "+ex.getErrorCode()+": "+ex.getMessage());  
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
