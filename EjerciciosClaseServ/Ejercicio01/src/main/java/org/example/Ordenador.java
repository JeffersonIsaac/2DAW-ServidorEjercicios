package org.example;

import java.util.ArrayList;

public class Ordenador {
    private String marca;
    private ArrayList<Periferico> listaPerifericos;
    private PlacaBase placaBase;
    private Procesador procesador;
    private ArrayList<Ram> listaRam;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public ArrayList<Periferico> getListaPerifericos() {
        return listaPerifericos;
    }

    public void setListaPerifericos(ArrayList<Periferico> listaPerifericos) {
        this.listaPerifericos = listaPerifericos;
    }

    public PlacaBase getPlacaBase() {
        return placaBase;
    }

    public void setPlacaBase(PlacaBase placaBase) {
        this.placaBase = placaBase;
    }

    public Procesador getProcesador() {
        return procesador;
    }

    public void setProcesador(Procesador procesador) {
        this.procesador = procesador;
    }

    public ArrayList<Ram> getListaRam() {
        return listaRam;
    }

    public void setListaRam(ArrayList<Ram> listaRam) {
        this.listaRam = listaRam;
    }
    public static double calcularPrecio (Ordenador ordenador){
        double precioProcesador = ordenador.getProcesador().getPrecio();
        double precioPlacaBase = ordenador.getPlacaBase().getPrecio();
        double precioPerifericos = ordenador.getListaPerifericos().stream().mapToDouble(Periferico::getPrecio).sum();
        double precioRam = ordenador.getListaRam().stream().mapToDouble(Ram::getPrecio).sum();
        return precioProcesador+precioPlacaBase+precioPerifericos+precioRam;
    }
}
