package net.btcbit;

import net.btcbit.transposition.Transposition;
import net.btcbit.util.FileUtil;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length < 2) {
            throw new IllegalArgumentException("Wrong input params count");
        }

        var pathToFile = args[0];
        var semitonesShift = Integer.parseInt(args[1]);

        var notes = FileUtil.readNotesFromFile(pathToFile);
        var transposition = new Transposition();
        var shiftedNotes = transposition.shift(notes, semitonesShift);
        FileUtil.writeToJsonFile(shiftedNotes, pathToFile + "_output");
    }
}
