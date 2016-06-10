package com.company.section9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ������
 * This is a kind of dynamic Proxy implementation. why we use dynamic proxy?
 */
public class GamePlayIH implements InvocationHandler {
	//��������
	Class cls =null;
	//�������ʵ��
	Object obj = null;
	
	//��Ҫ����˭
	public GamePlayIH(Object _obj){
		this.obj = _obj;
	}
	
	//���ñ�����ķ���
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before invoke");
		Object result = method.invoke(this.obj, args);
		System.out.println("after invoke");
		return result;
	}
	
}
