
package Datos;

import Modelo.Motocicleta;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jairo
 */
public class ArchivoObjetos {
    
    private File archivo;
    private FileInputStream aLectura;
    private FileOutputStream aEscritura;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ArchivoObjetos(String name) {
        this.archivo = new File(name);
    }
    
    public void abrirArchivo()throws IOException{
        
        this.oos = null;
        this.aEscritura = new FileOutputStream(this.archivo, true);
        this.oos = new ObjectOutputStream(this.aEscritura);
         
    }
    
    public void cerrarArchivo()throws IOException{
       if(this.oos!=null)   
           this.oos.close();
       
       if(this.aEscritura!=null)
           this.aEscritura.close();
    }
    public void guardar(Motocicleta moto) throws IOException{
        
            this.oos.writeObject(moto);
        
    }
    
    public List<Motocicleta> leer()throws IOException{
        
        this.ois=null;
        List<Motocicleta> lista = new ArrayList();
        
        try{
            this.aLectura= new FileInputStream(this.archivo);
            this.ois = new ObjectInputStream(this.aLectura);
            while(true){
                try{
                    Motocicleta moto = (Motocicleta)this.ois.readObject();
                    lista.add(moto);
                }catch(EOFException eoe){
                    break;
                }    
            }
            return lista;
        }
        catch(FileNotFoundException fne){
            throw new IOException("El archivo no existe");
            
        } catch (ClassNotFoundException ex) {
            throw new IOException("La clase Motocicleta no existe");
        }
        finally{
            if(this.ois!=null)
                this.ois.close();
            
            if(this.aLectura!=null)
                this.aLectura.close();
        }
        
    }
    
    
}
