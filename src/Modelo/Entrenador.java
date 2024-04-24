package Modelo;

public class Entrenador extends Persona{

    private int anioLicencia;

    public Entrenador(String nombre, String apellido, int anioLicencia){
        super(nombre, apellido);
        this.anioLicencia = anioLicencia;
    }
}
