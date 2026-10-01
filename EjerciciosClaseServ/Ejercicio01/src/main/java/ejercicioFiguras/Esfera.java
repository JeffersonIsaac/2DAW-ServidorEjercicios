package ejercicioFiguras;

public class Esfera extends Figura3D {
    private double radio;

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularVolumen() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radio, 3);
    }

    @Override
    public void dibujar(Object figura) {
        System.out.println("\n--- Dibujando Esfera 3D (Radio: " + this.radio + ") ---");
        double r = this.radio;

        // Vector de la fuente de luz (iluminando desde arriba a la izquierda hacia adentro)
        double luzX = -1.0, luzY = -1.0, luzZ = 1.0;
        // Normalizamos el vector de luz
        double longitudLuz = Math.sqrt(luzX*luzX + luzY*luzY + luzZ*luzZ);
        luzX /= longitudLuz; luzY /= longitudLuz; luzZ /= longitudLuz;

        // Paleta de caracteres por densidad (de sombra oscura a brillo intenso)
        String sombras = " .:-=+*#%@";

        // Iteramos sobre un plano 2D. Multiplicamos el límite X por 2 para corregir
        // que los caracteres de consola son el doble de altos que de anchos.
        for (double y = -r; y <= r; y++) {
            for (double x = -r * 2; x <= r * 2; x++) {
                double nx = x / 2.0; // Ajuste de aspecto
                double ny = y;

                // Comprobamos si el punto 2D cae dentro de la circunferencia
                if (nx*nx + ny*ny <= r*r) {
                    // Calculamos la profundidad (Z) usando el teorema de Pitágoras 3D
                    double nz = Math.sqrt(r*r - nx*nx - ny*ny);

                    // Calculamos la luz usando el producto escalar
                    double normalX = nx / r;
                    double normalY = ny / r;
                    double normalZ = nz / r;

                    double intensidad = normalX * luzX + normalY * luzY + normalZ * luzZ;

                    // Añadimos luz ambiental para que no desaparezca la parte trasera
                    intensidad = 0.2 + intensidad * 0.8;
                    if (intensidad < 0) intensidad = 0;
                    if (intensidad > 1) intensidad = 1;

                    // Asignamos el caracter correspondiente según la luz
                    int indiceSombra = (int) Math.round(intensidad * (sombras.length() - 1));
                    System.out.print(sombras.charAt(indiceSombra));
                } else {
                    System.out.print(" "); // Espacio exterior
                }
            }
            System.out.println();
        }
    }
}
