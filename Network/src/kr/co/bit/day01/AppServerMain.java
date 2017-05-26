package kr.co.bit.day01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 임규철
 */
public class AppServerMain {

	public static void main(String[] args) {
		// 서버소켓 10000포트를 열어놓고 기다림

		try {

			ServerSocket server = new ServerSocket(10000);
			System.out.println("어플리케이션 서버 구동중...");

			// 클라이언트 요청을 기다린다. 요청이 올때까지 무한대기상태..
			// 요청이오면 통신이 가능한 소켓을 생성하여 리턴한다.
			Socket client = server.accept();

			// 리턴된 소켓은, 클라이언트의 요청에 따른 클라이언트의 정보를 가지고 있어야 한다.
			System.out.println("접속된 클라이언트의 정보 : " + client);

			// 접속한 클라이언트에게 환영메시지를 보내고싶다.
			// DataOutputStream을 필터클래스로 사용함 (다른 것 해볼 것)
			// String msg = "서버 : 접속을 환영합니다.";

			OutputStream os = client.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			InputStream is = client.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			String msg = null;

			while (true) {
				msg = dis.readUTF();
				if (msg.equals("종료")) {
					server.close();
					break;
				}

				// DataOutputStream으로 메시지를 보냄
				dos.writeUTF(msg);
				dos.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("서버를 종료합니다.");

	}

}
