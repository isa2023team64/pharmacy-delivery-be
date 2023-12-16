package com.isa2023team64.pharmacydeliverybe.service;import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRCodeGenerator {

    public static byte[] generateQRCode(String reservationInfo) throws WriterException, IOException {
        int width = 300;
        int height = 300;

        BitMatrix bitMatrix = new MultiFormatWriter().encode(reservationInfo, BarcodeFormat.QR_CODE, width, height);

        // Convert BitMatrix to BufferedImage
        BufferedImage bufferedImage = toBufferedImage(bitMatrix);

        // Convert BufferedImage to bytes
        byte[] imageBytes = toByteArray(bufferedImage);

        return imageBytes;
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    private static byte[] toByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }
}
