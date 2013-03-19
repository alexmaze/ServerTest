package edu.tongji.alex.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

	private int PORT = 5000;
	private int BUFFER_SIZE = 2000;
	private String ADDRESS = "255.255.255.255";

	private DatagramSocket theSocket;

	private DatagramPacket recPacket;
	private DatagramPacket sendPacket;

	private byte recByte[];
	private byte sendByte[];

	public UdpClient() {
		init();
	}

	public UdpClient(String address, int port, int buffer_size) {
		ADDRESS = address;
		PORT = port;
		BUFFER_SIZE = buffer_size;
		init();
	}

	private void init() {
		recByte = new byte[BUFFER_SIZE];
		sendByte = new byte[BUFFER_SIZE];

		try {
			theSocket = new DatagramSocket(PORT + 1);

			while (true) {

				System.out.println("[CLIENT]: Please Input");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String sendString = br.readLine();

				// 发消息
				sendByte = sendString.getBytes();
				sendPacket = new DatagramPacket(sendByte, sendByte.length,
						InetAddress.getByName(ADDRESS), PORT);
				theSocket.send(sendPacket);

				// 接受服务器返回消息
				recPacket = new DatagramPacket(recByte, recByte.length);
				theSocket.receive(recPacket);
				String recString = new String(recByte, 0, recPacket.getLength());
				System.out.println("[CLIENT]: From Server: " + recString);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new UdpClient();
	}

}
