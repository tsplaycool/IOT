package cn.edu.bjtu.iot;

public class TDMA_Channel {
	public static int speed = 10;// ģ���ŵ���������
	public static String occupyChannelThreadName = "";// ռ���ŵ����߳�����

	public static void emptyChannel() {
		occupyChannelThreadName = "";
	}
}
