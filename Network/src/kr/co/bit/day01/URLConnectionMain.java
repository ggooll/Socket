package kr.co.bit.day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 임규철
 */
public class URLConnectionMain {

	// URLConnection -
	public static void main(String[] args) {

		// www.naver.com 에있는 내용을 읽어서 내 컴퓨터의 naver.html에 저장하도록
		try (FileOutputStream fos = new FileOutputStream("naver.html");
				OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
				BufferedWriter bw = new BufferedWriter(osw);) {

			URL urlObj = new URL("https://www.naver.com");
			// openConnection 메소드를 통해 URLConnection을 받아옴
			// API 내 getInputStream과 getOutputStream을 우선 기억
			URLConnection urlConn = urlObj.openConnection();
			InputStream is = urlConn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);

			while (true) {
				String str = br.readLine();
				if (str == null) {
					break;
				}
				// write
				bw.write(str);
				bw.newLine();
			}
			bw.flush();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("저장!");
	}

}
