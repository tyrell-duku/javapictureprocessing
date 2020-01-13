package picture;

public class Main {

    public static void main(String[] args) {

        if (args[0].equalsIgnoreCase("invert")) {
            Picture p = Utils.loadPicture(args[1]);

            for (int i = 0; i < p.getWidth(); i++) {
                for (int j = 0; j < p.getHeight(); j++) {
                    Color c = p.getPixel(i, j); //Initial colour (before inversion)
                    c.setBlue(255 - c.getBlue());
                    c.setRed(255 - c.getRed());
                    c.setGreen(255 - c.getGreen());
                    p.setPixel(i, j, c);
                }
            }

            Utils.savePicture(p, args[2]);

        } else if (args[0].equalsIgnoreCase("greyscale")) {
            Picture p = Utils.loadPicture(args[1]);

            for (int i = 0; i < p.getWidth(); i++) {
                for (int j = 0; j < p.getHeight(); j++) {
                    Color c = p.getPixel(i,j);
                    int sum = c.getBlue() + c.getRed() + c.getGreen();
                    int avg = sum / 3;
                    c.setRed(avg);
                    c.setBlue(avg);
                    c.setGreen(avg);
                    p.setPixel(i,j,c);
                }
            }

            Utils.savePicture(p, args[2]);
        }
    }
}