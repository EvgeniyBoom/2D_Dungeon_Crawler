package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public  int[][] mapTileNum;


    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];
        //mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadSingleMap("/maps/map01.txt");
        //loadWorldMap("/maps/world01(59x35).txt");
    }

    public void getTileImage() {

        // NEED A PLACEHOLDER FOR NUMBERS 0-9  Check the 12-th lesson!
        setup(0, "Grass/Grass_part_5", false);
        setup(1, "Grass/Grass_part_5", false);
        setup(2, "Grass/Grass_part_5", false);
        setup(3, "Grass/Grass_part_5", false);
        setup(4, "Grass/Grass_part_5", false);
        setup(5, "Grass/Grass_part_5", false);
        setup(6, "Grass/Grass_part_5", false);
        setup(7, "Grass/Grass_part_5", false);
        setup(8, "Grass/Grass_part_5", false);
        setup(9, "Grass/Grass_part_5", false);

        // Start true indexing
        setup(10, "RoomBorder/Black_tile", false);
        setup(11, "RoomBorder/Room_border_1", true);
        setup(12, "RoomBorder/Room_border_2", true);
        setup(13, "RoomBorder/Room_border_3", true);
        setup(14, "RoomBorder/Room_border_4", true);
        setup(15, "RoomBorder/Room_border_5", true);
        setup(16, "RoomBorder/Room_border_6", true);
        setup(17, "RoomBorder/Room_border_7", true);
        setup(18, "RoomBorder/Room_border_8", true);
        setup(19, "RoomBorder/Room_border_9", true);
        setup(20, "RoomBorder/Room_border_10", true);

        setup(21, "RoomBorder/Room_wooden_column_1", true);
        setup(22, "RoomBorder/Room_wooden_column_2", true);
        setup(23, "RoomBorder/Room_wooden_column_3", true);

        setup(24, "CastleWall/CastleWall_part5", true);
        setup(25, "CastleWall/CastleWall_part6", true);
        setup(26, "CastleWall/CastleWall_part7", true);
        setup(27, "CastleWall/CastleWall_part9", true);
        setup(28, "CastleWall/CastleWall_part10", true);
        setup(29, "CastleWall/CastleWall_part11", true);

        setup(30, "RoomFloor/Room_wooden_floor_1", false);
        setup(31, "RoomFloor/Room_wooden_floor_2", false);
        setup(32, "RoomFloor/Room_wooden_floor_3", false);
        setup(33, "RoomFloor/Room_wooden_floor_4", false);
        setup(34, "RoomFloor/Room_wooden_floor_5", false);
        setup(35, "RoomFloor/Room_wooden_floor_6", false);
        setup(36, "RoomFloor/Room_wooden_floor_7", false);
        setup(37, "RoomFloor/Room_wooden_floor_8", false);
        setup(38, "RoomFloor/Room_wooden_floor_9", false);
        setup(39, "RoomFloor/Room_wooden_floor_11", false);
        setup(40, "RoomFloor/Room_wooden_floor_12", false);
        setup(41, "RoomFloor/Room_wooden_column_with_wooden_floor", false);
        setup(55, "RoomFloor/Room_wooden_floor_10", false);

        setup(42, "RoomFloor/Room_wooden_floor_withCarpet_1", false);
        setup(43, "RoomFloor/Room_wooden_floor_withCarpet_2", false);
        setup(44, "RoomFloor/Room_wooden_floor_withCarpet_3", false);
        setup(45, "RoomFloor/Room_wooden_floor_withCarpet_4", false);
        setup(46, "RoomFloor/Room_wooden_floor_withCarpet_5", false);
        setup(47, "RoomFloor/Room_wooden_floor_withCarpet_(6-7-10-11)", false);
        setup(48, "RoomFloor/Room_wooden_floor_withCarpet_8", false);
        setup(49, "RoomFloor/Room_wooden_floor_withCarpet_9", false);
        setup(50, "RoomFloor/Room_wooden_floor_withCarpet_12", false);
        setup(51, "RoomFloor/Room_wooden_floor_withCarpet_13", false);
        setup(52, "RoomFloor/Room_wooden_floor_withCarpet_14", false);
        setup(53, "RoomFloor/Room_wooden_floor_withCarpet_15", false);
        setup(54, "RoomFloor/Room_wooden_floor_withCarpet_16", false);



    }

    public void setup(int index, String imagePath, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imagePath + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadSingleMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();
                while(col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadWorldMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();
                while(col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void  drawSingleMap(Graphics2D g2) {

        //SINGLE MAP
        /*int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }*/

        //SINGLE MAP
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxScreenCol && worldRow < gp.maxScreenRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxScreenCol) {
                worldCol = 0;
                worldRow++;
            }
        }


    }

    public void  drawWorldMap(Graphics2D g2) {

        //WORLD MAP
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
