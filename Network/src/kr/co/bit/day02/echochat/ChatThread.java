package kr.co.bit.day02.echochat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author 임규철
 * 
 */
public class ChatThread extends Thread {

	private static HashMap<String, PrintWriter> userMap = new HashMap<>();
	private String name;
	private Socket socket;
	// 서버가 각각 응답할 유저 정보

	public ChatThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try ( // 수신용
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				// 송신용
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				PrintWriter pw = new PrintWriter(osw);) {

			this.name = br.readLine();

			broadCast(name + " 님이 대화에 참여하셨습니다.");
			synchronized (userMap) {
				userMap.put(name, pw);
			}

			while (true) {
				String line = br.readLine();
				String sender = line.split(":")[0];
				String message = line.split(":")[1];

				// 나가기
				if (message.equalsIgnoreCase("quit")) {
					broadCast(sender + " 님이 나가셨습니다");
					synchronized (userMap) {
						userMap.remove(sender);
					}
					break;
				}

				// 귓속말
				if (message.startsWith("/to ")) {
					whisper(sender, message);
					continue;
				}

				// 모두에게 보내기
				broadCast(line);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeSocket();
		}
	}

	/**
	 * 귓속말을 하는 경우
	 * 
	 * @param sender - 귓속말을 보낸사람
	 * @param msg - /to username message
	 */
	private synchronized void whisper(String sender, String msg) {
		String[] splitMsg = msg.split(" ");
		String user = splitMsg[1];
		splitMsg[0] = "";
		splitMsg[1] = "";
		String message = Arrays.toString(splitMsg);

		PrintWriter printWriter = userMap.get(user);
		if (printWriter != null) {
			printWriter.println("귓속말) " + sender + ":" + message);
			printWriter.flush();
		}
	}

	/**
	 * 전체 송신
	 * 
	 * @param msg - 전체 송신할 메시지
	 */
	private synchronized void broadCast(String msg) {
		Iterator<String> iter = userMap.keySet().iterator();
		while (iter.hasNext()) {
			String user = iter.next();
			// 본인 것은 건너뜀
			if (user.equals(name)) {
				continue;
			}

			PrintWriter printWriter = userMap.get(user);
			printWriter.println(msg);
			printWriter.flush();
		}
	}

	/**
	 * 소켓 닫기
	 */
	private void closeSocket() {
		try {
			this.socket.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
