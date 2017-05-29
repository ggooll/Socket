package kr.co.bit.day02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * localIP = 222.106.22.30 / 192.168.1.6
 * 
 * cmd에서 java EchoClientMain ip주소, 포트넘버 명령으로 접속하기
 * 
 * chcp 65001 chcp 949 UTF-8 컴파일 명령어 : javac
 * 
 * -encoding UTF-8 EchoClientMain.java
 * 
 * @author 임규철
 */
public class EchoClientMain {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("명령어가 잘못되었습니다.");
			System.out.println("사용법 : java EchoClientMain IP번호 포트번호");
			System.exit(0);
		}

		try {
			System.out.println(Arrays.toString(args));
			Socket socket = new Socket(args[0], Integer.parseInt(args[1]));

			// 키보드에서 메시지 입력받는 객체 (System.in)
			InputStream keyis = System.in;
			InputStreamReader keyisr = new InputStreamReader(keyis);
			BufferedReader keyboardReader = new BufferedReader(keyisr);

			// 키보드로 입력받은 메시지를 서버에 전송할 객체
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw);

			// 서버에서 보내준 메시지를 수신할 객체
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			while (true) {
				System.out.print("서버에 전송할 메시지 입력(quit 입력시 종료) >> ");
				String msg = keyboardReader.readLine();

				if (msg.equalsIgnoreCase("quit")) {
					System.out.println("서버와의 접속을 종료합니다.");
					socket.close();
					break;
				}

				// 송신
				pw.println(msg);
				pw.flush();

				// 수신
				String echoMsg = br.readLine();
				System.out.println("서버에서 재전송한 메시지 : " + echoMsg);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
