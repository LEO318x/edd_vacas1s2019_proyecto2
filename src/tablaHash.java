
public class tablaHash {

    float porcentajeCapacidad;
    int numDatos;
    int reservacion;
    int iteracion;
    int T;
    //Tamaño inicial de la tabla;
    reservacionesH[] Tabla;

    public tablaHash(){
        this.Tabla = new reservacionesH[7];
        this.T = 7;
        this.porcentajeCapacidad = numDatos/ T;
        this.numDatos = 0;
        this.iteracion = 0;
        this.reservacion = 1;
    }


    public int funcionHash(String nombre){
        int H = 0;
        String letras = "";
        int K = 0;
        if(nombre.length()<3){
            letras = nombre;
        }else{
            letras = nombre.substring(0, 3);
        }
        for (char c: letras.toCharArray()) {
            int ascii = (int) c;
            K += ascii;
        }
        H = K % T;
        return H;
    }

    public int funcionHashColision(String nombre){
        String letras = "";
        int K = 0;
        if(nombre.length()<3){
            letras = nombre;
        }else{
            letras = nombre.substring(0, 3);
        }
        for (char c: letras.toCharArray()) {
            int ascii = (int) c;
            K += ascii;
        }

        int H = (K + (iteracion + 1)*(2))%T;
        return  H;
    }

    public void insertar(reservacionesH nuevo){
        nuevo.setNumReservacion(reservacion);

        porcentajeCapacidad = (float) numDatos/ T;
        int clave = funcionHash(nuevo.getNombreCliente());

        //Verificamos el porcentaje de desbordamiento, si es >=50 se proce a aumentar la tabla a su siguiente numero primo
        if(porcentajeCapacidad <= 0.5){
            while(true){
                if(Tabla[clave]==null){ // Si es null el contenido de la clave se procede a insertar
                    nuevo.setNumReservacion(reservacion);
                    Tabla[clave]=nuevo;
                    iteracion = 0;
                    numDatos++;
                    reservacion++;
                    System.out.println("Se ha insertado, clave "+clave);
                    break;
                }else{
                    while(true){ //Se procede a la resolución de la colisión.
                        clave = funcionHashColision(nuevo.getNombreCliente());
                        if(Tabla[clave]==null){ //Si la clave generada su contenido es nulo se procede a llenar
                            nuevo.setNumReservacion(reservacion);
                            Tabla[clave] = nuevo;
                            iteracion = 0;
                            numDatos++;
                            reservacion++;
                            System.out.println("Colision resolvida, clave "+clave);
                            break;
                        }else{ //La clave generada entra en colisión nuevamente, se itera y se repite el while
                            System.out.println("Hubo colision en clave "+clave);
                            iteracion++;
                        }
                    }
                    break;
                }
            }
        }else{
            //Aumentar el vector a su siguiente numero primo e inserta nuevamente.
            int numeroPrimoSiguiente = calcularPrimoSiguiente(T);
            aumentarTabla(numeroPrimoSiguiente);
            insertar(nuevo);
        }
    }


    private void aumentarTabla(int numTabla){
        reservacionesH[] temp = new reservacionesH[numTabla];
        System.arraycopy(Tabla, 0, temp, 0, Tabla.length);
        Tabla = new reservacionesH[numTabla];
        Tabla = temp;
        T = numTabla;

    }

    public reservacionesH getElemento(int clave){
        return Tabla[clave];
    }

    static boolean esPrimo(int numero){
        int m = 2;
        boolean band = true;
        while((band) && (m < numero)){
            if((numero % m) == 0){
                band = false;
            }else{
                m = m+1;
            }
        }
        return band;
    }

    private int calcularPrimoSiguiente(int numero){
        while(true){
            numero = numero + 1;
            if(esPrimo(numero)){
                return numero;
            }
        }
    }

}
