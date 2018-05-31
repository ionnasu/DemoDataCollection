package TezaParser;

import org.junit.Test;

import java.io.IOException;

public class Main {
    @Test
    public void main() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start C:\\Users\\nasui\\Desktop\\RunFace.bat 2&1");
    }

    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start C:\\Users\\nasui\\Desktop\\RunFace.bat 2&1");
    }
}
