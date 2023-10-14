import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Function_File {

    GUI gui;
    String fileName;
    String fileAdress;
    public Function_File(GUI gui){
        this.gui = gui;
    }

    public void newFile(){
        gui.textArea.setText(""); //ersetzten aktuellen Text mit dem leeren, um "eine neue Datei" zu erstellen
        gui.window.setTitle("New");
        fileName = null;
        fileAdress = null;
    }
    public void open(){
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if(fd.getFile() != null){ //wenn nicht leer, lol
            fileName = fd.getFile();
            fileAdress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileAdress + fileName)); //ist die Klasse, die nen Text von character-input stream liest
            //TODO: wieso steht hier "+"?
            //weil wir die Adresse von dieser Datei bekommen und in dem Folder sollen noch auf diese Datei verweise
            //wie: D:\Users\Big\ + DateiName.txt

            gui.textArea.setText("");
            String line = null;
            while ((line = br.readLine()) != null){ //TODO: was passiert hier?
                gui.textArea.append(line + "\n");
            }
            br.close();
        }
        catch (Exception e){
            System.out.println("Error: File not opened");
        }
    }

    //Uberschreibt den Inhalt der schon existierten Datei
    public void save(){
        if(fileName == null){ //wenn nichts drin
            saveAs();
        }
        else{
            try{
                FileWriter fw = new FileWriter(fileAdress + fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            }
            catch (Exception e){
                System.out.println("Error: something wrong with - save");
            }
        }
    }
    //Speichert die neue Datei
    public void saveAs(){
        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile() != null){
            fileName = fd.getFile();
            fileAdress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try{
            FileWriter fw = new FileWriter(fileAdress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();
        }
        catch (Exception e){
            System.out.println("Error: something wrong with - save");
        }
    }

    public void exit(){
        System.exit(0);
    }
}
