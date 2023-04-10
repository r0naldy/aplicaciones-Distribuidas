import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws Exception {
        // Crear imágenes imaginarias
        BufferedImage img1 = createImage(800, 600);
        BufferedImage img2 = createImage(1200, 800);
        BufferedImage img3 = createImage(1920, 1080);
        BufferedImage img4 = createImage(1280, 720);
        BufferedImage img5 = createImage(640, 480);

        // Procesamiento de imágenes secuencial
        long startSequential = System.currentTimeMillis();
        processImage(img1);
        processImage(img2);
        processImage(img3);
        processImage(img4);
        processImage(img5);
        long endSequential = System.currentTimeMillis();

        System.out.println("Tiempo de procesamiento secuencial: " + (endSequential - startSequential)/1000.0 + "s");

        // Procesamiento de imágenes paralelo
        long startParallel = System.currentTimeMillis();
        Thread t1 = new Thread(() -> processImage(img1));
        Thread t2 = new Thread(() -> processImage(img2));
        Thread t3 = new Thread(() -> processImage(img3));
        Thread t4 = new Thread(() -> processImage(img4));
        Thread t5 = new Thread(() -> processImage(img5));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        long endParallel = System.currentTimeMillis();

        System.out.println("Tiempo de procesamiento en paralelo: " + (endParallel - startParallel )/1000.0 + "s");
    }

    public static void processImage(BufferedImage image) {
        // Código para procesar una imagen
        System.out.println("Procesando imagen: " + image.toString());
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