/*Java����ģʽ(��http://voole.javaeye.com/blog/363885��ת��) 
1.����ģʽ 

����ģʽ�������ǣ�Ϊ���������ṩһ�ִ����Կ��ƶ��������ķ��ʡ���ĳЩ����£�һ���ͻ�������߲���ֱ��������һ�����󣬶������������ڿͻ��˺�Ŀ�����֮�����н�����á� 

����ģʽһ���漰���Ľ�ɫ�У� 

�����ɫ ��������ʵ����ʹ������Ĺ�ͬ�ӿڣ� 

�����ɫ ����������ɫ�ڲ����ж���ʵ��������ã��Ӷ����Բ�����ʵ����ͬʱ��������ṩ����ʵ������ͬ�Ľӿ��Ա����κ�ʱ�̶��ܴ�����ʵ����ͬʱ��������������ִ����ʵ�������ʱ�����������Ĳ������൱�ڶ���ʵ������з�װ�� 

��ʵ��ɫ �������ɫ���������ʵ��������������Ҫ���õĶ��� 

�����ԡ�Java��ģʽ���е�ʾ��Ϊ���� 

���룺 


Java����
*/ 
// �����ɫ��   
abstract   public   class  Subject {         
       abstract   public   void  request();   
}   
  
// ��ʵ��ɫ��ʵ����Subject��request()������   
public   class  RealSubject  extends  Subject {   
       public  RealSubject() {    
      }   
       public   void  request() {   
            System.out.println( " From real subject. " );   
      }   
}     
  
// �����ɫ��   
public   class  ProxySubject  extends  Subject {   
       private  RealSubject realSubject;  // ����ʵ��ɫ��Ϊ�����ɫ������    
        public  ProxySubject() {   
       }     
       public   void  request() {  // �÷�����װ����ʵ�����request����    
          preRequest();    
           if ( realSubject  ==   null  ) {   
                realSubject  =   new  RealSubject();   
          }   
          realSubject.request();  // �˴�ִ����ʵ�����request����    
            postRequest();    
       }   
       private   void  preRequest() {    
        // something you want to do before requesting    
      }   
       private   void  postRequest() {    
       // something you want to do after requesting    
      }   
}   
  
// �ͻ��˵��ã�   
Subject sub = new  ProxySubject();   
Sub.request();   


�����ϴ�����Կ������ͻ�ʵ����Ҫ���õ���RealSubject���request()������������ProxySubject������ RealSubject�࣬ͬ���ﵽĿ�ģ�ͬʱ����װ����������(preRequest(),postRequest())�����Դ���һЩ�������⡣ 

���⣬���Ҫ���������ķ���ʹ�ô���ģʽ����ô��ʵ��ɫ�����������Ѿ����ڵģ���������Ϊ���������ڲ����ԡ�����ʵ��ʹ��ʱ��һ����ʵ��ɫ�����Ӧһ�������ɫ���������ʹ�ûᵼ����ļ������ͣ����⣬������Ȳ���֪����ʵ��ɫ�������ʹ�ô����أ�����������ͨ��Java�Ķ�̬������������� 

2.��̬���� 

Java��̬������λ��Java.lang.reflect���£�һ����Ҫ�漰�����������ࣺ 

(1). Interface InvocationHandler ���ýӿ��н�������һ������Object��invoke(Object obj,Method method, Object[] args)����ʵ��ʹ��ʱ����һ������objһ����ָ�����࣬method�Ǳ�����ķ������������е�request()��argsΪ�÷����Ĳ������顣������󷽷��ڴ������ж�̬ʵ�֡� 

(2).Proxy �����༴Ϊ��̬�����࣬���������������е�ProxySubject��������Ҫ�����������ݣ� 
Protected Proxy(InvocationHandler h)�����캯�����������ڸ��ڲ���h��ֵ�� 

Static Class getProxyClass (ClassLoader loader, Class[] interfaces)�����һ�������࣬����loader����װ������interfaces����ʵ����ӵ�е�ȫ���ӿڵ����顣 

Static Object newProxyInstance(ClassLoader loader, Class[] interfaces, InvocationHandler h)�����ش������һ��ʵ�������غ�Ĵ�������Ե�����������ʹ��(��ʹ�ñ����������Subject�ӿ����������ķ���)�� 

��ν Dynamic Proxy������һ��class������������ʱ���ɵ�class����������ʱ������ṩһ��interface������Ȼ���class��������ʵ������Щ interface���㵱Ȼ���԰Ѹ�class��ʵ��������Щinterface�е��κ�һ�����á���Ȼ�������Dynamic Proxy��ʵ����һ��Proxy��������������ʵ���ԵĹ���������������ʵ��ʱ������ṩһ��handler�������ӹ�ʵ�ʵĹ�����(�μ�����3) 

