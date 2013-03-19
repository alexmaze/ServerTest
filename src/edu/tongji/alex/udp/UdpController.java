package edu.tongji.alex.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpController {


	int PORT = 5000;
	int BUFFER_SIZE = 2000;
	String ADDRESS = "255.255.255.255";
	DatagramSocket theSocket;
	DatagramPacket recPacket;
	DatagramPacket sendPacket;
	byte recByte[];
	byte sendByte[];
	
	public UdpController() {

		try {
			theSocket = new DatagramSocket(PORT + 1);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(String sendInfo) {


		recByte = new byte[BUFFER_SIZE];
		sendByte = new byte[BUFFER_SIZE];
		
		try {

			sendByte = sendInfo.getBytes();
			sendPacket = new DatagramPacket(sendByte, sendByte.length,
					InetAddress.getByName(ADDRESS), PORT);
			theSocket.send(sendPacket);

			recPacket = new DatagramPacket(recByte, recByte.length);
			theSocket.receive(recPacket);
			String recString = new String(recByte, 0, recPacket.getLength());
			System.out.println("[CLIENT]: From Server: " + recString);
			
			//theSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
