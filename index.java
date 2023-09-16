import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Captch {
    public static void main(String[] args) {
        int width = 200;
        int height = 80;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Generate a random captcha text
        String captchaText = generateCaptchaText(5); // You can adjust the length

        // Set the background color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Set the text color
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 36));

        // Draw the captcha text
        g2d.drawString(captchaText, 40, 50);

        // Add noise (random lines)
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Save the captcha image
        try {
            ImageIO.write(image, "png", new File("captcha.png"));
            System.out.println("Captcha image saved as 'captcha.png'");
        } catch (IOException e) {
            e.printStackTrace();
        }

        g2d.dispose();
    }

    private static String generateCaptchaText(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captchaText = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars.charAt(random.nextInt(chars.length()));
            captchaText.append(c);
        }

        return captchaText.toString();
    }
}