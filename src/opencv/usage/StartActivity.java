package opencv.usage;

public class StartActivity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//while()
		
		VideoManager videoManager = new VideoManager("/home/dima/Videos/SpaceBrothers-32.avi", 
				"/home/dima/Videos/Snapshots/");
		videoManager.SplitVideo();
		
		
	}

}
