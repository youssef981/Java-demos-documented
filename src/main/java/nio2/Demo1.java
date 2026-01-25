package nio2;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Demo1 {
    static void main(String[] args) {
        //this:
//        Path listing = Paths.get("/usr/bin/zip");
//        or
        Path listing = FileSystems.getDefault().getPath("/usr/bin/zip");
        System.out.println("File Name [" +
                listing.getFileName() + "]");
        System.out.println("Number of Name Elements in the Path [" +
                listing.getNameCount() + "]");
        System.out.println("Parent Path [" +
                listing.getParent() + "]");
        System.out.println("Root of Path [" +
                listing.getRoot() + "]");
        System.out.println("Subpath from Root, 2 elements deep [" +
                listing.subpath(0,1) + "]");
    }
}
