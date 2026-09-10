package de.example.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class PngToByteArrayConverter {

    private static final int BYTES_PER_LINE = 12;

    private PngToByteArrayConverter() {
    }

    public static void main(String[] args) {
        try {
            Path pngFile = parseArguments(args);

            validatePngFile(pngFile);

            byte[] imageData = readFile(pngFile);

            String snippet = generateJavaSnippet(
                    "IMAGE_DATA",
                    imageData);

            System.out.println(snippet);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static Path parseArguments(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Verwendung: java PngToByteArrayConverter <datei.png>");
        }

        return Path.of(args[0]);
    }

    private static void validatePngFile(Path path)
            throws IOException {

        if (!Files.exists(path)) {
            throw new IllegalArgumentException(
                    "Datei existiert nicht: " + path);
        }

        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException(
                    "Kein reguläres File: " + path);
        }

        String fileName =
                path.getFileName().toString().toLowerCase();

        if (!fileName.endsWith(".png")) {
            throw new IllegalArgumentException(
                    "Keine PNG-Datei: " + fileName);
        }

        byte[] bytes = Files.readAllBytes(path);

        if (bytes.length < 8) {
            throw new IllegalArgumentException(
                    "Datei ist zu klein für ein PNG");
        }

        byte[] pngSignature = {
                (byte) 0x89,
                0x50,
                0x4E,
                0x47,
                0x0D,
                0x0A,
                0x1A,
                0x0A
        };

        for (int i = 0; i < pngSignature.length; i++) {
            if (bytes[i] != pngSignature[i]) {
                throw new IllegalArgumentException(
                        "Datei besitzt keinen gültigen PNG-Header");
            }
        }
    }

    private static byte[] readFile(Path path)
            throws IOException {

        return Files.readAllBytes(path);
    }

    private static String generateJavaSnippet(
            String variableName,
            byte[] data) {

        StringBuilder sb = new StringBuilder();

        sb.append("private static final byte[] ")
          .append(variableName)
          .append(" = {\n");

        appendByteDefinitions(sb, data);

        sb.append("\n};");

        return sb.toString();
    }

    private static void appendByteDefinitions(
            StringBuilder sb,
            byte[] data) {

        for (int i = 0; i < data.length; i++) {

            if (i % BYTES_PER_LINE == 0) {
                sb.append("    ");
            }

            sb.append(toJavaByte(data[i]));

            if (i < data.length - 1) {
                sb.append(", ");
            }

            if ((i + 1) % BYTES_PER_LINE == 0
                    && i < data.length - 1) {
                sb.append('\n');
            }
        }
    }

    private static String toJavaByte(byte value) {
        return String.format("(byte)0x%02X", value & 0xFF);
    }
}