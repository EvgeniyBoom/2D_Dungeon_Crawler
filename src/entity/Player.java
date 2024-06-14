package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 46;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 36;
        solidArea.height = 17;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        worldX = (int) (gp.tileSize * 9.5);
        worldY = (int) (gp.tileSize * 5.5);
        speed = 5;
        direction = "down";
        direction_memory = "right";
    }
    public void getPlayerImage() {

        idle1 = setup("Idle_1");
        idle2 = setup("Idle_2");
        idle3 = setup("Idle_3");
        idle4 = setup("Idle_4");
        idle_left1 = setup("Idle_left_1");
        idle_left2 = setup("Idle_left_2");
        idle_left3 = setup("Idle_left_3");
        idle_left4 = setup("Idle_left_4");
        run_left1 = setup("Run_left_1");
        run_left2 = setup("Run_left_2");
        run_left3 = setup("Run_left_3");
        run_left4 = setup("Run_left_4");
        run_left5 = setup("Run_left_5");
        run_right1 = setup("Run_right_1");
        run_right2 = setup("Run_right_2");
        run_right3 = setup("Run_right_3");
        run_right4 = setup("Run_right_4");
        run_right5 = setup("Run_right_5");

    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (keyH.rightPressed == true || keyH.leftPressed == true || keyH.upPressed == true || keyH.downPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            }
            else if (keyH.downPressed == true) {
                direction = "down";
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
                direction_memory = "left";
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
                direction_memory = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //IF COLLISION IS FALSE
            if(collisionOn == false) {

                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter > 10) {
                if(sprite_Run_Num == 1) {
                    sprite_Run_Num = 2;
                } else if (sprite_Run_Num == 2) {
                    sprite_Run_Num = 3;
                } else if (sprite_Run_Num == 3) {
                    sprite_Run_Num = 4;
                } else if (sprite_Run_Num == 4) {
                    sprite_Run_Num = 5;
                } else { // if (sprite_Run_Num == 5)
                    sprite_Run_Num = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteCounter++;
            if(spriteCounter > 10) {
                if(sprite_Idle_Num == 1) {
                    sprite_Idle_Num = 2;
                } else if (sprite_Idle_Num == 2) {
                    sprite_Idle_Num = 3;
                } else if (sprite_Idle_Num == 3) {
                    sprite_Idle_Num = 4;
                } else if (sprite_Idle_Num == 4) {
                    sprite_Idle_Num = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key" -> {
                    gp.playSE(3);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gp.playSE(2);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a key!");
                    }
                }
                case "Bone_book" -> {
                    gp.playSE(1);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed Up!");
                }
                case "Chest" -> {
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                }
            }
        }
    }

    public void draw(Graphics2D g2){

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up", "down" -> {
                switch (direction_memory) {
                    case "right" -> {
                        switch (sprite_Idle_Num) {
                            case 1 -> image = idle1;
                            case 2 -> image = idle2;
                            case 3 -> image = idle3;
                            case 4 -> image = idle4;
                        }
                    }
                    case "left" -> {
                        switch (sprite_Idle_Num) {
                            case 1 -> image = idle_left1;
                            case 2 -> image = idle_left2;
                            case 3 -> image = idle_left3;
                            case 4 -> image = idle_left4;
                        }
                    }
                }
            }
            case "left" -> {
                switch (sprite_Run_Num) {
                    case 1 -> image = run_left1;
                    case 2 -> image = run_left2;
                    case 3 -> image = run_left3;
                    case 4 -> image = run_left4;
                    case 5 -> image = run_left5;
                    default -> image = idle_left1;
                }
            }
            case "right" -> {
                switch (sprite_Run_Num) {
                    case 1 -> image = run_right1;
                    case 2 -> image = run_right2;
                    case 3 -> image = run_right3;
                    case 4 -> image = run_right4;
                    case 5 -> image = run_right5;
                    default -> image = idle1;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
