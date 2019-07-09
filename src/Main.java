public class Main {

    public static void main(String[] args) {

       tablaHash hash = new tablaHash();

       /* reservacionesH x = new reservacionesH(100, 10, 1, "Mike");
        reservacionesH x2 = new reservacionesH(100, 10, 2, "Leonel");
        reservacionesH x3 = new reservacionesH(100, 10, 3, "Darwin");
        reservacionesH x4 = new reservacionesH(100, 10, 4, "Josue");
        reservacionesH x5 = new reservacionesH(100, 10, 5, "David");
        hash.insertar(x);
        hash.insertar(x2);
        hash.insertar(x3);
        hash.insertar(x4);
        hash.insertar(x5);
        hash.eliminar(3);
        hash.eliminar(20);
        hash.graficar();
        //hash.desplegar();*/

       cargaArchivos archivos = new cargaArchivos();
       //archivos.cargarReservaciones("/home/leo318x/Pruebas/reservaciones.csv", hash);
       archivos.cargarDestino("/home/leo318x/Pruebas/destinos.csv");
        //hash.desplegar();
       //hash.graficar();



        //System.out.println(hash.getElemento(1).getNumReservacion());

    }
}
