package opencv.usage;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class MainTread {
	public static void main(String argc[]) throws InterruptedException, IOException{

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
		Mat image = new Mat();
		//image = Highgui.imread("res/img2.jpg");
		FaceClassifier C = new FaceClassifier();
		//C.findFace(image);
		
		//if (0==0) return;
		
		VideoManager videoManager = new VideoManager("/home/sasha/Videos/videoTest2.mp4","/home/sasha/Videos/Snapshots/");
		videoManager.run();
		
			// the end of video
		
		FaceClassifier faceClassifier = new FaceClassifier(videoManager.bufferedImage);		

		while(videoManager.mediaReader.isOpen() == true){
			faceClassifier.reloadImg(videoManager.bufferedImage);
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
