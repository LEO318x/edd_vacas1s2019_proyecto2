public class reservacionesH {
    private int costoTotal;
    private int tiempoVuelo;
    private int numReservacion;
    private String nombreCliente;
    //private listaenlazada;

    public reservacionesH(int costoTotal, int tiempoVuelo, int numReservacion, String nombreCliente){
        setCostoTotal(costoTotal);
        setTiempoVuelo(tiempoVuelo);
        setNumReservacion(numReservacion);
        setNombreCliente(nombreCliente);
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void setTiempoVuelo(int tiempoVuelo) {
        this.tiempoVuelo = tiempoVuelo;
    }

    public void setNumReservacion(int numReservacion) {
        this.numReservacion = numReservacion;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public int getTiempoVuelo() {
        return tiempoVuelo;
    }

    public int getNumReservacion() {
        return numReservacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
}
