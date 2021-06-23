/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Datos.*;
import Modelo.Motocicleta;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jairo
 */
public class Test {

    public static void main(String[] args) {
        VentanaPrincipal principal = new VentanaPrincipal();
       
    }
//        Motocicleta motoA = new Motocicleta("ABC-123", "HONDA", 2010, 50, 1000);
//        Motocicleta motoB = new Motocicleta("ABC-456", "BMW", 2022, 0, 50000);
//        Motocicleta motoC = new Motocicleta("ABC-789", "YAMAHA", 2017, 20, 2000);
//
//        //IAccesoDatos datos = new ArchivoTexto();
//        //IAccesoDatos datos = new ImpArchivoObjeto();
//        IAccesoDatos datos = new ListaMotocicleta();
//        try {
//            
//            datos.registrarMotocicleta(motoA);
//            datos.registrarMotocicleta(motoB);
//            datos.registrarMotocicleta(motoC);
//            
//            System.out.println("Todas las Motocicletas Registradas");
//            System.out.println("----------------------------------------");
//            List<Motocicleta> catalogo = datos.leerMotocicletas();
//            imprimirLista(catalogo);
//            
//            System.out.println("\nBusqueda Motocicleta Por Placa");
//            System.out.println("-----------------------------------");
//            String placa="ABC-45689";
//            Motocicleta moto = datos.buscarMotocicleta(placa);
//            if(moto!=null){
//                System.out.println("Moto encontrada: ");
//                System.out.println(moto);
//            }
//            else{
//                System.out.println("La moto de placa "+placa+" no se encuentra registrada");
//            }
//            
//            System.out.println("\n Eliminar Motocicletas por KMS");
//            System.out.println("---------------------------------------");
//            int kms = 20;
//            int eliminados = datos.eliminarMotocicletas(kms);
//            System.out.println(eliminados + " registros eliminados");
//            
//            System.out.println("\nTodas las Motocicletas Registradas");
//            System.out.println("----------------------------------------");
//            catalogo = datos.leerMotocicletas();
//            imprimirLista(catalogo);
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace(System.out);
//        }
//    }

    public static void imprimirLista(List<Motocicleta> lista) {
        for (Motocicleta moto : lista) {
            System.out.println(moto);
        }
        System.out.println("Total registradas: " + lista.size());
    }
}
