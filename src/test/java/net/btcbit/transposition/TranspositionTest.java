package net.btcbit.transposition;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class TranspositionTest {

    @Test
    void testTransposition() {
        var transposition = new Transposition();
        var note = new Note(2, 1);

        var shifted = transposition.shift(note, 2);
        assertEquals(new Note(2, 3), shifted);

        shifted = transposition.shift(note, 12);
        assertEquals(new Note(3, 1), shifted);

        shifted = transposition.shift(note, -12);
        assertEquals(new Note(1, 1), shifted);


        shifted = transposition.shift(note, 12 * 3);
        assertEquals(new Note(5, 1), shifted);

        assertThrowsExactly(IllegalArgumentException.class, () -> transposition.shift(note, 12 * 3 + 1));

        shifted = transposition.shift(note, -12 * 4 - 3);
        assertEquals(new Note(-3, 10), shifted);

        assertThrowsExactly(IllegalArgumentException.class, () -> transposition.shift(note, -12 * 4 - 4));
    }

    @Test
    void testFileTransposition() throws IOException {
        var json = readResource("input.json");
        var objectMapper = new ObjectMapper();

        List<Note> notes = objectMapper.readValue(json, new TypeReference<>(){});

        var transposition = new Transposition();
        var shifted = transposition.shift(notes, -3);

        var expectedShiftedJson = readResource("output.json");
        assertEquals(expectedShiftedJson, objectMapper.writeValueAsString(shifted));
    }

    String readResource(String fileName) throws IOException {
        var resource = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (resource != null) {
            return IOUtils.toString(resource, UTF_8);
        }
        return null;
    }

}