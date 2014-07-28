package opencv.usage;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

 
public class FaceDetector {
	private static BufferedImage bufferedImage;

	public FaceDetector(BufferedImage bufferedImage){

		this.bufferedImage = bufferedImage;
		
	}
	
    public static void main(String[] args) throws IOException {
 
    	//URL url = FaceDetector.class.getResource("/home/andrey/workspace/OpencvTest/bin/combine_images.jpg");
    	File img = new File("/home/andrey/workspace/OpencvTest/bin/combine_images.jpg");
    	bufferedImage = ImageIO.read(img);
    	
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\nRunning TestClass");

        byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        CascadeClassifier testClass = new CascadeClassifier(FaceDetector.class.getResource("haarcascade_frontalface_alt.xml").getPath());
       // Mat image = Highgui.	
        Mat image = new Mat(bufferedImage.getHeight(),bufferedImage.getWidth(), CvType.CV_8UC3);
        image.put(0, 0, pixels);

        MatOfRect faceDetections = new MatOfRect();
        testClass.detectMultiScale(image, faceDetections);
 
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
 
        for (Rect rect : faceDetections.toArray()) {
            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
        
        String filename = "ouput.png";
        System.out.println(String.format("Writing %s", filename));
        Highgui.imwrite(filename, image);
    }
}
