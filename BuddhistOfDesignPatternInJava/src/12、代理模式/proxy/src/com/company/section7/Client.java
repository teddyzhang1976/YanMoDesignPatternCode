package com.company.section7;

import com.company.section6.GamePlayer;
import com.company.section6.IGamePlayer;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * 场景类
 * 与section6的区别在于，本例子使用被代理类的方法来获取代理类，而6中则是新建代理类
 * 传入被代理类，仅仅是使用代理的方式变化了，这些都是静态代理，当然，使用代理的必要性还是
 * 没有体现。
 */
public class Client {

	public static void main(String[] args) {
		//定义个游戏的角色
		IGamePlayer player = new GamePlayer("张三");
		//获得指定的代理
		IGamePlayer proxy = player.getProxy();
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

