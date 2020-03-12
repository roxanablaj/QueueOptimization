package queueOpt;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


//view va fi si ascultator
public class View extends JFrame implements ActionListener{
	
	Manager man;
	private JLabel minSos=new JLabel("Minimum Arrival Time:");
	private JLabel maxSos=new JLabel("Maximum Arrival Time");
	private JLabel minServ=new JLabel("Minimum Service Time:");
	private JLabel maxServ=new JLabel("Maximum Service Time:");
	private JLabel noq=new JLabel("Number of queues:");
	private JLabel simIntervals=new JLabel("Simulation Interval:");
	private JTextField tminSos=new JTextField(6);
	private JTextField tmaxSos=new JTextField(6);
	private JTextField tminServ=new JTextField(6);
	private JTextField tmaxServ=new JTextField(6);
	private JTextField tnoq=new JTextField(6);
	private JTextField tsimIntervals=new JTextField(6);
	private JTextField avgWait=new JTextField(6);
	private JTextField peakHour=new JTextField(6);
	private JTextField q1=new JTextField(6);
	private JTextField q2=new JTextField(6);
	private JTextField q3=new JTextField(6);
	private JLabel log=new JLabel("Arriving situation");
	private JLabel q=new JLabel("Queues situation");
	private JTextArea stareLog=new JTextArea(50,40);
	private JTextArea stareq=new JTextArea();
	private JButton start=new JButton("Start");
	
	public View() {
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p1.add(minSos);
		p1.add(tminSos);
		p1.add(maxSos);
		p1.add(tmaxSos);
		p1.add(minServ);
		p1.add(tminServ);
		p1.add(maxServ);
		p1.add(tmaxServ);
		p1.add(noq);
		p1.add(tnoq);
		p1.add(simIntervals);
		p1.add(tsimIntervals);
		p1.add(start);
		p1.add(new JLabel("Output"));
		p1.add(new JLabel("Average Waiting Time:"));
		p1.add(avgWait);
		p1.add(new JLabel("Peak Hour:"));
		p1.add(peakHour);
		p1.setSize(new Dimension(100,50));
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		p2.add(log);
		stareLog.setEditable(false);
		JScrollPane scroll=new JScrollPane(stareLog);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		p2.add(scroll);

		JPanel p3=new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
		p3.add(q);
		p3.add(new JLabel("Queue 1:"));
		p3.add(q1);
		p3.add(new JLabel("Queue 2:"));
		p3.add(q2);
		p3.add(new JLabel("Queue 3:"));
		p3.add(q3);
		JPanel sus=new JPanel();
		sus.setLayout(new FlowLayout());
		sus.add(p1);
		sus.add(p2);
		JPanel total=new JPanel();
		total.setLayout(new BoxLayout(total,BoxLayout.Y_AXIS));
		total.add(sus);
		total.add(p3);
		start.addActionListener(this);
		
		this.setContentPane(total);
		this.setVisible(true); 
		this.setSize(800, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Simulator");
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String mins=getTminServ();
		String maxs=getTmaxServ();
		String minso=getTminSos();
		String maxso=getTmaxSos();
		String simulare=getTsimIntervals();
		String stq=getStareq();
		String stl=getStareLog();
		String noqq=getTnoq();
		man=new Manager(this,Integer.parseInt(simulare),Integer.parseInt(mins),Integer.parseInt(maxs),Integer.parseInt(minso),Integer.parseInt(maxso),Integer.parseInt(noqq));
		Thread t=new Thread(man);
		t.start();
	}
	public void startButtonListener(ActionListener a) {
		start.addActionListener(a);
	}
	public void setQ1(String s) {
		this.q1.setText(s);
	}
	public void setQ2(String s) {
		this.q2.setText(s);
	}
	public void setQ3(String s) {
		this.q3.setText(s);
	}
	public void setPeakHour(String ora) {
		this.peakHour.setText(ora);
	}
	public void setAvgWait(String medie) {
		this.avgWait.setText(medie);
	}
	
	public void setTminSos(String tminSos) {
		this.tminSos.setText(tminSos);
	}
	
	public String getTminSos() {
		return tminSos.getText();
	}
	
	public void setTmaxSos(String tmaxSos) {
		this.tmaxSos.setText(tmaxSos);
	}
	
	public String getTmaxSos() {
		return tmaxSos.getText();
	}
	public String getTminServ() {
		return tminServ.getText();
	}

	public void setTminServ(String tminServ) {
		this.tminServ.setText(tminServ);
	}

	public String getTmaxServ() {
		return tmaxServ.getText();
	}

	public void setTmaxServ(String tmaxServ) {
		this.tmaxServ.setText(tmaxServ);
	}

	public String getTnoq() {
		return tnoq.getText();
	}

	public void setTnoq(String tnoq) {
		this.tnoq.setText(tnoq);
	}

	public String getTsimIntervals() {
		return tsimIntervals.getText();
	}

	public void setTsimIntervals(String tsimIntervals) {
		this.tsimIntervals.setText(tsimIntervals);
	}

	public String getStareLog() {
		return stareLog.getText();
	}

	public void setStareLog(String stareLog) {
		this.stareLog.append("\n");
		this.stareLog.append(stareLog);
	}

	public String getStareq() {
		return stareq.getText();
	}

	public void setStareq(String stareq) {
		this.stareq.setText(stareq);
	}
	
	public static void main(String[] args) {
		JFrame f=new View();
	}

	

}
