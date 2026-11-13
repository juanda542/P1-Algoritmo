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

    public void encolarBus(String bus){
        if(terminal.contains(bus)){
            System.out.println("Error: el bus ya está en el terminal");  
        }else{
        terminal.offer(bus);
        System.out.println("Bus " + bus + " ingresó al terminal.");
        }
    }

    public void desencolarBus(){
        String bus = terminal.poll();
        if (bus == null) {
            System.out.println("No hay buses esperando en el terminal.");
        }else{
    System.out.println("Saliendo bus " + bus + " del terminal.");
        }
    }
    

    public void crearTarjeta(String id) {
        if(tarjetas.containsKey(id)){
            System.out.println("Ya existe una tarjeta con el id: " + id);
        }else{
        tarjetas.put(id,new Tarjeta(id));
        System.out.println("Tarjeta registrada: " + id);
        }
    }
    

    public void cargarSaldoTarjeta(String id,int monto){
        Tarjeta tarjeta = tarjetas.get(id);
        if (tarjeta == null){ // en caso de que no exista
           System.out.println("La tarjeta " + id + " no existe.");
        }else if (!tarjeta.cargarSaldo(monto)) { // esta condicion funciona pq el cargar saldo devuelve falso si el monto es <= 0
        System.out.println("Monto inválido: debe ser mayor a 0.");      
        }else{
        System.out.println("Saldo cargado. Saldo actual de tarjeta " + id + ": $" + tarjeta.getSaldo());
        }
    }

    public void consultarSaldoTarjeta(String id){
        Tarjeta tarjeta = tarjetas.get(id);
        if (tarjeta == null){  
            System.out.println("La tarjeta " + id + " no existe.");
        }else{
            System.out.println("Saldo de tarjeta " + id + ": $" + tarjeta.getSaldo());
        }
    }

    public void subirParada(String idTarjeta, String codigoParada) {
        Tarjeta tarjeta = tarjetas.get(idTarjeta);
        Parada parada = paradas.get(codigoParada);
        if (tarjeta == null) {
            System.out.println("Error: la tarjeta " + idTarjeta + " no existe.");
        }else if (parada == null) {
            System.out.println("Error: la parada " + codigoParada + " no existe.");
        }else if (!tarjeta.cobrarPasaje(PASAJE)) {
            System.out.println("Saldo insuficiente en " + idTarjeta + " $ " + tarjeta.getSaldo());
        }else{
            parada.sumarSubida();
            System.out.println("Subida registrada en " + parada.getNombre() + ". Saldo restante: $" + tarjeta.getSaldo());
        }
    }

    public void registrarLinea(String nombre) {
        if (lineas.containsKey(nombre)) {
            System.out.println("Error: ya existe una línea llamada " + nombre);
        } else {
            lineas.put(nombre, new Linea(nombre));
            System.out.println("Línea registrada: " + nombre);
        }
    }

    
    public void agregarParadaALinea(String nombreLinea, String codigoParada) {
        Linea linea = lineas.get(nombreLinea);
        Parada parada = paradas.get(codigoParada);
        
        if (linea == null) {
            System.out.println("Error: la línea " + nombreLinea + " no existe.");
        }else if (parada == null) {
            System.out.println("Error: la parada " + codigoParada + " no existe.");
        }else {
            linea.agregarParada(parada); 
            System.out.println("Parada " + parada.getNombre() + " agregada a la línea " + linea.getNombre());
        }
    }

    public void mostrarRecorridoLinea(String codigoLinea) {
        Linea linea = lineas.get(codigoLinea);
        if (linea == null) {
            System.out.println("Error: la línea " + codigoLinea + " no existe.");
        }else {
            linea.mostrarRecorrido(); 
        }
    }

    
    }
