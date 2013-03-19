package edu.tongji.alex.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {

	private int PORT = 5000;
	private int BUFFER_SIZE = 2000;

	private DatagramSocket theSocket;

	private DatagramPacket recPacket;
	private DatagramPacket sendPacket;

	private byte recByte[];
	private byte sendByte[];

	public UdpServer() {
		init();
	}

	public UdpServer(int port, int buffer_size) {
		PORT = port;
		BUFFER_SIZE = buffer_size;
		init();
	}

	private void init() {
		recByte = new byte[BUFFER_SIZE];
		sendByte = new byte[BUFFER_SIZE];

		try {
			theSocket = new DatagramSocket(PORT);
			recPacket = new DatagramPacket(recByte, recByte.length);

			while (true) {

				// 等待接受数据
				theSocket.receive(recPacket);
				String recString = new String(recByte, 0, recPacket.getLength());
				System.out.println("[SERVER]: From Client: \"" + recString + "\"");

				// 发送返回信息
				String sendString = "\"" + recString + "\"" + " is that right?";
				sendByte = sendString.getBytes();
				sendPacket = new DatagramPacket(sendByte, sendByte.length,
						recPacket.getAddress(), recPacket.getPort());
				theSocket.send(sendPacket);
				System.out.println("[SERVER]: " + "Send Back Done!");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new UdpServer();
	}

}
