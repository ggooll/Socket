package kr.co.bit.day02.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author 임규철
 */
public class ReceiverMain {

	public static void main(String[] args) {

		try {
			// 일종의 서버 - 소켓을 열고 기다림
			DatagramSocket socket = new DatagramSocket(10003);
			System.out.println("[수신 시작]");

			// 미리 바이트배열과 사이즈를 정의해두고 있어야함(버퍼의 크기를 미리 정의)
			byte[] bytes = new byte[100];
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

			while (true) {
				// 데이터를 기다림, 인코딩을 맞춰야 한다. (한글의 경우.,.)
				socket.receive(packet);
				String msg = new String(packet.getData(), "utf-8");
				System.out.println("[수신 메시지 - " + msg + "]");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
