/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createandpopulatezip;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thangduong
 */
public class CreateAndPopulateZip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String [] lines = {
            "Line 1",
            "Line 2 2",
            "Line 3 3 3",
            "Line 4 4 4 4",
            "Line 5 5 5 5 5"
        };
        
        try (FileSystem zipFs = openZip(Paths.get("myData.zip"))) {
            copyToZip (zipFs);
            writeToFileInZip1(zipFs, lines);
            writeToFileInZip2(zipFs, lines);
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    
    
    private static FileSystem openZip (Path zipPath) throws IOException, URISyntaxException {
        Map<String, String> providerProps = new HashMap<>();
        providerProps.put("create", "true");
        
        URI zipUri = new URI("jar:file", zipPath.toUri().getPath(), null);
        FileSystem zipFs = FileSystems.newFileSystem(zipUri, providerProps);
        
        return zipFs;
    }
    
    private static void copyToZip (FileSystem zipFs) throws IOException {
        // Path sourcePath = FileSystems.getDefault().getPath("file1.txt");
        Path sourcePath = Paths.get("file1.txt");
        Path destPath = zipFs.getPath("/file1Copied.txt");
        
        Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
    }
    
    private static void writeToFileInZip1 (FileSystem zipFs, String [] data) throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(zipFs.getPath("/newFile1.txt"))){
            for (String d:data) {
                writer.write(d);
                writer.newLine();
            }
            
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
    
    private static void writeToFileInZip2 (FileSystem zipFs, String [] data) throws IOException {
        Files.write(zipFs.getPath("/newFile2.txt"), Arrays.asList(data), Charset.defaultCharset(), StandardOpenOption.CREATE);
    }
}
