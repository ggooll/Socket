package kr.co.bit.day02.echochat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author 임규철
 */
public class ChatClientMain {

	public static void main(String[] args) {

		try ( // 소켓 생성
				Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
				// 키보드에서 메시지 입력받는 객체 (System.in)
				InputStream keyis = System.in;
				InputStreamReader keyisr = new InputStreamReader(keyis);
				BufferedReader keyboardReader = new BufferedReader(keyisr);

				// 키보드로 입력받은 메시지를 서버에 전송할 객체
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				PrintWriter pw = new PrintWriter(osw);) {

			System.out.print("아이디를 입력하세요 >> ");
			String id = keyboardReader.readLine();

			// db에서 아이디 이름 입력 시
			// System.out.print("비밀번호를 입력하세요 >> ");
			// String password = keyboardReader.readLine();
			// String nickname = login(id, password);

			// 접속한 아이디를 보냄
			pw.println(id);
			pw.flush();

			// 수신용 쓰레드
			ClientReadThread readThread = new ClientReadThread(socket);
			readThread.start();

			while (true) {
				String msg = keyboardReader.readLine();
				if (msg.trim().length() > 0) {
					pw.println(id + ":" + msg);
					pw.flush();
				}

				if (msg.equalsIgnoreCase("quit")) {
					System.out.println("서버와의 접속을 종료합니다.");
					break;
				}
			}

		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
