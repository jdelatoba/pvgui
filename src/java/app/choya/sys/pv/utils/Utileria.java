/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author developer
 */
public class Utileria {
    
    public Utileria(){
    }
    
    //Coloca espacios en blanco a la izquierda
    public String field_blankl(String field, int nBSpace){
        int len = field.length();
        len     = nBSpace - len;
        for(int i = 0; i < (len - 1); i++){
            field = "&nbsp;"+field;
        }
        return field;
    }

    public String subField(String field, int len){
        if(field.length() > len){
            field   = field.substring(0, len);
        }
        return field;
    }

    //Coloca espacios en blanco a la izquierda
    public String field_zerol(String field, int nZero){
        int len = field.length();
        len     = nZero - len;

        for(int i = 0; i < len; i++){
            field = "0"+field;
        }

        return field;
    }

    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }
    }

    public boolean esValido(Character caracter)
    {
        char c = caracter.charValue();
        System.out.println("charValue "+c);
        if ( !(Character.isLetter(c) //si es letra
                || c == ' ' //o un espacio
                || c == 8 //o backspace
            ))
            return false;
        else
            return true;
    }

    public boolean esCadenaValida(String cadena, int longitudMaxima)
    {
        Character caracter = ' ';
        int longitud = cadena.length();
        boolean bandera = true;

        if(longitud <= longitudMaxima)
        {
            for(int i = 0; i < longitud; i++){
                caracter = cadena.charAt(i);

                char c = caracter.charValue();
                if ( !(Character.isLetter(c) //si es letra
                        || c == ' ' //o un espacio
                        || c == 8 //o backspace

                    )){
                    return false;
                } else {
                    bandera = true;
                }
            }
        }else{
            return false; //se detecta que la cadena tiene una longitud mayor
                             //a la esperada
        }
        return bandera;

    }


    public boolean esCadenaValidaDigito(String cadena, int longitudMaxima)
    {
        Character caracter = ' ';
        int longitud = cadena.length();
        boolean bandera = true;

        if(longitud <= longitudMaxima)
        {
            for(int i = 0; i < longitud; i++){
                caracter = cadena.charAt(i);

                char c = caracter.charValue();
                
                if(c == '&'){
                    bandera = true;
                }else{
                    if ( !(Character.isLetterOrDigit(c) //si es letra
                            || c == ' ' //o un espacio
                            || c == 8 //o backspace

                        )){
                        return false;
                    } else {
                        bandera = true;
                    }
                }

            }
        }else{
            return false; //se detecta que la cadena tiene una longitud mayor
                             //a la esperada
        }
        return bandera;

    }


    public double Redondear(double numero,int digitos)
	{
	      int cifras=(int) Math.pow(10,digitos);
	      return Math.rint(numero*cifras)/cifras;
	}
    
}
