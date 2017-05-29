package kr.co.bit.day02.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 임규철 java UDPEchoClientMain IP주소, 포트 와 같이 실행하고 싶음
 */
public class UDPEchoClientMain {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("명령어가 잘못되었습니다.");
			System.out.println("사용법 : java UDPEchoClientMain IP번호 포트번호");
			System.exit(0);
		}

		DatagramSocket socket = null;

		try {

			InetAddress addr = InetAddress.getByName(args[0]);
			int port = Integer.parseInt(args[1]);

			if (port < 0) {
				throw new Exception();
			}

			socket = new DatagramSocket();
			System.out.println("서버에 전송할 메시지를 입력하세요(quit 입력시 종료)");

			String line = null;
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

			while ((line = keyboard.readLine()) != null) {
				DatagramPacket sendPacket =
						new DatagramPacket(line.getBytes(), line.getBytes().length, addr, port);
				socket.send(sendPacket);

				if (line.equalsIgnoreCase("quit")) {
					break;
				}

				// 수신
				byte[] bytes = new byte[line.getBytes().length];
				DatagramPacket receivePacket = new DatagramPacket(bytes, bytes.length);
				socket.receive(receivePacket);

				String msg = new String(receivePacket.getData());
				System.out.println("재전송된 메시지 : " + msg);
			}

		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			if (socket != null) {
				socket.close();
			}
		}

	}

}
