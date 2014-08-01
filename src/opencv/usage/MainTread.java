package opencv.usage;

public class MainTread {
	
	public static void main(String argc[]) throws InterruptedException{
	
		VideoManager videoManager = new VideoManager("/home/andrey/Videos/SpaceBrothers-32.avi", 
				"/home/andrey/Videos/Snapshots/");
		videoManager.start();
		
		// the end of video
		while(videoManager.mediaReader.isOpen() == true){
			//videoManager.run();
			
			//FaceClassifier faceClassifier  = new FaceClassifier(videoManager.bufferedImage);
			//faceClassifier.start();
	
			//FaceComparator faceComparator = new FaceComparator(faceClassifier.getCropFaces());
			//faceComparator.start();	
			
			//FaceLinker faceLinker = new FaceLinker(faceComparator.getActorsNames());
			//faceLinker.start();
				
			//MarkupManager markupManager = new MarkupManager(faceClassifier.getRectFaces(),faceLinker.GetLinks(), 10);
			//	markupManager.start();
		}	
	}
}
