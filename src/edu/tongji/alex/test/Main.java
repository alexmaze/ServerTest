package edu.tongji.alex.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		System.out.println("[CLIENT]: Please Input");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in));
		String sendString = br.readLine();
		
		byte bytInfo[] = sendString.getBytes();
		
		Thread.currentThread().sleep(5000);
		
		System.out.println(bytInfo);
		
		
	}

}
