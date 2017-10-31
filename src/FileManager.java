import javax.sound.sampled.AudioFormat;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import javafx.scene.image.Image;
// probably import java.io.Serializable


public class FileManager /* probably extends serializable*/ {
    private final int TILES = 20; // how many tiles ?
    private File curFil; // addition: File
    private ArrayList<Image> scannedImages;
    private ArrayList<String> scannedTexts;
    private ArrayList<AudioFormat> scannedAudios;
    private Scanner scan;
    private int[][] scannedMap;
    private String base; // addition: need to fill directory
    private final int NUMBER_IMAGES = 1;

    // initialize file manager
    public FileManager(){
        scannedImages = new ArrayList<>();
        scannedTexts = new ArrayList<>();
        scannedAudios = new ArrayList<>();
        scannedMap = new int[TILES][TILES];
        base = "/home/ozan/Desktop/oos/1B.battlecity-cs319/src/"; // directory basE !!!
        // maybe we need a string to hold directory
        // which will initialized here and changed in
        // methods
    }

    // level names will be "level" + levelCount + ".txt" -> level3.txt e.g
    public int[][] getMapLevelData(int level) throws FileNotFoundException{
        curFil = new File(base + "level" + level + ".txt");
        scan = new Scanner(curFil);
        for(int i = 0; i < TILES && scan.hasNextLine(); i++){
            for(int j = 0; j < TILES && scan.hasNext(); j++){
                scannedMap[i][j] = scan.nextInt();
            }
        }
        return scannedMap;

    }
    // highest score will be in file highS.txt
    public void saveHighestScore(int highestScore) throws java.io.IOException{
        curFil = new File(base + "highS.txt");
        scan = new Scanner(curFil);
        if(highestScore > scan.nextInt()) {
            FileWriter fWriter = new FileWriter(curFil);
            PrintWriter pWriter = new PrintWriter(curFil);
            pWriter.println (highestScore);
        }

    }
    public int getHighestScore() throws FileNotFoundException{
        curFil = new File(base + "highS.txt");
        scan = new Scanner(curFil);
        return scan.nextInt();
    }
    // howToPlay.txt
    public String getHowToPlayDoc() throws FileNotFoundException{
        curFil = new File(base + "howToPlay.txt");
        scan = new Scanner(curFil);
        String s = "";
        while(scan.hasNext()){
            s = scan.next();
        }
        return s;
    }
    // credits.txt
    public String getCreditsDoc() throws FileNotFoundException{
        curFil = new File(base + "credits.txt");
        scan = new Scanner(curFil);
        String s = "";
        while(scan.hasNext()){
            s = scan.next();
        }
        return s;
    }
    // settings.txt each line will be new array element e.g WASD(space) would be first line
    public ArrayList<String> getSettingsDoc() throws FileNotFoundException{
        curFil = new File(base + "settings.txt");
        scan = new Scanner(curFil);
        ArrayList<String> setting = new ArrayList<>();
        while(scan.hasNext()){
            setting.add(scan.nextLine());
        }
        return setting;
    }
    //public void setSettings( int id, String setting[]){} // do we need id or we can know by number of lines ?


    public File getCurFil() {
        return curFil;
    }

    // images from file
    public ArrayList<Image> getScannedImages() throws FileNotFoundException {
        String img;
        for(int i = 0; i < NUMBER_IMAGES; i++){
            img = base + "image" + i + ".png";
            scannedImages.add(new Image(new File(img).toURI().toString()));
        }
        return scannedImages;
    }


    public ArrayList<String> getScannedTexts() {
        return scannedTexts;
    }

    public ArrayList<AudioFormat> getScannedAudios() {
        String audio = "";
        for(int i = 0; i < NUMBER_IMAGES; i++){
            audio = base + "audio" + i + ".mp3";
            scannedAudios.add(); // audioooo
        }
        return scannedAudios;
    }

    public int[][] getScannedMap() {
        return scannedMap;
    }



}

