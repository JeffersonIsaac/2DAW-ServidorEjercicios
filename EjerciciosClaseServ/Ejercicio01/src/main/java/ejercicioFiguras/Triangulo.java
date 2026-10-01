package ejercicioFiguras;
public class Triangulo extends Figura2D{

    private double base;
    private double altura;

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (base*altura)/2;
    }

    @Override
    public void dibujar(Object figura) {
        System.out.println("Dibujando un Triángulo de altura: " + this.altura);
        int h = (int) Math.round(this.altura);

        for (int i = 1; i <= h; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

}