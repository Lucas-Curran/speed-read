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
    private String text1, text2, text3, changeText;
    Color textColor;
    
    
    public ReactionTime() {
        addMouseListener(this);

        
 
      
        text1 = "This is a reaction test.";
        text2 = "When the background turns green, click the screen.";
        text3 = "Click the screen to begin.";
        changeText = "";
        textColor = Color.WHITE;
    }

    public void paint(Graphics g) {
        g.setColor(textColor);
        g.setFont(new Font("Consolas", Font.PLAIN, 24));
        
        g.drawString(changeText, this.getWidth()/2 - 50, this.getHeight()/2);
        g.drawString(text1, 350, 100);
        if (counter == 0) {
            
            g.drawString(text2, 190, 150);
            g.drawString(text3, 350, 200);
            this.setBackground(Color.red);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
        switch (counter) {
            
            case 0:
                try {
                System.out.println(counter);                
                repaint();
                Thread.sleep(rNum.nextInt(10000) + 1000);
                this.setBackground(Color.green.darker());
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
                textColor = Color.BLACK;
                changeText = String.valueOf(change) + " ms";
                text1 = "Click the screen again to replay";
                repaint();
                break;
            case 2:
                textColor = Color.WHITE;
                counter = -1;
                changeText = "";
                change = 0;
                repaint();
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
