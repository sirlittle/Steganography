/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stego;

import java.awt.image.BufferedImage;    

/**
 * This class is mainly used to encode and decode strings. There is no need for 
 * a constructor since it has a fixed encode/decode.
 * @author vanvari
 */
public class StringManipulator {
    /**
     * encodes secret text into image.
     * @param pub original image to be encoded into.
     * @param secret text to be encoded.
     * @param nimg new image to have encoded image on it.
     */
    public void encode(BufferedImage pub, String secret, BufferedImage nimg){
        int[] chars = new int[pub.getWidth()*pub.getHeight()];
        for(int i = 0; i < secret.length(); i++){
            chars[i]  = (int)secret.charAt(i);
        }
        for(int i =0; i < pub.getWidth(); i++){
            for(int j = 0; j < pub.getHeight(); j++){
                int[] clors = new int[4];
                clors = pub.getRaster().getPixel(i,j,clors);
                int currentInt = chars[pub.getHeight()* i + j];
                int red = currentInt >> 5;
                int blue = (currentInt >> 2)  % 8;
                int green = currentInt % 4;
                clors[0] = (clors[0]/8)*8 + red;
                clors[1] = (clors[1]/8)*8 + blue;
                clors[2] = (clors[2]/4)*4 + green;
                clors[3] = currentInt;
                nimg.getRaster().setPixel(i,j,clors);
                
            }
        }
    }
    /**
     * Decodes public image.
     * @param pub Image to be decoded.
     * @param result The result if you just want the object to get changed.
     * @return result with decoded text.
     */
    public String decode(BufferedImage pub, String result){
        for(int i =0; i < pub.getWidth(); i++){
            for(int j = 0; j < pub.getHeight(); j++){
                int[] clors = new int[4];
                clors = pub.getRaster().getPixel(i, j, clors);
                int p1  = (clors[0]%8) << 5 ;
                int p2 = (clors[1] %8) <<2 ;
                int p3 = (clors[2] %4 );
                char chRes = (char)((p1|p2)|p3);
                if(chRes != 0)
                    result += chRes;
            }
        }
        return result;
    }
}
