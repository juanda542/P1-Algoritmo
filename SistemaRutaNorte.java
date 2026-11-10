import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public class SistemaRutaNorte {
    public static final int PASAJE = 800;

    private Queue<Bus> terminal;
    private HashMap<String, Parada> paradas;
    private HashMap<String, Linea> lineas;
    private HashMap<String,Tarjetas> tarjetas;

    public SistemaRutaNorte (){
        this.paradas = new HashMap<>();
        this.lineas = new HashMap<>();
        this.tarjetas = new HashMap<>();
        this.terminal = new LinkedList<>();

    }


   

    
}
