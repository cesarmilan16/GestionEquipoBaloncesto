package Modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import Herramientas.Utilidades;

public class Liga {
    private ArrayList<Equipo> equipos = new ArrayList<>();
    private ArrayList<Partido> partidos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private Equipo buscarEquipo(String nombre){
        Equipo equipoEncontrado = null;
        int contador = 0;
        while (equipoEncontrado == null && contador < equipos.size()) {
            if (equipos.get(contador).getNombre().equals(nombre)) {
                equipoEncontrado = equipos.get(contador);
            }
            contador++;
        }
        return equipoEncontrado;
    }
    
    public void altaEquipo(){
        System.out.println("***************************");
        System.out.println("******* Alta Equipo *******");
        System.out.println("***************************");
        System.out.println("Nombre equipo: ");
        String nombre = scanner.nextLine();
        System.out.println("Ciudad equipo");
        String ciudad = scanner.nextLine();

        Equipo equipoEncontrado = buscarEquipo(nombre);

        if (equipoEncontrado == null) {
            Equipo equipo = new Equipo(nombre, ciudad);
            equipos.add(equipo);            
        }
        else{
            System.out.println("Ese equipo ya existe.");
        }
    }

    public void bajaEquipo(){
        System.out.println("***************************");
        System.out.println("******* Baja Equipo *******");
        System.out.println("***************************");
        System.out.println("Nombre equipo: ");
        String nombre = scanner.nextLine();

        Equipo equipoEncontrado = buscarEquipo(nombre);

        if (equipoEncontrado != null) {
            equipos.remove(equipoEncontrado);            
        }
        else{
            System.out.println("Ese equipo no existe.");
        }
    }

    public void gestionEquipo(){
        System.out.println("***************************");
        System.out.println("******* Gestión Equipo *******");
        System.out.println("***************************");
        System.out.println("Nombre equipo: ");
        String nombre = scanner.nextLine();

        Equipo equipoEncontrado = buscarEquipo(nombre);

        if (equipoEncontrado != null) {
            equipoEncontrado.gestionEquipo();            
        }
        else{
            System.out.println("Ese equipo no existe.");
        }
    }

    public void listarJugadores(){
        if (equipos.isEmpty()) {
            System.out.println("No hay ningún equipo dado de alta");
        }
        else{
            for (Equipo equipo : equipos){
                equipo.datosJugadorMalAlto();
            }
        }
    }

    public void listarEntrenadores() {
        try {
            ArrayList<Entrenador> entrenadores = new ArrayList<>();
    
            for (Equipo equipo : equipos) {
                entrenadores.add(equipo.getEntrenador());
            }
    
            // Ordena los entrenadores por año de licencia
            Collections.sort(entrenadores, Comparator.comparingInt(entrenador -> entrenador.getAnioLicencia()));
    
            // Imprime los detalles de cada entrenador
            for (Entrenador entrenador : entrenadores) {
                entrenador.escribirDatos();
            }
    
            // Si no se agregan entrenadores, lanza una excepción
            if (entrenadores.isEmpty()) {
                throw new Exception("No se han agregado entrenadores.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método para introducir la fecha del partido
    public Date introducirFecha() {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        while (true) {
            System.out.println("Introduce la fecha del partido en formato dd/mm/aaaa");
            String entrada = scanner.nextLine();
            try {
                Date fecha = formato.parse(entrada);
                return fecha;
            } catch (ParseException e) {
                System.out.println("Fecha incorrecta. Inténtalo de nuevo.");
            }
        }
    }

    // Método para meter el partido
    public void meterPartido() {
        System.out.println("***************************");
        System.out.println("********* Partido *********");
        System.out.println("***************************");
        // Introducimos el equipo local
        System.out.println("Nombre equipo local: ");
        String nombreLocal = scanner.nextLine();
        // Introducimos el equipo visitante
        System.out.println("Nombre equipo visitante: ");
        String nombreVisitante = scanner.nextLine();

        // Los buscamos a los dos
        Equipo equipoLocal = buscarEquipo(nombreLocal);
        // Si no se encuenta el equipo local...
        if (equipoLocal == null) {
            System.out.println("Equipo local no disponible");
        }
        Equipo equipoVisitante = buscarEquipo(nombreVisitante);
        // Si no se encuantra el equipo visitante...
        if (equipoVisitante == null && equipoLocal.equals(equipoVisitante)) {
            System.out.println("Equipo visitante no disponible");
        }
        // Si se encuentran los dos
        else {
            // Metemos las puntuaciones de ambos equipos
            int puntuacionLocal = Utilidades.leerEntero(" puntos del equipo Local");
            int puntuacionVisitante = Utilidades.leerEntero(" puntos del equipo Visitante");

            // Introducimos la fecha del partido
            Date fechaPartido = introducirFecha();

            // Creamos la instancia del partido
            Partido partido = new Partido(fechaPartido, puntuacionLocal, puntuacionVisitante);
            partidos.add(partido);

            // Si tiene mas puntuación el local se le sumará 3 puntos
            if (partido.getPuntuacionLocal() > partido.getPuntuacionVisitante()) {
                equipoLocal.agregarPuntos(3);;
            }
            // Si tiene mas puntuación el visitante se le sumará 3 puntos
            else if (partido.getPuntuacionLocal() < partido.getPuntuacionVisitante()) {
                equipoVisitante.agregarPuntos(3);;
            }
            // Si queda empate sumarán 1 ambos
            else {
                equipoLocal.agregarPuntos(1);
                equipoVisitante.agregarPuntos(1);
            }
            // Imprimimos todos los datos del partido
            partido.imprimirPartido(equipoLocal.getNombre(), equipoVisitante.getNombre());
        }
    }

    public void clasificacion() {

        // Ordena los entrenadores por año de licencia
        Collections.sort(equipos, Comparator.comparingInt(equipo -> ((Equipo) equipo).recuentoPuntos()).reversed());

        // Iteramos por los equipos ordenados y los imprimimos
        int i = 0;
        for (Equipo equipo : equipos) {
            i += 1;
            System.out.println("Equipo " + i + ":");
            System.out.println("Nombre equipo: " + equipo.getNombre());
            System.out.println("Total puntos: " + equipo.recuentoPuntos());
        }
    }

    public void imprimirPartidos() {
        for (Partido partido : partidos) {
            partido.imprimirPartido(null, null);
        }
    }
}