package kr.co.bit.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 임규철
 */
public class AppServerMain {

    public static void main(String[] args) {

        // 서버소켓 10000포트를 열어놓고 기다림

        try {
            ServerSocket server = new ServerSocket(10000);
            System.out.println("어플리케이션 서버 구동중...");

            // 클라이언트 요청을 기다린다. 요청이 올때까지 무한대기상태..
            // 요청이오면 통신이 가능한 소켓을 생성하여 리턴한다.
            Socket client = server.accept();

            // 리턴된 소켓은, 클라이언트의 요청에 따른 클라이언트의 정보를 가지고 있어야 한다.
            System.out.println("접속된 클라이언트의 정보 : " + client);

            // DataOutputStream을 필터클래스로 사용함 (다른 것 해볼 것)
            // String msg = "서버 : 접속을 환영합니다.";

            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();

            // Stream - Byte
            // DataInputStream dis = new DataInputStream(is);
            // DataOutputStream dos = new DataOutputStream(os);

            OutputStreamWriter osw = new OutputStreamWriter(os);
            PrintWriter out = new PrintWriter(osw);

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String msg = null;

            while (true) {
                msg = br.readLine();
                // msg = dis.readUTF();
                if (msg.equals("종료")) {
                    server.close();
                    break;
                }

                // DataOutputStream으로 메시지를 보냄
                // dos.writeUTF(msg);
                // dos.flush();

                out.println(msg);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("서버를 종료합니다.");

    }

}
