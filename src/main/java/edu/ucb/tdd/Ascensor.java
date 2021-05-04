package edu.ucb.tdd;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class Ascensor {
    private Character[] ubiPersona = {'0','0','0'};
    private Character[] ubiAscensor = {'x','0','0'};
    private final int LIM_INF = 1;
    private final int LIM_SUP = 3;
    private final int tamPisos = ubiAscensor.length;
    private boolean subida=true;

    public String seleccionarPiso(int piso){
        int posPersona=0;
        int pisoActual = 0;
        int p=crearPersona();
       while(piso == p){
           p=crearPersona();
       }
       System.out.println(p);
       ubiPersona[p-1]='x';

        if (piso < LIM_INF || piso >LIM_SUP){
            throw new RuntimeException("Fuera de los limites");
        }


        for (int i=0;i<tamPisos;i++){
            if(ubiPersona[i]=='x'){
                posPersona = i+1;
            }
        }

        while (ubiAscensor[posPersona-1] != 'x'){
            pisoActual=moverAscensor();
            System.out.println("piso "+pisoActual);
        }

        while (ubiAscensor[piso-1] != 'x'){
            pisoActual=moverAscensor();
            System.out.println("piso "+pisoActual);
        }


        return "Piso "+pisoActual;
    }

    public int crearPersona() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        int rand = sr.nextInt(LIM_SUP)+LIM_INF;
        return rand;
    }
    public int moverAscensor(){
        int pos =0;
        for(int i=0;i<tamPisos;i++){
            if(ubiAscensor[i]== 'x'){
                pos = i;
                ubiAscensor[i] = '0';
            }
        }

        if(pos==(tamPisos-1)){
            subida=false;
        }
        if(pos==0){
            subida=true;
        }

        if(subida){
            pos++;
        }else{
            pos--;
        }

        ubiAscensor[pos] = 'x';
        return (pos+1);
    }
}
