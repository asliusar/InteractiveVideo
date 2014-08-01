package opencv.usage;

import java.util.List;

public class FaceLinker extends Thread{

	private List<String> faceNames;
	private List<String> faceLinks;
	
	public FaceLinker(List<String> faceNames){
		this.faceNames = faceNames;
		faceLinks.add("qwe");
		faceLinks.add("rty");
	}
	
	@Override
	public void run(){
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("links are done!");
	}
	
	public List<String> GetLinks(){
		return faceLinks;
	}
	
}
