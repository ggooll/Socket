package kr.co.bit.day01;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author 임규철
 */
public class AppClientMain {

	public static void main(String[] args) {

		try {
			// 클라이언트 입장. 호스트 네임(IP주소), 혹은 도메인과 포트번호가 필요함
			Socket socket = new Socket("192.168.1.5", 10000);

			// 서버가 보낸 환영메시지를 받기위한 InputStream
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);

			// 서버가 DataOutputStream으로 보낸 메시지를 읽음
			String msg = dis.readUTF();
			System.out.println(msg);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
