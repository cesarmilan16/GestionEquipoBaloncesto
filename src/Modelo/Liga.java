package Modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Liga {
    private ArrayList<Equipo> equipos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private Equipo buscarEquipo(String nombre){
        Equipo equipoEncontrado = null;
        int contador = 0;
        while (equipoEncontrado != null && contador < equipos.size()) {
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

    public void listaJugadores(){
        for (Equipo equipo : equipos){
            equipo.datosJugadorMalAlto();
        }
    }
}