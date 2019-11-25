/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fecha;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author dani
 */
public class Cliente {
    
    public static void main(String[] args) throws IOException{
        Socket socket;
        DataInputStream bufferEntrada;
        DataOutputStream bufferSalida;
        Scanner scanner = new Scanner(System.in);
        
        socket = (new Socket("localhost", 5000));
        System.out.println("Conectado al host " + socket.getInetAddress().getHostAddress());
        bufferEntrada = new DataInputStream(socket.getInputStream());
        bufferSalida = new DataOutputStream(socket.getOutputStream());
        
        System.out.print("Day: ");
        int day = scanner.nextInt();
        bufferSalida.writeUTF(String.valueOf(day));
        System.out.print("Month: ");
        int month = scanner.nextInt();
        bufferSalida.writeUTF(String.valueOf(month));
        System.out.print("Year: ");
        int year = scanner.nextInt();
        bufferSalida.writeUTF(String.valueOf(year));
        
        String birth = bufferEntrada.readUTF();
        System.out.println(birth);
        
        bufferEntrada.close();
        bufferSalida.close();
        socket.close();
        
        System.out.println("Conexion con el servidor cerrada...");
        
        
    }
    
}
