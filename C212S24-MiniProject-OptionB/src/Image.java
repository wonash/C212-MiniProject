import java.awt.*;

abstract class Image {

    private int width;
    private int height;
    private Color[][] pixel;


    /**
     * creates a new Image object with a designated width and height
     * @param width
     * @param height
     */

    public Image(int width, int height) {
        this.width = width;
        this.height = height;

        this.pixel = new Color[this.height][this.width];
    }

    public int getWidth() {
        return width;
    } //gets and returns the width of this image

    /**
     * mutator method to change the width of this image
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() { // gets and returns the height of this image
        return height;
    }


    /**
     * mutator method to change the height of this image
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public Color[][] getPixel() {  // gets and returns the array of pixels for an image
        return pixel;
    }

    /**
     * sets the pixel 2d array to a new, given 2d array of Color objects
     * @param pixel the new Color 2D array to replace the current one with
     */
    public void setPixel(Color[][] pixel) {
        this.pixel = pixel;
    }

    /**
     * sets a specified Color object (pixel) in an image to a given Color object
     * @param row the row of the 2d array at which to change the pixel
     * @param col the column of the 2d array at which to change the pixel
     * @param c the new Color object we are going to be adding to replace the current one
     */
    public void set_one_pix(int row, int col, Color c){
        this.pixel[row][col]= c;
    }
}

