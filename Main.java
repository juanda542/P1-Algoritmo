import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SistemaRutaNorte sistema = new SistemaRutaNorte();
         
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

                sistema.deshacerCambioLinea(nombreDeshacer);
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
