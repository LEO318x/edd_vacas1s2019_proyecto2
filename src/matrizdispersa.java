public class matrizdispersa {

    public class nodoOrtogonal {

        int fila;
        int columna;
        String dato;
        nodoOrtogonal arriba;
        nodoOrtogonal abajo;
        nodoOrtogonal izquierda;
        nodoOrtogonal derecha;

        public nodoOrtogonal(int fila, int columna, String dato){
            this.dato = dato;
            this.fila = fila;
            this.columna = columna;
            this.arriba = null;
            this.abajo = null;
            this.izquierda = null;
            this.derecha = null;
        }

    }

    public class encabezado{
        int id;
        encabezado siguiente;
        encabezado anterior;
        nodoOrtogonal lista;

        public encabezado(int id){
            this.id = id;
            this.siguiente = null;
            this.anterior = null;
            this.lista = null;
        }
    }

    public class listaEncabezado{
        encabezado primero;

        public void insertar(encabezado nuevo){
            if(primero == null){
                primero = nuevo;
            }else{
                if(nuevo.id < primero.id) { //Inserción al inicio
                    nuevo.siguiente = primero;
                    primero.anterior = nuevo;
                    primero = nuevo;
                }else{
                    encabezado actual = primero;
                    while(actual.siguiente != null){
                        if(nuevo.id < actual.siguiente.id){ //Inserción en el medio
                            nuevo.siguiente = actual.siguiente;
                            actual.siguiente.anterior = nuevo;
                            nuevo.anterior = actual;
                            actual.siguiente = nuevo;
                            break;
                        }
                        actual = actual.siguiente;
                    }

                    if(actual.siguiente == null){ //Inserción al final
                        actual.siguiente = nuevo;
                        nuevo.anterior = actual;
                    }
                }
            }
        }

        public encabezado getEncabezado(int id){
            encabezado actual = primero;
            while(actual != null){
                if(actual.id == id){
                    return actual;
                }
                actual = actual.siguiente;
            }
            return null;
        }
    }


    public class matriz{
        listaEncabezado eFilas;
        listaEncabezado eColumnas;

        public matriz(){
            this.eFilas = new listaEncabezado();
            this.eColumnas = new listaEncabezado();
        }

        public void insertar(int fila, int columna, String dato){
            nodoOrtogonal nuevo = new nodoOrtogonal(fila, columna, dato);

            /*
             * ***********************
             *
             *  INSERTAMOS EN FILAS
             *
             * ***********************
             */

            encabezado eFila = eFilas.getEncabezado(fila);
            if(eFila == null){ //Si no existe el encabezado se crea uno nuevo.
                eFila = new encabezado(fila);
                eFilas.insertar(eFila);
                eFila.lista = nuevo;
            }else{
                //Insertamos al inicio
                if(nuevo.columna < eFila.lista.columna){
                    nuevo.derecha = eFila.lista;
                    eFila.lista.izquierda = nuevo;
                    eFila.lista = nuevo;
                }else{
                    nodoOrtogonal actual = eFila.lista;
                    while(actual.derecha != null){
                        if(nuevo.columna < actual.derecha.columna){

                            if(nuevo.columna != actual.derecha.izquierda.columna){
                                nuevo.derecha = actual.derecha;
                                actual.derecha.izquierda = nuevo;
                                nuevo.izquierda = actual;
                                actual.derecha = nuevo;
                            }else{
                                //Reemplazamos info si ya existe en las coordenadas
                                //actual.dato = nuevo.dato;
                                System.out.println("El valor ya existe, por lo que no fue ingresado");
                            }
                            break;
                        }
                        actual = actual.derecha;
                    }
                    //Insertamos al final
                    if(actual.derecha == null){
                        if(actual.columna != nuevo.columna){
                            actual.derecha = nuevo;
                            nuevo.izquierda = actual;
                        }else{
                            //reemplazamos valor si ya existe
                            //actual.dato = nuevo.dato;
                            System.out.println("El valor ya existe, por lo que no fue ingresado");

                        }
                        //actual.derecha = nuevo;
                        //nuevo.izquierda = actual;
                    }
                }
            }

            /*
             * ***********************
             *
             *  INSERTAMOS EN COLUMNAS
             *
             * ***********************
             */
            encabezado eColumna = eColumnas.getEncabezado(columna);
            if(eColumna == null){ //Si no existe encabezado se crea.
                eColumna = new encabezado(columna);
                eColumnas.insertar(eColumna);
                eColumna.lista = nuevo;
            }else{
                //Insertamos al inicio
                if(nuevo.fila < eColumna.lista.fila){
                    nuevo.abajo = eColumna.lista;
                    eColumna.lista.arriba = nuevo;
                    eColumna.lista = nuevo;

                }else{
                    nodoOrtogonal actual = eColumna.lista;
                    while(actual.abajo != null){
                        //Insertamos en medio
                        if(nuevo.fila < actual.abajo.fila){

                            if(nuevo.fila != actual.abajo.arriba.fila){
                                nuevo.abajo = actual.abajo;
                                actual.abajo.arriba = nuevo;
                                nuevo.arriba = actual;
                                actual.abajo = nuevo;
                            }else{
                                //Reemplazamos la info si ya existe en las coordenadas
                                //actual.dato = nuevo.dato;
                                System.out.println("El valor ya existe, por lo que no fue ingresado");

                            }
                            break;
                        }
                        actual = actual.abajo;
                    }

                    //Insertamos al final
                    if(actual.abajo == null){
                        if(actual.fila != nuevo.fila){
                            actual.abajo = nuevo;
                            nuevo.arriba = actual;
                        }else{
                            //Reemplazamos info si ya existe en las coordenadas
                            //actual.dato = nuevo.dato;
                            System.out.println("El valor ya existe, por lo que no fue ingresado");

                        }
                    }
                }
            }

        }

        public void recorrerFilas(){
            encabezado eFila = eFilas.primero;
            System.out.println("Recorrido Por Filas: ");

            while(eFila != null){
                nodoOrtogonal  actual = eFila.lista;
                while(actual != null){
                    System.out.print("["+actual.fila+","+actual.columna+"]="+actual.dato);
                    if(eFila.siguiente != null || actual.derecha != null){

                    }
                    actual = actual.derecha;
                }
                eFila = eFila.siguiente;
                System.out.println("");
            }

        }

        public void recorrerColumnas(){
            encabezado eColumna = eColumnas.primero;
            System.out.println("Recorrido Por Columnas: ");

            while(eColumna != null){
                nodoOrtogonal actual = eColumna.lista;
                while(actual != null){
                    System.out.print("["+actual.fila+","+actual.columna+"]");

                    if(eColumna.siguiente != null || actual.abajo != null){

                    }
                    actual = actual.abajo;
                }
                eColumna = eColumna.siguiente;
                System.out.println("");
            }


        }

        public void rutasPosibles(int inicio, int destino){

        }
    }

    //public nodoOrtogonal nodoOrtogonal;
    //public encabezado encabezado;
    //public listaEncabezado listaEncabezado;
    public matriz matriz = new matriz();

}
