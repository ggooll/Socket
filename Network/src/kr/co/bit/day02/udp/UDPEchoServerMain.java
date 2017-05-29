package kr.co.bit.day02.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author 임규철
 * 
 *         사용 - java UDPEchoServerMain 10004
 */
public class UDPEchoServerMain {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("명령어가 잘못되었습니다.");
			System.out.println("사용법 : java UDPEchoServerMain 포트번호");
			System.exit(0);
		}

		// 10004번 포트를 열어놓고 기다림
		// UDP는 데이터를 받는순간 클라이언트의 IP 정보를 알 수 있다.
		DatagramSocket socket = null;

		try {

			int port = Integer.parseInt(args[0]);
			socket = new DatagramSocket(port);

			System.out.println("접속대기상태입니다...");
			while (true) {
				byte[] buffer = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
				socket.receive(receivePacket);
				String msg = new String(receivePacket.getData(), 0, buffer.length);

				System.out.println("client가 보낸메시지 : " + msg);

				if (msg.trim().equalsIgnoreCase("quit")) {
					System.out.println("종료합니다");
					break;
				}

				DatagramPacket sendPacket =
						new DatagramPacket(receivePacket.getData(), receivePacket.getData().length,
								receivePacket.getAddress(), receivePacket.getPort());

				socket.send(sendPacket);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (socket != null) {
				socket.close();
			}
		}

	}

}
