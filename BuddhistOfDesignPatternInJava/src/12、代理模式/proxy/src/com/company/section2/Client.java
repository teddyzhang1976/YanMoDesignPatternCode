package com.company.section2;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * 场景类
 */
public class Client {

	public static void main(String[] args) {
		//定义一个痴迷的玩家
		IGamePlayer player = new GamePlayer("张三");
		
		//然后再定义一个代练者
		//Q: why don't you do it yourself?
		//A: 1. 一种情况是：proxy实现代理接口的时候可以加入权限机制，看section3中的例子，其中可以加入dobefore和
		//doafter这样的函数达到filter和路由的作用。本例中是没有任何必要，因为proxy类仅仅转call了被代理类的方法。
		IGamePlayer proxy = new GamePlayerProxy(player);
		
		//开始打游戏，记下时间戳
		System.out.println("开始时间是：2009-8-25 10:45");
		proxy.login("zhangSan", "password");
		//开始杀怪
		proxy.killBoss();
		//升级
		proxy.upgrade();
		//记录结束游戏时间
		System.out.println("结束时间是：2009-8-26 03:40");
		
	}

}
