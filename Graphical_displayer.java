package spiral;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

public class Graphical_displayer extends Frame implements ActionListener, ItemListener{
	Generator[] g=new Generator[2];private int i=0;
	private Checkbox[] ch=new Checkbox[2];
	private Active_canvas a;
	private Button[] b=new Button[2];
	private LinkedList<Arch> list1=new LinkedList<>();
	class Active_canvas extends Canvas implements Runnable{
		private Thread myThread=new Thread(this);
		private int working;
		Active_canvas(){myThread.start();}
		public void run() {try {
			double o=0;
			while(!Thread.interrupted() && o<30) {
				synchronized (myThread) {if(working==0)myThread.wait();}
				int gg=g[i].get_value();
				Arch l=new Arch(new Point_2D(getWidth()/2,getHeight()/2),gg,o, o+=Math.PI/2 , this);
				list1.add(l);
				for (int i = 0; i <list1.size(); i++) {
					list1.get(i).w_start();
				}
				synchronized (myThread) {while(!l.isfinished())myThread.wait(2);}
			}
		}catch (InterruptedException e) {}
		}
		public void w_start() {synchronized (myThread) {working=1;myThread.notify();}}
		public void w_stop() {synchronized (myThread) {working=0;}}
		public void w_interrupt() {myThread.interrupt();}
	}
	public Graphical_displayer() {
		super("Spirals");setBounds(500,100,400,370);
		g[0]=new Fibonacci_generator();
		g[1]=new Triangular_generator();
		CheckboxGroup cg=new CheckboxGroup();
		a=new Active_canvas();a.setSize(400,250);a.setBackground(Color.LIGHT_GRAY);
		Panel hd=new Panel();
		Panel cnt=new Panel();
		Panel ft=new Panel();
		b[0]=new Button("Draw");b[0].addActionListener(this);
		b[1]=new Button("Stop");b[1].addActionListener(this);
		ch[0]=new Checkbox("Fibonacci progression",cg,true);
		ch[1]=new Checkbox("Triangular progression",cg,false);
		hd.add(ch[0]);ch[1].addItemListener(this);
		hd.add(ch[1]);ch[0].addItemListener(this);
		ft.add(b[0]);
		ft.add(b[1]);
		cnt.add(a);
		add(hd,BorderLayout.NORTH);
		add(ft,BorderLayout.SOUTH);
		add(cnt,BorderLayout.CENTER);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Draw")) {
			i=ch[0].getState()?0:1;
			a.w_start();
		}
		else if(arg0.getActionCommand().equals("Stop")) {
			a.w_stop();
		}
	}
	public static void main(String[] args) {
		Graphical_displayer g=new Graphical_displayer();
	}
	public void itemStateChanged(ItemEvent arg0) {
		synchronized (a.myThread) {
		if (ch[0].getState()) { i=0;}
		else i=1;
		}
	}
}





