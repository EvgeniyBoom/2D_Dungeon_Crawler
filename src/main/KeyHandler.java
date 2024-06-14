package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    boolean checkDrawTime;
    boolean enterPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            titleState(code);
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState) {
            playState(code);
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        // OPTIONS STATE
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }
    }

    public void titleState (int code) {

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if(gp.ui.commandNum == 1) {
                // Nothing there
            }
            if(gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }
    public void playState (int code) {
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        // PAUSE
        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }

        // OPTIONS
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }

        // DEBUG
        if(code == KeyEvent.VK_T) {
            if(checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
    }
    public void pauseState (int code) {
        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }
    public void optionsState (int code) {
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch(gp.ui.subState) {
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if(gp.ui.subState == 0) {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                }
            }
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if(gp.ui.subState == 0) {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
