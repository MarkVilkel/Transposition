package net.btcbit.transposition;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public record Note(int octaveNumber, int noteNumber) {

    private static final int MAX_NOTE_NUMBER = 12;
    private static final int MIN_NOTE_NUMBER = 1;

    public Note validate(String prefix) {
        if (octaveNumber < -3 || octaveNumber > 5) {
            throw new IllegalArgumentException(prefix + " octave number " + octaveNumber + " is out of range [-3;5]");
        }

        if (noteNumber < MIN_NOTE_NUMBER || noteNumber > MAX_NOTE_NUMBER) {
            throw new IllegalArgumentException(prefix + " note number " + noteNumber + " is out of range [1;12]");
        }

        if (octaveNumber == -3 && noteNumber < 10) {
            throw new IllegalArgumentException(prefix + " note number " + noteNumber + " is out of range [10;12] for octave number -3");
        }

        if (octaveNumber == 5 && noteNumber > 1) {
             throw new IllegalArgumentException(prefix + " note number " + noteNumber + " is out of range [1;1] for octave number 5");
        }

        return this;
    }

    public Note shift(int semitonesShift) {
        var octavesShift = semitonesShift / MAX_NOTE_NUMBER;
        var notesShift = semitonesShift % MAX_NOTE_NUMBER;

        var newOctaveNumber = octaveNumber + octavesShift;
        var newNotesNumber = noteNumber + notesShift;

        if (newNotesNumber > MAX_NOTE_NUMBER) {
            newNotesNumber -= MAX_NOTE_NUMBER;
            newOctaveNumber ++;
        } else if (newNotesNumber < MIN_NOTE_NUMBER) {
            newNotesNumber = MAX_NOTE_NUMBER + newNotesNumber;
            newOctaveNumber--;
        }

        return new Note(newOctaveNumber, newNotesNumber);
    }
}
