package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed,downPressed,leftPressed,rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){//NORTH
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){//SOUTH
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){//WEST
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){//EAST
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){//NORTH
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){//SOUTH
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){//WEST
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){//EAST
            rightPressed = false;
        }

    }
}
