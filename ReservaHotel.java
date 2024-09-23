import java.util.Scanner;

public class ReservaHotel {
    

    static String [] nombresClientes=new String[5];
    static double[] preciosHabitaciones = {50, 100, 150, 200, 250};
    static int[] numerosHabitaciones = {101, 102, 103, 104, 105};
    static int[] nochesReservadas = new int[5];
    static boolean [] estadoHabitaciones= new boolean[5];
    
    
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean continuar=true;

    while (continuar){
        System.out.println("Sistema de Gestión de Reservas");
        System.out.println("1. Reservar una habitación");
        System.out.println("2. Cancelar una reserva");
        System.out.println("3. Mostrar reporte");
        System.out.println("4. Salir del programa");
        System.out.println("Seleccione una opción:");
        int opcion = scanner.nextInt();
        scanner.nextLine();
    

        switch (opcion){
        case 1 :
            registrarReserva(scanner);
        break;
        case 2:
            cancelarReserva(scanner);
        break;
        case 3:
            mostrarReporte();
        break;
        case 4:
            System.out.println("Saliendo del sistema...");
        continuar=false;
        break;
            default:
                    System.out.println("Opción no válida, intente nuevamente");
        }
    }
    scanner.close();
}
    public static void registrarReserva(Scanner scanner){
    

    System.out.println("Ingrese numero de habitacion(101-105)");
    int numHabitacion=scanner.nextInt();

    if (numHabitacion < 101 || numHabitacion > 105) {
    System.out.println("El número de habitación no es válido.");
    return;
    }

    int indice=numHabitacion-101;
    

    if(estadoHabitaciones[indice]){
    System.out.println("La habitación esta ocupada");
    return;
    }

    scanner.nextLine();

    System.out.println("Ingrese el nombre del cliente");
    String nombreCliente=scanner.nextLine();

    System.out.println("Ingrese el numero de noches");
    int numNoches=scanner.nextInt();

    nombresClientes[indice]=nombreCliente;
    estadoHabitaciones[indice]=true;
    nochesReservadas[indice]=numNoches;

    double totalReserva=numNoches*preciosHabitaciones[indice];
    System.out.println("Reserva realizada a nombre de: "+nombreCliente+ "-Total a Pagar:"+totalReserva);

    }


    public static void cancelarReserva(Scanner scanner) {

        System.out.print("Ingrese el numero de habitacion cuya reserva desea cancelar (101-105): ");
        int numHabitacion = scanner.nextInt();
        scanner.nextLine();
    
        if (numHabitacion < 101 || numHabitacion > 105) {
            System.out.println("El numero de habitacion no es valido.");
            return;
        }
    
        int indice = numHabitacion - 101; 
    
        if (!estadoHabitaciones[indice]) {
            System.out.println("La habitacion esta disponible.");
            return;
        }
    

        nombresClientes[indice] = null;
        nochesReservadas[indice] = 0;
        estadoHabitaciones[indice] = false;
    
        System.out.println("Reserva cancelada con exito.");
    }

    public static void mostrarReporte() {
        System.out.println("Reporte Final:");
        
        // Mostrar clientes y total a pagar
        for (int indice = 0; indice < numerosHabitaciones.length; indice++) {
            if (estadoHabitaciones[indice]) {
                double totalApagar = nochesReservadas[indice] * preciosHabitaciones[indice];
                System.out.println("Cliente: " + nombresClientes[indice] + " - Habitación: " + numerosHabitaciones[indice] + " - Total a pagar: " + totalApagar);
            }
        }
    
        // Función para mostrar habitaciones según su estado
        mostrarHabitaciones("Habitaciones disponibles: ", false);
        mostrarHabitaciones("Habitaciones ocupadas: ", true);
    }
    
    private static void mostrarHabitaciones(String mensaje, boolean estado) {
        System.out.print(mensaje);
        StringBuilder habitaciones = new StringBuilder();
        
        for (int indice = 0; indice < numerosHabitaciones.length; indice++) {
            if (estadoHabitaciones[indice] == estado) {
                if (habitaciones.length() > 0) {
                    habitaciones.append(", ");
                }
                habitaciones.append(numerosHabitaciones[indice]);
            }
        }
        
        System.out.println(habitaciones.length() > 0 ? habitaciones : "Ninguna");
    }
}