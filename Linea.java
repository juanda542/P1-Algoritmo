import java.util.LinkedList;
public class Linea {
    private String nombre;
    private LinkedList<Parada> paradas;
    private Stack<>
    
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
        System.out.println("Parada: " + parada.getNombre() + " agregada exitosamente en la línea: " + nombre);
    }
    

    //Eliminar parada por su índice
    public Parada eliminarParada(int indice) {
        if (indice >= 0 && indice < paradas.size()) {
            Parada eliminada = paradas.remove(indice);
            System.out.println("Parada"+ eliminada.getNombre() + "eliminada de la línea " + nombre);
            return eliminada;
        } else {
            System.out.println("Índice inválido.");
            return null;
        }
    }

    //Obtener la lista completa de paradas
    public LinkedList<Parada> getParadas() {
        return paradas;
    }

     // Mostrar el recorrido actual
    public void mostrarRecorrido() {
        System.out.println("Recorrido de la línea " + nombre + ": " + paradas);
    }

    
}
