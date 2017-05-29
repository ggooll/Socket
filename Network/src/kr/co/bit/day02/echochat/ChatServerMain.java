package kr.co.bit.day02.echochat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 임규철
 */
public class ChatServerMain {

	public static void main(String[] args) {

		// 다수의 사용자
		try (ServerSocket server = new ServerSocket(10002);) {
			System.out.println("접속 대기중입니다.");

			while (true) {
				Socket client = server.accept();
				System.out.println("접속된 클라이언트 정보 : " + client.getInetAddress());

				// 접속될때마다의 inputStream, outputStream을 저장해둬야함
				ChatThread echoThread = new ChatThread(client);
				echoThread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
