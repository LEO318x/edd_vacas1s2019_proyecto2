
/**
 *
 */
public class vernam {

    //Usamos base64(caracteres imprimibles ascii) para así no tener caracteres raros en el cifrado
    private static String base64 ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    public String Codificar(String data, String key){
        int keypos = 0;
        String datosBinarios = "";

        for (char c : data.toCharArray()) {
            //Convertimos los datos y la llave en código ascii, luego sacamos XOR
            int xor = (((int)c ^ (int) key.toCharArray()[keypos]))+(key.length());
            if(++keypos >= key.length()){
                keypos = 0;
            }
            datosBinarios += DecABin(xor, 8);
        }

        int m = 0;
        String cifrado = "";

        for (int i = 0; i < datosBinarios.length(); i += 4){
            int v = BinADec(datosBinarios.substring(i, i+4));
            cifrado += getB64desdeN(v*4+m);
            if(++m>3){
              m = 0;
            }
        }
        return  cifrado;
    }

    public String Decodificar(String datos, String key){
        int m = 0;
        String datosBinarios = "";
        for (char c: datos.toCharArray()) {
            int v = (getNdesdeB64(c)-m) / 4;
            datosBinarios += DecABin(v, 4);
            if(++m > 3){
                m = 0;
            }

        }
        int keypos = 0;
        String decodificar = "";
        for (int i= 0; i < datosBinarios.length(); i+=8){
            if(i+8>datosBinarios.length()){
                break;
            }
            int c = BinADec(datosBinarios.substring(i, i+8));
            int dc = (c -key.length()) ^(int)key.toCharArray()[keypos];
            if(++keypos >= key.length()){
                keypos = 0;
            }
            decodificar += new String(new char[1]).replace("\0", Character.toString((char)dc));
        }
        return decodificar;
    }

    private String DecABin(int valor, int longitud){
        String binString = "";
        while (valor > 0){
            binString += valor % 2;
            valor /=2;
        }

        //Necesitamos revertir la cadena binaria para poder convertir xD
        String revertirString = "";
        for (char c : binString.toCharArray()) {
            revertirString = new String(new char[1]).replace("\0", Character.toString(c))+revertirString;
        }
        binString = revertirString;
        binString = new String(new char[longitud-binString.length()]).replace("\0", "0")+binString;
        return binString;

    }
    private int BinADec(String Binario){
        return Integer.parseInt(Binario, 2);
    }

    //Se espera una posición del alfabeto base64 y retorna el caracter de dicho alfabeto.
    private String getB64desdeN(int n){
        if (n > base64.length()){
            return "=";
        }
        return new String(new char[1]).replace("\0", Character.toString(base64.toCharArray()[n]));
    }
    //Se espera un caracter del alfabeto base64 y retorna su posición de dicho alfabeto.
    private int getNdesdeB64(char n) {
        return base64.indexOf(n);
    }

}
