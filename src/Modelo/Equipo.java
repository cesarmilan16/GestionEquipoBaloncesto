package Modelo;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private String ciudad;
    private Entrenador entrenador;
    private ArrayList<Jugador> plantilla;

    public Equipo(String nombre, String ciudad){
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getNombre(){
        return nombre;
    }
}
