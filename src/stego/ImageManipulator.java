/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * By far the best class in this program.
 * This class works with all images, it helps create images, encrypt them,
 * and decrypt them. The actual methodology will be slightly explained.
 * @author vanvari
 */
public class ImageManipulator {
    int bitShifter;
    int base;
    int factor;
    /**
     * Creates a generic manupulator that can encrypt and decrypt with amount of
     * bits to be changed explained by bts.
     * @param bts amount of bits to shift in encrypt and decrypt.
     */
    public ImageManipulator(int bts){
        bitShifter = bts;
        base = (int)Math.pow(2,bts);
        factor = (int)Math.pow(2,(8-bts));
    }
    /**
     * This was written with the help of stackOverflow.
     * It changes an image to a bufferedImage.
     * @param img image to be changed
     * @return buffered image version of img.
     */
    public BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
    /**
     * loads image from filepath
     * @param filePath The file path to the actual location of the image to be loaded.
     * @return loaded image.
     */
    public BufferedImage loadImage(String filePath){
        BufferedImage publicImg = null;
        try{
            publicImg = ImageIO.read(new File(filePath));
        }
        catch(Exception e){
        }
        return publicImg;
    }
    /**
     * changes the secret image by placing the new decrypted image there.
     * Gets the pixel color at each point and then gets the component amount.
     * @param cover The public image
     * @param secret The image that will have the new shifted 
     */
    public void decrypt(BufferedImage cover, BufferedImage secret){
       secret = toBufferedImage(secret.getScaledInstance(cover.getWidth(), cover.getHeight(), BufferedImage.SCALE_FAST));
       for( int i = 0; i < cover.getWidth(); i++ ){
            for( int j = 0; j < cover.getHeight(); j++ ){
                Color c = new Color(cover.getRGB(i,j));
                int red = c.getRed()%base *factor;
                int gre = c.getGreen()%base *factor;
                int blu = c.getBlue()%base*factor;
                int[] clors = {red,gre,blu};
                try{
                    secret.getRaster().setPixel(i,j,clors);
                }
                catch(Exception E){
                }
            }
        }     
    }
    /**
     * Clears lowest/least significant bits based on the bitshift stored in this.
     * Hides in the reduced pixels of secret image
     * @param pub Public Image to be hidden in.
     * @param secret Secret Image to be reduced.
     * @param nimg New Image to be written on.
     */
    public void encrypt(BufferedImage pub, BufferedImage secret, BufferedImage nimg){
       secret = toBufferedImage((secret.getScaledInstance(pub.getWidth(),pub.getHeight(), BufferedImage.SCALE_SMOOTH)));
       for( int i = 0; i < pub.getWidth(); i++ ){
            for( int j = 0; j < pub.getHeight(); j++ ){
                Color secretC = new Color(secret.getRGB(i,j));
                Color realC = new Color(pub.getRGB(i,j));
                int[] clors = new int[3];
                int r=(secretC.getRed()/factor);
                int g=(secretC.getGreen()/factor);
                int b=(secretC.getBlue()/factor);
                clors[0] = (realC.getRed()/base)*base + r;
                clors[1] = (realC.getGreen()/base)*base+ g;
                clors[2]= (realC.getBlue()/base)*base + b;
                try{
                    nimg.getRaster().setPixel(i,j,clors);
                }
                catch(Exception E){
                    System.out.println("encrypt error");
                }
            }
        }                
    }
    /**
     * Returns a blank white Image. Mainly used to construct program. 
     * @param img Image to be made white
     * @return White image.
     */
    public BufferedImage whiteImage(BufferedImage img){
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        return img;
    }

}
