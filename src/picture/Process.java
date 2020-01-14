package picture;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Process {

 public Process() {}

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

  public static void flipH(String loadedPic, String placeToSave) {
    Picture original = Utils.loadPicture(loadedPic);
    Picture newPic = Utils.createPicture(original.getWidth(), original.getHeight());
    for (int i = 0; i < original.getWidth(); i++) {
      for (int j = 0; j < original.getHeight(); j++) {
        Color c = original.getPixel(i, j);
        newPic.setPixel(original.getWidth() - (i + 1), j, c);
      }
    }
    Utils.savePicture(newPic, placeToSave);

  }

  public static void flipV(String loadedPic, String placeToSave) {
    Picture original = Utils.loadPicture(loadedPic);
    Picture newPic = Utils.createPicture(original.getWidth(), original.getHeight());
    for (int i = 0; i < original.getWidth(); i++) {
      for (int j = 0; j < original.getHeight(); j++) {
        Color c = original.getPixel(i, j);
        newPic.setPixel(i, original.getHeight() - (j + 1), c);
      }
    }
    Utils.savePicture(newPic, placeToSave);
  }

  public static void blend(String[] args, String placeToSave) {
    List<String> argsList = Arrays.asList(args);
    argsList.remove(0);
    argsList.remove(args.length - 1); // Removes first and last elements of
    // arguments, leaving just the image links

    List<Picture> pictures = argsList.stream()
        .map(Utils::loadPicture)
        .collect(Collectors.toList());

    int smallestW = Collections.min(pictures.stream()
        .map(p -> (p.getWidth()))
        .collect(Collectors.toList()));

    int smallestH = Collections.min(pictures.stream()
        .map(p -> (p.getHeight()))
        .collect(Collectors.toList()));

    Picture p = Utils.createPicture(smallestW, smallestH);

    for (int i = 0; i < smallestW; i++) {
      for (int j = 0; j < smallestH; j++) {
        List <Color> cols = pictures.stream()
            .map(k -> k.getPixel(i,j))
            .collect(Collectors.toList());
      }
    }

  }

}