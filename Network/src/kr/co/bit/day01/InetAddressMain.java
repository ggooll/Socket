package kr.co.bit.day01;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author 임규철
 */
public class InetAddressMain {

	public static void main(String[] args) {

		try {

			// 내 컴퓨터의 정보를 얻어옴 localHost
			InetAddress localHost = InetAddress.getLocalHost();
			System.out.println(localHost.getHostName());
			System.out.println(localHost.getHostAddress());

			// 임의의 주소에 대한 정보를 얻어옴(도메인(name)을 주고)
			InetAddress bit = InetAddress.getByName("www.bitacademy.com");
			System.out.println(bit);
			InetAddress naver = InetAddress.getByName("www.naver.com");
			System.out.println(naver);

			// 접속이 많은 회사들, 여러대의 호스트를 All by name으로 조회할 수 있음
			InetAddress[] navers = InetAddress.getAllByName("www.naver.com");
			System.out.println(Arrays.toString(navers));
			// 202.179.177.22

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}

	}
}
