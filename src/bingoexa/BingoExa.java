/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoexa;

import java.util.Random;

/**
 *
 * @author Karina Robledo Hernández
 */
public class BingoExa {

    private static int[] valores = new int[144];
    private static int contador = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("NUEVA MATRIZ");
        int[][] generarMatriz = generarMatriz();
        System.out.println("NUEVA MATRIZ");
        int[][] generarMatriz1 = generarMatriz();
        System.out.println("NUEVA MATRIZ");
        int[][] generarMatriz2 = generarMatriz();
        System.out.println("NUEVA MATRIZ");
        int[][] generarMatriz3 = generarMatriz();
        
        valores = new int[valores.length]; //Limpia el arreglo
        contador = 0; //Reinicializa el indice
        while(contador < valores.length){
            crearValor();
        }
        
        for (int i = 0; i != valores.length; i++) {
            System.out.println(valores[i]);
        }
        
    }
    
       public static int[][] generarMatriz(){
        int[][] matriz = new int[6][6];
        Random r = new Random();
            for (int i = 0; i < matriz.length; i++) { //Columnas
                for (int j = 0; j < matriz[0].length; j++) { //Filas
                     
                   matriz[i][j] = crearValor();
                   System.out.print(" " + matriz[i][j]);
                    
                }  
                System.out.println("");
            }
        return matriz;
        }
        
        public static boolean encontrarValor(int num){
            for (int i = 0; i < valores.length; i++) {
                if(num == valores[i]){ return true;} //Método para ver si encuentra el valor
            }
            return false;
        }
        
        public static int crearValor(){ //Funcion recursiva
        Random r = new Random();
        int num = (int)(r.nextDouble() * 149 + 1); 
        if(encontrarValor(num)){ 
            return crearValor();
        }
        else {
            valores[contador] = num;
            contador++;
            return num;
            }
        }
}
