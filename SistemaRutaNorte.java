import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SistemaRutaNorte {
    public static final int PASAJE = 800;
    /// Los buses se identificar simplemente con un String que representa la patente
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
    
    // Metodos paradas


    public void registrarParada(String codigo, String nombre) {
        if (paradas.containsKey(codigo)) {
            System.out.println("Ya existe una parada con el código " + codigo);
        }else{
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

    // Metodos Bus
    
    public void encolarBus(String patente){
        if(terminal.contains(patente)){
            System.out.println("El bus ya está en el terminal");  
        }else{
        terminal.offer(patente);
        System.out.println("Bus de patente: " + patente + " ingresó al terminal.");
        }
    }

    public void desencolarBus(){
        String patente = terminal.poll();
        if (patente == null) {
            System.out.println("No hay buses esperando en el terminal.");
        }else{
    System.out.println("Saliendo bus de patente: " + patente + " del terminal.");
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
        if (tarjeta == null){ 
           System.out.println("La tarjeta " + id + " no existe.");
        }else{
            tarjeta.cargarSaldo(monto);
        }
    }

    public void consultarSaldoTarjeta(String id){
        Tarjeta tarjeta = tarjetas.get(id);
        if (tarjeta == null){  
            System.out.println("La tarjeta " + id + " no existe.");
        }else{
            tarjeta.consultarSaldo();
        }
    }

    // Se utiliza el cobro del pasaje de una tarjeta para representar a la persona subiendose a la parada 
    public void subirParada(String idTarjeta, String codigoParada) {
        
        Tarjeta tarjeta = tarjetas.get(idTarjeta);
        Parada parada = paradas.get(codigoParada);
        
        if (tarjeta == null) {
            System.out.println("La tarjeta " + idTarjeta + " no existe.");
        }else if (parada == null) {
            System.out.println("La parada " + codigoParada + " no existe.");
        }else if(tarjeta.cobrarPasaje(PASAJE)){
            parada.sumarSubida();
        }
    }


    // Metodos líneas
    
    public void registrarLinea(String nombre) {
        if (lineas.containsKey(nombre)) {
            System.out.println("Ya existe una línea llamada " + nombre);
        } else {
            lineas.put(nombre, new Linea(nombre));
            System.out.println("Línea registrada: " + nombre);
        }
    }

    // Agregar sin posición
    public void agregarParadaALinea(String nombreLinea, String codigoParada) {
        Linea linea = lineas.get(nombreLinea);
        Parada parada = paradas.get(codigoParada);
        
        if (linea == null) {
            System.out.println("La línea " + nombreLinea + " no existe.");
        }else if (parada == null) {
            System.out.println("La parada " + codigoParada + " no existe.");
        }else{
            linea.agregarParada(parada); 

        }
    }
    
    // Agregar en posición
    public void insertarParadaEnLinea(String nombreLinea, String codigoParada, int indice) {
        Linea linea = lineas.get(nombreLinea);
        Parada parada = paradas.get(codigoParada);
        
        if (linea == null) {
            System.out.println("La línea " + nombreLinea + " no existe.");
        } else if (parada == null) {
            System.out.println("La parada " + codigoParada + " no existe.");
        } else {
            linea.insertarParada(parada, indice);
        }
    }

    // Eliminar una parada de la linea por su indice
    public void eliminarParadaDeLinea(String nombreLinea, int indice) {
        Linea linea = lineas.get(nombreLinea);
        if (linea == null) {
            System.out.println("La línea " + nombreLinea + " no existe.");
        } else {
            linea.eliminarParada(indice);
        }
    }
    
    // Recuperar el recorrido anterior
    public void deshacerCambioEnLinea(String nombreLinea) {
        Linea linea = lineas.get(nombreLinea);
        if (linea == null) {
            System.out.println("La línea " + nombreLinea + " no existe.");
        } else {
            linea.deshacerUltimoCambio();
        }
    }

    // Los datos iniciales son el punto de partida
    public void finalizarCargaInicial(){
        for (Linea linea : lineas.values()) {
            linea.limpiarHistorial();
        }
    }

    public void mostrarRecorridoLinea(String nombreLinea) {
        Linea linea = lineas.get(nombreLinea);
        if (linea == null) {
            System.out.println("La línea " + nombreLinea + " no existe.");
        }else {
            linea.mostrarRecorrido(); 
        }
    }

    
    }
