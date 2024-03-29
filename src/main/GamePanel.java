/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main;

import inputs.KeyboardInputs;
import java.awt.Dimension;
import java.awt.Graphics;
import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;

import inputs.MouseInputs;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import utils.LoadSave;


/**
 * Panel class
 * @author matti
 */
public class GamePanel extends javax.swing.JPanel {

    /** mouse inputs interface */
    private MouseInputs mouseInputs;
    /** game reference */
    private Game game;

    /**
     * Constructor, need game refence
     * @param game Game reference
     */
    public GamePanel(Game game) {
        this.game= game;
        initComponents();
        mouseInputs = new MouseInputs(this);
        setCursor(
                  Toolkit
                  .getDefaultToolkit()
                  .createCustomCursor(
                         new ImageIcon(LoadSave.GetSpriteAtlas("pointer.png")).getImage(),
                         new Point(15,15),
                         "Scope Pointer"
                  )
             );
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setFocusable(true);
    }

    /**
     * Paint geme on repaint
     * @param g Graphics of frame
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
        
    }

    /**
     * Panel sizes are set
     */
    public void setPanelSize() {
        Dimension size = new Dimension((int)GAME_WIDTH, (int)GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Panel Size: " + GAME_WIDTH + "*" + GAME_HEIGHT);
    }

     
    /** update function */
    public void updateGame() {
    }

    /** get Game reference */
    public Game getGame() {
        return game;
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
