package picture;

public class Main {

  public static void main(String[] args) {

    switch (args[0]) {
      case "invert":
        Process.invert(args);
        System.out.println("Image has been inverted!");
        System.out.println("Please navigate to " + args[2] + " to see it!");
        break;

      case "grayscale":
        Process.greyscale(args);
        System.out.println("Image has been converted to greyscale!");
        System.out.println("Please navigate to " + args[2] + " to see it!");
        break;

      case "rotate":
        switch (args[1]) {
          case "90":
            Process.rotate90(args);
            System.out.println("Image has been rotated 90 degrees!");
            System.out.println("Please navigate to " + args[2] + " to see it!");
            break;
          case "180":
            Process.rotate180(args[2], args[3]);
            System.out.println("Image has been rotated 180 degrees!");
            System.out.println("Please navigate to " + args[3] + " to see it!");
            break;
          default:
            Process.rotate90(args);
            Process.rotate180(args[3], args[3]);
            System.out.println("Image has been rotated 270 degrees!");
            break;
        }
        break;

      case "flip":
        switch (args[1]) {
          case "H":
            Process.flipH(args[2], args[3]);
            System.out.println("Image has been flipped horizontally!");
            break;
          default:
            Process.flipV(args[2], args[3]);
            System.out.println("Image has been flipped vertically!");
        }
        break;

      case "blend":
        Process.blend(args);
        System.out.print("Image has been blended with " + (args.length - 2));
        System.out.print(" images! Please navigate to " + args[args.length - 1]);
        System.out.print(" to see it!");
        break;

      default:
        Process.blur(args);
        System.out.println("Image has been blurred!");
        System.out.println("Please navigate to " + args[2] + " to see it!");
    }
  }
}
