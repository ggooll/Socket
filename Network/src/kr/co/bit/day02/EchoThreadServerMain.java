package kr.co.bit.day02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 임규철
 * 
 *         1:N 클라이언트를 접속할 수 있는 서버 - 쓰레드 활용
 */
public class EchoThreadServerMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("EchoServer를 구동합니다.");

		try {
			ServerSocket server = new ServerSocket(10002);

			// 다수의 클라이언트를 받고 싶다.
			while (true) {
				// 서버소켓이 접속된 클라이언트와 통신할 소켓을 생성
				Socket client = server.accept();
				System.out.println("접속된 클라이언트 정보 : " + client.getInetAddress());

				// 쓰레드가 만들어져야 함 (각각의 클라이언트와의 통신)
				EchoThread echoThread = new EchoThread(client);
				echoThread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
