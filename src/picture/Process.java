package picture;

public class Process {

    private Picture picture;

    public Process(Picture picture) {
        this.picture = picture;
    }

    public  void invert() {
        for (int i = 0; i < picture.getWidth(); i++) {
            for (int j = 0; i < picture.getWidth(); j++) {
                Color c = picture.getPixel(i,j); //Initial colour (before inversion)
                c.setBlue(255-c.getBlue());
                c.setRed(255-c.getRed());
                c.setGreen(255-c.getGreen());
                picture.setPixel(i,j,c);

            }
        }
    }

}

