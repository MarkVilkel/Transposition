package net.btcbit.transposition;

import java.util.List;

public class Transposition {

    public List<Note> shift(List<Note> notes, int semitonesShift) {
        return notes.stream().map(n -> shift(n, semitonesShift)).toList();
    }

    public Note shift(Note n, int semitonesShift) {
        return n.shift(semitonesShift).validate("Resulting");
    }

}
