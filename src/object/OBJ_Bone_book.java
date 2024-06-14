package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Bone_book extends SuperObject{

    GamePanel gp;

    public OBJ_Bone_book(GamePanel gp) {

        this.gp = gp;

        name = "Bone_book";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/Bone_book.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
