package picture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Process {

  public Process() {}

  public static void invert(String[] args) {
    String loadedPic = args[1];
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

    Utils.savePicture(p, args[2]);
  }

  public static void greyscale(String[] args) {
    String loadedPic = args[1];
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
    Utils.savePicture(p, args[2]);
  }

  public static void rotate90(String[] args) {
    String placeToSave = args[3];
    String loadedPic = args[2];
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

  public static void flipH(String loadedPic, String placeToSave) {
    Picture old = Utils.loadPicture(loadedPic);
    Picture newPic = Utils.createPicture(old.getWidth(), old.getHeight());
    for (int i = 0; i < old.getWidth(); i++) {
      for (int j = 0; j < old.getHeight(); j++) {
        Color c = old.getPixel(i, j);
        newPic.setPixel(old.getWidth() - (i + 1), j, c);
      }
    }
    Utils.savePicture(newPic, placeToSave);
  }

  public static void flipV(String loadedPic, String placeToSave) {
    Picture old = Utils.loadPicture(loadedPic);
    Picture newPic = Utils.createPicture(old.getWidth(), old.getHeight());
    for (int i = 0; i < old.getWidth(); i++) {
      for (int j = 0; j < old.getHeight(); j++) {
        Color c = old.getPixel(i, j);
        newPic.setPixel(i, old.getHeight() - (j + 1), c);
      }
    }
    Utils.savePicture(newPic, placeToSave);
  }

  public static void blend(String[] args) {
    List<String> argsList = Arrays.asList(args);
    String outputLocation = args[args.length - 1];
    List<String> picsString = argsList.subList(1, args.length - 1);

    List<Picture> pictures =
        picsString.stream().map(Utils::loadPicture).collect(Collectors.toList());

    int smallestW =
        Collections.min(pictures.stream().map(Picture::getWidth).collect(Collectors.toList()));

    int smallestH =
        Collections.min(pictures.stream().map(Picture::getHeight).collect(Collectors.toList()));

    Picture p = Utils.createPicture(smallestW, smallestH);

    for (int i = 0; i < smallestW; i++) {
      for (int j = 0; j < smallestH; j++) {
        int finalI = i;
        int finalJ = j;
        List<Color> cols =
            pictures.stream().map(k -> k.getPixel(finalI, finalJ)).collect(Collectors.toList());

        p.setPixel(i, j, averageColour(cols));
      }
    }

    Utils.savePicture(p, outputLocation);
  }

  private static Color averageColour(List<Color> colours) {
    int size = colours.size();
    int reds = colours.stream().map(Color::getRed).reduce(0, Integer::sum);
    int blues = colours.stream().map(Color::getBlue).reduce(0, Integer::sum);
    int greens = colours.stream().map(Color::getGreen).reduce(0, Integer::sum);
    return new Color(reds / size, greens / size, blues / size);
  }

  public static void blur(String[] args) {
    String placeToSave = args[2];
    Picture loaded = Utils.loadPicture(args[1]);
    Picture newPic = Utils.createPicture(loaded.getWidth(), loaded.getHeight());

    for (int i = 0; i < newPic.getWidth(); i++) {
      for (int j = 0; j < newPic.getHeight(); j++) {
        if (isBorder(loaded, i, j)) {
          Color same = loaded.getPixel(i, j);
          newPic.setPixel(i, j, same);
        } else {
          List<Color> surrounding = new ArrayList<>();

          for (int deltaI = -1; deltaI <= 1; deltaI++) {
            for (int deltaJ = -1; deltaJ <= 1; deltaJ++) {
              surrounding.add(loaded.getPixel(i + deltaI, j + deltaJ));
            }
          }
          newPic.setPixel(i, j, averageColour(surrounding));
        }
      }
    }
    Utils.savePicture(newPic, placeToSave);
  }

  private static boolean isBorder(Picture p, int i, int j) {
    int height = p.getHeight();
    int width = p.getWidth();
    return (i <= 0 || j <= 0 || i + 1 >= width || j + 1 >= height);
  }
}
