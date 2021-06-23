
package Negocio;

import Datos.*;
import Modelo.Motocicleta;
import java.io.IOException;
import java.util.List;

public class GestionMotocicletas{

    private IAccesoDatos datos;

    public GestionMotocicletas() {
        
        this.datos = new ImpArchivoObjeto();
        //this.datos = new ArchivoTexto();
        //this.datos = new ListaMotocicleta();
    }
    
    
    public void registrarMotocicleta(Motocicleta moto) throws IOException {
        
        if(moto==null)
            throw new NullPointerException("Debe registrar una motocicleta");
        
        if(moto.getPlaca()==null || moto.getPlaca().isEmpty())
            throw new NullPointerException("Debe registrar la Placa de la Motocicleta");
        
        if(moto.getMarca()==null || moto.getMarca().isEmpty())
            throw new NullPointerException("Debe registrar la marca de la motocicleta");
        
        if(moto.getKms()<0)
            throw new NullPointerException("Debe registrar un valor valido para los KMS de la motocicleta");
        
        if(moto.getValor()<=0)
            throw new NullPointerException("Debe registrar un Valor valido de la motocicleta");
        
        if(this.buscarMotocicleta(moto.getPlaca())!=null)
            throw new NullPointerException("La motocicleta ya se encuentra Registrada");
        
        this.datos.registrarMotocicleta(moto);
    }

    public List<Motocicleta> leerMotocicletas() throws IOException {
        return this.datos.leerMotocicletas();
    }

    public Motocicleta buscarMotocicleta(String placa) throws IOException {
        if(placa==null || placa.isEmpty())
            throw new NullPointerException("Se reqiere un valor valido para la Placa");
        
        return this.datos.buscarMotocicleta(placa);
    }

    public List<Motocicleta> consultarMotocicleta(double value) throws IOException {
        return this.datos.consultarMotocicleta(value);
    }

    public int eliminarMotocicletas(int kms) throws IOException {
        return this.datos.eliminarMotocicletas(kms);
    }

    public void eliminarMotocicletas(String placa) throws IOException {
        if(placa==null || placa.isEmpty())
            throw new NullPointerException("Se reqiere un valor valido para la Placa");
        
        this.datos.eliminarMotocicletas(placa);
    }
    
    public List<Motocicleta> consultarMotocicleta(String texto) throws IOException {
        return this.datos.consultarMotocicleta(texto);
    }
    
}
