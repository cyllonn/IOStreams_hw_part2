import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static String PATH = "D://IO_Games/savegames/";

    public static void main(String[] args) {
        String[] pathArray = {PATH + "game1.dat", PATH + "game2.dat", PATH + "game3.dat"};
        String zipPath = PATH + "save.zip";
        GameProgress game1 = new GameProgress(100, 2, 1, 34.2);
        GameProgress game2 = new GameProgress(99, 15, 3, 6678.1);
        GameProgress game3 = new GameProgress(56, 12, 6, 7890.22);
        saveGame(PATH + "game1.dat", game1);
        saveGame(PATH + "game2.dat", game2);
        saveGame(PATH + "game3.dat", game3);
        if (zipFiles(zipPath, pathArray)) {
            for (String s : pathArray) {
                File file = new File(s);
                file.delete();
            }
        }
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            byte[] bytes = gameProgress.toString().getBytes();
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static boolean zipFiles(String pathZip, String[] list) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZip, true))) {
            for (String path : list) {
                FileInputStream fis = new FileInputStream(path);
                File file = new File(path);
                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}



