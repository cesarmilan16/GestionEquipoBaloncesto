import java.util.Scanner;

import Modelo.Liga;

public class App {

    public static Liga liga = new Liga();
    public static void main(String[] args) throws Exception {
        boolean salir = false;
        while (!salir) {
            salir = mostraMenuPrincipal();
        }
    }


    private static boolean mostraMenuPrincipal() {
        boolean salir = false;
        System.out.println("*****************************");
        System.out.println("*******Menu principal********");
        System.out.println("*****************************");
        System.out.println("1.- Alta equipo");
        System.out.println("2.- Baja equipo");
        System.out.println("3.- Gestión equipo");
        System.out.println("4.- Lista de los jugadores más altos");
        System.out.println("5.- Lista ordenada de entrenadores");
        System.out.println("6.- Introducir partido");
        System.out.println("7.- Dar clasificación");
        System.out.println("8.- Dar clasificación");
        System.out.println("9.- Salir");
        

        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                liga.altaEquipo();
                break;
            case "2":
                liga.bajaEquipo();
                break;
            case "3":
                liga.gestionEquipo();
                break;
            case "4":
                liga.listarJugadores();
                break;
            case "5":
                liga.listarEntrenadores();
                break;
            case "6":
                liga.meterPartido();
                break;
            case "7":
                liga.clasificacion();
                break;
            case "8":
                liga.imprimirPartidos();
            case "9":
                scanner.close();
                salir = true;
                break;
            default:
                break;
        }
        return salir;
    }
}