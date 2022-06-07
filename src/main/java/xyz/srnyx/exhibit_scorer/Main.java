package xyz.srnyx.exhibit_scorer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;


public class Main {
    /**
     * Main method for the program
     *
     * @param   args    command line arguments
     */
    public static void main(String[] args) {
        final ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Format());

        final Logger logger = Logger.getLogger("Exhibit-Scorer");
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);


        if (args.length < 2) {
            logger.warning("Usage: \"java -jar Exhibit-Scorer-all.jar <shakespeare|slang> <sentence...>\"");
            return;
        }

        final String type = args[0];

        if (!(type.equals("shakespeare") || type.equals("slang"))) {
            logger.warning("Invalid type: \"" + type + "\"");
            return;
        }

        final String[] sentence = Arrays.copyOfRange(args, 1, args.length);
        logger.info("Score: " + check(type, sentence) + "\n");
        logger.info("ALL words: " + Arrays.toString(sentence));
        logger.info("PASSED words: " + words(type, sentence, true));
        logger.info("FAILED words: " + words(type, sentence, false));
    }

    /**
     * Checks the sentence for the given type's words
     *
     * @param   type        the type of words to check for
     * @param   sentence    the sentence to check
     *
     * @return              how many words were found
     */
    private static int check(String type, String[] sentence) {
        List<String> list = new ArrayList<>();
        if (type.equalsIgnoreCase("shakespeare")) list = shakespeare();
        if (type.equalsIgnoreCase("slang")) list = slang();

        int points = 0;
        for (final String word : sentence) if (list.contains(trim(word))) points++;
        return points;
    }

    /**
     * Returns a list of the given type's words that were either found or not found
     *
     * @param   type        the type of words to check for
     * @param   sentence    the sentence to check
     * @param   passed      whether to return the words that were found or not found
     *
     * @return              a list of the given type's words that were either found or not found
     */
    private static List<String> words(String type, String[] sentence, boolean passed) {
        List<String> list = new ArrayList<>();
        if (type.equalsIgnoreCase("shakespeare")) list = shakespeare();
        if (type.equalsIgnoreCase("slang")) list = slang();

        final List<String> words = new ArrayList<>();
        for (final String word : sentence) {
            if (passed) {
                if (list.contains(trim(word))) words.add(word);
                continue;
            }

            if (!list.contains(trim(word))) words.add(word);
        }
        return words;
    }

    /**
     * Removes non-alphabet characters from the given word
     *
     * @param   word    the word to trim
     *
     * @return          the trimmed word
     */
    private static String trim(String word) {
        return word.replaceAll("[^A-Za-z]", "").toLowerCase().trim();
    }

    /**
     * @return  a list of the Shakespeare words
     */
    private static List<String> shakespeare() {
        final List<String> words = new ArrayList<>();

        words.add("hobnob");
        words.add("academe");
        words.add("moonbeam");
        words.add("frugal");
        words.add("equivocal");
        words.add("obsequious");
        words.add("remorseless");
        words.add("madcap");
        words.add("zany");
        words.add("dauntless");
        words.add("tranquil");
        words.add("thee");
        words.add("thou");
        words.add("thy");
        words.add("thine");
        words.add("art");
        words.add("wert");

        return words;
    }

    /**
     * @return  a list of the slang words
     */
    private static List<String> slang() {
        final List<String> words = new ArrayList<>();

        words.add("buggin");
        words.add("trippin");
        words.add("illin");
        words.add("word");
        words.add("poppin");
        words.add("bomb");
        words.add("flava");
        words.add("fresh");
        words.add("turn");
        words.add("fly");
        words.add("doubt");
        words.add("frontin");
        words.add("steelo");
        words.add("dope");
        words.add("swag");
        words.add("yolo");

        return words;
    }
}