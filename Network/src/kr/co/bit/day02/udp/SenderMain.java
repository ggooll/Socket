package kr.co.bit.day02.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author 임규철
 * 
 *         localIP = 222.106.22.30 / 192.168.1.6
 */
public class SenderMain {

	public static void main(String[] args) {

		// UDP 송신
		try {

			DatagramSocket socket = new DatagramSocket();
			System.out.println("[송신 시작]");

			for (int i = 1; i <= 5; i++) {
				String msg = "메세지-" + i;

				// 인코딩은 프로젝트 설정을 따름
				// byte[] bytes = msg.getBytes("ms949");
				byte[] bytes = msg.getBytes("utf-8");

				// DatagramPacket은 byte배열으로, 길이, 도달할 소켓의 주소(**)
				DatagramPacket packet = new DatagramPacket(bytes, bytes.length,
						new InetSocketAddress("222.106.22.30", 10003));

				// 쓰레드 sleep으로 지연시킨 후
				// 실행 도중에 Receiver를 실행시키면 중간부터 받는 것을 볼 수 있다.
				Thread.sleep(1000);

				// 소켓을 통해 send
				socket.send(packet);
				System.out.println("전송한 데이터의 크기 : " + bytes.length);
			}

			System.out.println("[송신 완료]");
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
