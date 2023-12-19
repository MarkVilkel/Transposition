package net.btcbit;

import java.io.IOException;

import net.btcbit.transposition.Transposition;
import net.btcbit.util.FileUtil;

public class Application {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length < 2) {
            throw new IllegalArgumentException("Wrong input params count");
        }

        var inputFileName = args[0];
        var semitonesShift = Integer.parseInt(args[1]);
        var outputFileName = args.length > 2 ? args[2] : inputFileName + "_output";

        var notes = FileUtil.readNotesFromFile(inputFileName);
        var transposition = new Transposition();
        var shiftedNotes = transposition.shift(notes, semitonesShift);
        FileUtil.writeToJsonFile(shiftedNotes, outputFileName);
    }
}
