package Modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Partido {
    // Atributos de la clase
    private Date fechaPartido;
    private int puntuacionLocal;
    private int puntuacionVisitante;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;

    // Constructor de la clase
    public Partido(Date fechaPartido, int puntuacionLocal, int puntuacionVisitante, Equipo equipoLocal, Equipo equipoVisitante) {
        this.fechaPartido = fechaPartido;
        this.puntuacionLocal = puntuacionLocal;
        this.puntuacionVisitante = puntuacionVisitante;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }

    // Métodos getters
    public int getPuntuacionLocal() {
        return puntuacionLocal;
    }


    public int getPuntuacionVisitante() {
        return puntuacionVisitante;
    }

    // Método para imprimir los datos del partido
    public void imprimirPartido() {
        System.out.println("Partido: " + equipoLocal.getNombre() + " VS " + equipoVisitante.getNombre() );
        // Creamos el formato de como queremos imprimir la fecha
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Fecha: " + df.format(fechaPartido));
        System.out.println("Puntos equipo local: " + puntuacionLocal);
        System.out.println("Puntos equipo visitante: " + puntuacionVisitante);
    }
}