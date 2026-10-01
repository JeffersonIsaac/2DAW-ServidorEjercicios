package modelo;

public class Usuario {

    private String nombre;
    private String contra;
    private String nombreCompleto;
    private int edad;
    private String correo;

    public Usuario (String nombre,String contra, String nombreCompleto, int edad, String correo){
    this.nombre = nombre;
    this.contra = contra;
    this.nombreCompleto = nombreCompleto;
    this.edad = edad;
    this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
