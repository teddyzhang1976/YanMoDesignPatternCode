package cn.javass.dp.decorator.example5;

import java.io.*;

public class Client {
	public static void main(String[] args) throws Exception {
		//��ʽ����ļ�
		DataOutputStream dout = new DataOutputStream(
			new EncryptOutputStream(
				new BufferedOutputStream(
					new FileOutputStream("MyEncrypt.txt"))));
		//Ȼ��Ϳ������������
		dout.write("abcdxyz".getBytes());
		dout.close();
	}
}
