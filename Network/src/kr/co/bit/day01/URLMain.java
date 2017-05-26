package kr.co.bit.day01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 임규철
 */
public class URLMain {

	public static void main(String[] args) {

		// 네트워크도 IO 기반

		try {
			// URL
			URL urlObj = new URL("https://www.naver.com");
			System.out.println("프로토콜 : " + urlObj.getProtocol());
			System.out.println("호스트 : " + urlObj.getHost());
			System.out.println("포트 : " + urlObj.getPort());
			System.out.println("경로 : " + urlObj.getPath());
			System.out.println("쿼리 : " + urlObj.getQuery());
			System.out.println("-----------------------------------------------------");

			// openStream = 해당 URL의 파일을 읽어들일 스트림을 얻는다.
			// InputStream은 한글을 2바이트로 인식(유니코드)
			// InputStreamReader를 이용하여 3바이트로 인식되게끔 해야함- 필터 클래스, 총과 소음기..)
			// 인코딩 방식을 명시하지 않는경우 2바이트가 디폴트이므로, utf-8이면 명시할 것
			InputStream is = urlObj.openStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");

			while (true) {
				int a = isr.read();
				if (a == -1) {
					break;
				}
				System.out.print((char) a);
			}

		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
