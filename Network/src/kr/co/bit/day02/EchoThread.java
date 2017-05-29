package kr.co.bit.day02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author 임규철
 * 
 */
public class EchoThread extends Thread {

	private Socket socket;

	public EchoThread(Socket socket) {
		this.socket = socket;

	}

	@Override
	public void run() {

		try {
			// 클라이언트가 전송해준 메시지를 수신하는 객체가 필요함(inputStream)
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			// 수신한 메세지를 클라이언트에게 송신하는 객체가 필요함(outputStream)
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw);

			while (true) {
				// 수신
				String msg = br.readLine();

				// 메시지를 읽지 못했을 경우, 혹은 클라이언트에게 quit을 전송받게끔 구현할 수도 있다.
				if (msg == null) {
					System.out.println(socket.getInetAddress() + " 클라이언트와의 접속해제.");
					socket.close();
					break;
				}

				System.out.println(socket.getInetAddress().getHostAddress() + "의 메시지 = " + msg);

				// 송신
				pw.println(msg);
				pw.flush();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
