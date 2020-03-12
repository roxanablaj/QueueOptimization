package queueOpt;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Manager implements Runnable{
	BlockingQueue<Client> clientPool;//totalitatea clientilor pe care ii tratez
	ArrayList<Casa> casele;//casele la care se pot aseza clientii
	private int simulare=20;
	private int timp=0;
	private int minService=1;
	private int maxService=7;
	private int minSosire=1;
	private int maxSosire=15;
	private int noq=2;
   //private int moreClients=0;
	private int maxClients=-1;
	private int peakHour=-1;
	private int totalWaiting=0;
	private String[] QueuesSituation;
	View v;
	double avgWaiting=0;
	//private String stareLog;
	public Manager(View v,int simulare, int minService, int maxService, int minSosire, int maxSosire, int noq) {
		this.simulare=simulare;
		this.v=v;
		this.minService=minService;
		this.maxService=maxService;
		this.minSosire=minSosire;
		this.maxSosire=maxSosire;
		this.noq=noq;
		clientPool=new ArrayBlockingQueue<Client>(20);
		QueuesSituation=new String[noq];
		casele=new ArrayList<Casa>();
		Random r=new Random();
		int nr=r.nextInt(simulare);
		generareClienti(10);
		//aici imi creez casele
		for (int i=0; i<noq; i++) {
			Casa c=new Casa();
			casele.add(c);
		}
	}
	
	public void run() {
	
		//am dat start tuturo thread -urilor ce reprezinta case
		for (Casa c:casele) {
			c.start();
		}
		
		while(simulare>timp) {
			try {
				//stareLog="";
				int sum=0;
				for (Casa c: casele) {
					sum+=c.getNr_clienti();
				}
				if (sum>maxClients) {
					maxClients=sum;
					peakHour=timp;
				}
				
				Client cli=clientPool.peek();
				//casa la care adaug clientul
				Casa c=casele.get(getMin());
				
				if(cli!=null) {
					if (timp==cli.getTsosire()) {
						cli=clientPool.take();
						int waitingTime=0;
						//calculare timp asteptat de catre fiecare client(parcurgere clienti din fata lui)
						for (Client clt:c.getRand()) {
							waitingTime+=clt.getTdurata();
				}
				//clientul c este asezat la casa cu linia cea mai scurta
				if (cli.getRemaningtime()==0) {
					//pentru a modifica doar daca nu s-a calculat waiting time initial
					cli.setRemaningtime(waitingTime);
					totalWaiting+=waitingTime;
					}
				c.adaugaRand(cli);
			
				
				int index=0;
				for (Casa cas: casele) {
					QueuesSituation[index++]=cas.toString();
				}
				
				String qs="";
				for (int i=0; i<index; i++) {
					qs+=QueuesSituation[i]+"\n";
				}
				
				String info="";
				for(Casa cs:casele) {
					info=cs.doString();
						if (info!=null) {
					this.setText(info);
					}
				}
				
				info=info+"\nClientul "+cli.getCid()+" a ajuns la "+cli.getTsosire()+" a intrat la casa "+c.getIdul()+" cu size "+c.getNr_clienti()+"si sta pentru"+cli.getTdurata()+" are de asteptat "+cli.getRemaningtime();
				System.out.println("Clientul "+cli.getCid()+" a ajuns la "+cli.getTsosire()+" a intrat la casa "+c.getIdul()+" cu size "+c.getNr_clienti()+"si sta pentru"+cli.getTdurata()+" are de asteptat "+cli.getRemaningtime());
				System.out.println(qs);
				
				if (info!=null) {
				this.setText(info);
				};
				v.setQ1(QueuesSituation[0]);
				v.setQ2(QueuesSituation[1]);
				if (noq==3) {
				v.setQ3(QueuesSituation[2]);};
}
				Thread.sleep(1000);
			}
			}
			catch(InterruptedException e) {}
			timp++;
		}
		//trebuie sa modific campurile si dupa ce se termina simularea
		while(this.storeIsEmpty()!=1) {
		int index=0;
		for (Casa cas: casele) {
			QueuesSituation[index++]=cas.toString();
		}
		String qs="";
		for (int i=0; i<index; i++) {
			qs+=QueuesSituation[i]+"\n";
		}
		v.setQ1(QueuesSituation[0]);
		v.setQ2(QueuesSituation[1]);
		if (noq==3) {
		v.setQ3(QueuesSituation[2]);};
		try {
		Thread.sleep(1000);
		}catch(InterruptedException e) {};
		}
		if (this.storeIsEmpty()==1) {
			v.setQ1("");
			v.setQ2("");
			v.setQ3("");
		}
		//aici calculez average waiting time in toate casele
		int numarTotalClienti=0;
		//int asteptareTotala=0;
		for(Casa c: casele) {
			numarTotalClienti+=c.getTotal();}
			/*for (Client cl: c.getRand()) {
				asteptareTotala+=cl.getRemaningtime();
			}
		}*/
	   avgWaiting=(float)(1.0 * totalWaiting)/numarTotalClienti;
	   v.setAvgWait(String.format("%.3f", avgWaiting));
	   v.setPeakHour(Integer.toString(peakHour));
	   System.out.println("NOFC "+numarTotalClienti+" AsteptareT "+totalWaiting);
		System.out.println("Average witing time is"+avgWaiting);
		System.out.println("PeakHour is:"+peakHour);
	}
	
	//public static void main(String[] args) {
	//	Manager m=new Manager(10,1,5,1,35,3);
	//	Thread t=new Thread(m);
	//	t.start();
	//}
	public int getMin() {
		int min=21;
		int id=0;
		if (casele.size()!=0) {
		for (Casa c:casele) {
			if (c.getNr_clienti()<min) {
				min=c.getNr_clienti();
				id=c.getIdul();
			}
		}}
		return id;
	}
	public void  setText(String s) {
		v.setStareLog(s);
	}
	public int storeIsEmpty() {
		int sum=0;
		for (Casa cas: casele) {
			sum+=cas.getNr_clienti();
		}
		if (sum==0)
			return 1;
		return 0;
	}
	public void generareClienti(int n) {
		Random r=new Random();
		int timpAjuns=minSosire;
		for (int i=0; i<n; i++) {
			//Math.random()->pentru numere intre 0 si 1
			//int tsosire=r.nextInt(maxSosire-timpAjuns+1)+timpAjuns;
			int tsosire=timpAjuns++;
			int tprocesare=r.nextInt(maxService-minService+1)+minService;
			Client c=new Client(tsosire, tprocesare);
			//timpAjuns+=(tsosire-timpAjuns);
			clientPool.add(c);
		}
	}
	

}

