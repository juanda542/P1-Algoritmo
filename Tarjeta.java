public class Tarjeta {
    private String id;
    private int saldo;

    public Tarjeta(String id){

        this.id = id;
        this.saldo = 0;

    }
    
    // Cobrar pasaje
    public boolean cobrarPasaje(int precio){
        if (saldo >= precio) {
            saldo -= precio;
            return true; 
        }
        return false; 
    }
    
    
    // Cargar saldo a la tarjeta
    public boolean cargarSaldo(int cantidad){
        if (cantidad > 0) {
            saldo += cantidad;
            return true;
        }
        return false;
    }
    

    public int getSaldo() {
        return saldo;
    }

    public String getId() {
        return id;
    }
    
    
}
