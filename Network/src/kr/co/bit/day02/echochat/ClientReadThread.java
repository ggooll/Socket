package kr.co.bit.day02.echochat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author 임규철
 */
public class ClientReadThread extends Thread {

	private Socket socket;

	public ClientReadThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try ( // 서버에서 보내준 메시지를 수신할 객체
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);) {

			// 받은걸 써주기만 한다.
			while (true) {
				String echoMsg = br.readLine();
				System.out.println(echoMsg);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
