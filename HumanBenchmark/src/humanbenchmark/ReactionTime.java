/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package humanbenchmark;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Copyright © 2021. All rights reserved.
 * @author mrjones and :) Sung
 */

public class ReactionTime extends Canvas implements MouseListener {

    private int counter = 0;
    private long begin, change;
    Random rNum = new Random();
    private String text;
    
    
    public ReactionTime() {
        addMouseListener(this);


 
      
        text = "This is a reaction test. When the background turns green, click the screen. "
                + "Click the screen to begin.";
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(text, 100, 100);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
        switch (counter) {
            
            case 0:
                
                try {
                System.out.println(counter);
                this.setBackground(Color.red);
                text = "";
                repaint();
                Thread.sleep(rNum.nextInt(10000) + 1000);
                this.setBackground(Color.green);
                begin = System.currentTimeMillis();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                repaint();
                break;
                
            case 1:
                System.out.println("2");
                change = e.getWhen() - begin;
                System.out.println(change);
                break;
            
            case 2:
                break;
        
        }
        counter++;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
