package picture;

import static picture.Process.blend;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    if (args[0].equalsIgnoreCase("invert")) {
      Process.invert(args[1], args[2]);
      System.out.println("Image has been inverted!");
      System.out.println("Please navigate to " + args[2] + "to see it!");
    }
    if (args[0].equalsIgnoreCase("grayscale")) {
      Process.greyscale(args[1], args[2]);
      System.out.println("Image has been converted to greyscale!");
    }
    if (args[0].equalsIgnoreCase("rotate")) {
      if (args[1].equals("90")) {
        Process.rotate90(args[2], args[3]);
        System.out.println("Image has been rotated 90 degrees!");
      } else if (args[1].equals("180")) {
        Process.rotate180(args[2], args[3]);
        System.out.println("Image has been rotated 180 degrees!");
      } else {
        Process.rotate90(args[2], args[3]);
        Process.rotate180(args[3], args[3]);
        System.out.println("Image has been rotated 270 degrees!");
      }
    } else if (args[0].equalsIgnoreCase("flip")) {
      if (args[1].equalsIgnoreCase("h")) {
        Process.flipH(args[2], args[3]);
      } else {
        Process.flipV(args[2], args[3]);
      }
    } else if (args[0].equalsIgnoreCase("blur")) {
      Process.blend(args, args[args.length - 1]);
    }
  }



  public int aRed(Color c1, Color c2) {
    return c1.ge
  }

  public static Color averageColour(Color c1, Color c2) {
    int red1 = (c1.getRed() + c2.getRed()) / 2;
    int blue1 = (c1.getBlue() + c2.getBlue()) / 2;
    int green1 = (c1.getGreen() + c2.getGreen()) / 2;
    return new Color(red1,green1,blue1);
  }

}
