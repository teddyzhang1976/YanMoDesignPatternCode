/*Java代理模式(从http://voole.javaeye.com/blog/363885处转载) 
1.代理模式 

代理模式的作用是：为其他对象提供一种代理以控制对这个对象的访问。在某些情况下，一个客户不想或者不能直接引用另一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用。 

代理模式一般涉及到的角色有： 

抽象角色 ：声明真实对象和代理对象的共同接口； 

代理角色 ：代理对象角色内部含有对真实对象的引用，从而可以操作真实对象，同时代理对象提供与真实对象相同的接口以便在任何时刻都能代替真实对象。同时，代理对象可以在执行真实对象操作时，附加其他的操作，相当于对真实对象进行封装。 

真实角色 ：代理角色所代表的真实对象，是我们最终要引用的对象。 

以下以《Java与模式》中的示例为例： 

代码： 


Java代码
*/ 
// 抽象角色：   
abstract   public   class  Subject {         
       abstract   public   void  request();   
}   
  
// 真实角色：实现了Subject的request()方法。   
public   class  RealSubject  extends  Subject {   
       public  RealSubject() {    
      }   
       public   void  request() {   
            System.out.println( " From real subject. " );   
      }   
}     
  
// 代理角色：   
public   class  ProxySubject  extends  Subject {   
       private  RealSubject realSubject;  // 以真实角色作为代理角色的属性    
        public  ProxySubject() {   
       }     
       public   void  request() {  // 该方法封装了真实对象的request方法    
          preRequest();    
           if ( realSubject  ==   null  ) {   
                realSubject  =   new  RealSubject();   
          }   
          realSubject.request();  // 此处执行真实对象的request方法    
            postRequest();    
       }   
       private   void  preRequest() {    
        // something you want to do before requesting    
      }   
       private   void  postRequest() {    
       // something you want to do after requesting    
      }   
}   
  
// 客户端调用：   
Subject sub = new  ProxySubject();   
Sub.request();   


由以上代码可以看出，客户实际需要调用的是RealSubject类的request()方法，现在用ProxySubject来代理 RealSubject类，同样达到目的，同时还封装了其他方法(preRequest(),postRequest())，可以处理一些其他问题。 

另外，如果要按照上述的方法使用代理模式，那么真实角色必须是事先已经存在的，并将其作为代理对象的内部属性。但是实际使用时，一个真实角色必须对应一个代理角色，如果大量使用会导致类的急剧膨胀；此外，如果事先并不知道真实角色，该如何使用代理呢？这个问题可以通过Java的动态代理类来解决。 

2.动态代理 

Java动态代理类位于Java.lang.reflect包下，一般主要涉及到以下两个类： 

(1). Interface InvocationHandler ：该接口中仅定义了一个方法Object：invoke(Object obj,Method method, Object[] args)。在实际使用时，第一个参数obj一般是指代理类，method是被代理的方法，如上例中的request()，args为该方法的参数数组。这个抽象方法在代理类中动态实现。 

(2).Proxy ：该类即为动态代理类，作用类似于上例中的ProxySubject，其中主要包含以下内容： 
Protected Proxy(InvocationHandler h)：构造函数，估计用于给内部的h赋值。 

Static Class getProxyClass (ClassLoader loader, Class[] interfaces)：获得一个代理类，其中loader是类装载器，interfaces是真实类所拥有的全部接口的数组。 

Static Object newProxyInstance(ClassLoader loader, Class[] interfaces, InvocationHandler h)：返回代理类的一个实例，返回后的代理类可以当作被代理类使用(可使用被代理类的在Subject接口中声明过的方法)。 

所谓 Dynamic Proxy是这样一种class：它是在运行时生成的class，在生成它时你必须提供一组interface给它，然后该class就宣称它实现了这些 interface。你当然可以把该class的实例当作这些interface中的任何一个来用。当然啦，这个Dynamic Proxy其实就是一个Proxy，它不会替你作实质性的工作，在生成它的实例时你必须提供一个handler，由它接管实际的工作。(参见文献3) 

在使用动态代理类时，我们必须实现InvocationHandler接口，以第一节中的示例为例： 

代码： 

//抽象角色(之前是抽象类，此处应改为接口)： 

