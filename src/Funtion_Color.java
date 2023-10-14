import java.awt.*;

public class Funtion_Color {
    GUI gui;
    public Funtion_Color(GUI gui){
        this.gui = gui;
    }

    public void changeColor(String color){
        switch (color){
            case "white" -> {
                gui.window.getContentPane().setBackground(Color.white);
                gui.textArea.setBackground(Color.white);
                gui.textArea.setForeground(Color.black);
            }
            case "black" -> {
                gui.window.getContentPane().setBackground(Color.black);
                gui.textArea.setBackground(Color.black);
                gui.textArea.setForeground(Color.white);
            }
            case "blue" -> {
                gui.window.getContentPane().setBackground(Color.blue);
                gui.textArea.setBackground(Color.blue);
                gui.textArea.setForeground(Color.white);
            }
        }
    }
}
