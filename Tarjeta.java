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
            System.out.println("Pasaje cobrado a tarjeta:  " + id);
            return true; 
        }
        System.out.println("Saldo insuficiente en tarjeta " + id + ": $" + saldo);
        return false; 
    }
    
    // Cargar saldo a la tarjeta
    public void cargarSaldo(int cantidad){
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Saldo cargado exitosamente. Saldo actual: $" + saldo);
        }else{
        System.out.println("Monto inválido: debe ser mayor a 0.");
        }
    }

    public void consultarSaldo() {
        System.out.println("Saldo de tarjeta: $ " + saldo);
    }
    

    public int getSaldo() {
        return saldo;
    }

    public String getId() {
        return id;
    }
    
    
}
