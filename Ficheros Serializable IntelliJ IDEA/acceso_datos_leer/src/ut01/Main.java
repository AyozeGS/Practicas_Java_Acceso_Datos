package ut01;

import java.io.EOFException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Curso curso;
        Alumno alumno;
        FileInputStream fis_cursos = null, fis_alumnos = null;//Declaración de los flujos de salida de datos a ficheros
        ObjectInputStream ois_cursos = null, ois_alumnos = null; //Declaración de los flujos de salida que procesan los datos
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YY");

        try {
            //APERTURA DEL FICHERO
            //Inicialización del flujo de datos.
            fis_cursos = new FileInputStream("cursos.dat");
            ois_cursos = new ObjectInputStream(fis_cursos);
            fis_alumnos = new FileInputStream("alumnos.dat");
            ois_alumnos = new ObjectInputStream(fis_alumnos);

            //PROCESAMIENTO DE DATOS
            //readObject es el método para leer un objeto en el fichero
            try {
                while (true){
                    alumno = (Alumno) ois_alumnos.readObject();
                    System.out.println(alumno.getDni()+" "+alumno.getNombre()+" "+alumno.getPrimerApellido()+
                            " "+alumno.getSegundoApellido()+", "+sdf.format(alumno.getFechaNacimiento()));
                    System.out.println(alumno.getDireccion()+", "+alumno.getCodigoPostal()+", "+alumno.getProvincia());

                    System.out.println(alumno.getTelefono());
                }
            } catch (EOFException e) { //Captura excepción de fin de fichero
                System.out.println("Fin de fichero alumnos.\n");
            }

            try {
                while(true){
                    curso = (Curso) ois_cursos.readObject();
                    System.out.println(curso.getCodigo() + " " + curso.getDenominacion() + " (" + curso.getNumHoras()
                            +" horas) de "+sdf.format(curso.getFechaInicio())+" a "+sdf.format(curso.getFechaInicio()));
                }
            } catch (EOFException e) { //Captura excepción de fin de fichero
                System.out.println("Fin de fichero cursos.");
            }


        } catch (IOException|ClassNotFoundException e) { //Captura excepciones que se puedan generar
            e.printStackTrace();
        } finally {
            //CIERRE DE LOS FICHEROS
            //Al cerrar el flujo ObjectInputStream se cierra el flujo FileInputStream
            //pero puede producirse una excepción antes de crear
            try{
                if (ois_cursos != null)
                    ois_cursos.close();
                else if (fis_cursos != null)
                    fis_cursos.close();
                if (ois_alumnos != null)
                    ois_alumnos.close();
                else if (fis_alumnos != null)
                    fis_alumnos.close();
            } catch (IOException e) { //Captura excepciones que se puedan generar
                System.out.println("Error al cerrar flujo de datos");
            }
        }
    }
}
