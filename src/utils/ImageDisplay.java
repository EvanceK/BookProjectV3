package utils;


import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ImageDisplay  {
	
	public static ImageIcon createImageIcon(String imagePath, int width, int height) {
        InputStream is = ImageDisplay.class.getResourceAsStream(imagePath);
        
        if (is == null) {
            System.out.println("Couldn't find file: " + imagePath);
            // 返回預設圖標
            return createDefaultImageIcon(width, height);
        }
        
        try {
            Image img = ImageIO.read(is);
            if (img == null) {
                throw new IOException("Failed to read image from input stream");
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            // 返回預設圖標
            return createDefaultImageIcon(width, height);
        }
    }
	
	 private static ImageIcon createDefaultImageIcon(int width, int height) {
	        try {
	            String defaultImagePath = "/resources/noimage.jpg";
	            InputStream is = ImageDisplay.class.getResourceAsStream(defaultImagePath);
	            if (is != null) {
	                Image img = ImageIO.read(is);
	                Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	                return new ImageIcon(scaledImage);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        // 如果預設圖片也無法加載，返回空的 ImageIcon
	        return new ImageIcon();
	    }
	
	 public static ImageIcon loadImageIcon(String path, int width, int height) {
	        URL imgURL = ImageDisplay.class.getResource(path);
	        if (imgURL != null) {
	            ImageIcon icon = new ImageIcon(imgURL);
	            Image img = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
	            return new ImageIcon(img);
	        } else {
	            System.out.println("Couldn't find file: " + path);
	            // 使用默认图标
	            return new ImageIcon("C:\\Users\\speci\\IdeaProjects\\gjun01\\work\\BookProjectV3\\bin\\resources\\noimage.jpg"); 
	        }
	    }
	 
	 
	 
	 
}
