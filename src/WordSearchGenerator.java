import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class WordSearchGenerator {

    private char[][] puzzle;
    private ArrayList<String> words;

    public WordSearchGenerator(int size) {
        puzzle = new char[size][size];
        words = new ArrayList<>();
        initializePuzzle();
    }

    private void initializePuzzle() {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                puzzle[i][j] = ' ';
            }
        }
    }

    public void addWord(String word) {
        words.add(word.toUpperCase());
    }

    public void generate() {
        Collections.shuffle(words);
        Random random = new Random();
        for (String word : words) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(puzzle.length);
                int y = random.nextInt(puzzle.length);
                int dirX = random.nextInt(3) - 1;
                int dirY = random.nextInt(3) - 1;
                if (dirX == 0 && dirY == 0) {
                    continue;
                }
                if (tryPlaceWord(word, x, y, dirX, dirY)) {
                    placed = true;
                }
            }
        }
        fillPuzzle();
    }

    private boolean tryPlaceWord(String word, int x, int y, int dirX, int dirY) {
        if (x + word.length() * dirX < 0 || x + word.length() * dirX >= puzzle.length) {
            return false;
        }
        if (y + word.length() * dirY < 0 || y + word.length() * dirY >= puzzle.length) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int cx = x + i * dirX;
            int cy = y + i * dirY;
            if (puzzle[cx][cy] != ' ' && puzzle[cx][cy] != c) {
                return false;
            }
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int cx = x + i * dirX;
            int cy = y + i * dirY;
            puzzle[cx][cy] = c;
        }
        return true;
    }

    private void fillPuzzle() {
        Random random = new Random();
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] == ' ') {
                    puzzle[i][j] = (char) (' ');
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        WordSearchGenerator generator = new WordSearchGenerator(10);
        generator.addWord("JAVA");
        generator.addWord("PROGRAM");
        generator.addWord("WORD");
        generator.addWord("SEARCH");
        generator.generate();
        generator.print();
    }
}