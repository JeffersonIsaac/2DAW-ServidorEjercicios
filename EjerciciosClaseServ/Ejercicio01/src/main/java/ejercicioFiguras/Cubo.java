package ejercicioFiguras;

public class Cubo extends Figura3D{

    private double lado;

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularVolumen() {
        return lado*3;
    }
    @Override
    public void dibujar(Object figura) {
        System.out.println("Dibujando un Cubo 3D de lado: " + this.lado);
        int l = (int) Math.round(this.lado);
        int profundidad = l / 2; // Perspectiva para el fondo

        int ancho = l + profundidad;
        int alto = l + profundidad;
        char[][] lienzo = new char[alto][ancho];

        // Inicializamos el lienzo con espacios en blanco
        for (int i = 0; i < alto; i++) java.util.Arrays.fill(lienzo[i], ' ');

        // 1. Dibujar cara frontal (Luz media)
        for (int y = profundidad; y < alto; y++) {
            for (int x = 0; x < l; x++) lienzo[y][x] = '#';
        }

        // 2. Dibujar cara superior (Luz fuerte)
        for (int y = 0; y < profundidad; y++) {
            int desplazamiento = profundidad - y;
            for (int x = 0; x < l; x++) lienzo[y][x + desplazamiento] = '@';
        }

        // 3. Dibujar cara lateral derecha (Sombra oscura)
        for (int dx = 0; dx < profundidad; dx++) {
            int x = l + dx;
            int startY = profundidad - dx - 1;
            for (int y = 0; y < l; y++) {
                if (startY + y < alto && x < ancho) {
                    lienzo[startY + y][x] = ':';
                }
            }
        }

        // Imprimir el lienzo en consola
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) System.out.print(lienzo[i][j] + " ");
            System.out.println();
        }
    }
}
