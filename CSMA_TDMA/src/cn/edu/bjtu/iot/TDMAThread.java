package cn.edu.bjtu.iot;

public class TDMAThread extends Thread {

	private final static int SLOT_A = 1024;
	private final static int SLOT_B = 1025;
	private int currentTimeSlot = SLOT_A; // ��ǰʱ��Ƭ��ת��������
	TDMA A = new TDMA(100, "TDMA_A");
	TDMA B = new TDMA(80, "TDMA_B");

	public void run() {
		super.run();
		while (A.getRestDataCapacity() != 0 || B.getRestDataCapacity() != 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (currentTimeSlot == SLOT_A) {
				currentTimeSlot = SLOT_B;
				A.sendATimeSlot();
			} else if (currentTimeSlot == SLOT_B) {
				currentTimeSlot = SLOT_A;
				B.sendATimeSlot();
			}
		}
	}
}
