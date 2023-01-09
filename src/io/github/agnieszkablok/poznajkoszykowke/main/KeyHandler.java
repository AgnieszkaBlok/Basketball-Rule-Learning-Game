package io.github.agnieszkablok.poznajkoszykowke.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Implementation of KeyListener that listens for keyboard presses and releases (W, S, A, D for moving & Esc button for exiting to main menu)
 */
 public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void clearPressedButtons(){
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_ESCAPE){
            Component component = e.getComponent();
            Window window = SwingUtilities.getWindowAncestor(component);
            window.dispose();
            GameMenu.showGameMenu();
        }
        if (code == KeyEvent.VK_W) {
            upPressed = true;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;


        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code =e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;


        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;

        }


    }
}
