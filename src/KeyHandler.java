package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { //KeyListener allows the program to recieve keyboard events

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean shiftPressed,interactPressed;

    //Must have all the three methods


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }


    // If key is pressed

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        //   returns the integer of the key that is pressed
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            upPressed = true;

        }

        if (key == KeyEvent.VK_S) {
            downPressed = true;

        }

        if (key == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (key == KeyEvent.VK_A) {
            leftPressed = true;
        }
        
        if (key == KeyEvent.VK_SHIFT) {//
            shiftPressed = true;
        }

        if (key == KeyEvent.VK_E) {
            interactPressed = true;
        }
    }

    @Override

    // If key is not pressed

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (key == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (key == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        
        if (key == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }

        if (key == KeyEvent.VK_E) {
            interactPressed = false;
        }


    }


    public boolean interactPressed() {
        return false;
    }

}