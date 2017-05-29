package kr.co.bit.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 임규철
 */
public class EchoServerMain {

	public static void main(String[] args) {

		// 서버는 포트를 열어놓고 대기
		try {
			ServerSocket server = new ServerSocket(10000);
			System.out.println("클라이언트의 접속을 기다립니다...");

			// 기다리다가 접속하면 클라이언트와 통신할 수 있는 소켓이 생성된다.
			Socket client = server.accept();
			System.out.println("접속한 클라이언트의 정보 : " + client.getInetAddress());

			// 클라이언트가 전송해준 메시지를 수신하는 객체가 필요함(inputStream)
			InputStream is = client.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			// 수신한 메세지를 클라이언트에게 송신하는 객체가 필요함(outputStream)
			OutputStream os = client.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw);

			while (true) {
				// 수신
				String msg = br.readLine();

				// 메시지를 읽지 못했을 경우, 혹은 클라이언트에게 quit을 전송받게끔 구현할 수도 있다.
				if (msg == null) {
					System.out.println("클라이언트와의 접속해제.");
					client.close();
					break;
				}

				System.out.println(client.getInetAddress().getHostAddress() + "의 메시지 = " + msg);

				// 송신
				pw.println(msg);
				pw.flush();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
