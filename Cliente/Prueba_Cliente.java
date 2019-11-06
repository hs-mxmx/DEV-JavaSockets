/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba_cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Prueba_Cliente {

    public static void main(String[] args) throws UnknownHostException {
        Cliente cliente = new Cliente();
        Scanner escaner = new Scanner(System.in);
        System.out.print("Introduce IP del servidor: [192.168.1.106 por defecto]: ");
        String ip = escaner.nextLine();
        if(ip.length()<=0) ip = "192.168.1.106";
        System.out.print("Introduce puerto: [5000 por defecto]: ");
        String port = escaner.nextLine();
        if(port.length()<=0)port = "5000";
        cliente.ejecutarConexion(ip, Integer.valueOf(port));
        cliente.escribirDato();
    }
    
    /*        ServerSocket servidor;
        Socket conexion;
        DataOutputStream salida;
        DataInputStream entrada;
        int num = 0;
        try
        {
            servidor = new ServerSocket(5000); // Creamos un ServerSocket en el puerto 5000
            System.out.println("Servidor Arrancado....");
            while (true)
            {
                conexion = servidor.accept();     // Esperamos una conexión
                num++;
                System.out.println("Conexión nº "+num+" desde: "+conexion.getInetAddress().getHostName());
                entrada = new DataInputStream(conexion.getInputStream());  // Abrimos los canales de E/S
                salida  = new DataOutputStream(conexion.getOutputStream());
                String mensaje = entrada.readUTF();    //Leemos el mensaje del cliente
                System.out.println("Conexión nº "+num+". Mensaje recibido: "+mensaje);
                salida.writeUTF("Buenos dias " + mensaje);  // Le respondemos
                conexion.close();                           // Y cerramos la conexión
            }
        } catch (IOException e) {
       
    }*/
       
    }
    

