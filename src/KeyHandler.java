import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {
    GUI gui;
    public KeyHandler(GUI gui){
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
            gui.file.save();
        }
        else if(e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_S){
            gui.file.saveAs();
        }
        else if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_C){ //f fur "color" in Menu
            gui.menuColor.doClick();
        }
        else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z){
            gui.function_edit.undo();
        }
        else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y){
            gui.function_edit.redo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
