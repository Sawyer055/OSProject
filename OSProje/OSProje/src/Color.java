import java.util.ArrayList;
import java.util.Random;

public class Color {

    private ArrayList<String> colorList;

    public Color() {
        colorList = new ArrayList<>();
    }

    public String getRandomColor() {
        String[] colorCodes = generateRandomColorCodes();

        int rnd;
        do {
            if (colorList.size() == colorCodes.length) {
                rnd = new Random().nextInt(colorCodes.length);
                return colorCodes[rnd];
            }
            rnd = new Random().nextInt(colorCodes.length);
        } while (colorList.contains(colorCodes[rnd]));

        colorList.add(colorCodes[rnd]);
        return colorCodes[rnd];
    }

    private String[] generateRandomColorCodes() {
        String[] colorCodes = new String[32];

        for (int i = 0; i < colorCodes.length; i++) {
            // Rastgele ANSI renk kodu üretme
            int colorType = new Random().nextInt(2); // 0: normal renk, 1: parlak renk
            int colorIntensity = new Random().nextInt(2); // 0: normal yoğunluk, 1: parlak yoğunluk
            int colorIndex = new Random().nextInt(8); // Renk indeksi (0-7)

            colorCodes[i] = String.format("\033[%d;%dm", colorType, 30 + colorIntensity * 60 + colorIndex);
        }

        return colorCodes;
    }
}
