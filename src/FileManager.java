import javafx.scene.media.AudioClip;
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
    private ArrayList<AudioClip> scannedAudios;
    private Scanner scan;
    private int[][] scannedMap;
    private final int NUMBER_IMAGES = 1;
    private final int NUMBER_AUDIOS = 1;

    // initialize file manager
    public FileManager(){
        scannedImages = new ArrayList<>();
        scannedTexts = new ArrayList<>();
        scannedAudios = new ArrayList<>();
        scannedMap = new int[TILES][TILES];
        // maybe we need a string to hold directory
        // which will initialized here and changed in
        // methods
    }

    // level names will be "level" + levelCount + ".txt" -> level3.txt e.g
    public int[][] getMapLevelData(int level) throws FileNotFoundException{
        curFil = new File(this.getClass().getResource("/MediaFiles/level"+level+".txt").toString());
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
        curFil = new File(this.getClass().getResource("/MediaFiles/highS.txt").toString());
        scan = new Scanner(curFil);
        if(highestScore > scan.nextInt()) {
            FileWriter fWriter = new FileWriter(curFil);
            PrintWriter pWriter = new PrintWriter(curFil);
            pWriter.println (highestScore);
        }

    }
    public int getHighestScore() throws FileNotFoundException{
        curFil = new File(this.getClass().getResource("/MediaFiles/highS.txt").toString());
        scan = new Scanner(curFil);
        return scan.nextInt();
    }
    // howToPlay.txt
    public String getHowToPlayDoc() throws FileNotFoundException{
        curFil = new File(this.getClass().getResource("/MediaFiles/howToPlay.txt").getFile());
        scan = new Scanner(curFil);
        String s = "";
        while(scan.hasNext()){
            s += scan.nextLine();
        }
        return s;
    }
    // credits.txt
    public String getCreditsDoc() throws FileNotFoundException{
        curFil = new File(this.getClass().getResource("/MediaFiles/credits.txt").getFile());
        System.out.println(curFil.toString());
        scan = new Scanner(curFil);
        String s = "";
        while(scan.hasNext()){
            s += scan.nextLine();
        }
        return s;
    }
    // settings.txt each line will be new array element e.g WASD(space) would be first line
    public ArrayList<String> getSettingsDoc() throws FileNotFoundException{
        curFil = new File(this.getClass().getResource("/MediaFiles/settings.txt").getFile());
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
        for(int i = 0; i < NUMBER_IMAGES; i++){ // assumed png
            scannedImages.add(new Image(new File(this.getClass().getResource("/MediaFiles/image" + i + ".png").toString()).toURI().toString()));
        }
        return scannedImages;
    }


    public ArrayList<String> getScannedTexts() {
        return scannedTexts;
    }

    public ArrayList<AudioClip> getScannedAudios() {
        String audio = "";
        for(int i = 0; i < NUMBER_AUDIOS; i++){
            audio = this.getClass().getResource("/MediaFiles/audio" + i + ".mp3").toString();
            scannedAudios.add(new AudioClip(audio)); // audioooo
        }
        return scannedAudios;
    }

    public int[][] getScannedMap() {
        return scannedMap;
    }



}

