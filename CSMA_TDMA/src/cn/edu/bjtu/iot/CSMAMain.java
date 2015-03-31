package cn.edu.bjtu.iot;

public class CSMAMain {
	private static int callBackNum = 0;
	private static long beforeTime;
	private static long afterTime;
	private static IStatusCallBack callBack = new IStatusCallBack() {

		public void onTransferSuccess(String name) {
			callBackNum++;
			// ȫ���ص����
			if (callBackNum == 4) {
				afterTime = System.currentTimeMillis();
				long temp = 340 * 1000 / (afterTime - beforeTime);
				System.out.println("������:" + temp);
				System.out
						.println("�ŵ�������:"
								+ (afterTime - beforeTime - CSMA_Channel.totalEmpytTime)
								* 100 / (afterTime - beforeTime) + "%");
			}
		}
	};

	public static void main(String[] args) {
		beforeTime = System.currentTimeMillis();
		Thread csmaA = new CSMAThread(100, "Thread-A", callBack);
		Thread csmaB = new CSMAThread(80, "Thread-B", callBack);
		Thread csmaC = new CSMAThread(90, "Thread-C", callBack);
		Thread csmaD = new CSMAThread(70, "Thread-D", callBack);
		csmaA.start();
		csmaB.start();
		csmaC.start();
		csmaD.start();
	}
}
