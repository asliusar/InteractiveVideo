package opencv.usage;


public class MainTread {
	
	public static void main(String argc[]) throws InterruptedException{
	
		VideoManager videoManager = new VideoManager("/home/dima/Videos/SpaceBrothers-32.avi","/home/dima/Videos/Snapshots/");
		videoManager.run();
				
		// the end of video
		while(videoManager.mediaReader.isOpen() == true){
			videoManager.run();
			
			FaceClassifier faceClassifier = new FaceClassifier(videoManager.bufferedImage);
				faceClassifier.run();
				
			FaceComparator faceComparator = new FaceComparator(faceClassifier.getCropFaces());
				
			FaceLinker faceLinker = new FaceLinker(faceComparator.getActorsNames());
				
			MarkupManager markupManager = new MarkupManager(faceClassifier.getRectFaces(),faceLinker.GetLinks(), 10);
				markupManager.start();
		}
		
	}
}
