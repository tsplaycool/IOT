package cn.edu.bjtu.iot;

public class CSMA_Channel {

	public static int speed = 10;// ģ���ŵ���������
	public static String occupyChannelThreadName = "";// ռ���ŵ����߳�����
	public static long emptyBefore = System.currentTimeMillis();
	public static long totalEmpytTime;

	public static void emptyChannel() {
		emptyBefore = System.currentTimeMillis();
		occupyChannelThreadName = "";
	}

	public static void setChannelName(String name) {
		occupyChannelThreadName = name;
		totalEmpytTime += ((System.currentTimeMillis() - emptyBefore));
//		System.out.println("�ܵ��ŵ�����ʱ��:" + totalEmpytTime);
	}

}
