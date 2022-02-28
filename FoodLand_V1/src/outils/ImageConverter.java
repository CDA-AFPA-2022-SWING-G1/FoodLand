package outils;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.Toolkit;

public class ImageConverter {

	ImageIcon icon;
	
	public ImageIcon imageConverter(BufferedImage originalImage , int h, int w) {
		
		//Image img = Toolkit.getDefaultToolkit().createImage(source);
		//BufferedImage originalImage =  new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
//	    Graphics2D bGr = originalImage.createGraphics();
//	    bGr.drawImage(img, 0, 0, null);
//	    bGr.dispose();

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int x, y, cropWidth, cropHeight;
        if(width > height) {
            x = (width - height) / 2;
            y = 0;
            cropWidth = cropHeight = height;
        } else {
            x = 0;
            y = (height - width) / 2;
            cropWidth = cropHeight = width;
        }

        BufferedImage resizedImage = crop(originalImage, x, y, cropWidth, cropHeight);

		//System.out.println(resizedImage);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
	private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
	      BufferedImage dest = src.getSubimage(src.getWidth()/2, src.getHeight()/2, rect.width, rect.height);
	      return dest; 
	   }
	
	
	public BufferedImage crop(BufferedImage src, int x, int y,
			int width, int height)
			throws IllegalArgumentException {
    long t = -1;
    
      t = System.currentTimeMillis();

		if (src == null)
			throw new IllegalArgumentException("src cannot be null");
		if (x < 0 || y < 0 || width < 0 || height < 0)
			throw new IllegalArgumentException("Invalid crop bounds: x [" + x
					+ "], y [" + y + "], width [" + width + "] and height ["
					+ height + "] must all be >= 0");

		int srcWidth = src.getWidth();
		int srcHeight = src.getHeight();

		if ((x + width) > srcWidth)
			throw new IllegalArgumentException(
					"Invalid crop bounds: x + width [" + (x + width)
							+ "] must be <= src.getWidth() [" + srcWidth + "]");
		if ((y + height) > srcHeight)
			throw new IllegalArgumentException(
					"Invalid crop bounds: y + height [" + (y + height)
							+ "] must be <= src.getHeight() [" + srcHeight
							+ "]");


		// Create a target image of an optimal type to render into.
		BufferedImage result = createOptimalImage(src, width, height);
		Graphics g = result.getGraphics();

		/*
		 * Render the region specified by our crop bounds from the src image
		 * directly into our result image (which is the exact size of the crop
		 * region).
		 */
		g.drawImage(src, 0, 0, width, height, x, y, (x + width), (y + height),
				null);
		g.dispose();

		// Apply any optional operations (if specified).
//		if (ops != null && ops.length > 0)
//			result = apply(result, ops);

		return result;
	}
	
	
	private BufferedImage createOptimalImage(BufferedImage src,
			int width, int height) throws IllegalArgumentException {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("width [" + width
					+ "] and height [" + height + "] must be > 0");

		return new BufferedImage(
				width,
				height,
				(src.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB
						: BufferedImage.TYPE_INT_ARGB));
	}
	
	
	public BufferedImage ByteToBuffImage(byte[] b) throws IOException {
		BufferedImage bf  = ImageIO.read(new ByteArrayInputStream(b));
		return bf;
	}
	
}
