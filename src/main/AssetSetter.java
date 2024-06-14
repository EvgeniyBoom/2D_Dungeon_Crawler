package main;

import object.OBJ_Bone_book;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject_Bone_book(int index, int x, int y) {

        gp.obj[index] = new OBJ_Bone_book(gp);
        gp.obj[index].worldX = x * gp.tileSize;
        gp.obj[index].worldY = y * gp.tileSize;
    }

    public void setObject_Key(int index, int x, int y) {

        gp.obj[index] = new OBJ_Key(gp);
        gp.obj[index].worldX = x * gp.tileSize;
        gp.obj[index].worldY = y * gp.tileSize;
    }

    public void setObject_Door(int index, int x, int y) {

        gp.obj[index] = new OBJ_Door(gp);
        gp.obj[index].worldX = x * gp.tileSize;
        gp.obj[index].worldY = y * gp.tileSize;
    }

    public void setObject_Chest(int index, int x, int y) {

        gp.obj[index] = new OBJ_Chest(gp);
        gp.obj[index].worldX = x * gp.tileSize;
        gp.obj[index].worldY = y * gp.tileSize;
    }

}
