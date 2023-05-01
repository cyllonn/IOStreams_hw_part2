import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static String PATH = "D://IO_Games/savegames/";

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(100, 2, 1, 34.2);
        GameProgress game2 = new GameProgress(99, 15, 3, 6678.1);
        GameProgress game3 = new GameProgress(56, 12, 6, 7890.22);
        saveGame(PATH + "game1.dat", game1);
        saveGame(PATH + "game2.dat", game2);
        saveGame(PATH + "game3.dat", game3);


    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            byte[] bytes =  gameProgress.toString().getBytes();
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