Java代码 
public interface Subject{   
      public void request();   
}    
  
//具体角色RealSubject：实现了Subject接口的request()方法。   
public class RealSubject implements Subject{    
      public RealSubject(){   
  
      }   
      public void request(){    
            System.out.println("From real subject.");    
      }    
}   
  
//代理角色：   
import java.lang.reflect.Method;   
import java.lang.reflect.InvocationHandler;   
public class DynamicSubject implements InvocationHandler{   
      private Object sub;    
      public DynamicSubject(Object sub){               
            this.sub = sub;   
      }   
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {   
          System.out.println("before calling " + method);    
          method.invoke(sub,args);    
          System.out.println("after calling " + method);    
          return null;    
      }   
}  

public interface Subject{ 
      public void request(); 
} 

//具体角色RealSubject：实现了Subject接口的request()方法。 
public class RealSubject implements Subject{ 
      public RealSubject(){ 

      } 
      public void request(){ 
            System.out.println("From real subject."); 
      } 
} 

//代理角色： 
import java.lang.reflect.Method; 
import java.lang.reflect.InvocationHandler; 
public class DynamicSubject implements InvocationHandler{ 
      private Object sub; 
      public DynamicSubject(Object sub){            
            this.sub = sub; 
      } 
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { 
          System.out.println("before calling " + method); 
          method.invoke(sub,args); 
          System.out.println("after calling " + method); 
          return null; 
      } 
} 


该代理类的内部属性为Object类，实际使用时通过该类的构造函数DynamicSubject(Object sub)对其赋值；此外，在该类还实现了invoke方法，
该方法中的"method.invoke(sub,args)" 其实就是调用被代理对象的将要被执行的方法，方法参数sub是实际的被代理对象，args为执行被代理对象相应操作所需的参数。
通过动态代理类，我们可以在调用之前或之后执行一些相关操作。 

客户端： 
代码： 

Java代码 
import java.lang.reflect.InvocationHandler;   
import java.lang.reflect.Proxy;   
import java.lang.reflect.Constructor;   
import java.lang.reflect.Method;   
public class Client{    
   static public void main(String[] args) throws Throwable{   
   RealSubject rs = new RealSubject(); //在这里指定被代理类   
   InvocationHandler ds = new DynamicSubject(rs); //初始化代理类   
   Class cls = rs.getClass();   
   //以下是分解步骤   
   /*  
   Class c = Proxy.getProxyClass(cls.getClassLoader(),cls.getInterfaces());  
   Constructor ct=c.getConstructor(new Class[]{InvocationHandler.class});  
   Subject subject =(Subject) ct.newInstance(new Object[]{ds});  
*/  
  
//以下是一次性生成   
  
   Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),ds);   
   subject.request();   
}  


通过这种方式，被代理的对象(RealSubject)可以在运行时动态改变，需要控制的接口(Subject接口)可以在运行时改变，控制的方式(DynamicSubject类)也可以动态改变，
从而实现了非常灵活的动态代理关系。 

3.代理模式使用原因和应用方面 

（1）授权机制 不同级别的用户对同一对象拥有不同的访问权利,如Jive论坛系统中,就使用Proxy进行授权机制控制,访问论坛有两种人:注册用户和游客(未注册用户),
Jive中就通过类似ForumProxy这样的代理来控制这两种用户对论坛的访问权限. 

（2）某个客户端不能直接操作到某个对象,但又必须和那个对象有所互动. 
      举例两个具体情况: 
      如果那个对象是一个是很大的图片,需要花费很长时间才能显示出来,那么当这个图片包含在文档中时,使用编辑器或浏览器打开这个文档,打开文档必须很迅速,
      不能等待大图片处理完成,这时需要做个图片Proxy来代替真正的图片. 
     
      如果那个对象在Internet的某个远端服务器上,直接操作这个对象因为网络速度原因可能比较慢,那我们可以先用Proxy来代替那个对象. 
     
      总之原则是,对于开销很大的对象,只有在使用它时才创建,这个原则可以为我们节省很多宝贵的Java内存. 所以,有些人认为Java耗费资源内存,我以为这和程序编制思路也有一定的关系. 

（3）现实中，Proxy应用范围很广,现在流行的分布计算方式RMI和Corba等都是Proxy模式的应用 
