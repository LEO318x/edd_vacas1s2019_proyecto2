public class reservacionesH {
    private int costoTotal;
    private int tiempoVuelo;
    private long numReservacion;
    private String nombreCliente;
    private listaenlazada ls;


    public reservacionesH(int costoTotal, int tiempoVuelo, long numReservacion, String nombreCliente, listaenlazada ls){
        setCostoTotal(costoTotal);
        setTiempoVuelo(tiempoVuelo);
        setNumReservacion(numReservacion);
        setNombreCliente(nombreCliente);
        setLs(ls);

    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void setTiempoVuelo(int tiempoVuelo) {
        this.tiempoVuelo = tiempoVuelo;
    }

    public void setNumReservacion(long numReservacion) {
        this.numReservacion = numReservacion;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setLs(listaenlazada ls) {
        this.ls = ls;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public int getTiempoVuelo() {
        return tiempoVuelo;
    }

    public long getNumReservacion() {
        return numReservacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public listaenlazada getLs() {
        return ls;
    }
}
