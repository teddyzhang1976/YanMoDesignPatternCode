package cn.javass.dp.builder.example4;

public class Client {
	public static void main(String[] args) {
		//����������
		ConcreteBuilder builder = new ConcreteBuilder("001",12345L,67890L);
		//������Ҫ�����ݣ�Ȼ�󹹽����պ�ͬ����
		InsuranceContract contract = builder.setPersonName("����").setOtherData("test").build();
		//�������պ�ͬ����ķ���
		contract.someOperation();
	}
}
