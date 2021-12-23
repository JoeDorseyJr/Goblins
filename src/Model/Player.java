package Model;

import Controller.KeyHandler;
import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private int positionX;
    private int positionY;
    private Color color = Color.white;

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        this.positionX = gp.screenWidth/2 - (gp.tileSize/2);
        this.positionY = gp.screenHeight/2 - (gp.tileSize/2);

        this.collisionArea = new Rectangle(16,24,gp.tileSize-24,gp.tileSize-24);

        init();
        getPlayerImage();
    }


    //Setters
    public void setColor(Color color) {
        this.color = color;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    //Getters
    public Color getColor() {
        return color;
    }
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }


    //Methods
    public void init(){
        this.worldY = gp.tileSize * 6;
        this.worldX = gp.tileSize * 8;
        this.speed = 4;
        this.direction = "down";
    }
    public void update(){

        if (keyH.upPressed == true || keyH.downPressed == true
        || keyH.rightPressed == true || keyH.leftPressed == true) {

            if (keyH.upPressed) {
                this.direction = "up";
            } else if (keyH.downPressed) {
                this.direction = "down";
            } else if (keyH.leftPressed) {
                this.direction = "left";
            } else if (keyH.rightPressed) {
                this.direction = "right";
            }

            //Check for Collision
            collisionOn = false;
            gp.collisionsChecker.checkTile(this);

            //Player can move if collision is false
            if(collisionOn == false){
                switch (this.getDirection()){
                    case "up":
                        this.setWorldY(this.getWorldY() - this.getSpeed());
                        break;
                    case "down":
                        this.setWorldY(this.getWorldY() + this.getSpeed());
                        break;
                    case "left":
                        this.setWorldX(this.getWorldX() - this.getSpeed());
                        break;
                    case "right":
                        this.setWorldX(this.getWorldX() + this.getSpeed());
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else  if ( spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (this.getDirection()) {
            case("up"):
                if (spriteNum == 1) {
                    image = upL;
                } else {
                    image = upR;
                }
                break;
            case("down"):
                if (spriteNum == 1) {
                    image = downLL;
                } else {
                    image = downRL;
                }
                break;
            case ("left"):
                if (spriteNum == 1) {
                    image = leftM;
                } else {
                    image = leftS;
                }
                break;
            case("right"):
                if (spriteNum == 1) {
                    image = rightM;
                } else {
                    image = rightS;
                }
                break;
        }
        g2.drawImage(image,this.getPositionX(),this.getPositionY(),gp.tileSize,gp.tileSize,null);
//        g2.setColor(this.getColor());
//        g2.fillRect(this.getPositionY(), this.getPositionX(), gp.tileSize,gp.tileSize);
    }

    public void getPlayerImage(){
        try {
            upL = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/up_left.png")));
            upR = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/up_right.png")));
            downLL = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/down_left_left.png")));
            downLR = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/down_left_right.png")));
            downRL = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/down_right_left.png")));
            downRR = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/down_right_right.png")));
            rightM = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/right_move.png")));
            rightS = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/right_stand.png")));
            leftM = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/left_move.png")));
            leftS = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/goblin/left_stand.png")));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
