package edu.tongji.alex.telnet;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

public class MyClientT {

	private TelnetClient telnet = new TelnetClient("VT220");
	private InputStream in;
	private PrintStream out;
	private String prompt;
	String s = "QNET";

	public void TelnetCmd(String server, String user, String password,
			String cmd) {
		try {
			telnet.connect(server, 23);

			//System.out.println("Login............................");
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());

			// Login
			readUntil("login: ");
			write(user);
			readUntil("assword: ");
			write(password);

			// Advance to a prompt
			prompt = ">";
			readUntil(prompt);

			System.out.println(System.getProperty("file.encoding"));
			
			// Exec Cmd
			String retString = sendCmd(cmd);
//			byte retBytes[] = retString.getBytes();
//			System.out.println(new String(retBytes, "GB18030"));

			System.out.println(retString);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("logon failed");
		}
	}

	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			// boolean found = false;
			char ch = (char) in.read();

			while (true) {
				//System.setProperty("GBK", "iso8859-1");
				//System.setProperty("file.encoding", "UTF-8");
				//System.out.print(ch);
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						// System.out.print( sb.toString());
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void write(String value) {
		try {
			out.println(value);
			out.flush();
			//System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sendCmd(String command) {
		try {
			prompt = ">";
			write(command);
			return readUntil(s + prompt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {

			System.out.println("Start");
			MyClientT telnet = new MyClientT();
			telnet.TelnetCmd("192.168.1.2", "admin", "admin1", "#device,76,10,3");
			telnet.TelnetCmd("192.168.1.2", "admin", "admin1", "#device,76,10,3");
			System.out.println("End");
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
