import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws Exception {
        // Crear imágenes imaginarias
        BufferedImage img11 = createImage(800, 600);
        BufferedImage img21 = createImage(1200, 800);
        BufferedImage img31 = createImage(1920, 1080);
        BufferedImage img41 = createImage(1280, 720);
        BufferedImage img51 = createImage(640, 480);

        // Procesamiento de imágenes secuencial
        long startSequential = System.currentTimeMillis();
        processImagenessecuencial(img11, img21, img31, img41, img51);
        long endSequential = System.currentTimeMillis();

        System.out.println("Tiempo de procesamiento secuencial: " + (endSequential - startSequential)/1000.0 + "s");

        // Procesamiento de imágenes paralelo
        long startParallel = System.currentTimeMillis();
        processImagenesparalelo(img11, img21, img31, img41, img51);
        long endParallel = System.currentTimeMillis();

        System.out.println("Tiempo de procesamiento en paralelo: " + (endParallel - startParallel )/1000.0 + "s");
        System.out.println("Tiempo ganado con el procesamiento en paralelo: " + ((endSequential - startSequential)-(endParallel - startParallel ))/1000.0 + "s");

    }

    // Código para procesar una imagen
    public static void processImage(BufferedImage image) {

        System.out.println("Procesando imagen: " + image.toString());
    }
    // Código para procesar imágenes secuencialmente
    public static void processImagenessecuencial(BufferedImage... images) {

        Arrays.stream(images).forEach(Main::processImage);
    }
    // Código para procesar imágenes en paralelo
    public static void processImagenesparalelo(BufferedImage... images) throws InterruptedException {

        Arrays.stream(images).parallel().forEach(Main::processImage);
    }

    public static BufferedImage createImage(int width, int height) throws IOException {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = (int) (Math.random() * 256);
                int g = (int) (Math.random() * 256);
                int b = (int) (Math.random() * 256);

                int rgb = (r << 16) | (g << 8) | b;
                img.setRGB(x, y, rgb);
            }
        }

        return img;
    }

}