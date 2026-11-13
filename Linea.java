import java.util.LinkedList;
public class Linea {
    private String nombre;
    private LinkedList<Parada> paradas;
    private Stack<String> historialCambios;

    
    public Linea (String nombre){
        this.nombre = nombre;
        this.paradas = new LinkedList<>();
    }

     public String getNombre() {
        return nombre;
    }
    
    //Agregar parada
    public void agregarParada(Parada parada) {
        paradas.add(parada);
        historialCambios.push("AGREGAR:" + parada.getCodigo());
        System.out.println("Parada: " + parada.getNombre() + " agregada exitosamente en la línea: " + nombre);
    }

    public void insertarParada(Parada parada, int indice) {
        if (indice >= 0 && indice <= paradas.size()) {
            paradas.add(indice, parada);
            historialCambios.push("AGREGAR:" + parada.getCodigo());
            System.out.println("Parada: " + parada.getNombre() + " agregada en la línea: " + nombre);
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    //Eliminar parada por su índice
public Parada eliminarParada(int indice) {
        if (indice >= 0 && indice < paradas.size()) {
            Parada eliminada = paradas.remove(indice);
            historialCambios.push("ELIMINAR:" + indice + ":" + eliminada.getCodigo());
            System.out.println("-> Parada " + eliminada.getNombre() + " eliminada de la línea " + nombre);
            return eliminada;
        } else {
            System.out.println("-> Índice inválido.");
            return null;
        }
    }

    public void deshacerCambio(HashMap<String, Parada> mapaGlobalParadas) {
        if (historialCambios.isEmpty()) {
            System.out.println("-> No hay cambios que deshacer en la línea " + nombre);
            return;
        }

        String ultimaAccion = historialCambios.pop();
        String[] partes = ultimaAccion.split(":");
        String tipo = partes[0];

        if (tipo.equals("AGREGAR")) {
            String codigoParada = partes[1];
            paradas.removeIf(p -> p.getCodigo().equals(codigoParada));
            System.out.println("-> [DESHACER] Se revirtió la adición de la parada " + codigoParada);
        } else if (tipo.equals("ELIMINAR")) {
            int indice = Integer.parseInt(partes[1]);
            String codigoParada = partes[2];
            Parada restaurada = mapaGlobalParadas.get(codigoParada);
            if (restaurada != null) {
                paradas.add(indice, restaurada);
                System.out.println("-> [DESHACER] Se restauró la parada " + restaurada.getNombre() + " en el índice " + indice);
            }
        }
    }

    //Obtener la lista completa de paradas
    public LinkedList<Parada> getParadas() {
        return paradas;
    }

     // Mostrar el recorrido actual
    public void mostrarRecorrido() {
        System.out.println("Recorrido de la línea " + nombre);
        for (Parada p : paradas) {
            System.out.println(p.getCodigo() + " " + p.getNombre());
        }
    }

    
}
