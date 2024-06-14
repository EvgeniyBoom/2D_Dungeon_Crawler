package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int  worldX, worldY;
    public int speed;

    public BufferedImage idle1, idle2, idle3, idle4;
    public BufferedImage idle_left1, idle_left2, idle_left3, idle_left4;
    public BufferedImage run_left1, run_left2, run_left3, run_left4, run_left5;
    public BufferedImage run_right1, run_right2, run_right3, run_right4, run_right5;
    public String direction;
    public String direction_memory;

    public int spriteCounter = 0;
    public int sprite_Idle_Num = 1;
    public int sprite_Run_Num = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
