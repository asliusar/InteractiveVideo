package opencv.usage;


public class MainTread {
	
	public static void main(String argc[]) throws InterruptedException{
	
		VideoManager videoManager = new VideoManager("/home/dima/Videos/SpaceBrothers-32.avi","/home/dima/Videos/Snapshots/");
		videoManager.run();
				
		// the end of video
		while(videoManager.mediaReader.isOpen() == true){
					
			FaceClassifier faceClassifier = new FaceClassifier(videoManager.bufferedImage);
				faceClassifier.run();
				
			videoManager.run();	
			
			faceClassifier.join();
				
			FaceComparator faceComparator = new FaceComparator(faceClassifier.getCropFaces());
				faceComparator.run();
			
			FaceLinker faceLinker = new FaceLinker(faceComparator.getActorsNames());
				faceLinker.run();	
			
			MarkupManager markupManager = new MarkupManager(faceClassifier.getRectFaces(),faceLinker.GetLinks(), 10);
				markupManager.run();
		}
		
	}
}
