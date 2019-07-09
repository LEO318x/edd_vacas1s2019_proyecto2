import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class cargaArchivos {




    public void cargarReservaciones(String ruta, tablaHash hash){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File (ruta);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null) {
                try{
                    String[] datos = linea.split(",");
                    listaenlazada ls = new listaenlazada();
                    for(int i = 4; i < datos.length; i++){
                        ls.insertarFinal(Integer.parseInt(datos[i].trim()));
                    }
                    reservacionesH nuevaReservacion = new reservacionesH(Integer.parseInt(datos[2].trim()),Integer.valueOf(datos[3].trim()),Long.valueOf(datos[1].trim()), datos[0].trim(), ls);
                    hash.insertar(nuevaReservacion);
                }catch (Exception e){
                   // e.printStackTrace();
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    public void cargarDestino(String ruta){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File (ruta);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null) {
                try{

                    String datos[] = linea.split(",");
                    for(int i=0; i<datos.length; i++){
                        if(datos.length != 1){
                            //REALIZAR CARGA
                        }

                    }

                }catch (Exception e){
                    // e.printStackTrace();
                }

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
