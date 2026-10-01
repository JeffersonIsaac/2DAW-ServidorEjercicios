package ejercicioFiguras;

public class Cuadrado extends Figura2D{

    private double lado;

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado*2;
    }
    @Override
    public void dibujar(Object figura) {
        System.out.println("Dibujando un Cuadrado de lado: " + this.lado);
        int l = (int) Math.round(this.lado);

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

}
