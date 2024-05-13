package Modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    public void altaEquipo() {
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

    public void bajaEquipo() {
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

    public void gestionEquipo() {
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

    public void listarJugadores() {
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
            // Creamos la expresión regular
            String fechaPattern = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/((2000)|(20[0-2][0-9])|2030)$";
            // La compilamos en un objeto Pattern
            Pattern pattern = Pattern.compile(fechaPattern);

            System.out.println("Introduce la fecha del partido en formato dd/mm/aaaa");
            String entrada = scanner.nextLine();
            
            // Creamos un objetor Matcher que comprobara la coincidencia con nuestro patrón
            Matcher matcher = pattern.matcher(entrada);
            // Si coincide se ejecutará el código
            if (matcher.matches()) {
                // Tratamos de convertir el String fecha en un Date y devolverla
                try {
                    Date fecha = formato.parse(entrada);
                    return fecha;
                // Manejo de la excepción parse
                } catch (ParseException e) {
                    System.out.println("Fecha incorrecta. Inténtalo de nuevo.");
                }
            }
            // Si no nos dará el siguiente mensaje
            else {
                System.out.println("La fecha ingresada no es correcta.");
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
        // Buscamos el equipo local
        Equipo equipoLocal = buscarEquipo(nombreLocal);
        // Si no se encuenta el equipo local nos mostrará el siguiente mensaje
        if (equipoLocal == null) {
            System.out.println("Equipo local no disponible");
        }
        // Si se encuentra seguimos con el código
        else {
            // Introducimos el equipo visitante
            System.out.println("Nombre equipo visitante: ");
            String nombreVisitante = scanner.nextLine();

            // Buscamos el equipo visitante
            Equipo equipoVisitante = buscarEquipo(nombreVisitante);
            // Si no se encuantra el equipo visitante nos mostrará el siguiente mensaje
            if (equipoVisitante == null) {
                System.out.println("Equipo visitante no disponible");
            }
            // Si son el mismo equipo nos saltará el siguiente mensaje
            else if (equipoLocal.equals(equipoVisitante)) {
                System.out.println("El equipo local es el mismo que el visitante");
            }
            // Si se encuentran los dos y son diferentes equipos de la liga continuamos con el código
            else {
                // Metemos las puntuaciones de ambos equipos
                int puntuacionLocal = Utilidades.leerEntero(" puntos del equipo Local");
                int puntuacionVisitante = Utilidades.leerEntero(" puntos del equipo Visitante");

                // Introducimos la fecha del partido
                Date fechaPartido = introducirFecha();

                // Creamos la instancia del partido
                Partido partido = new Partido(fechaPartido, puntuacionLocal, puntuacionVisitante, equipoLocal, equipoVisitante);
                partidos.add(partido);

                // Si tiene mas puntuación el local se le sumará 3 puntos
                if (partido.getPuntuacionLocal() > partido.getPuntuacionVisitante()) {
                    equipoLocal.agregarPuntos(3);;
                }
                // Si tiene mas puntuación el visitante se le sumará 3 puntos
                else if (partido.getPuntuacionLocal() < partido.getPuntuacionVisitante()) {
                    equipoVisitante.agregarPuntos(3);
                }
                // Si queda empate sumarán 1 ambos
                else {
                    equipoLocal.agregarPuntos(1);
                    equipoVisitante.agregarPuntos(1);
                }
                // Imprimimos todos los datos del partido
                partido.imprimirPartido();
            }
        }
    }

    // Método que nos da la clasificación
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

    // Método que imprime todo los partidos
    public void imprimirPartidos() {
        for (Partido partido : partidos) {
            partido.imprimirPartido();
        }
    }
}