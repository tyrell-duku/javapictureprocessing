package picture;

public class Main {

  public static void main(String[] args) {
    if (args[0].equalsIgnoreCase("invert")) {
      invert(args[1], args[2]);
      System.out.println("Image has been inverted!");
    }
    if (args[0].equalsIgnoreCase("grayscale")) {
      greyscale(args[1], args[2]);
      System.out.println("Image has been converted to greyscale!");
    }
    if (args[0].equalsIgnoreCase("rotate")) {
      if (args[1].equals("90")) {
        rotate90(args[2], args[3]);
        System.out.println("Image has been rotated 90 degrees!");
      } else if (args[1].equals("180")) {
        rotate180(args[2], args[3]);
        System.out.println("Image has been rotated 180 degrees!");
      } else {
        rotate270(args[2], args[3]);
        System.out.println("Image has been rotated 270 degrees!");
      }
    }
  }

  public static void invert(String loadedPic, String placeToSave) {
    Picture p = Utils.loadPicture(loadedPic);

    for (int i = 0; i < p.getWidth(); i++) {
      for (int j = 0; j < p.getHeight(); j++) {
        Color c = p.getPixel(i, j); // Initial colour (before inversion)
        c.setBlue(255 - c.getBlue());
        c.setRed(255 - c.getRed());
        c.setGreen(255 - c.getGreen());
        p.setPixel(i, j, c);
      }
    }

    Utils.savePicture(p, placeToSave);
  }

  public static void greyscale(String loadedPic, String placeToSave) {
    Picture p = Utils.loadPicture(loadedPic);

    for (int i = 0; i < p.getWidth(); i++) {
      for (int j = 0; j < p.getHeight(); j++) {
        Color c = p.getPixel(i, j);
        int sum = c.getBlue() + c.getRed() + c.getGreen();
        int avg = sum / 3;
        c.setRed(avg);
        c.setBlue(avg);
        c.setGreen(avg);
        p.setPixel(i, j, c);
      }
    }
    Utils.savePicture(p, placeToSave);
  }

  public static void rotate90(String loadedPic, String placeToSave) {
    Picture p = Utils.loadPicture(loadedPic);
    Picture rotated = Utils.createPicture(p.getHeight(), p.getWidth());
    for (int i = 0; i < p.getWidth(); i++) {
      for (int j = 0; j < p.getHeight(); j++) {
        Color rotation = p.getPixel(i, j);
        rotated.setPixel(p.getHeight() - (j + 1), i, rotation);
      }
    }
    Utils.savePicture(rotated, placeToSave);
  }

  public static void rotate180(String loadedPic, String placeToSave) {
    Picture p = Utils.loadPicture(loadedPic);
    Picture rotated = Utils.createPicture(p.getWidth(), p.getHeight());
    for (int i = 0; i < p.getWidth(); i++) {
      for (int j = 0; j < p.getHeight(); j++) {
        Color c = p.getPixel(i, j);
        rotated.setPixel(p.getWidth() - (i + 1), p.getHeight() - (j + 1), c);
      }
    }
    Utils.savePicture(rotated, placeToSave);
  }

  public static void rotate270(String loadedPic, String placeToSave) {
    Picture p = Utils.loadPicture(loadedPic);
    Picture rotated = Utils.createPicture(p.getHeight(), p.getWidth());
  }
}
