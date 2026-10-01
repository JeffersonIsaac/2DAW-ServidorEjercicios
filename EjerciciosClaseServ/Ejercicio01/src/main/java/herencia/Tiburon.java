package herencia;

import java00.Person;

public class Tiburon extends Animal{
    private int numAletas;


    public int getNumAletas() {
        return numAletas;
    }

    public void setNumAletas(int numAletas) {
        this.numAletas = numAletas;
    }

    @Override
    public void comer(Object comida) {
        if(comida instanceof Person p){
            System.out.println(p.getNombre() + ": Socorro! Un tiburon!!");
            System.out.println("Ñam Ñam, una persona, a comer!!!");
            this.setPeso(this.getPeso() + (p.getPeso()  / 10));
        }else{
            System.out.println("Los tiburones solo comen personas");
        }


    }
}
