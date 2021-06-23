
package Datos;

import Modelo.Motocicleta;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jairo
 */
public class ArchivoTexto implements IAccesoDatos {
    
    private File archivo;
    private FileWriter aEscritura;
    private Scanner aLectura;

    public ArchivoTexto(String name) {
        this.archivo = new File(name);
    }

    public ArchivoTexto() {
        this("CatalogoMotos.dat");
    }
    
    

    @Override
    public void registrarMotocicleta(Motocicleta moto) throws IOException {
        
        PrintWriter pw = null;
        try{
            this.aEscritura = new FileWriter(this.archivo, true);
            pw = new PrintWriter(this.aEscritura);
            pw.println(moto.getEstructuraTexto());
        
        }catch(IOException ioe){
            throw ioe;
        }
        finally{
            if(pw!=null)
                pw.close();
            if(this.aEscritura!=null)
                this.aEscritura.close();
        }
    }

    private Motocicleta crearMotocicleta(String linea){
        String datos[] = linea.split(";");
        Motocicleta moto = new Motocicleta();
        moto.setPlaca(datos[0]);
        moto.setMarca(datos[1]);
        moto.setModelo(Integer.parseInt(datos[2]));
        moto.setKms(Integer.parseInt(datos[3]));
        moto.setValor(Double.parseDouble(datos[4]));
        return moto;
    }
    
    @Override
    public List<Motocicleta> leerMotocicletas() throws IOException {
        
        List<Motocicleta> lista = new ArrayList();
        try{
            this.aLectura = new Scanner(this.archivo);
            while(this.aLectura.hasNext()){
                String linea = this.aLectura.nextLine();
                Motocicleta moto = this.crearMotocicleta(linea);
                lista.add(moto);
            }
            return lista;
        }catch(IOException ioe){
            throw ioe;
        }
        finally{
            if(this.aLectura!=null)
                this.aLectura.close();
        }
    }

    @Override
    public Motocicleta buscarMotocicleta(String placa) throws IOException {
          
           Motocicleta encontrada = null;
           try{
                this.aLectura = new Scanner(this.archivo);
                while(this.aLectura.hasNext()){
                    String linea = this.aLectura.nextLine();
                    Motocicleta moto = this.crearMotocicleta(linea);
                    if(moto.getPlaca().equalsIgnoreCase(placa)){
                        encontrada = moto;
                        break;
                    }
                }
                return encontrada;
           }
           catch(IOException ioe){
               throw ioe;
           }
           finally{
               if(this.aEscritura!=null)
                   this.aLectura.close();
           }
        
    }

    @Override
    public List<Motocicleta> consultarMotocicleta(double value) throws IOException {
           List<Motocicleta> lista = new ArrayList();
           try{
                this.aLectura = new Scanner(this.archivo);
                while(this.aLectura.hasNext()){
                    String linea = this.aLectura.nextLine();
                    Motocicleta moto = this.crearMotocicleta(linea);
                    lista.add(moto);
                }
                return lista;
           }
           catch(IOException ioe){
               throw ioe;
           }
           finally{
               if(this.aEscritura!=null)
                   this.aLectura.close();
           }
    }
    
    private void actualizarArchivo(File nvoArchivo) throws IOException{
         if(nvoArchivo.exists()){
             nvoArchivo.createNewFile();
         }
         
         if(this.archivo.delete()){
             if(!nvoArchivo.renameTo(this.archivo)){
                 throw new IOException("El Archivo temporal no fue renombrado");
             }
         }
         else
             throw new IOException("El Archivo original no fue eliminado");
        
    }
    
    
    @Override
    public int eliminarMotocicletas(int kms) throws IOException {
        int contador=0;
          
        try{
             this.aLectura = new Scanner(this.archivo);
             ArchivoTexto tmp = new ArchivoTexto("Temporal.dat");
             while(this.aLectura.hasNext()){
                 Motocicleta moto= this.crearMotocicleta(this.aLectura.nextLine());
                 if(moto.getKms()>kms){
                     contador++;
                 }
                 else{
                     tmp.registrarMotocicleta(moto);
                 }
             }
             this.aLectura.close();
             this.actualizarArchivo(tmp.archivo);
             return contador;
        }
        catch(IOException ioe){
            throw ioe;
        }
        finally{
        }
        
    }
    
     @Override
    public void eliminarMotocicletas(String placa) throws IOException {
        int contador=0;
          
        try{
             this.aLectura = new Scanner(this.archivo);
             ArchivoTexto tmp = new ArchivoTexto("Temporal.dat");
             while(this.aLectura.hasNext()){
                 Motocicleta moto= this.crearMotocicleta(this.aLectura.nextLine());
                 if(!moto.getPlaca().equalsIgnoreCase(placa)){
                     tmp.registrarMotocicleta(moto);
                 }
             }
             this.aLectura.close();
             this.actualizarArchivo(tmp.archivo);
        }
        catch(IOException ioe){
            throw ioe;
        }
        finally{
        }
        
        
        
    }

    @Override
    public List<Motocicleta> consultarMotocicleta(String texto) throws IOException {
        List<Motocicleta> lista = new ArrayList();
           try{
                this.aLectura = new Scanner(this.archivo);
                while(this.aLectura.hasNext()){
                    String linea = this.aLectura.nextLine();
                    Motocicleta moto = this.crearMotocicleta(linea);
                    if(moto.getPlaca().toUpperCase().contains(texto.toUpperCase()) || moto.getMarca().toUpperCase().contains(texto.toUpperCase())){
                        lista.add(moto);
                    }
                }
                return lista;
           }
           catch(IOException ioe){
               throw ioe;
           }
           finally{
               if(this.aEscritura!=null)
                   this.aLectura.close();
           }
    }
}
