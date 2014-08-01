package opencv.usage;

import java.util.List;

import org.opencv.core.Rect;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class MarkupManager {
	List<Rect> rectList;
	List<String> faceLinkList;
	double secondOfSnapShot;
	
	public MarkupManager( List<Rect> list, List<String> faceLinkList, double secondOfSnapShot ) throws InterruptedException{
		this.rectList = list;
		this.faceLinkList = faceLinkList;
		this.secondOfSnapShot = secondOfSnapShot;
		
		//setDaemon(true);
		System.out.println("1");
	}
	
	public void run(){
		System.out.print("Murk is done");
			
		
		
	}
}
