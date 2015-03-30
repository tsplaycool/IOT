package cn.edu.bjtu.iot;

public class TDMA {

	private int dataCapacity;// ģ�⴫���������
	private int restDataCapacity;// ʣ����Ҫ�����������
	private String threadName; // �߳�����

	public TDMA(int dataCapacity, String threadName) {
		this.setDataCapacity(dataCapacity);
		this.setRestDataCapacity(dataCapacity);
		this.setThreadName(threadName);
	}

	// ������һ��ʱ��Ƭ
	public void sendATimeSlot() {
		System.out.print("ʱ��Ƭ��ת���� " + threadName);
		restDataCapacity = restDataCapacity - 10;
		if (restDataCapacity < 0) {
			restDataCapacity = 0;
		}
		if (restDataCapacity == 0) {
			System.out.println(" ,�������!");
		} else {
			System.out.println(" ,�����ˣ�" + (dataCapacity - restDataCapacity));
		}
	}

	public int getDataCapacity() {
		return dataCapacity;
	}

	public void setDataCapacity(int dataCapacity) {
		this.dataCapacity = dataCapacity;
	}

	public int getRestDataCapacity() {
		return restDataCapacity;
	}

	public void setRestDataCapacity(int restDataCapacity) {
		this.restDataCapacity = restDataCapacity;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}
