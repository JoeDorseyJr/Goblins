package Model;

import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class LandDraw {
    GamePanel gp;
    public Land[] land;
    public int[][] landTileNumber;

    public LandDraw(GamePanel gp){
        this.gp = gp;
        land = new Land[10];
        landTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/Maps/WorldMap.txt");
        getLandImage();
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while(col < gp.maxWorldCol) {
                    String[] numbers = line.split("\t");
                    int num = Integer.parseInt(numbers[col]);
                    landTileNumber[col][row] = num;
                    col++;
                }
                if (col >= gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLandImage(){
        try {
            land[0] = new Land();
            land[1] = new Land();
            land[2] = new Land();
            land[3] = new Land();
            land[4] = new Land();
            land[5] = new Land();

            land[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Land/grass.png")));

            land[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Land/wall.png")));
            land[1].collision = true;

            land[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Land/water.png")));
            land[2].collision = true;

            land[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Land/tree.png")));
            land[3].collision = true;

            land[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Land/sand.png")));
            land[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Land/cobble.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){
       int col = 0;
       int row = 0;

       while(col < gp.maxWorldCol && row < gp.maxWorldRow){
           int tileNum = landTileNumber[col][row];

           // Camera effect - only show screen size and draw tile in the correct position
           int x = col * gp.tileSize;
           int y = row * gp.tileSize;
           int screenX = x - gp.player.worldX + gp.player.getPositionX();
           int screenY = y - gp.player.worldY + gp.player.getPositionY();

           //Only draws what can be seen by screen
           if(x + gp.tileSize > gp.player.worldX - gp.player.getPositionX() &&
              x - gp.tileSize < gp.player.worldX + gp.player.getPositionX() &&
              y + gp.tileSize > gp.player.worldY - gp.player.getPositionY() &&
              y - gp.tileSize < gp.player.worldY + gp.player.getPositionY()) {
               g2.drawImage(land[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
           }
           col++;
           x += gp.tileSize;

           if (col == gp.maxWorldCol){
               col = 0;
               x = 0;
               row++;
               y += gp.tileSize;
           }
       }
    }
}
