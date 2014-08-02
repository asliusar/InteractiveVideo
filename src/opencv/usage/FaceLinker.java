package opencv.usage;

import java.util.ArrayList;
import java.util.List;

public class FaceLinker {

	private List<String> faceNames;
	private List<String> faceLinks;
	
	public FaceLinker(List<String> faceNames){
		this.faceNames = faceNames;
		faceLinks = new ArrayList<String>();
		faceLinks.add("qwe");
		faceLinks.add("rty");
	}
	
	public void run(){
		
		System.out.println("links are done!");
	}
	
	public List<String> GetLinks(){
		return faceLinks;
	}
	
}
