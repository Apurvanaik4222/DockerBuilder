package Utils;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        Config.initialize();
       System.out.println(Constants.BROWSER);
        System.out.println(Config.getProperty(Constants.BROWSER));
    }
}
