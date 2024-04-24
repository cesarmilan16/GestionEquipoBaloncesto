package Modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Liga {
    private ArrayList<Equipo> equipos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    
    public void altaEquipo(){
        System.out.println("***************************");
        System.out.println("******* Alta Equipo *******");
        System.out.println("***************************");
        System.out.println("Nombre equipo: ");
        String nombre = scanner.nextLine();
        System.out.println("Ciudad equipo");
        String ciudad = scanner.nextLine();

        Equipo equipoEncontrado = null;
        int contador = 0;
        while (equipoEncontrado != null && contador < equipos.size()) {
            if (equipos.get(contador).getNombre().equals(nombre)) {
                equipoEncontrado = equipos.get(contador);
            }
            contador++;
        }

        if (equipoEncontrado == null) {
            Equipo equipo = new Equipo(nombre, ciudad);
            equipos.add(equipo);            
        }
        else{
            System.out.println("Ese equipo ya existe.");
        }
    }
}
