/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingoexa;
import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
/**
 *
 * @author Karina Robledo Hernández
 */
public class Servidor {

    private static int[] valores = new int[144];
    private static int contador = 0;
     
    public static void main(String[] args) {
        try{
        MulticastSocket s = new MulticastSocket(8888);
        InetAddress grupo = null;
        String dir = "229.1.2.3";
        ArrayList<ClienteInfo> Clientes = new ArrayList(); //ArrayList con la información de los clientes
        try{
            grupo = InetAddress.getByName(dir);
        }
        catch(UnknownHostException uke){
            System.out.println("Direccion no valida"); 
            System.exit(0);
        }
        s.joinGroup(grupo);
            System.out.println("Unido al grupo " +dir+" comienza a esperar");
        for(;;){
              DatagramPacket p = new DatagramPacket(new byte[1024],1024);
              s.receive(p);
              String id_c = new String(p.getData(),0,p.getLength());
              String ip_c = p.getAddress().getHostAddress();
              int pto_c = p.getPort();
              ClienteInfo cliente = new ClienteInfo();
              cliente.setId(id_c);
              cliente.setIp(ip_c);
              cliente.setPto(pto_c);
              Clientes.add(cliente);
              if(Clientes.size() == 4){break;} //Si llegan a ser 4 clientes
        }
        
        //Enviar matrices a los clientes
        for (int i = 0; i < 4; i++) {
               ClienteInfo cli;
               cli = Clientes.get(i);
               InetAddress ia = InetAddress.getByName(cli.getIp());
               int pto = cli.getPto();
               byte[] datos = generarByteArray(generarMatriz());
               DatagramPacket dp = new DatagramPacket(datos, datos.length, ia, pto);
               s.send(dp);
        }
        
        //Crear datos aleatorios
        valores = new int[valores.length]; //Limpia el arreglo
        contador = 0; //Reinicializa el indice
        while(contador != valores.length){
            crearValor();
        }
        
        //Enviando numeros 
        for (int i = 0; i < valores.length; i++) {
                byte num = (byte) valores[i];
                byte[] datos = new byte[1];
                datos[0] = num;
                DatagramPacket p3 = new DatagramPacket(datos,datos.length,grupo,2001);
                s.send(p3);   
        }
        
        //Esperando al ganador
        for(;;){
            DatagramPacket p = new DatagramPacket(new byte[1024],1024);
            s.receive(p);
            String id_c = new String(p.getData(),0,p.getLength());
            System.out.println("El ganardor es: " + id_c);
        }
        
        }catch(Exception e){
        }
    }
        
        public static byte[] generarByteArray(int matriz[][]){
        byte[] datos = new byte[36];
        int cont = 0;
         for (int i = 0; i < matriz.length; i++) { //Columnas
                for (int j = 0; j < matriz[0].length; j++) { //Filas
                     
                   datos[cont] = (byte)matriz[i][j];
                   cont++;
                    
                }     
            }
        return datos;
        }
        
        public static int[][] generarMatriz(){
        int[][] matriz = new int[6][6];
        Random r = new Random();
            for (int i = 0; i < matriz.length; i++) { //Columnas
                for (int j = 0; j < matriz[0].length; j++) { //Filas
                     
                   matriz[i][j] = crearValor();
                    
                }     
            }
        return matriz;
        }
        
        //Método para ver si encuentra el valor
        public static boolean encontrarValor(int num){
            for (int i = 0; i < valores.length; i++) {
                if(num == valores[i]){ return true;} 
            }
            return false;
        }
        
        //Funcion recursiva
        public static int crearValor(){ 
        Random r = new Random();
        int num = (int)(r.nextDouble() * 149 + 1); 
        if(encontrarValor(num)){ 
            return crearValor();}
        else {
            valores[contador] = num;
            contador++;
            return num;
            }
        }
}