��ʹ�ö�̬������ʱ�����Ǳ���ʵ��InvocationHandler�ӿڣ��Ե�һ���е�ʾ��Ϊ���� 

���룺 

//�����ɫ(֮ǰ�ǳ����࣬�˴�Ӧ��Ϊ�ӿ�)�� 

Java���� 
public interface Subject{   
      public void request();   
}    
  
//�����ɫRealSubject��ʵ����Subject�ӿڵ�request()������   
public class RealSubject implements Subject{    
      public RealSubject(){   
  
      }   
      public void request(){    
            System.out.println("From real subject.");    
      }    
}   
  
//�����ɫ��   
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

//�����ɫRealSubject��ʵ����Subject�ӿڵ�request()������ 
public class RealSubject implements Subject{ 
      public RealSubject(){ 

      } 
      public void request(){ 
            System.out.println("From real subject."); 
      } 
} 

//�����ɫ�� 
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


�ô�������ڲ�����ΪObject�࣬ʵ��ʹ��ʱͨ������Ĺ��캯��DynamicSubject(Object sub)���丳ֵ�����⣬�ڸ��໹ʵ����invoke������
�÷����е�"method.invoke(sub,args)" ��ʵ���ǵ��ñ��������Ľ�Ҫ��ִ�еķ�������������sub��ʵ�ʵı��������argsΪִ�б����������Ӧ��������Ĳ�����
ͨ����̬�����࣬���ǿ����ڵ���֮ǰ��֮��ִ��һЩ��ز����� 

�ͻ��ˣ� 
���룺 

Java���� 
import java.lang.reflect.InvocationHandler;   
import java.lang.reflect.Proxy;   
import java.lang.reflect.Constructor;   
import java.lang.reflect.Method;   
public class Client{    
   static public void main(String[] args) throws Throwable{   
   RealSubject rs = new RealSubject(); //������ָ����������   
   InvocationHandler ds = new DynamicSubject(rs); //��ʼ��������   
   Class cls = rs.getClass();   
   //�����Ƿֽⲽ��   
   /*  
   Class c = Proxy.getProxyClass(cls.getClassLoader(),cls.getInterfaces());  
   Constructor ct=c.getConstructor(new Class[]{InvocationHandler.class});  
   Subject subject =(Subject) ct.newInstance(new Object[]{ds});  
*/  
  
//������һ��������   
  
   Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),ds);   
   subject.request();   
}  


ͨ�����ַ�ʽ��������Ķ���(RealSubject)����������ʱ��̬�ı䣬��Ҫ���ƵĽӿ�(Subject�ӿ�)����������ʱ�ı䣬���Ƶķ�ʽ(DynamicSubject��)Ҳ���Զ�̬�ı䣬
�Ӷ�ʵ���˷ǳ����Ķ�̬�����ϵ�� 

3.����ģʽʹ��ԭ���Ӧ�÷��� 

��1����Ȩ���� ��ͬ������û���ͬһ����ӵ�в�ͬ�ķ���Ȩ��,��Jive��̳ϵͳ��,��ʹ��Proxy������Ȩ���ƿ���,������̳��������:ע���û����ο�(δע���û�),
Jive�о�ͨ������ForumProxy�����Ĵ����������������û�����̳�ķ���Ȩ��. 

��2��ĳ���ͻ��˲���ֱ�Ӳ�����ĳ������,���ֱ�����Ǹ�������������. 
      ���������������: 
      ����Ǹ�������һ���Ǻܴ��ͼƬ,��Ҫ���Ѻܳ�ʱ�������ʾ����,��ô�����ͼƬ�������ĵ���ʱ,ʹ�ñ༭���������������ĵ�,���ĵ������Ѹ��,
      ���ܵȴ���ͼƬ�������,��ʱ��Ҫ����ͼƬProxy������������ͼƬ. 
     
      ����Ǹ�������Internet��ĳ��Զ�˷�������,ֱ�Ӳ������������Ϊ�����ٶ�ԭ����ܱȽ���,�����ǿ�������Proxy�������Ǹ�����. 
     
      ��֮ԭ����,���ڿ����ܴ�Ķ���,ֻ����ʹ����ʱ�Ŵ���,���ԭ�����Ϊ���ǽ�ʡ�ܶ౦���Java�ڴ�. ����,��Щ����ΪJava�ķ���Դ�ڴ�,����Ϊ��ͳ������˼·Ҳ��һ���Ĺ�ϵ. 

��3����ʵ�У�ProxyӦ�÷�Χ�ܹ�,�������еķֲ����㷽ʽRMI��Corba�ȶ���Proxyģʽ��Ӧ�� 
