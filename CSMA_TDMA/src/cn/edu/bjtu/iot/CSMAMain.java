package cn.edu.bjtu.iot;

public class CSMAMain {
	private static int callBackNum = 0;
	private static long beforeTime;
	private static long afterTime;
	private static IStatusCallBack callBack = new IStatusCallBack() {

		public void onTransferSuccess(String name) {
			callBackNum++;
			// ȫ���ص����
			if (callBackNum == 2) {
				afterTime = System.currentTimeMillis();
				long temp = 180 * 1000 / (afterTime - beforeTime);
				System.out.println("��������" + temp);
				System.out.println("�ӳ�:" + (afterTime - beforeTime)/1000);
			}
		}
	};

	public static void main(String[] args) {
		beforeTime = System.currentTimeMillis();
		Thread csmaA = new CSMAThread(100, "Thread-A", callBack);
		Thread csmaB = new CSMAThread(80, "Thread-B", callBack);
		csmaA.start();
		csmaB.start();
	}
}
