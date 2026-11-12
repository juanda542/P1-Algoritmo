import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;

public class SistemaRutaNorte {
    public static final int PASAJE = 800;
    /// Los buses se identificar simplemente con un String puede ser patente o literalmetne BUS1 BUS2...
    private Queue<String> terminal;
    private HashMap<String, Parada> paradas;
    private HashMap<String, Linea> lineas;
    private HashMap<String,Tarjeta> tarjetas;

    public SistemaRutaNorte (){
        this.paradas = new HashMap<>();
        this.lineas = new HashMap<>();
        this.tarjetas = new HashMap<>();
        this.terminal = new LinkedList<>();
    }
    // Agregar una parada al sistema
    public void registrarParada(String codigo, String nombre) {
        if (!paradas.containsKey(codigo)) {
            paradas.put(codigo, new Parada(codigo, nombre));
            System.out.println("Parada registrada: " + nombre);
        }
    }

    // Buscar parada por código
    public Parada buscarParada(String codigo) {
        return paradas.get(codigo);
    }

    public boolean registrarSubida(String codigo) {
        Parada parada = paradas.get(codigo);

        if (parada == null) {
            return false;
        }
        parada.sumarSubida();
        return true;
    }

    public void mostrarSubidas() {
        for (Parada p : paradas.values()) {
            System.out.println(p.getCodigo() + " - " + p.getNombre() + ": " + p.getSubidas() + " subidas");
        }
    }
    
    public ArrayList<Parada> paradasMasConcurridas() {
        ArrayList<Parada> masConcurridas = new ArrayList<>();
        int subidasMax = -1;

        for (Parada p : paradas.values()) {

            if (p.getSubidas() > subidasMax) {
                subidasMax = p.getSubidas();
                masConcurridas = new ArrayList<>();
                masConcurridas.add(p);

            } else if (p.getSubidas() == subidasMax) {
            masConcurridas.add(p);
            }
        }
        if (subidasMax == 0){
            return new ArrayList<>();
        }


        return masConcurridas;
    }
 
}
