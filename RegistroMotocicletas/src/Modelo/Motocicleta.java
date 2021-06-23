package Modelo;

import java.io.Serializable;
import java.util.List;

public class Motocicleta implements Serializable {
    private String placa, marca;
    private int modelo, kms;
    private double valor;
    
   
    public Motocicleta() {
    }

    public Motocicleta(String placa) {
        this.placa = placa;
    }

    public Motocicleta(String marca, int modelo, int kms, double valor) {
        this.marca = marca;
        this.modelo = modelo;
        this.kms = kms;
        this.valor = valor;
    }

    public Motocicleta(String placa, String marca, int modelo, int kms, double valor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.kms = kms;
        this.valor = valor;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public int getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the kms
     */
    public int getKms() {
        return kms;
    }

    /**
     * @param kms the kms to set
     */
    public void setKms(int kms) {
        this.kms = kms;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Motocicleta{" + "placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", kms=" + kms + ", valor=" + valor + '}';
    }
    
    public String getEstructuraTexto(){
       return  placa + ";" + marca + ";" + modelo + ";" + kms + ";" + valor ;
    }
}
