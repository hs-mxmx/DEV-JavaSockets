/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fecha;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 *
 * @author dani
 */
public class Servidor {
    
    public static void main(String[] args) throws IOException{
        Socket socket;
        ServerSocket serverSocket;
        DataInputStream bufferEntrada;
        DataOutputStream bufferSalida;
        int birth, day, month, year;
        
        System.out.println("Esperando conexión de nuevo cliente...");
        serverSocket = new ServerSocket(5000);
        socket = serverSocket.accept();
        System.out.println("Conexion establecida con " + socket.getInetAddress().getHostAddress());
        
        bufferEntrada = new DataInputStream(socket.getInputStream());
        bufferSalida = new DataOutputStream(socket.getOutputStream());
        
        String s_day = bufferEntrada.readUTF();
        System.out.println("Day: " + s_day);
        String s_month = bufferEntrada.readUTF();
        System.out.println("Month: " + s_month);
        String s_year = bufferEntrada.readUTF();
        System.out.println("Year: " + s_year);
        
        day = Integer.valueOf(s_day);
        month = Integer.valueOf(s_month);
        year = Integer.valueOf(s_year);
        
        int year_actual = Calendar.getInstance().get(Calendar.YEAR);
        int month_actual = Calendar.getInstance().get(Calendar.MONTH);
        int day_actual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        
        year = (year_actual - year);
        if(month_actual > month){
            month = (month_actual - month);
        }else if(month_actual == month){
            
        }else if (month_actual < month){
            if(day_actual > day){
                year = year + 1;
            }
        }
        
        String birth_date = (String.valueOf(year) + " y/o and " + String.valueOf(month) + " months.");
        
        
        String date = (birth_date);
        System.out.println(date);
        
        bufferSalida.writeUTF(date);
        
        
        
        bufferEntrada.close();
        bufferSalida.close();
        socket.close();
        System.out.println("Conexion cerrada...");
      
    }
    
    
    public void birth_date(int day, int month, int year){

    }
    
}
