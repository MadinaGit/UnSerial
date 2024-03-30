import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {

        openZip("D://Games/savegames/zip.zip", "D://Games/savegames/");
        openProgress("D://Games/savegames/save1.dat");
        openProgress("D://Games/savegames/save2.dat");
        openProgress("D://Games/savegames/save3.dat");
    }

    public static void openZip(String zipFileName, String zipToFolderName) {

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                String name = entry.getName();
                FileOutputStream fous = new FileOutputStream(zipToFolderName + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fous.write(c);
                }
                fous.flush();
                zin.closeEntry();
                fous.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress(String pathSaveGameDat) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(pathSaveGameDat);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
    }
}
