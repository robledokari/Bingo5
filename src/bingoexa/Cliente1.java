/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingoexa;
import java.net.*;
import java.io.*;
/**
 *
 * @author Karina Robledo Hernández
 */
public class Cliente1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            MulticastSocket cl = new MulticastSocket(8888);
            InetAddress grupo = InetAddress.getByName("229.1.2.3");
            int [][] tabla = new int[6][6];
            cl.joinGroup(grupo);
            System.out.println("Cliente unido al grupo...");
            cl.setReuseAddress(true);
            
            //Enviar ID
            String msj = "karis";
            byte[] b = msj.getBytes();
            DatagramPacket p = new DatagramPacket(b,b.length,grupo,2001);
            cl.send(p);
            
            for(;;){
            p = new DatagramPacket(new byte[1024],1024);
            cl.receive(p);
            //Esperando que reciba la matriz
            }
            
         //  byte[] nums =  p.getData();
           
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}