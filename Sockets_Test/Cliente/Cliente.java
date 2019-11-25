/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba_cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class Cliente{
   
    private Socket socket;
    private DataInputStream bufferEntrada = null;
    private DataOutputStream bufferSalida = null;
    Scanner teclado = new Scanner(System.in);
    final String terminar = "salir()";
    
    public void iniciarSesion(String ip, int port){
        try{
            socket = (new Socket(ip,port));
            System.out.println("Estableciendo conexion con el servidor " + ip + " en el puerto " + port);
        }catch(Exception e){
            System.out.println("No se pudo establecer conexion con " + ip + " error: " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void abrirFlujos(){
        try{
        bufferEntrada = new DataInputStream(socket.getInputStream());
        bufferSalida = new DataOutputStream(socket.getOutputStream());
        bufferSalida.flush();
        }catch(IOException e){
            System.out.println("No se pudieron abrir los flujos correctamente... " + e.getMessage());
            if(e.getMessage().contains("Socket closed")) System.exit(0);
        }
    }
    
    public void enviar(String dato){
        try{
            bufferSalida.writeUTF(dato);
            bufferSalida.flush();
        }catch(IOException e){
            System.out.println("Error durante el envio del mensaje... " + e.getMessage());
            if(e.getMessage().contains("Socket closed")) System.exit(0);
        }
    }
    
    public void recibirDatos(){
        String dato = "";
        try{
            do{
            dato = (String) bufferEntrada.readUTF();
                System.out.println("\n[Servidor]: " + dato);
                System.out.print("[Cliente]: ");
              }while(!dato.equals(terminar));
        }catch(IOException e){
            System.out.println("Error durante el envio de mensaje... " + e.getMessage());
            if(e.getMessage().contains("Socket closed")) System.exit(0);
        }
    }
    
    public void escribirDato(){
        String dato = "";
        while(true){
            System.out.print("[Cliente]: ");
            dato = teclado.nextLine();
            if(dato.length() > 0)
                enviar(dato);
        }
    }
    
    public void cerrarSesion(){
        try{
            bufferSalida.close();
            bufferEntrada.close();
            socket.close();
            System.out.println("Conexion finalizada");
            System.exit(0);
        }catch(IOException e){
            System.out.println("Error de IOException durante el cierre de sesion... " + e.getMessage());
            if(e.getMessage().contains("Socket closed")) System.exit(0);
        }
    }
    
    public void ejecutarConexion(String ip, int port){
        Thread hilo = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    iniciarSesion(ip,port);
                    abrirFlujos();
                    recibirDatos();
                }finally{
                    cerrarSesion();
                }
            }
        });
        hilo.start();
    }

}

