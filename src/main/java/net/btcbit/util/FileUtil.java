package net.btcbit.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.btcbit.transposition.Note;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static List<Note> readNotesFromFile(String pathToFile) throws IOException {
        var json = FileUtils.readFileToString(new File(pathToFile), UTF_8);
        return OBJECT_MAPPER.readValue(json, new TypeReference<>(){});
    }

    public static void writeToJsonFile(List<Note> shiftedNotes, String fileName) throws IOException {
        var json = OBJECT_MAPPER.writeValueAsString(shiftedNotes);
        FileUtils.writeStringToFile(new File(fileName), json, UTF_8);
    }

}
