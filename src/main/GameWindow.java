/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

/**
 * Window class
 * @author matti
 */
public class GameWindow {
    /** window object */
    JFrame jframe;
    
    /**
     * Constructor, need reference to gamePanel, and if it should be fullscreen
     * @param gamePanel
     * @param fullscreen 
     */
    public GameWindow(GamePanel gamePanel,boolean fullscreen) {
        jframe = new JFrame("Night of Monsters");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);//paint gamePanel on frame
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowLostFocus();
            }
        });
        
        jframe.setResizable(false);
        if(fullscreen){
            jframe.setUndecorated(true);
            //jframe.setAlwaysOnTop(true);
            jframe.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        }else{
            jframe.pack();//auto-fit gamePanel
        }
        jframe.setLocationRelativeTo(null);//center on screen
        jframe.setVisible(true);//set visible - always at the end of constructor!
        
    }
    
    /**
     * Set dimension of window to the frame ones, center the window
     */
    public void repackPanel(){
        jframe.pack();//auto-fit gamePanel
        jframe.setLocationRelativeTo(null);//center on screen
        
    }
}
