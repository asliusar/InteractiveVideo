package opencv.usage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StartActivity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//while()
		Path currentRelativePath = Paths.get("");
		String currentAbsolutePath = currentRelativePath.toAbsolutePath().toString();
		String[] parts = currentAbsolutePath.split("/");
		String curUserDir = "/"+parts[1]+"/"+parts[2];
		//System.out.println(curUserDir+"/Videos/videoTest1.mp4");
		VideoManager videoManager = new VideoManager(curUserDir+"/Videos/videoTest1.mp4", 
				curUserDir+"/Videos/Snapshots/");
		//videoManager.SplitVideo();
		
		
	}

}
