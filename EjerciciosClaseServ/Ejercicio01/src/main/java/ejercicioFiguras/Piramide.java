package ejercicioFiguras;

public class Piramide extends Figura3D {
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
    public double calcularVolumen() {
        return (2 * base * altura) / 3;
    }


    @Override
    public void dibujar(Object figura) {
        System.out.println("\n--- Dibujando Pirámide 3D (Base: " + this.base + ", Altura: " + this.altura + ") ---");
        int w = (int) this.base * 2;
        int h = (int) this.altura;
        int d = (int) this.base;

        int canvasW = w + d + 1;
        int canvasH = h + d + 1;
        char[][] canvas = new char[canvasH][canvasW];

        for (int i = 0; i < canvasH; i++) {
            for (int j = 0; j < canvasW; j++) canvas[i][j] = ' ';
        }

        for (int z = d; z >= 0; z--) {
            for (int y = 0; y <= h; y++) {
                // Calculamos el ancho y profundidad actual según la altura (punta = 0)
                int currentW = (w * y) / h;
                int currentD = (d * y) / h;

                // Calculamos los límites para centrar la pirámide
                int startX = (w - currentW) / 2;
                int endX = startX + currentW;

                int startZ = (d - currentD) / 2;
                int endZ = startZ + currentD;

                if (z >= startZ && z <= endZ) {
                    for (int x = startX; x <= endX; x++) {
                        // Dibujar solo la superficie
                        if (x == startX || x == endX || z == startZ || z == endZ || y == h) {
                            int sx = x + z;
                            int sy = y + (d - z);

                            char c = ' ';
                            if (z == startZ) c = '#';      // Cara frontal
                            else if (x == endX) c = ':';   // Cara lateral inclinada derecha
                            else if (y == h) c = '_';      // Suelo

                            // Aristas diagonales
                            boolean bordeX = (x == startX || x == endX);
                            boolean bordeZ = (z == startZ || z == endZ);
                            if ((bordeX && bordeZ) || y == 0) {
                                c = '*';
                            }

                            if (c != ' ' && sx >= 0 && sx < canvasW && sy >= 0 && sy < canvasH) {
                                canvas[sy][sx] = c;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < canvasH; i++) {
            System.out.println(new String(canvas[i]));
        }
    }
}


