package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Ordenador.calcularPrecio;

public class Main {



    static void main() {

//Se crean los componentes del ordenador.
        Procesador i9 = new Procesador();
        i9.setPrecio(400);
        i9.setMarca(MARCA.INTEL);
        PlacaBase msi = new PlacaBase();
        msi.setModelo("MSI MAG X870E Tomahawk WiF");
        msi.setChipset("X870E");
        msi.setPrecio(650);
        Ram ddr5 = new Ram();
        ddr5.setTipo("DDR5");
        ddr5.setHz(5300);
        ddr5.setPrecio(450);
        Periferico monitor = new Periferico();
        monitor.setTipo("Monitor");
        monitor.setPrecio(150);
        Periferico raton = new Periferico();
        raton.setTipo("Raton");
        raton.setPrecio(20);
        Periferico teclado = new Periferico();
        raton.setTipo("Teclado");
        raton.setPrecio(50);
        Periferico altavoz = new Periferico();
        raton.setTipo("Altavoz");
        raton.setPrecio(80);

// Se instancia un ordenador.
        Ordenador ordenador1 = new Ordenador();
        ordenador1.setMarca("LENOVO");
        ordenador1.setProcesador(i9);
        ordenador1.setPlacaBase(msi);
        ordenador1.setListaPerifericos(new ArrayList<>(List.of(monitor,raton,teclado,altavoz)));
        ordenador1.setListaRam(new ArrayList<>(List.of(ddr5)));

// se crea el calculo del precio.
        double precio = calcularPrecio(ordenador1);
        System.out.println(precio);



//POLIMORFISMO

        /** LOS OBJETOS PADRES TE PERMITEN ACCEDER A TODOS LOS ELEMENTOS HIJOS.
         * LA REFERENCIA PADRE TE PERMITE APUNTAR A CUALQUIER REFERENCIA HIJA.
         * LA REFERENCIA PADRE TE PERMITE USAR LOS METODOS DE LA PADRE PERO NO LOS DE LA HIJA.
         * TE PERIMTE HACER LISTAS QUE ENGLOBEN ELEMENTOS HIJOS.
         * PARA USAR LA REFERENCIA HIJA SE DEBE CASTEAR Y USARLO
         *
         *
         * ***LAS CLASES ABSTRACTAS NO SE PUEDEN INSTANCIAR -
         * METODO IMPLEMENTABLE PUEDE ESTAR EN UNA CLASE ABSTRACTA
         *
         *
         *
         *
         */
    }
}
