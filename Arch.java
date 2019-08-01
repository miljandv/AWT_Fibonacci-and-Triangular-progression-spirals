package spiral;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Arch extends Thread{
	Point_2D center;
	int finish;
	LinkedList<Point_2D> list=new LinkedList<>();
	double r,anglestart,anglefinish;
	Canvas canvas;
	public Arch(Point_2D centerPoint_2d,double radius,double startangle,double endangle,Canvas c) {
		center=centerPoint_2d;r=radius;anglefinish=endangle;anglestart=startangle;canvas=c;
	start();}
	public Point_2D get_Point_2d(double d){try {
		if(d<anglestart || d>anglefinish)throw new G_angle();
		return new Point_2D(center.getX()+r*Math.cos(d),center.getY()-r*Math.sin(d));
	}catch (G_angle e) {System.err.println(e);}
	return null;
	}
	private double cnt;
	private int working;
	private void draw() {
		if (cnt<anglefinish) {
		Graphics g=canvas.getGraphics();
		g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		list.add(get_Point_2d(cnt));
		for (int i = 0; i < list.size()-1; i++) {
			g.drawLine((int)list.get(i).getX(),(int) list.get(i).getY(), (int)list.get(i+1).getX(), (int)list.get(i+1).getY());
		}
		}
		
	}
	public void run() {try{
		cnt=anglestart;
		while (!interrupted()) {
			synchronized (this) {if(working==0)wait();}
			draw();
			cnt+=2*Math.PI/1000;
			sleep(3);
			if(cnt>=anglefinish) {finish=1;w_stop();}
		}
		
	}catch (InterruptedException e) {}
	}
	public synchronized void w_start() {working=1;notify();}
	public synchronized void w_stop() {working=0;}
	public synchronized boolean isfinished() {return finish==1;}
}


