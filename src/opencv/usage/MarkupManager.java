package opencv.usage;

import java.util.List;

import org.opencv.core.Rect;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class MarkupManager extends Thread {
	List<Rect> rectList;
	List<String> faceLinkList;
	double secondOfSnapShot;
	
	public MarkupManager( List<Rect> list, List<String> faceLinkList, double secondOfSnapShot ) throws InterruptedException{
		this.rectList = list;
		this.faceLinkList = faceLinkList;
		this.secondOfSnapShot = secondOfSnapShot;
		
		setDaemon(true);
		sleep(1000);
		System.out.println("1");
	}
	
	public void run(){
		try {
			sleep(500);
			boolean b =  isDaemon();
			System.out.println("2 = " + b);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
}
