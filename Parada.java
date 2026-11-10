public class Parada {
    private String codigo;
    private String nombre;
    private int subidas;

    public Parada(String codigo,String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
        this.subidas = 0;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public int getSubidas(){
        return subidas;
    }

    public void sumarSubida(){
        subidas++;
    }
    
}
