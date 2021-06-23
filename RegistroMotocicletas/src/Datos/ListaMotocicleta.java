
package Datos;

import Modelo.Motocicleta;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jairo
 */
public class ListaMotocicleta implements IAccesoDatos, Serializable {
    private List<Motocicleta> lista;

    public ListaMotocicleta() {
        this.lista = new ArrayList();
    }

    
    @Override
    public void registrarMotocicleta(Motocicleta moto) throws IOException {
        this.lista.add(moto);
    }

    @Override
    public List<Motocicleta> leerMotocicletas() throws IOException {
        return this.lista;
    }

    @Override
    public Motocicleta buscarMotocicleta(String placa) throws IOException {
        for(Motocicleta moto: this.lista){
            if(moto.getPlaca().equalsIgnoreCase(placa)){
                return moto;
            }
        }
        return null;
    }

    @Override
    public List<Motocicleta> consultarMotocicleta(double value) throws IOException {
        List<Motocicleta> consulta= new ArrayList();
        for(Motocicleta moto: this.lista){
            if(moto.getValor()>value){
                consulta.add(moto);
            }
        }
        return consulta;
    }

    @Override
    public int eliminarMotocicletas(int kms) throws IOException {
        Iterator<Motocicleta> i = this.lista.iterator();
        int contador=0;
        while(i.hasNext()){
            Motocicleta moto = (Motocicleta)i.next();
            if(moto.getKms()>kms){
                i.remove();
                contador ++;
            }
        }
        return contador;
    }
    
    @Override
    public void eliminarMotocicletas(String placa) throws IOException {
        Iterator<Motocicleta> i = this.lista.iterator();
        int contador=0;
        while(i.hasNext()){
            Motocicleta moto = (Motocicleta)i.next();
            if(moto.getPlaca().equalsIgnoreCase(placa)){
                i.remove();
            }
        }
    }

    @Override
    public List<Motocicleta> consultarMotocicleta(String texto) throws IOException {
        List<Motocicleta> consulta= new ArrayList();
        for(Motocicleta moto: this.lista){
            if(moto.getPlaca().toUpperCase().contains(texto.toUpperCase()) || moto.getMarca().toUpperCase().contains(texto.toUpperCase())){
                consulta.add(moto);
            }
        }
        return consulta;
    }
}
