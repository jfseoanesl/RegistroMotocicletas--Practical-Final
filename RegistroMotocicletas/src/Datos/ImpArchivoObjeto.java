
package Datos;

import Modelo.Motocicleta;
import java.io.*;
import java.util.List;


public class ImpArchivoObjeto implements IAccesoDatos{
    private File archivo;
    private FileInputStream aLectura;
    private FileOutputStream aEscritura;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ImpArchivoObjeto(String name) {
        this.archivo = new File(name);
    }

    public ImpArchivoObjeto() {
        this("CatalogoMotosObjetos.obj");
    }
    
    private void guardar(ListaMotocicleta lista) throws IOException{
        
        this.oos=null;
        try{
            this.aEscritura = new FileOutputStream(this.archivo, false);
            this.oos = new ObjectOutputStream(this.aEscritura);
            this.oos.writeObject(lista);
        }catch(IOException ioe){
            throw ioe;
        }
        finally{
            if(this.oos!=null)
                this.oos.close();
            
            if(this.aEscritura!=null)
                this.aEscritura.close();
        }
    
    }
    
    private ListaMotocicleta leer() throws IOException{
        
        ListaMotocicleta lista = null;
        if(this.archivo.exists()){
            
            this.ois=null;
            try{
                this.aLectura = new FileInputStream(this.archivo);
                this.ois= new ObjectInputStream(this.aLectura);
                lista = (ListaMotocicleta) this.ois.readObject();
                return lista;
            }catch(IOException ioe){
                throw ioe;
            } catch (ClassNotFoundException ex) {
                throw new IOException("La clase Lista Motocicleta no existe");
            } 
            finally{
                if(this.ois!=null)
                    this.ois.close();
                
                if(this.aLectura!=null)
                    this.aLectura.close();
            }
        }
        else{
            lista = new ListaMotocicleta();
            return lista;
        }
        
    }
    

    

    @Override
    public void registrarMotocicleta(Motocicleta moto) throws IOException {
        ListaMotocicleta lista = this.leer();
        lista.registrarMotocicleta(moto);
        this.guardar(lista);
    }

    @Override
    public List<Motocicleta> leerMotocicletas() throws IOException {
         ListaMotocicleta lista = this.leer();
         return lista.leerMotocicletas();
    }

    @Override
    public Motocicleta buscarMotocicleta(String placa) throws IOException {
         ListaMotocicleta lista = this.leer();
         return lista.buscarMotocicleta(placa);
    }

    @Override
    public List<Motocicleta> consultarMotocicleta(double value) throws IOException {
         ListaMotocicleta lista = this.leer();
         return lista.consultarMotocicleta(value);
    }

    @Override
    public int eliminarMotocicletas(int kms) throws IOException {
         ListaMotocicleta lista = this.leer();
         int cont=lista.eliminarMotocicletas(kms);
         this.guardar(lista);
         return cont;
    }
    
    @Override
    public void eliminarMotocicletas(String placa) throws IOException {
         ListaMotocicleta lista = this.leer();
         lista.eliminarMotocicletas(placa);
         this.guardar(lista);
    }

    @Override
    public List<Motocicleta> consultarMotocicleta(String texto) throws IOException {
        ListaMotocicleta lista = this.leer();
        return lista.consultarMotocicleta(texto);
    }
}
