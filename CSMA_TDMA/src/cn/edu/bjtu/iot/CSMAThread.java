package cn.edu.bjtu.iot;

import java.util.Random;

public class CSMAThread extends Thread {
	private IStatusCallBack callBack;// ����ɹ��ص�
	private int dataCapacity;// ģ�⴫���������
	private int restDataCapacity;// ʣ����Ҫ�����������
	private String threadName; // �߳�����

	public CSMAThread(int dataCapacity, String threadName,
			IStatusCallBack callBack) {
		this.dataCapacity = dataCapacity;
		this.restDataCapacity = dataCapacity;
		this.threadName = threadName;
		this.callBack = callBack;
	}

	// ������ָ���˱��㷨��س�Ա����
	private int backCount = 0; // (���˴���)������ͻ�Ĵ���

	public void run() {
		super.run();
		while (restDataCapacity != 0) {
			// ���Դ�������16��,����ʧ��
			if (backCount == 16) {
				System.out.println("����ʧ��");
			}
			// �ж��ŵ��Ƿ����ڱ�ʹ��
			if (!CSMA_Channel.occupyChannelThreadName.equals(threadName)
					&& !"".equals(CSMA_Channel.occupyChannelThreadName)) {
				// �ŵ���ռ��,����ȴ�ʱ��
				backCount++;
				int sleepTime = getSleepTime();
				System.out.println(threadName + ": ��" + backCount
						+ "�γ��ԣ�������ͻ,˯�� " + sleepTime + "s");
				try {
					sleep(sleepTime * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				CSMA_Channel.occupyChannelThreadName = threadName;
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// ģ�⴫��ʣ������
				restDataCapacity = restDataCapacity - CSMA_Channel.speed;
				System.out.println(threadName + " ������ "
						+ (dataCapacity - restDataCapacity));
				if (restDataCapacity == 0) {
					System.out.println(threadName + " �������!");
					// �������
					CSMA_Channel.emptyChannel();
					callBack.onTransferSuccess(threadName);
				}
			}
		}
	}

	// ����ȴ�ʱ��
	private int getSleepTime() {
		Random r = new Random();
		return r.nextInt((int) Math.pow(2, backCount));
	}
}
