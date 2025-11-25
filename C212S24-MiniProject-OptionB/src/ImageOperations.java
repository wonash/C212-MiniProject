import java.awt.*;
import java.util.Arrays;

class ImageOperations {

    /**
     * Removes the red channel of an image
     * @param img the image to remove the red channel from
     * @return the given image with the red channel removed
     */
    public static PpmImage zeroRed(PpmImage img) {
        PpmImage result = new PpmImage(img.getWidth(), img.getHeight());

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color pix = img.getPixel()[y][x];
                Color new_pix = new Color(0, pix.getGreen(), pix.getBlue());
                result.set_one_pix(y, x, new_pix);
            }
        }
        return result;
    }

    /**
     * Converts an image to grayscale.
     * @param img the image to perform the operation on
     * @return the given image converted to grayscale
     */
    public static PpmImage grayscale(PpmImage img){
        PpmImage result = new PpmImage(img.getWidth(), img.getHeight());
        for (int x= 0; x<img.getHeight();x++){
            for (int y=0; y< img.getWidth();y++){
                Color pix= img.getPixel()[x][y];
                int avg= (pix.getBlue()+pix.getGreen()+pix.getRed())/3;
                Color new_pix= new Color(avg,avg,avg);
                result.set_one_pix(x,y,new_pix);
            }
        }
        return result;

    }

    /**
     * Inverts the colors of a given image
     * @param img the image to perform the method on
     * @return the given image with inverted colors
     */
    public static PpmImage invert(PpmImage img){
        PpmImage result = new PpmImage(img.getWidth(), img.getHeight());
        for (int x= 0; x<img.getHeight();x++){
            for (int y=0; y< img.getWidth();y++){
                Color pix= img.getPixel()[x][y];
                Color new_pix= new Color(255-pix.getRed(),255-pix.getGreen(),255-pix.getBlue());
                result.set_one_pix(x,y,new_pix);
            }
        }
        return result;

    }

    /**
     * crops an image based on specifications. For example, if the image is 200×200,
     * and we type --crop 100 100 50 50, we crop pixels starting from (100,100)
     * inclusive and go to (150,150) exclusive.
     * @param img the image to crop
     * @param x1  int value representing which pixel to start the new image in the x axis
     * @param y1 int value representing which pixel to start the new image in the y axis
     * @param w int value representing the width of the desired image after cropping
     * @param h int value representing the height of the desired image after cropping
     * @return the cropped image
     */
    public static PpmImage crop(PpmImage img, int x1, int y1, int w, int h){
        int a=0;
        PpmImage cropped = new PpmImage(w, h);
        for (int x = y1; x < y1 + h; x++){
            int b = 0;
            for (int y = x1; y < x1 + w; y++){
                Color pix = img.getPixel()[x][y];
                cropped.set_one_pix(a, b, pix);
                b++;
            }
            a++;
        }
        return cropped;
    }

    /**
     * Takes an image and mirrors one half of the image onto the other based on the
     * specified direction. For example, if the direction is "H" for horizontal, then the
     * top half of the image should be reflected onto the bottom half. If the direction is
     * "V" for vertical, then the left half of the image should be reflected onto the right
     * side, like a mirror.
     * @param img the image to mirror
     * @param mode String representing the direction in which to mirror the image
     * @return the mirrored image result
     */
    public static PpmImage mirror(PpmImage img, String mode){
        PpmImage result = new PpmImage(img.getWidth(), img.getHeight());
        if (mode.equals("H")){
            for (int x = 0; x < img.getWidth(); x++){
                for (int y = 0; y < img.getHeight() / 2; y++){
                    result.set_one_pix(x, img.getHeight() - 1 - y, img.getPixel()[x][y]);
                    result.set_one_pix(x, y, img.getPixel()[x][img.getHeight() - 1 - y]);
                }
            }
        } else {
            for (int x = 0; x < img.getWidth() / 2; x++){
                for (int y = 0; y < img.getHeight(); y++){
                    result.set_one_pix(img.getWidth() - 1 - x, y, img.getPixel()[x][y]);
                    result.set_one_pix(x, y, img.getPixel()[img.getWidth() - 1 - x][y]);
                }
            }
        }
        return result;
    }

    /**
     * takes a given image and makes an image containing n repetitions of the image
     * in the dir direction. For example, if the direction is "H," then the method should
     * make an image where the given image is repeated n times in a horizontal line basically
     * @param img the image to repeat
     * @param n the number of times to repeat the image
     * @param dir the direction in which to repeat the image. Either "H" for horizontal or
     *            "V" for vertical
     * @return
     */
    public static PpmImage repeat(Image img, int n, String dir) {
        if (dir.equals("H")) {
            PpmImage h_img = new PpmImage(n * img.getWidth(), img.getHeight());
            for (int y = 0; y < h_img.getHeight(); y++) {
                for (int x = 0; x < h_img.getWidth(); x++) {
                    h_img.set_one_pix(y, x, img.getPixel()[y % img.getHeight()][x % img.getWidth()]);
                }
            }
            return h_img;
        } else {
            PpmImage v_img = new PpmImage(img.getWidth(), n * img.getHeight());
            for (int y = 0; y < v_img.getHeight(); y++) {
                for (int x = 0; x < v_img.getWidth(); x++) {
                    v_img.set_one_pix(x, y, img.getPixel()[x % img.getWidth()][y % img.getHeight()]);
                }
            }
            return v_img;
        }
    }

    /**
     * allows the user to call the methods of this class from the terminal
     * @param args the arguments
     */
    public static void main(String[] args) {
        PpmImage m = new PpmImage();

        if (args[0].equals("--zerored")) {
            m.readImage(args[1]);
            PpmImage result = zeroRed(m);
            result.output(args[2]);
        }
        if (args[0].equals("--grayscale")) {
            m.readImage(args[1]);
            PpmImage result = grayscale(m);
            result.output(args[2]);
        }
        if (args[0].equals("--invert")) {
            m.readImage(args[1]);
            PpmImage result = invert(m);
            result.output(args[2]);
        }
        if (args[0].equals("--crop")) {
            m.readImage(args[5]);
            PpmImage result = crop(m, Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            result.output(args[6]);
        }
        if (args[0].equals("--mirror")) {
            m.readImage(args[2]);
            PpmImage result = mirror(m, args[1]);
            result.output(args[3]);
        }
        if (args[0].equals("--repeat")) {
            m.readImage(args[3]);
            PpmImage result = repeat(m, Integer.parseInt(args[2]), args[1]);
            result.output(args[4]);
        }
    }
}




