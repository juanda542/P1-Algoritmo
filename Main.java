import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
        SistemaRutaNorte sistema = new SistemaRutaNorte();

        // Paradas
        String[][] datos = {
            {"P0","Terminal Norte"},{"P1","Plaza Colón"},{"P2","Hospital"},
            {"P3","Universidad"},{"P4","Mercado"},{"P5","Estadio"},
            {"P6","Aeropuerto"},{"P7","Costanera"},{"P8","Cuartel"},
            {"P9","Playa Chinchorro"},{"P10","Terminal Agro"},{"P11","Poblado"}
        };
        
        for (String[] d : datos) {

            sistema.registrarParada(d[0], d[1]);
        }
        
        // Líneas
        sistema.registrarLinea("L0");
        sistema.registrarLinea("L1");
        sistema.registrarLinea("L2"); // quedara vacía

        sistema.agregarParadaALinea("L0", "P0");
        sistema.agregarParadaALinea("L0", "P1");
        sistema.agregarParadaALinea("L0", "P2");
        sistema.agregarParadaALinea("L0", "P3");
        sistema.agregarParadaALinea("L0", "P4");
        
        sistema.agregarParadaALinea("L1", "P5");
        sistema.agregarParadaALinea("L1", "P6");
        sistema.agregarParadaALinea("L1", "P7");
        sistema.agregarParadaALinea("L1", "P8");

        // Tarjetas
        sistema.crearTarjeta("T0");
        sistema.cargarSaldoTarjeta("T0",1600);

        sistema.crearTarjeta("T1");
        sistema.cargarSaldoTarjeta("T1", 800); // Saldo justo

        sistema.crearTarjeta("T2");  // Saldo insuficiente
        
        // Buses
        
        sistema.encolarBus("AB1234");
        sistema.encolarBus("CD5678");
        sistema.encolarBus("EF9012");
        sistema.encolarBus("GH3456");

        // Subidas
        sistema.subirParada("T0", "P0");
        sistema.subirParada("T0", "P1");
        sistema.subirParada("T1", "P2");

        sistema.finalizarCargaInicial();
        
        Scanner sc = new Scanner(System.in);
         
        int opcion = -1;

        while (opcion != 0) {

            System.out.println("\n===== RUTANORTE =====");
            System.out.println("1. Registrar parada");
            System.out.println("2. Buscar parada");
            System.out.println("3. Mostrar subidas");
            System.out.println("4. Mostrar parada más concurrida");
            System.out.println("5. Registrar línea");
            System.out.println("6. Insertar parada en línea");
            System.out.println("7. Eliminar parada de línea");
            System.out.println("8. Deshacer cambio en línea");
            System.out.println("9. Ingresar bus al terminal");
            System.out.println("10. Despachar bus");
            System.out.println("11. Crear tarjeta");
            System.out.println("12. Cargar saldo");
            System.out.println("13. Consultar saldo");
            System.out.println("14. Pagar pasaje");
            System.out.println("0. Salir");

        System.out.print("Seleccione una opción: ");
        opcion = sc.nextInt();

        switch (opcion) {

            case 1:
                System.out.print("Ingrese código de parada: ");
                String codigo = sc.next();

                sc.nextLine(); // Limpiar salto de línea

                System.out.print("Ingrese nombre de parada: ");
                String nombre = sc.nextLine();

                sistema.registrarParada(codigo, nombre);
                break;

            case 2:
                System.out.print("Ingrese código de parada: ");
                String codigoBuscar = sc.next();

                Parada encontrada = sistema.buscarParada(codigoBuscar);

                if (encontrada != null) {
                    System.out.println("Parada encontrada:");
                    System.out.println("Código: " + encontrada.getCodigo());
                    System.out.println("Nombre: " + encontrada.getNombre());
                } else {
                    System.out.println("No se encontró la parada.");
                }
                break;

            case 3:
                sistema.mostrarSubidas();
                break;

            case 4:
                ArrayList<Parada> concurridas = sistema.paradasMasConcurridas();

                if (concurridas.isEmpty()) {
                    System.out.println("Todavía no hay subidas registradas.");
                } else {
                    for (Parada p : concurridas) {
                        System.out.println(p.getNombre() + ": " + p.getSubidas() + " subidas");
                    }
                }
                break;

            case 5:
                System.out.print("Ingrese nombre de la línea: ");
                String nombreLinea = sc.next();

                sistema.registrarLinea(nombreLinea);
                break;

            case 6:
                System.out.print("Nombre de la línea: ");
                String linea = sc.next();

                System.out.print("Código de la parada: ");
                String codigoInsertar = sc.next();

                System.out.print("Posición donde insertar: ");
                int indice = sc.nextInt();

                sistema.insertarParadaEnLinea(linea, codigoInsertar, indice);
                break;
            case 7:
                System.out.print("Nombre de la línea: ");
                String nombreEliminar = sc.next();

                System.out.print("Índice de la parada a eliminar: ");
                int indiceEliminar = sc.nextInt();

                sistema.eliminarParadaDeLinea(nombreEliminar, indiceEliminar);
                break;
            case 8:
                System.out.print("Nombre de la línea: ");
                String nombreDeshacer = sc.next();

                sistema.deshacerCambioEnLinea(nombreDeshacer);
                break;
            case 9:
                System.out.print("Ingrese patente del bus: ");
                String patente = sc.next();

                sistema.encolarBus(patente);
                break;
            case 10:
                sistema.desencolarBus();
                break;
            case 11:
                System.out.print("Ingrese ID de la tarjeta: ");
                String id = sc.next();

                sistema.crearTarjeta(id);
                break;
            case 12:
                System.out.print("Ingrese ID de la tarjeta: ");
                String idTarjeta = sc.next();

                System.out.print("Ingrese monto a cargar: ");
                int monto = sc.nextInt();

                sistema.cargarSaldoTarjeta(idTarjeta, monto);
                break;
            case 13:
                System.out.print("Ingrese ID de la tarjeta: ");
                String idConsulta = sc.next();

                sistema.consultarSaldoTarjeta(idConsulta);
                break;
            case 14:
                System.out.print("Ingrese ID de la tarjeta: ");
                String idPago = sc.next();

                System.out.print("Ingrese código de la parada: ");
                String codigoParada = sc.next();

                sistema.subirParada(idPago, codigoParada);
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }       


    }
    sc.close();
    }
}
