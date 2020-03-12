package queueOpt;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Casa extends Thread{
	private static int index=0;
	private int id;
	private int nr_clienti=0;
	private int total;
	//private int avgWait;
	String sir;
	Boolean ok=false;
	BlockingQueue<Client> rand;

	public Casa() {
	rand=new ArrayBlockingQueue<Client>(20);	
	id=index++;
	}

	//luam clientul din coada de la casa si il puneam sa doarma cat are timpul de procesare
	
	public void run() {
		while(true) {
		try {

			Client c=rand.peek();
			if (c!=null) {
			Thread.sleep(1000*c.getTdurata());
			c=rand.take();
			sir="Clientul "+c.getCid()+" a plecat de la casa "+ id;
			System.out.println("Clientul "+c.getCid()+" a plecat de la casa "+ id);
			nr_clienti--;
			}
			else
			{
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e) {}
		}
	}
	public String toString() {
		String s="";
		for (Client c:rand) {
			s+="<="+c.getCid();
		}
		return s;
	}
	public BlockingQueue<Client> getRand(){
		return this.rand;
	}
	public int getIdul() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNr_clienti() {
		return nr_clienti;
	}
	public String doString() {
		return sir;
	}

	public void setNr_clienti(int nr_clienti) {
		this.nr_clienti = nr_clienti;
	}
	public int getTotal() {
		return this.total;
	}
	public void adaugaRand(Client c) {
		rand.add(c);
		nr_clienti++;
		total++;
	}
	

}
