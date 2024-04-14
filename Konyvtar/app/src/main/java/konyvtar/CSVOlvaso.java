package konyvtar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CSVOlvaso {

    public List<String[]> olvasCSV(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String[]> sorok = Files.lines(path)
                .map(sor -> sor.split(";"))
                .collect(Collectors.toList());
        return sorok;
    }
}
