import java.awt.*;
import java.io.*;
import java.util.Scanner;

class PpmImage extends Image {

    /**
     * constructs a new instance of a PpmImage with a specified width and height
     * @param width the width of object
     * @param height the height of the object
     */
    public PpmImage(int width, int height) {
        super(width, height);
    }

    /**
     * creates a new PpmImage object with a height and width of 0
     */
    public PpmImage() {
        super(0, 0);
    }

    /**
     * goes through a file and gets each pixel's RGB values
     * @param filename the file to go through
     */
    void readImage(String filename) {
        try {
            Scanner s = new Scanner(new File(filename));

            s.nextLine();
            int width = s.nextInt();
            int height = s.nextInt();
            this.setWidth(width);
            this.setHeight(height);

            // skip two line to make mantis not BLUE??
            s.nextLine();
            s.nextLine();

            this.setPixel(new Color[height][width]);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int red = s.nextInt();
                    int green = s.nextInt();
                    int blue = s.nextInt();
                    super.getPixel()[i][j] = new Color(red, green, blue);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Whoops " + filename);
            e.printStackTrace();
        }
    }

    /**
     * puts the rgb value data into a file
     * @param filename the file we are writing to
     */
    public void output(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("P3\n");
            writer.write(getWidth() + " " + getHeight() + "\n");
            writer.write("255\n");

            for (int i = 0; i < getHeight(); i++) {
                for (int j = 0; j < getWidth(); j++) {
                    writer.write(getPixel()[i][j].getRed() + " " + getPixel()[i][j].getGreen() + " " + getPixel()[i][j].getBlue() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}