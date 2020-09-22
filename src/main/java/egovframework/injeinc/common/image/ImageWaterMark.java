package egovframework.injeinc.common.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageWaterMark {

	/**
	 *  text 삽입
	 * @param text
	 * @param sourceImageFile
	 * @param destImageFile
	 */
	public static void addTextWaterMark(String text, File sourceImageFile, File destImageFile) {
		try {
			BufferedImage sourceImage = ImageIO.read(sourceImageFile);
			Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
			
			AlphaComposite alpahChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
			g2d.setComposite(alpahChannel);
			g2d.setColor(Color.CYAN);
			g2d.setFont(new Font("Verdana", Font.BOLD, 20));
			
			FontMetrics fontMetrics = g2d.getFontMetrics();
			Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);
			
			int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
			int centerY = sourceImage.getHeight()/2;
			
			g2d.drawString(text, centerX, centerY);
			
			ImageIO.write(sourceImage, "png", destImageFile);
			g2d.dispose();
			
			System.out.println("Text watermark added to the image");
			
		} catch(IOException e) {
			System.err.println(e);
		}
	}
	
	/**
	 *  watermark 삽입
	 * @param watermarkImageFile
	 * @param sourceImageFile
	 * @param destImageFile
	 */
	public static void addImageWaterMark(File watermarkImageFile, File sourceImageFile, File destImageFile) {
		try {
			BufferedImage sourceImage = ImageIO.read(sourceImageFile);
			BufferedImage watermarkImage = ImageIO.read(watermarkImageFile);
			
			Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
			AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
			g2d.setComposite(alphaChannel);
			
			// 오른쪽 하단
			int topLeftX = (sourceImage.getWidth() - watermarkImage.getWidth()) / 2;
			int topLeftY = (sourceImage.getHeight() - watermarkImage.getHeight()) / 2;
			
//			int topLeftX = sourceImage.getWidth();
//			int topLeftY = sourceImage.getHeight();
			
			int coordiX = watermarkImage.getWidth();
			int coordiY = watermarkImage.getHeight();
			
			if (sourceImage.getWidth() > coordiX && sourceImage.getHeight() > coordiY) {
				//  좌표이동
				g2d.translate(topLeftX, topLeftY);
				// drawImage 대상 이미지, x좌표, y좌표, 폭, 높이
				g2d.drawImage(watermarkImage, topLeftX, topLeftY, coordiX, coordiY, null);
				ImageIO.write(sourceImage, "png", destImageFile);
				g2d.dispose();
				
				System.out.println("Watermark is added to the Image");
			} else {
				System.out.println("이미지의 크기가 부적합합니다.");
			}
		} catch(IOException e) {
			System.err.println(e);
		}
	}
	
}