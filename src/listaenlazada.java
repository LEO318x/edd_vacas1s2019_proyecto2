public class listaenlazada {

    public class nodo{
        public int dato;
        public nodo siguiente;


        //Insertar al final
        public nodo(int d){
            this.dato = d;
            this.siguiente = null;
        }

        //Insertar inicio
        public nodo(int d, nodo n){
            dato = d;
            siguiente = n;
        }

    }

    protected  nodo inicio, ultimo;
    public listaenlazada(){
        inicio = null;
        ultimo = null;
    }

    public void insertarInicio(int id){
        inicio = new nodo(id, inicio);
        if(ultimo ==null){
            ultimo = inicio;
        }
    }

    public void insertarFinal(int id){
        if(!esVacia()){
            ultimo.siguiente = new nodo(id);
            ultimo = ultimo.siguiente;
        }else{
            inicio = ultimo = new nodo(id);
        }
    }

    public void eliminarInicio(){
        if(inicio == ultimo){
            inicio =null;
            ultimo = null;
        }else{
            inicio = inicio.siguiente;
        }
    }

    public void eliminarFinal(){
        if(inicio == ultimo){
            inicio = ultimo = null;
        }else{
            nodo temporal = inicio;
            while(temporal.siguiente!=ultimo){
                temporal = temporal.siguiente;
            }
            ultimo = temporal;
            ultimo.siguiente = null;
        }
    }

    public void eliminar(int id){
        if(!esVacia()){
            if(inicio==ultimo && id == inicio.dato){
                inicio = ultimo = null;
            }else if(id==inicio.dato){
                inicio=inicio.siguiente;
            }else{
                nodo anterior, temp;
                anterior = inicio;
                temp = inicio.siguiente;
                while(temp!=null && temp.dato!=id){
                    anterior = anterior.siguiente;
                    temp = temp.siguiente;
                }
                if(temp!=null){
                    anterior.siguiente = temp.siguiente;
                    if(temp == ultimo){
                        ultimo = anterior;
                    }
                }
            }
        }
    }

    public void buscar(int id){
        nodo temporal = inicio;
        while(temporal!=null ){
            if(temporal.dato == id){
                System.out.println("Si se encuentra en la lista: "+id);
                break;
            }else{
                System.out.println("No se encuentra en la lista: " + id);
            }
            temporal = temporal.siguiente;
        }

    }

    public boolean esVacia(){
        if(inicio == null){
            return true;
        }else{
            return false;
        }
    }

    public void desplegar(){
        nodo actual = inicio;
        while(actual!=null){
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }




}

