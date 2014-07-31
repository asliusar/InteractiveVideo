package opencv.usage;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.text.Position.Bias;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IRational;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;


public class VideoManager {
	
	private static String inputFilename = "/home/andrey/Videos/SpaceBrothers-32.avi";
	private static String outputFilePrefix = "/home/andrey/Videos/Snapshots/";
	private static BufferedImage bufferedImage;
    //kto-to rak
    public static double SECONDS_BETWEEN_FRAMES ;
    
    
    // The video stream index, used to ensure we display frames from one and
    // only one video stream from the media container.
    private static int mVideoStreamIndex = -1;
    
    // Time of last frame write
    private static long mLastPtsWrite = Global.NO_PTS;
    
    public static  long MICRO_SECONDS_BETWEEN_FRAMES;

    public VideoManager(String inputFilename, String outputFilePrefix){
    	this.inputFilename = inputFilename;
    	this.outputFilePrefix = outputFilePrefix;
    	SECONDS_BETWEEN_FRAMES = 3/GetVideoFrames(inputFilename);
    	MICRO_SECONDS_BETWEEN_FRAMES = 
    	        (long)(Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
    }
    
    public BufferedImage SplitVideo(){

    	GetVideoFrames(inputFilename);
    	
        IMediaReader mediaReader = ToolFactory.makeReader(inputFilename);

        // stipulate that we want BufferedImages created in BGR 24bit color space
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
        ImageSnapListener imageSnapListener = new ImageSnapListener();
        mediaReader.addListener(imageSnapListener);
        // read out the contents of the media file and
        // dispatch events to the attached listener
        	
        	
        while (mediaReader.readPacket() == null){
        	if(bufferedImage != null)
        		bufferedImage = imageSnapListener.bufferedImage;	
        }
        
        return bufferedImage;
    }

    public static double GetVideoFrames(String inputFileName) {
    	IContainer container = IContainer.make();
    	int result = container.open(inputFileName, IContainer.Type.READ, null);
    	IStream stream = container.getStream(0);
    	IStreamCoder hodor = stream.getStreamCoder();
    	int frameRate = (int)(hodor.getFrameRate().getDouble()+0.5);
    	return frameRate;
    }
    
    private static class ImageSnapListener extends MediaListenerAdapter {

    	public BufferedImage bufferedImage;
    	
        public void onVideoPicture(IVideoPictureEvent event) {

            if (event.getStreamIndex() != mVideoStreamIndex) {
                // if the selected video stream id is not yet set, go ahead an
                // select this lucky video stream
                if (mVideoStreamIndex == -1)
                    mVideoStreamIndex = event.getStreamIndex();
                // no need to show frames from this video stream
                else
                    return;
            }

            // if uninitialized, back date mLastPtsWrite to get the very first frame
            if (mLastPtsWrite == Global.NO_PTS)
                mLastPtsWrite = event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES;

            // if it's time to write the next frame
            if (event.getTimeStamp() - mLastPtsWrite >= 
                    MICRO_SECONDS_BETWEEN_FRAMES) {
                
            bufferedImage = event.getImage();
                String outputFilename = dumpImageToFile(event.getImage());
                
                // indicate file written
                double seconds = ((double) event.getTimeStamp()) / 
                    Global.DEFAULT_PTS_PER_SECOND;
                System.out.printf(
                        "at elapsed time of %6.3f seconds wrote: %s\n",
                        seconds, outputFilename);

                // update last write time
                mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
            }
            //return event.getImage();
        }
        
        private String dumpImageToFile(BufferedImage image) {
            try {
                String outputFilename = outputFilePrefix + 
                     System.currentTimeMillis() + ".png";
                ImageIO.write(image, "png", new File(outputFilename));
                return outputFilename;
            } 
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

}
