package ut01;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class NewObjectOutputStream extends ObjectOutputStream {

    //El constructor llama al constructor de la superclase
    public NewObjectOutputStream(OutputStream outputStream) throws IOException {
        super(outputStream);
    }

    //Se redefine el método writeStreamHeader para que no escriba la cabecera en mitad del fichero.
    @Override
    protected void writeStreamHeader() throws IOException
    {
    }
}
