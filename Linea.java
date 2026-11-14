import java.util.LinkedList;
import java.util.Stack;
public class Linea {
    private String nombre;
    private LinkedList<Parada> paradas;
    private Stack<Cambio> historial;
    
    public Linea (String nombre){
        this.nombre = nombre;
        this.paradas = new LinkedList<>();
        this.historial = new Stack<>();
    }

     public String getNombre() {
        return nombre;
    }
    
    //Agregar parada
    public void agregarParada(Parada parada) {
        paradas.add(parada);
        historial.push(new Cambio("AGREGAR", parada, paradas.size()-1));
        System.out.println("Parada: " + parada.getNombre() + " agregada exitosamente en la línea: " + nombre);
    }

    public void insertarParada(Parada parada, int indice) {
        if (indice >= 0 && indice <= paradas.size()) {
            paradas.add(indice, parada);
            historial.push(new Cambio("AGREGAR", parada, indice));
            System.out.println("Parada: " + parada.getNombre() + " agregada en la línea: " + nombre);
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    //Eliminar parada por su índice
public Parada eliminarParada(int indice) {
        if (indice >= 0 && indice < paradas.size()) {
            Parada eliminada = paradas.remove(indice);
            historial.push(new Cambio("ELIMINAR", eliminada, indice));
            System.out.println("Parada "+ eliminada.getNombre() + " eliminada de la línea " + nombre);
            return eliminada;
        } else {
            System.out.println("-> Índice inválido.");
            return null;
        }
    }

    // Deshacer el ultimo cambio. Dos llamadas seguidas deshacen los dos ultimos.
    public void deshacerUltimoCambio() {
        if (historial.isEmpty()) {
            System.out.println("No hay cambios para deshacer en la línea" + nombre);
            return;
        }
        Cambio ultimo = historial.pop();
        if (ultimo.getTipo().equals("AGREGAR")){
            paradas.remove(ultimo.getIndice());
            System.out.println("Deshecho: se quitó" + ultimo.getParada().getNombre());
        } else if (ultimo.getTipo().equals("ELIMINAR")){
            paradas.add(ultimo.getIndice(), ultimo.getParada());
            System.out.println("Deshecho: se recuperó " + ultimo.getParada().getNombre());
        }
    }

    // Limpia el historial
    public void limpiarHistorial() {
        historial.clear();
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
