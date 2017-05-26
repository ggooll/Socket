package kr.co.bit.day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 임규철
 */
public class AppClientMain {

	public static void main(String[] args) {

		Socket socket = null;

		try {
			// 클라이언트 입장. 호스트 네임(IP주소), 혹은 도메인과 포트번호가 필요함
			socket = new Socket("192.168.1.6", 10000);

			// 서버가 보낸 환영메시지를 받기위한 InputStream
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// DataInputStream dis = new DataInputStream(is);
			// DataOutputStream dos = new DataOutputStream(os);

			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter out = new PrintWriter(osw);
			Scanner scan = new Scanner(System.in);

			while (true) {
				String msg = scan.nextLine();
				out.println(msg);
				out.flush();

				// dos.writeUTF(msg);
				// dos.flush();

				if (msg.equals("종료")) {
					socket.close();
					scan.close();
					System.exit(0);
				}

				// 서버가 DataOutputStream으로 보낸 메시지를 읽음
				// msg = dis.readUTF();

				msg = br.readLine();
				System.out.println(msg);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

	}

}
