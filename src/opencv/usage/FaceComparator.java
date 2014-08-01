package opencv.usage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FaceComparator {

	private List<BufferedImage> bufferedImages;
	private List<String> actorsName;
	
	public FaceComparator(List<BufferedImage> bufferedImages) {
	
		this.bufferedImages = bufferedImages;
		actorsName = new ArrayList<String>();
		actorsName.add("brian");
		actorsName.add("jim");
	}
	
	public void run(){
		System.out.println("compare is done!");
	}
	
	public List<String> getActorsNames(){
		return actorsName;
	}
}
