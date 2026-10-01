package ejercicioFiguras;
public class Circulo extends Figura2D{

    private double radio;

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }
    @Override
    public void dibujar(Object figura) {
        System.out.println("Dibujando un Círculo de radio: " + this.radio);
        // Convertimos a entero para poder iterar en el bucle
        int r = (int) Math.round(this.radio);

        for (int i = -r; i <= r; i++) {
            for (int j = -r; j <= r; j++) {
                // Si el punto está dentro del radio, dibujamos un asterisco
                if (i * i + j * j <= r * r) {
                    System.out.print("* ");
                } else {
                    System.out.print("  "); // Espacio en blanco
                }
            }
            System.out.println(); // Salto de línea
        }
    }

}
