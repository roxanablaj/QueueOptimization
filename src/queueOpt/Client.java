package queueOpt;

public class Client {
	private static int ind=0;
	private int cid;
	private int tsosire;
	private int tprocesare;
	private int remainigTime=0;
	
	public int getRemaningtime() {
		return this.remainigTime;
	}
	
	public void setRemaningtime(int timp) {
		this.remainigTime=timp;
	}
	//public int getTasteptare() {
	//	return tasteptare;
	//}
	//public void setTasteptare(int timp) {
	//	this.tasteptare=timp;
	//}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getTsosire() {
		return tsosire;
	}
	public void setTsosire(int tsosire) {
		this.tsosire = tsosire;
	}
	public int getTdurata() {
		return tprocesare;
	}
	public void setTdurata(int tdurata) {
		this.tprocesare = tdurata;
	}
	public Client( int tsosire, int tdurata) {
		this.cid=ind++;
		this.tsosire=tsosire;
		this.tprocesare=tdurata;
	}

}
