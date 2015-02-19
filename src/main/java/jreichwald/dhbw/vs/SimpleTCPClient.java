package jreichwald.dhbw.vs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of a simple tcp client 
 * @author Prof. Dr. Julian Reichwald <julian.reichwald@dhbw-mannheim.de>
 *
 */
public class SimpleTCPClient {

	/**
	 * Log Instance 
	 */
	private static Logger _log = LogManager.getLogger(SimpleTCPClient.class);
	
	/**
	 * Main Method 
	 * @param args
	 */
	public static void main(String[] args) {
		_log.debug("Client starting.");

		try {
			_log.debug("Trying to connect to server ....");
			Socket client = new Socket("localhost",11111);
			
			_log.debug("Connection established.");
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
			//send a message to the server and flush the buffer
			writer.write("Das hier ist ein test\n");
			writer.flush();
			
			
			//receive a response from the server 
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			while (!reader.ready())
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			_log.debug("Reading response..");
			//Read response from server 
			String response = reader.readLine();
		
			_log.debug("Received response: "+response);
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
