/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author dani
 */
public class Servidor {
    private boolean inicio = true;
    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream bufferEntrada = null;
    private DataOutputStream bufferSalida = null;
    Scanner scanner = new Scanner(System.in);
    final String terminar = "salir()";
    
    public void iniciarSesion(int port){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Esperando conexion en el puerto " + String.valueOf(port));
            socket = serverSocket.accept();
            System.out.println("Conexion establecida con " + socket.getInetAddress().getCanonicalHostName() + "\n");
        }catch(Exception e){
            System.out.println("Error iniciando conexion..." + e.getMessage());
            System.exit(0);
        }
    }
    
    public void abrirFlujos(){
        try{
            bufferEntrada = new DataInputStream(socket.getInputStream());
            bufferSalida = new DataOutputStream(socket.getOutputStream());
            bufferSalida.flush();
        }catch(IOException e){
            System.out.println("Error durante la apertura de los flujos...");
        }
    }
    
    public void recibirDatos(){
        String dato = "";
        try{
            do{
                dato = (String) bufferEntrada.readUTF();
                System.out.println("\n[Cliente] " + dato);
                System.out.print("[Servidor]: ");
            }while(!dato.equals(terminar));
        }catch(IOException e){
            cerrarSesion();
        }
    }
    
    public void enviar(String dato){
        try{
            bufferSalida.writeUTF(dato);
            bufferSalida.flush();
        }catch(IOException e){
            System.out.println("Error en durante el envio... " + e.getMessage());
        }
    }
    
    public void escribirDatos(){
        while(true){
            System.out.print("[Servidor]: ");
                enviar(scanner.nextLine());
            }       
        }
    
    public void clienteSesion(){
        try{
            bufferSalida.writeUTF("Bienvenido!");
            System.out.println("[Servidor]: Bienvenido!");
            bufferSalida.flush();
            System.out.print("[Servidor]: ");
        }catch(IOException e){
            System.out.println("Error durante la sesion del cliente... " + e.getMessage());
        }
    }
    
    public void cerrarSesion(){
        try{
            bufferEntrada.close();
            bufferSalida.close();
            socket.close();
        }catch(IOException e){
            System.out.println("Error durante el cierre de sesion... " + e.getMessage());
        }finally{
            System.out.println("Conexion finalizada...");
        }
    }
     
    public void ejecutarConexion(int port){
        Thread hilo = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    try{
                        iniciarSesion(port);
                        abrirFlujos();
                        clienteSesion();
                        recibirDatos();
                        
                    }finally{
                        cerrarSesion();
                    }
                }
            }
        });
        hilo.start();
    }
}
