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
public class Test_Sockets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servidor server = new Servidor();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce puerto para establecer conexion [5000 por defecto]: ");
        String port = scanner.nextLine();
        if(port.length() <= 0) port = "5000";
        server.ejecutarConexion(Integer.parseInt(port));
        server.escribirDatos();
    }
    
}



/*ServerSocket servidor;
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