import fr.istic.aco.editor.Command;
import fr.istic.aco.editor.ConcretCommand.*;
import fr.istic.aco.editor.Invoker.UiInvoker;
import fr.istic.aco.editor.Invoker.UiInvokerImpl;
import fr.istic.aco.editor.Receiver.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe principale
 *
 * @author Sanogo kadidiatou
 * @version 1.0
 */
public class Main {

    private static UiInvokerImpl invoker;
    private static Engine receiver;
    private static Recorder recorder;
    private static UndoManager undoManager;

    public static void main(String[] args) {

        runMenu();

    }


    private static String menu(){

        UiInvoker invoker = new UiInvokerImpl();
        System.out.println("Contenu du Buffer : " + receiver.getBufferContents());
        System.out.println("Contenu du Clipboard : " + receiver.getClipboardContents());
        System.out.println();
        System.out.println("******V3 UI******");
        System.out.println("      =====      \n");
        System.out.println(" 1 - Insert (i)");
        System.out.println(" 2 - Delete (d)");
        System.out.println(" 3 - Cut (x)");
        System.out.println(" 4 - Copy (c)");
        System.out.println(" 5 - Paste (v)");
        System.out.println(" 6 - Selection (select)");
        System.out.println(" 7 - Start recording (start)");
        System.out.println(" 8 - Stop recording (stop)");
        System.out.println(" 9 - Replay (replay)");
        System.out.println(" 10 - Undo (undo)");
        System.out.println(" 11 - Redo (redo)");
        System.out.println(" 12 - Exit (e) \n");

        System.out.print("Choix : ");
        return getUserInput();

    }

    private static String getUserInput(){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choix=null;
        try {
             choix = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return choix;
    }

    private static void runMenu(){

        invoker = new UiInvokerImpl();
        receiver = new EngineImpl();
        recorder = new RecorderImpl();
        undoManager = new UndoManagerImpl();

        invoker.setReadStream(System.in);


        String choix = "";

        do {
            choix = menu();
            runCMD(choix);
        }while (!choix.equalsIgnoreCase("e"));

        System.out.println("Sortie !!!");
    }

    private static void runCMD(String cmd){

        Command command;

        switch (cmd){

            case "i":
                System.out.println("Entrer votre texte : ");
                invoker.setTextUI();
                command = new Insert(receiver,invoker,recorder,undoManager);
                command.execute();
                break;

            case "d" :
                System.out.println("Delete");
                command = new Delete(receiver,recorder,undoManager);
                command.execute();
                break;

            case "x" :
                System.out.println("Couper");
                command = new CutSelectedText(receiver,recorder,undoManager);
                command.execute();
                break;

             case "c" :
                System.out.println("Copier");
                 command = new CopySelectedText(receiver,recorder,undoManager);
                 command.execute();
                break;

             case "v" :
                System.out.println("Coller");
                 command = new PasteClipboard(receiver,recorder,undoManager);
                 command.execute();
                break;

             case "select" :
                System.out.println("Selection");
                invoker.setBeginIndexUI();
                invoker.setEndIndexUI();
                command = new SelectionChange(receiver.getSelection(),invoker,recorder,undoManager,receiver);
                command.execute();
                break;

            case "start" :
                System.out.println("Debut enregistrement");
                command = new Start(recorder);
                command.execute();
                break;

            case "stop" :
                System.out.println("Fin enregistrement");
                command = new Stop(recorder);
                command.execute();
                break;

            case "replay" :
                System.out.println("Replay");
                command = new Replay(recorder);
                command.execute();
                break;

            case "undo" :
                System.out.println("Undo");
                command = new Undo(undoManager);
                command.execute();
                receiver = ((Undo)command).getEngine();
                break;

            case "redo" :
                System.out.println("Redo");
                command = new Redo(undoManager);
                command.execute();
                receiver = ((Redo)command).getEngine();

                break;
        }
    }
}
