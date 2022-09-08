package fr.istic.aco.editor.Invoker;

import fr.istic.aco.editor.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

/**
 * Classe d'implementation de l'Invoker
 *
 * @author Eddy KOKOYE & Kadidiatou SANOGO
 * @version 1.0
 */
public class UiInvokerImpl implements UiInvoker{

    private HashMap<String, Command> commands = new HashMap<>();
    private boolean stopLoop = false;
    private InputStream inputStream;
    private BufferedReader bufferedReader;

    private String textToInsert;

    private int beginIndex;
    private int endIndex;

    @Override
    public void runInvokerLoop() {

        while (!stopLoop){
            String userInput = null;
            try{
                userInput = readUserInput();
            }catch (IOException e){ e.printStackTrace();}
            if (userInput == null || userInput.equals("")){
                stopLoop = true;
                break;
            }
            Command cmdToExecute = commands.get(userInput);
            if (cmdToExecute != null){
                cmdToExecute.execute();
            }
        }
    }

    private String readUserInput() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public void stopLoop() {
        stopLoop = true;
    }

    @Override
    public void setReadStream(InputStream inputStream) {
        if (inputStream == null){
            throw new IllegalArgumentException("null inputStream");
        }
        this.inputStream = inputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void addCommand(String keyword, Command cmd) {

        if ((keyword == null) || (cmd == null)){
            throw new IllegalArgumentException("null parameter");
        }
        commands.put(keyword,cmd);
    }

    @Override
    public String getText(){
        return this.textToInsert;
    }

    @Override
    public void setTextForTest(String s) {

        this.textToInsert = s;
    }

    @Override
    public void setTextUI() {

        String input = null;
        try {
            input = readUserInput();
        }catch (IOException e){
            e.printStackTrace();
        }
        this.textToInsert = input;
    }

    @Override
    public void setBeginIndexForTest(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    @Override
    public void setBeginIndexUI() {
        String input = null;
        try {
            input = readUserInput();
        }catch (IOException e){
            e.printStackTrace();
        }
        this.beginIndex = Integer.parseInt(Objects.requireNonNull(input));
    }

    @Override
    public int getBeginIndex() {
        return beginIndex;
    }

    @Override
    public void setEndIndexForTest(int endIndex) {
        this.endIndex = endIndex;
    }

    @Override
    public void setEndIndexUI() {

        String input = null;
        try {
            input = readUserInput();
        }catch (IOException e){
            e.printStackTrace();
        }
        this.endIndex = Integer.parseInt(Objects.requireNonNull(input));
    }

    @Override
    public int getEndIndex() {
        return endIndex;
    }
}
