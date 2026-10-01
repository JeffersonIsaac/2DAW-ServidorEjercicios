package ejercicioFiguras;

import java.util.Scanner;

public class MainFigura {

    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el valor del radio de tu circulo:  ");
        double radio = sc.nextDouble();
        Circulo circulo1 = new Circulo();
        circulo1.setRadio(radio);
        circulo1.dibujar(circulo1);

        System.out.println("Introduce el valor del lado de tu cuadrado:  ");
        double ladoCuadrado = sc.nextDouble();
        Cuadrado cuadrado1 = new Cuadrado();
        cuadrado1.setLado(ladoCuadrado);
        cuadrado1.dibujar(cuadrado1);
        System.out.println("Introduce el valor de la Base de tu triángulo:  ");
        double baseTri = sc.nextDouble();
        System.out.println("Introduce el valor de la Altura de tu triángulo:  ");
        double alturatTri = sc.nextDouble();

        Triangulo triangulo1 = new Triangulo();
        triangulo1.setBase(baseTri);
        triangulo1.setAltura(alturatTri);
        triangulo1.dibujar(triangulo1);


        System.out.println("AHORA 3D ");
        System.out.println("Introduce el valor del radio de tu esfera:  ");
        double radioEsf = sc.nextDouble();

        Esfera esfera1 = new Esfera();
        esfera1.setRadio(radioEsf);
        esfera1.dibujar(esfera1);

        System.out.println("Introduce el valor del lado de tu cubo:  ");
        double ladoCubo = sc.nextDouble();

        Cubo cubo1 = new Cubo();
        cubo1.setLado(ladoCubo);
        cubo1.dibujar(cubo1);

        System.out.println("Introduce el valor de la Base de tu pirámide:  ");
        double basePi = sc.nextDouble();
        System.out.println("Introduce el valor de la Altura de tu pirámide:  ");
        double alturatPi = sc.nextDouble();

        Piramide piramide1 = new Piramide();
        piramide1.setBase(basePi);
        piramide1.setAltura(alturatPi);
        piramide1.dibujar(piramide1);



    }
}
