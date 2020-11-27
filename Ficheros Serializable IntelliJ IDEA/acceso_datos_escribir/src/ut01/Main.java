package ut01;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        //Variables para los ficheros
        File fileCurso;
        File fileAlumno;
        boolean cursoAddable;
        boolean alumnoAddable;
        // Variables para los objetos Curso
        String codigo, denominacion;
        int numHoras;
        Date fechaInicio, fechaFin;
        //Declaración de los flujos iniciadores de salida de datos a ficheros
        FileOutputStream fos_cursos = null, fos_alumnos = null;
        //Declaración de los flujos que actúan de filtro y procesan los datos de objetos a bytes
        ObjectOutputStream oos_cursos = null, oos_alumnos = null;
        //Declaración e inicialización del vector de objetos de tipo Curso
        Curso[] cursos = new Curso[5];
        cursos[0] = new Curso("3678",
                "Iniciación a la red Internet",
                60,
                new Date(2018,9,1),
                new Date(2018,9,15));
        cursos[1] = new Curso("3609",
                "Informática de usuario",
                200,
                new Date(2018,9,20),
                new Date(2018,11,4));
        cursos[2] = new Curso("2758",
                "Informática de usuario",
                200,
                new Date(2018,11,9),
                new Date(2019,1,4));
        cursos[3] = new Curso("3689",
                "Gobernanta/e de hotel",
                615,
                new Date(2018,9,18),
                new Date(2019,2,18));
        cursos[4] = new Curso("3668",
                "Empleado/a de oficina",
                790,
                new Date(2018,9,18),
                new Date(2019,4,14));

        try {
            //APERTURA DE LOS FICHEROS
            /*
            Nota: Se elige false como segundo parámetro de las instancias de FileOutputStream porque no
            se pide añadir nuevos datos posteriormente. El fichero se sobreescribirá con los nuevos datos.
            Si se elige "true" hay que sobreescribir el método Header de la clase ObjectOutputStream
            por medio de una subclase, que implementerán las clases Alumno y Curso. Así se evitará
            duplicar la cabecera del fichero y generar excepciones de la clase StreamCorruptedException.
            */
            fileCurso = new File("cursos.dat");
            cursoAddable = false;
            fos_cursos = new FileOutputStream(fileCurso, cursoAddable); // Nueva instancia de flujo para el fichero "cursos.dat"

            fileAlumno = new File("alumnos.dat");
            alumnoAddable = false;
            fos_alumnos = new FileOutputStream(fileAlumno, alumnoAddable);

            //PROCESAMIENTO DE DATOS DE Cursos.dat
            /*
            Nota: El método writeObject escribe los datos de un objeto en el fichero pero los almacena. Si se
            reescribe el mismo objeto con alguna modificación, pero siendo el puntero el mismo, no se producirá
            cambio en el flujo de datos y el fichero se escribirán los mismos datos que la primera vez. Algo común
            si se reutilizan variables para recoger datos introducidos por teclado o desde una interfaz de usuario.
            Para evitarlo es recomendable escribir cada objeto como una nueva instancia. Y si esto no fuese posible
            también se puede crear una nueva instancia para el flujo de datos de objetos tras cada escritura. Pero
            usando una subclase para no duplicar las cabeceras tras la primera escritura en fichero.
             */

            // Alternativa 1: Cada objeto del vector tiene una dirección de puntero distinta. Solución más fácil
            // para este caso particular, pero no permite generalizar para contextos más habituales. No permite trabajar
            // con datos introducidos por el usuario ni permite añadir objetos a un fichero con datos.

            /*oos_cursos = new ObjectOutputStream(fos_cursos);
            for (int i=0;i<5;i++){
                oos_cursos.writeObject(cursos[i]);
            }*/

            // Alternativa 2: Reutilización de una instancia de Curso que recorre el vector. Es funcional en este caso
            // porque se actualiza el puntero con cada posición del vector, pero si se actualiza aux_curso mediante los
            // métodos setters, la instancia del flujo no recogerá las cambios si no se resetea o se crea una nueva
            // instancia. Tampoco permite añadir objetos a un fichero que contenga datos porque se incurriría en una
            // excepción StreamCorruptedException

            /*Curso aux_curso;
            oos_cursos = new ObjectOutputStream(fos_cursos);
            for (int i=0;i<5;i++){
                aux_curso = cursos[i]; //Se modifica el objeto
                oos_cursos.writeObject(aux_curso);
            }*/

            // Alternativa 3: Uso de variables para recoger los datos del vector y nueva instancia de Curso en cada
            // escritura. Esta solución se puede extrapolar y trabajar con datos introducidos por el usuario en lugar
            // del vector con datos predefinidos. Además, al crear una instancia del flujo de objetos para cada escritura
            // y comprobar si se instancia ObjectOutputStream o NewObjectOutputStream, permite elegir entre reescribir
            // el fichero desde el inicio o añadir objetos al final. Este último caso permite que el fichero sea usado
            // por varias aplicaciones que escriban datos mientras se ejecutan simultáneamente

            // Se escribe la cabecera si el archivo no contiene datos o si se sobreescribe desde el principio.
            if (fileCurso.length() == 0 || cursoAddable == false)
                oos_cursos = new ObjectOutputStream(fos_cursos); // Nueva instancia para el flujo de objetos de tipo Curso
            // No se escribe cabecera en caso de que el fichero tenga datos y se añadan datos al final.
            else
                oos_cursos = new NewObjectOutputStream(fos_cursos); // Nueva instancia para el flujo de objetos de tipo Curso
            for (int i=0;i<5;i++){
                codigo = cursos[i].getCodigo();
                denominacion = cursos[i].getDenominacion();
                numHoras = cursos[i].getNumHoras();
                fechaInicio = cursos[i].getFechaInicio();
                fechaFin = cursos[i].getFechaFin();
                oos_cursos.writeObject(new Curso(codigo, denominacion, numHoras, fechaInicio, fechaFin));
            }

            //PROCESAMIENTO DE DATOS DE Alumnos.dat

            // Escritura en el fichero "alumnos.dat" inicializando la instancia en la misma llamada del método de
            // escritura sin usar variables en sus parámetros. Es recomendable instanciar un objeto Alumno e
            // inicializarlo junto al resto de variable del método main. Aunque en este último apartado he optado
            // por la solución directa y alternativa.
            if (fileAlumno.length() == 0 || alumnoAddable == false)
                oos_alumnos = new ObjectOutputStream(fos_alumnos);
            else
                oos_alumnos = new NewObjectOutputStream(fos_alumnos);
            oos_alumnos.writeObject(
                    new Alumno("78523412D",
                            "Ayoze",
                            "Gil",
                            "Sosa",
                            "Calle El Rincon",
                            "35000",
                            "Las Palmas",
                            "928654321",
                             new Date(1985,11,14)));


        } catch (IOException e) { //Captura excepciones de tipo IN OUT
            System.out.println("Error de en el flujo de datos");
        } finally {
            //CIERRE DE LOS FICHEROS
            //El flujo FileOutputStream se cierra con el flujo ObjectOutputStream, pero puede que no llegue a crearse
            // una instancia del segundo. En ese caso se cierra el primero.
            try{
                if (oos_cursos != null)
                    oos_cursos.close();
                else if (fos_cursos != null)
                    fos_cursos.close();
                if (oos_alumnos != null)
                    oos_alumnos.close();
                else if (fos_alumnos != null)
                    fos_alumnos.close();
            } catch (IOException e) { //Captura excepción al cerrar flujo
                System.out.println("Error al cerrar flujo de datos");
            }
        }
    }
}
