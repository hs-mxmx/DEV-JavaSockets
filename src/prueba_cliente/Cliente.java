/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba_cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Daniel
 */
public class Cliente{
    public Cliente(String host, int port){
    try {
			String serverHostname = new String("172.22.54.169");
			System.out.println("Connecting to host " + serverHostname + " on port " + port + ".");
			Socket echoSocket = null;
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				echoSocket = new Socket(serverHostname, port);
				out = new PrintWriter(echoSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			} catch (UnknownHostException e) {
				System.err.println("Unknown host: " + serverHostname);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Unable to get streams from server");
				System.exit(1);
			}

			/** {@link UnknownHost} object used to read from console */
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("client: ");
				String userInput = stdIn.readLine();
				/** Exit on 'q' char sent */
				if ("q".equals(userInput)) {
					break;
				}
				out.println(userInput);
				System.out.println("server: " + in.readLine());
			}

			/** Closing all the resources */
			out.close();
			in.close();
			stdIn.close();
			echoSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
    
        }
    }
}

