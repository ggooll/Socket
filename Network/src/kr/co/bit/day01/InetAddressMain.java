package kr.co.bit.day01;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author �ӱ�ö
 */
public class InetAddressMain {

	public static void main(String[] args) {

		try {

			// �� ��ǻ���� ������ ���� localHost
			InetAddress localHost = InetAddress.getLocalHost();
			System.out.println(localHost.getHostName());
			System.out.println(localHost.getHostAddress());

			// ������ �ּҿ� ���� ������ ����(������(name)�� �ְ�)
			InetAddress bit = InetAddress.getByName("www.bitacademy.com");
			System.out.println(bit);
			InetAddress naver = InetAddress.getByName("www.naver.com");
			System.out.println(naver);
			
			// ������ ���� ȸ���, �������� ȣ��Ʈ�� All by name���� ��ȸ�� �� ����
			InetAddress[] navers = InetAddress.getAllByName("www.naver.com");
			System.out.println(Arrays.toString(navers));
			// 202.179.177.22 
			
			
			
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}

	}
}
