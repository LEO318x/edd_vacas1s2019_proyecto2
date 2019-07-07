public class Main {

    public static void main(String[] args) {
       tablaHash hash = new tablaHash();

        reservacionesH x = new reservacionesH(100, 10, 1, "Mike");
        reservacionesH x2 = new reservacionesH(100, 10, 2, "Leonel");


        hash.insertar(x);
        hash.insertar(x2);
        hash.eliminar(30);
        hash.desplegar();
        //System.out.println(hash.getElemento(2).getNombreCliente());

    }
}
