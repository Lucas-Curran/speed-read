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
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/**
 * Copyright © 2021. All rights reserved.
 * @author Lucas Curran
 */
public class Reading extends Canvas implements MouseListener {
    
    int counter = 0;
    String[] script = {""};
    int rNumber;
    Integer random = 0;
    boolean winner = false;
    boolean loser = false;
    boolean tutorial = true;
    int total = 0;
    int loserInt = 0, winnerInt = 0;
    
    public Reading() {
        addMouseListener(this);
        Random rNumb = new Random();
        rNumber = rNumb.nextInt(10);
    }
    
    public void paint( Graphics g ) {      
        g.setFont(new Font("Consolas", Font.PLAIN, 36)); 
        
        if (winner && counter == 2) {
           g.drawString("Winner!", 100, 100);
           g.drawString("Press to go again.", 100, 150);
           counter = 0;
           tutorial = false;
           winner = false;
           winnerInt++;
           total++;
        } else if (loser && counter == 2) {
           g.drawString("Too slow!", 100, 100);
           g.drawString("Press to go again.", 100, 150);
           counter = 0;
           tutorial = false;
           loser = false;
           loserInt++;
           total++;
        }
        
        if (total == 5) {
            g.drawString("You are done!", 100, 100);
            g.drawString("You won " + winnerInt + " times!", 100, 130);
            g.drawString("You lost " + loserInt + " times!", 100, 160);
            counter = 100;
        }
        
        if (counter == 0 && tutorial == true) {
            g.setFont(new Font("Consolas", Font.PLAIN, 36));
          
            g.drawString("This is a test for timing sense.", 50, 100);
            g.drawString("Random numbers will flash on the screen,", 50, 150);
            g.drawString("and you must click when a certain number pops up.", 50, 200);
            g.drawString("You will do 5 rounds.", 50, 250);
            g.drawString("Once you click, the test will begin.", 50, 300);           
        } else if (counter == 1) {          
                   Timer timer = new Timer();
                   Random rNumb = new Random();
                   int delay = 350;
                   random = rNumb.nextInt(10);
                   g.drawString("The number you need is: " + rNumber, 25, 400);
                   TimerTask tt = new TimerTask() {
                       @Override                   
                       public void run() {
                           Integer random = rNumb.nextInt(10);
                           g.setColor(Color.BLACK);                                 
                           repaint();                         
                       };
                   };                
                  g.drawString(random.toString(), 400, 200);
                  timer.schedule(tt, delay);
            }
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
         if (rNumber == random && counter == 2) {
                winner = true;
         } else if (rNumber != random && counter == 2) {
                loser = true;
         }

            //repaint();      
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
}
