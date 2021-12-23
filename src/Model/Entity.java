package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX,worldY,speed = 4;
    public BufferedImage upR,upL,downLL,downLR,downRR,downRL,rightS,rightM,leftS,leftM;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle collisionArea;
    public boolean collisionOn = false;

    public void setWorldX(int x){
        this.worldX = x;
    }

    public void setWorldY(int y){
        this.worldY = y;
    }

    public void setSpeed (int speed){
        this.speed  = speed;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

    //getter
    public int getWorldX(){
        return this.worldX;
    }

    public int getWorldY(){
        return this.worldY;
    }

    public int getSpeed(){
        return this.speed;
    }

    public String getDirection() {
        return this.direction;
    }
}



