package opencv.usage;
import java.awt.Color;

public class Pixel
{
	public Pixel(Color rgb) {
		// TODO Auto-generated constructor stub
		all = rgb.getRGB();
        //Red
        r=rgb.getRed();
        //Green
        g =rgb.getGreen();
        //Blue
        b = rgb.getBlue();
        mid=(r+b+g)/3;
        black=false;
	}
	public void replace(Pixel in)
	{
		r=in.r;
		g=in.g;
		all=in.all;
		b=in.b;	
		black=in.black;
	}
	public int r,g,b;
	public int all;
	public int mid;
	public boolean black;
}