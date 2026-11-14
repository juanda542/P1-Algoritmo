public class Cambio {
    private String tipo;
    private Parada parada;
    private int indice;

    public Cambio(String tipo, Parada parada, int indice) {
        this.tipo = tipo;
        this.parada = parada;
        this.indice = indice;
    }

    public String getTipo(){
        return tipo;
    }

    public Parada getParada(){
        return parada;
    }

    public int getIndice(){
        return indice;
    }
}