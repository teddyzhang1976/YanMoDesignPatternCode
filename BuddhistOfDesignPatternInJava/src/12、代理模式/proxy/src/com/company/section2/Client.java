package com.company.section2;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ������
 */
public class Client {

	public static void main(String[] args) {
		//����һ�����Ե����
		IGamePlayer player = new GamePlayer("����");
		
		//Ȼ���ٶ���һ��������
		//Q: why don't you do it yourself?
		//A: 1. һ������ǣ�proxyʵ�ִ���ӿڵ�ʱ����Լ���Ȩ�޻��ƣ���section3�е����ӣ����п��Լ���dobefore��
		//doafter�����ĺ����ﵽfilter��·�ɵ����á���������û���κα�Ҫ����Ϊproxy�����תcall�˱�������ķ�����
		IGamePlayer proxy = new GamePlayerProxy(player);
		
		//��ʼ����Ϸ������ʱ���
		System.out.println("��ʼʱ���ǣ�2009-8-25 10:45");
		proxy.login("zhangSan", "password");
		//��ʼɱ��
		proxy.killBoss();
		//����
		proxy.upgrade();
		//��¼������Ϸʱ��
		System.out.println("����ʱ���ǣ�2009-8-26 03:40");
		
	}

}
