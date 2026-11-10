import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

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


   

    
}
