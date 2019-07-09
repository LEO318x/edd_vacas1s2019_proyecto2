import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class tablaHash {

    float porcentajeCapacidad;
    float porcentajeCapacidadVacia;
    int numDatos;
    int iteracion;
    int T;
    //Tamaño inicial de la tabla;
    reservacionesH[] Tabla;

    public tablaHash(){
        this.Tabla = new reservacionesH[7];
        this.T = 7;
        this.porcentajeCapacidad = numDatos/ T;
        this.porcentajeCapacidadVacia = 0;
        this.numDatos = 0;
        this.iteracion = 0;
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
        //Calendar c = Calendar.getInstance();
        //String id = Integer.toString(c.get(Calendar.YEAR))+Integer.toString(c.get(Calendar.MONTH))+Integer.toString(c.get(Calendar.DATE))+Integer.toString(c.get(Calendar.HOUR))+Integer.toString(c.get(Calendar.MINUTE))+Integer.toString(c.get(Calendar.MILLISECOND));
        //long reservacion = Long.parseLong(id);
        //nuevo.setNumReservacion(reservacion);


        porcentajeCapacidad = (float) numDatos/ T;
        int clave = funcionHash(nuevo.getNombreCliente());

        //Verificamos el porcentaje de desbordamiento, si es >=50 se proce a aumentar la tabla a su siguiente numero primo
        if(porcentajeCapacidad <= 0.5){
            while(true){
                if(Tabla[clave]==null){ // Si es null el contenido de la clave se procede a insertar
                    //nuevo.setNumReservacion(reservacion);
                    Tabla[clave]=nuevo;
                    iteracion = 0;
                    numDatos++;
                    //reservacion++;
                    //System.out.println("Se ha insertado, clave "+clave);
                    break;
                }else{
                    while(true){ //Se procede a la resolución de la colisión.
                        clave = funcionHashColision(nuevo.getNombreCliente());
                        if(Tabla[clave]==null){ //Si la clave generada su contenido es nulo se procede a llenar
                            //nuevo.setNumReservacion(reservacion);
                            Tabla[clave] = nuevo;
                            iteracion = 0;
                            numDatos++;
                            //reservacion++;
                            //System.out.println("Colision resolvida, clave "+clave);
                            break;
                        }else{ //La clave generada entra en colisión nuevamente, se itera y se repite el while
                            //System.out.println("Hubo colision en clave "+clave);
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


    private void aumentarTabla(int tamanioTabla){
        reservacionesH[] temp = new reservacionesH[tamanioTabla];
        System.arraycopy(Tabla, 0, temp, 0, Tabla.length);
        Tabla = new reservacionesH[tamanioTabla];
        Tabla = temp;
        T = tamanioTabla;

    }

    private void disminuirTabla(int tamanioTabla){
       /* reservacionesH[] temp = Tabla;
        Tabla = new reservacionesH[tamanioTabla];
        for(int i= 0; i < temp.length; i++){
            if(temp[i]!=null){
                //System.out.println(getElemento(temp, i).getNumReservacion());
                insertar(getElemento(temp, i));
            }
        }
        T = tamanioTabla;*/
    }

    public reservacionesH getElemento(int clave){
        return Tabla[clave];
    }

    public reservacionesH getElemento(reservacionesH[] Tablan, int clave){
        return Tablan[clave];
    }

    public void desplegar(){
        for (int i= 0; i<Tabla.length; i++){
            if(Tabla[i]==null){
                System.out.println("null");
            }else{
                System.out.println(Tabla[i].getNombreCliente());
                Tabla[i].getLs().desplegar();
            }

        }
    }

    public void eliminar(long num){
        /*int aux = 0;
        int i = 0;
        while(i < Tabla.length){
            if(Tabla[aux]!=null){
                if(Long.valueOf(getElemento(aux).getNumReservacion()).equals(num)){
                    Tabla[aux] = null;
                    numDatos--;
                    System.out.println("Eliminado");
                    break;
                }
            }
            i++;
            aux++;
        }*/

        for(int i=0; i < Tabla.length; i++){
            if(Tabla[i]!=null){
                if(Long.valueOf(getElemento(i).getNumReservacion()).equals(num)){
                    Tabla[i] = null;
                    numDatos--;
                    System.out.println("Eliminado");
                    break;
                }
            }
        }

       /*porcentajeCapacidadVacia = 1 - (float)numDatos/T;
        System.out.println("---"+porcentajeCapacidadVacia);
        if(porcentajeCapacidadVacia>=0.51) {
            int primoAnt=calcularPrimoAnterior(T);
            disminuirTabla(primoAnt);
        }*/

    }

    public void graficar(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        String datos = "";
        try
        {
            fichero = new FileWriter("tablahash.dot");
            pw = new PrintWriter(fichero);
            pw.println("digraph G { rankdir = LR; node[shape = record]; splines=line; ranksep=.75;");
            pw.println("subgraph cluster_0 { style=invis;");
            pw.print("T[label=\"");
            for(int i=0; i< Tabla.length; i++){
                if(Tabla[i]!=null){
                    if(i != Tabla.length-1){
                        pw.print("<t"+i+">"+i+"|");
                    }else{
                        pw.print("<t"+i+">"+i);
                    }
                    datos += "e"+i+"[label=\""+getElemento(i).getNombreCliente()+"\"];\n";
                    datos += "T:t"+i+"->e"+i+":w;\n";
                }else{
                    if(i != Tabla.length-1){
                        pw.print("<t"+i+">"+i+"|");
                    }else{
                        pw.print("<t"+i+">"+i);
                    }
                }
            }
            pw.println("\" style=filled fillcolor=\"#8e44ad\"];");
            pw.println("}");
            pw.println(datos);
            pw.println("}");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        ProcessBuilder pb = new ProcessBuilder("dot","-Tpng","tablahash.dot","-o","tablahash.png");
        try{
            Process p = pb.start();
            p.waitFor();
            pb = new ProcessBuilder("nomacs", "tablahash.png");
            p = pb.start();
            //p.waitFor();
        }catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }

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

    private int calcularPrimoAnterior(int numero){
        while(true){
            numero = numero - 1;
            if(esPrimo(numero)){
                return numero;
            }
        }
    }

}
