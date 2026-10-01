package herencia;

public class OsoPanda extends Animal{
    private String colorPelo;

    @Override
    public void comer(Object comida) {
        if (comida == this){
            System.out.println(" NO me voy a comer a mi mismo ! ");
        }

        if (this.getEdad() <= 2){
            if (comida instanceof String){
                // preguntar y convertir
                String s = (String) comida;
                if(s.equals("leche")){
                    System.out.println("Soy un osito bebe :D u voy a tomar leche ");
                    this.setPeso(this.getPeso()+0.5);
                }
            }
        }else{
            // Esto es equivalente a preguntar y convertir como arriba
            if (comida instanceof Animal animal){
                System.out.println("Ummm ...! Soy un Oso adulto y voy a comer un animal :D :D! ");
                this.setPeso(this.getPeso() + (animal.getPeso())/10);
            }else {
                System.out.println("Solo como animales");
            }
        }
    }
}
