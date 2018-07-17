package sokoban.domain;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Level of game, being 2D array of bytes.
 *
 * @author Ketom
 */
public class Level {
    /**
     * Values of bytes corresponding to different entities.
     */
    public static final byte WALL = '#';
    public static final byte PLAYER = '@';
    public static final byte PLAYER_ON_SPOT = '+';
    public static final byte BOX = '$';
    public static final byte BOX_ON_SPOT = '*';
    public static final byte SPOT = '.';
    public static final byte TILE = ' ';

    /**
     * The bytes of which this Level consist.
     */
    private byte[][] bytes;

    /**
     * The width of this Level.
     */
    private int width;

    /**
     * The height of this Level.
     */
    private int height;

    /**
     * Constructs empty Level with given width and height.
     * @param width width of level
     * @param height height of level
     */
    public Level(int width, int height) {
        this.bytes = new byte[width][height];
        this.width = width;
        this.height = height;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                this.bytes[x][y] = TILE;
            }
        }
    }

    /**
     * Constructs Level with given width, height and content bytes.
     * @param width width of new level
     * @param height height of new level
     * @param bytes bytes of which new Level will consist
     */
    public Level(int width, int height, byte[][] bytes) {
        this.bytes = new byte[width][height];
        this.width = width;
        this.height = height;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                this.bytes[x][y] = bytes[x][y];
            }
        }
    }

    /**
     * Constructs Level from bytes read from File.
     * @param file The file from which new Level should be loaded.
     * @return New Level constructed from bytes read from File.
     * @throws IOException throws IOException when unable to read file
     */
    static public Level fromFile(File file) throws IOException {
        return fromBytes(Files.readAllBytes(file.toPath()));
    }

    /**
     * Constructs Level from bytes in string.
     * @param string The string from which new Level should be loaded.
     * @return New Level constructed from bytes in string.
     */
    static public Level fromString(String string) {
        return fromBytes(string.getBytes(StandardCharsets.US_ASCII));
    }

    /**
     * Constructs Level from bytes.
     * @param bytes The bytes from which new Level should be created.
     * @return New Level constructed from given bytes.
     */
    static public Level fromBytes(byte[] bytes) {
        int maxLineWidth = 0;
        int currentLineWidth = 0;
        List<List<Byte>> outerList = new ArrayList<>();
        List<Byte> innerList = new ArrayList<>();
        outerList.add(innerList);

        for (Byte b : bytes) {
            switch (b) {
                case '\n':
                    innerList = new ArrayList<>();
                    outerList.add(innerList);
                    if (maxLineWidth < currentLineWidth) {
                        maxLineWidth = currentLineWidth;
                    }
                    currentLineWidth = 0;
                    continue;

                case '\r':
                    continue;

                case WALL:
                case PLAYER:
                case PLAYER_ON_SPOT:
                case BOX:
                case BOX_ON_SPOT:
                case SPOT:
                case TILE:
                    break;

                default:
                    b = TILE;
            }
            innerList.add(b);
            currentLineWidth++;
        }
        if(maxLineWidth < currentLineWidth) {
            maxLineWidth = currentLineWidth;
        }

        if(outerList.get(outerList.size() - 1).size() == 0) {
            outerList.remove(outerList.size() - 1);
        }

        int width = maxLineWidth;
        int height = outerList.size();
        byte[][] result = new byte[width][height];

        for(int y = 0; y < height; y++) {
            innerList = outerList.get(y);
            for(int x = 0; x < width; x++) {
                Byte b = (x < innerList.size()) ? innerList.get(x) : null;
                result[x][y] = (b != null) ? b : 0;
            }
        }

        return new Level(width, height, result);
    }

    /**
     * Constructs example Level filled with bytes.
     * @return Example Level
     */
    static public Level example() {
        return fromString(
                "#######\n" +
                        "#.@ # #\n" +
                        "#$* $ #\n" +
                        "#   $ #\n" +
                        "# ..  #\n" +
                        "#  *  #\n" +
                        "#######");
    }

    /**
     * Returns a byte located at given (x, y) coordinates.
     * @param x the X coordinate
     * @param y the Y coordinate
     * @return byte located at given (x, y) coordinates
     */
    public byte get(int x, int y) {
        return bytes[x][y];
    }

    /**
     * Returns the width of this Level
     * @return width of this Level
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this Level
     * @return height of this Level
     */
    public int getHeight() {
        return height;
    }

    /**
     * Saves Level to File
     * @param file The file to which this Level should be saved.
     * @throws IOException throws IOException when unable to read file
     */
    public void toFile(File file) throws IOException {
        byte[] bytes = new byte[height * (width + 1)];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                bytes[y * (width + 1) + x] = this.bytes[x][y];
            }
            bytes[y * (width + 1) + width] = '\n';
        }
        Files.write(file.toPath(), bytes);
    }
}
