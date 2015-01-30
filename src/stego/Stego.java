package stego;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * This class is the main display for the program. This took FOREVER to make.
 * 
 * @author Salil Vanvari
 */
public class Stego extends JFrame implements ActionListener{
    JLabel pic1, pic2, pic3;
    BufferedImage img1, img2, img3;
    final ImageManipulator im = new ImageManipulator(2);
    final StringManipulator sm = new StringManipulator();
    JTextArea textArea;
    /**
     * Sets up the frame and ensures that it will function.
     */
    public Stego(){
        pic1 = new JLabel("", SwingConstants.CENTER);
        pic2 = new JLabel("", SwingConstants.CENTER);
        pic3 = new JLabel("", SwingConstants.CENTER);
        
        img1 = im.whiteImage(new BufferedImage(300,400,BufferedImage.TYPE_3BYTE_BGR));
        img2 = im.whiteImage(new BufferedImage(300,400,BufferedImage.TYPE_3BYTE_BGR));
        img3 = im.whiteImage(new BufferedImage(300,400,BufferedImage.TYPE_3BYTE_BGR));
        
        pic1.setIcon(new ImageIcon(img1));
        pic2.setIcon(new ImageIcon(img2));
        pic3.setIcon(new ImageIcon(img3));
        
        JScrollPane coverSP = new JScrollPane(pic1); 
        coverSP.setPreferredSize(new Dimension(400, 300));
        
        textArea = new JTextArea("Enter Text Here");
        textArea.setLineWrap(true);
        JScrollPane textSP = new JScrollPane(textArea);
        textSP.setPreferredSize(new Dimension(400,300));
        
        JScrollPane secretSP = new JScrollPane(pic2); 
        secretSP.setPreferredSize(new Dimension(400, 300));
        
        JScrollPane publicSP = new JScrollPane(pic3);
        publicSP.setPreferredSize(new Dimension(400 , 150));
        
        this.add(coverSP, BorderLayout.WEST);
        JPanel secretPanel = new JPanel();
        secretPanel.add(textSP, BorderLayout.NORTH);
        secretPanel.add(secretSP, BorderLayout.SOUTH);
        this.add(secretPanel, BorderLayout.CENTER);
        this.add(publicSP, BorderLayout.EAST);
        
        GridLayout grd = new GridLayout(2,3,60,0);
        JPanel loadBtns = new JPanel(grd);
        
        JButton coverLoad = new JButton("Load Image");
        coverLoad.setActionCommand("coverLoad");
        coverLoad.addActionListener(this);
        
        JButton secretLoad =  new JButton("Load Image");
        secretLoad.setActionCommand("secretLoad");
        secretLoad.addActionListener(this);
        
        JButton publicLoad = new JButton("Load Image");
        publicLoad.setActionCommand("publicLoad");
        publicLoad.addActionListener(this);

        JButton coverSave = new JButton("Save Image");
        coverSave.setActionCommand("coverSave");
        coverSave.addActionListener(this);

        JButton secretSave = new JButton("Save Image");
        secretSave.setActionCommand("secretSave");
        secretSave.addActionListener(this);

        JButton publicSave = new JButton("Save Image");
        publicSave.setActionCommand("publicSave");
        publicSave.addActionListener(this);

        JButton encode = new JButton("Encode Text");
        encode.setActionCommand("encode");
        encode.addActionListener(this);

        JButton decode = new JButton("Decode Picture on Right");
        decode.setActionCommand("decode");
        decode.addActionListener(this);

        JButton encrypt = new JButton("Encrypt Picture in Middle");
        encrypt.setActionCommand("encrypt");
        encrypt.addActionListener(this);

        JButton decrypt = new JButton("Decrypt Picture on Right");
        decrypt.setActionCommand("decrypt");
        decrypt.addActionListener(this);
        
        loadBtns.add(coverLoad);
        loadBtns.add(secretLoad);
        loadBtns.add(publicLoad);
        loadBtns.add(coverSave);
        loadBtns.add(secretSave);
        loadBtns.add(publicSave);
        
        this.add(loadBtns,BorderLayout.SOUTH);
        
        JPanel actionButtons = new JPanel();
        actionButtons.add(encode);
        actionButtons.add(decode);
        actionButtons.add(encrypt);
        actionButtons.add(decrypt);
        
        this.add(actionButtons, BorderLayout.NORTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0,0,screenSize.width, screenSize.height);
        this.setVisible(true);
    }
    /**
     * Runs program.
     */
    public static void main(String[] args) 
    {
        Stego fr = new Stego();
        /*
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG, GIF", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        Image nimgimg = nimg.getScaledInstance(600, 600, BufferedImage.SCALE_SMOOTH);
        JLabel picL = new JLabel(new ImageIcon(nimgimg));

        int returnVal = chooser.showOpenDialog(fr);
        File file = null;
        if(returnVal == JFileChooser.APPROVE_OPTION) {      
           file = chooser.getSelectedFile();
        }
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageManipulator im = new ImageManipulator(2);
        BufferedImage secretImg = im.loadImage(file.getPath());
        BufferedImage publicImg = im.loadImage(file.getPath());
        StringManipulator sm = new StringManipulator();
        int wpub = publicImg.getWidth();
        int hpub = publicImg.getHeight();
        //BufferedImage realSecretImg = im.toBufferedImage(secretImg.getScaledInstance(wpub,hpub, BufferedImage.SCALE_SMOOTH));
        BufferedImage nimg = new BufferedImage(wpub, hpub, BufferedImage.TYPE_3BYTE_BGR);
        String result = "";
        //im.encrypt(publicImg, realSecretImg, nimg);
        try{
             ImageIO.write(nimg,"png",new File("/Users/vanvari/Desktop/output.png"));
        }
        catch(Exception e){}
        //nimg = im.loadImage("/Users/vanvari/Desktop/output.png");
        //BufferedImage decryptedImg = new BufferedImage(wpub, hpub, BufferedImage.TYPE_3BYTE_BGR);
        //im.decrypt(nimg, decryptedImg);*/
    }
    /**
     * Performs actions once it receives actions.
     * @param e the action event received by the method.
     */
    public void actionPerformed(ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG, GIF", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "coverLoad":
                int returnVal = chooser.showOpenDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File f = chooser.getSelectedFile();
                    img1 = im.loadImage(f.getAbsolutePath());
                    pic1.setIcon(new ImageIcon(img1));
                }
                break;
            case "secretLoad":
                returnVal = chooser.showOpenDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File f = chooser.getSelectedFile();
                    img2 = im.loadImage(f.getAbsolutePath());
                    pic2.setIcon(new ImageIcon(img2));
                }

                break;
            case "publicLoad":
                returnVal = chooser.showOpenDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File f = chooser.getSelectedFile();
                    img3 = im.loadImage(f.getAbsolutePath());
                    pic3.setIcon(new ImageIcon(img3));
                }
                break;
            case "coverSave":
                returnVal = chooser.showSaveDialog(this);
                BufferedImage bfg = img1;
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File f = chooser.getSelectedFile();
                    String fName = f.getAbsolutePath();
                    if(!fName.substring(fName.length() -4).equals(".png")){
                        fName += ".png";
                    }
                    try{
                        ImageIO.write(bfg,"png",new File(fName));
                    }
                    catch(Exception exc){}
                }
                break;
            case "secretSave":
                returnVal = chooser.showSaveDialog(this);
                bfg = img2;
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File f = chooser.getSelectedFile();
                    String fName = f.getAbsolutePath();
                    if(!fName.substring(fName.length() -4).equals(".png")){
                        fName += ".png";
                    }
                    try{
                        ImageIO.write(bfg,"png",new File(fName));
                    }
                    catch(Exception exc){}
                }
                break;
            case "publicSave":
                returnVal = chooser.showSaveDialog(this);
                bfg = img3;
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File f = chooser.getSelectedFile();
                    String fName = f.getAbsolutePath();
                    if(!fName.substring(fName.length() -4).equals(".png")){
                        fName += ".png";
                    }
                    try{
                        ImageIO.write(bfg,"png",new File(fName));
                    }
                    catch(Exception exc){}
                }
                break;
            case "encode":
                String text = textArea.getText();
                int wpub = img1.getWidth();
                int hpub = img1.getHeight();
                BufferedImage nimg = new BufferedImage(wpub, hpub, BufferedImage.TYPE_3BYTE_BGR);
                sm.encode(img1, text, nimg);
                img3 = nimg;
                pic3.setIcon(new ImageIcon(img3));
                break;
            case "decode":
                String result = sm.decode(img3, "");
                textArea.setText(result);
                break;
            case "encrypt":
                wpub = img1.getWidth();
                hpub = img1.getHeight();
                nimg = new BufferedImage(wpub, hpub, BufferedImage.TYPE_3BYTE_BGR);
                im.encrypt(img1, img2, nimg);
                img3 = nimg;
                pic3.setIcon(new ImageIcon(img3));
                break;
            case "decrypt":
                wpub = img1.getWidth();
                hpub = img1.getHeight();
                nimg = new BufferedImage(wpub, hpub, BufferedImage.TYPE_3BYTE_BGR); 
                //BufferedImage realSec = im.toBufferedImage(nimg.getScaledInstance(wpub, hpub, BufferedImage.SCALE_FAST));
                im.decrypt(img3, nimg);
                img2 = nimg;
                pic2.setIcon(new ImageIcon(img2));
                break;
        }
    }

}

