import javax.swing.*;
//import javax.swing.event.UndoableEditEvent;
//import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
//import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JFrame window;
    //TEXT AREA
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;
    //TOP MENU BAR
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    //FILE MENU
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    //FORMAT MENU
    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize16, iFontSize32;
    JMenu menuFont, menuFontSize;
    //EDIT MENU
    JMenuItem iUndo, iRedo;
    //COLOR MENU
    JMenuItem iColor1, iColor2, iColor3;
    //CLASSES
    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Funtion_Color color = new Funtion_Color(this);
    UndoManager um = new UndoManager();
    Function_Edit function_edit = new Function_Edit(this);
    KeyHandler keyHandler = new KeyHandler(this);

    public static void main(String[] args){
        new GUI();
    }
    public GUI(){
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        createColorMenu();
        color.changeColor("white");
        window.setVisible(true);
    }
    public void createWindow(){
        window  = new JFrame("Notepad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea(){
        textArea = new JTextArea();
        textArea.getDocument().addUndoableEditListener(e -> um.addEdit(e.getEdit()));
        textArea.addKeyListener(keyHandler);
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //wo, unter welcher Bedingung vertical, unter w.B. horizontal
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); //damit scrollbars verschwinen und haben keine BorderSize
        window.add(scrollPane);
    }

    public void createMenuBar(){
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuColor = new JMenu("Color");
        menuBar.add(menuColor);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);
    }

    public void createFileMenu(){
        iNew = new JMenuItem("New");
        iNew.addActionListener(this); //TODO
        iNew.setActionCommand("New"); //TODO: mehr daruber lesen
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this); //TODO
        iOpen.setActionCommand("Open"); //TODO: mehr daruber lesen
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this); //TODO
        iSave.setActionCommand("Save"); //TODO: mehr daruber lesen
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save as");
        iSaveAs.addActionListener(this); //TODO
        iSaveAs.setActionCommand("Save as"); //TODO: mehr daruber lesen
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this); //TODO
        iExit.setActionCommand("Exit"); //TODO: mehr daruber lesen
        menuFile.add(iExit);
    }

    public void createEditMenu(){
        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("redo");
        menuEdit.add(iRedo);
    }

    public void createFormatMenu(){
        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCSMS);

        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("size - 8");
        menuFontSize.add(iFontSize8);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("size - 16");
        menuFontSize.add(iFontSize16);

        iFontSize32 = new JMenuItem("32");
        iFontSize32.addActionListener(this);
        iFontSize32.setActionCommand("size - 32");
        menuFontSize.add(iFontSize32);
    }

    public void createColorMenu(){
        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("white");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("black");
        menuColor.add(iColor2);

        iColor3 = new JMenuItem("Cian");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("blue");
        menuColor.add(iColor3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New" -> file.newFile();
            case "Open" -> file.open();
            case "Save as" -> file.saveAs();
            case "Save" -> file.save();
            case "Exit" -> file.exit();
            case "Word Wrap" -> format.wordWrap();
            case "size - 8" -> format.createFont(8);
            case "size - 16" -> format.createFont(16);
            case "size - 32" -> format.createFont(32);
            case "Arial" -> format.setFont(command);
            case "Times New Roman" -> format.setFont(command);
            case "Comic Sans MS" -> format.setFont(command);
            case "white" -> color.changeColor("white");
            case "black" -> color.changeColor("black");
            case "blue" -> color.changeColor("blue");
            case "undo" -> function_edit.undo();
            case "redo" -> function_edit.redo();
        }
    }
}
