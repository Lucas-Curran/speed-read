/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package humanbenchmark;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Copyright © 2021. All rights reserved.
 * @author Lucas Curran
 */
public class Reading extends Canvas implements MouseListener {
    
    int counter = 0;
    String[] script = {""};
    
    public Reading() {
        addMouseListener(this);
    }
    
    public void paint( Graphics g ) {        
        if (counter == 0) {
            g.setFont(new Font("Consolas", Font.PLAIN, 36));
          
            g.drawString("This is a test for reading speed.", 100, 100);
            g.drawString("The words will slowly increase in speed", 100, 150);
            g.drawString("until you can no longer read them.", 100, 200);
            g.drawString("Once you click, the test will begin.", 100, 250);
            g.drawString("Click again once you can", 100, 300);
            g.drawString("no longer read the text.", 100, 350);
        } else if (counter == 1) {
            g.setFont(new Font("Consolas", Font.PLAIN, 36));
                   Timer timer = new Timer();
                   int delay = 1;
                   TimerTask tt = new TimerTask() {
                       @Override                   
                       public void run() {
                           try {
                                Color background = new Color(240,240,240);
                                g.setColor(background);
                                g.drawRect(0, 0, 1000, 1000);
                                g.setColor(Color.BLACK);
                                g.drawString(readLine(), 200,350);                               
                                repaint();                         
                           } catch (IOException ex) {
                                Logger.getLogger(Reading.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       };
                   };                  
                  timer.scheduleAtFixedRate(tt, delay, 1); 
        }
    }

    public String readLine()
        throws IOException {
            String file = "resources/script.txt";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();           
            reader.close();
            return currentLine;
}
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        counter++;
        System.out.println(counter);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
}
