
package Datos;

import Modelo.Motocicleta;
import java.io.IOException;
import java.util.List;


public interface IAccesoDatos {
    
    void registrarMotocicleta(Motocicleta moto) throws IOException;
    List<Motocicleta> leerMotocicletas()throws IOException;
    Motocicleta buscarMotocicleta(String placa) throws IOException;
    List<Motocicleta> consultarMotocicleta(double value) throws IOException;
    int eliminarMotocicletas(int kms) throws IOException;
    void eliminarMotocicletas(String placa) throws IOException;
    List<Motocicleta> consultarMotocicleta(String texto) throws IOException;
}
