package com.company.section4;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * 场景类
 */
public class Client {

	public static void main(String[] args) {

		//定义一个代练者,其内部直接定义了其所代理的真实类，这个真实类的名字叫张三了。
		//由于这里的代理还是只是转调了真实类的方法，因此看不出其存在的必要性。
		//如果在其转调真实类的方法前后加入些东西就会变的有意义了似乎。
		IGamePlayer proxy = new GamePlayerProxy("张三");
		
		//开始打游戏，记下时间戳
		System.out.println("开始时间是：2009-8-25 10:45");
		proxy.login("zhangSan", "password");
		//开始杀怪
		//此处可以在代理的login方法中加入预验证机制，这样就可以达到filter的作用。不过真实项目什么时候需要酱紫的机制呢？
		proxy.killBoss();
		//升级
		proxy.upgrade();
		//记录结束游戏时间
		System.out.println("结束时间是：2009-8-26 03:40");
		
	}

}
