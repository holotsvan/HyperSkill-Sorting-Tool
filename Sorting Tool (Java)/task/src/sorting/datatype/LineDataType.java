package sorting.datatype;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class LineDataType extends DataType {
    List<String> lines = new ArrayList<>();
    public LineDataType(String inputFile) throws FileNotFoundException {
        if ("".equals(inputFile)){
            SCANNER = new Scanner(new File(inputFile));
        }else {
            SCANNER = new Scanner(System.in);
        }
    }
    @Override
    public String sortByNaturalOrder() {
        lines.sort(Comparator.comparing(Long::valueOf));

                StringBuilder sb = new StringBuilder();
        sb.append(String.format("Total words: %s.%n",lines.size()));
        sb.append("Sorted data: ");
        lines.forEach(elem -> sb.append(elem).append("\n"));

        SCANNER.close();
        return sb.toString();
    }

    @Override
    public String sortByCount() {

        Map<String, Integer> dataToCount = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        lines.forEach(elem -> dataToCount.put(elem, dataToCount.getOrDefault(elem, 0) + 1));

        Map<Integer, List<String>> countToData = new TreeMap<>();

        dataToCount.forEach((key, value) -> {
            if (!countToData.containsKey(value)) {
                countToData.put(value, new ArrayList<>());
            }
            countToData.get(value).add(key);
        });

        sb.append(String.format("Total numbers: %s%n",lines.size()));
        countToData.forEach((key, value) -> {
            Collections.sort(value);
            int percents = key * 100 / lines.size();
            for (var elem : value) {
                sb.append(elem).append(": ").append(key).append("time(s), ").append(percents).append("%\n");
            }
        });

        SCANNER.close();
        return sb.toString();
    }

    @Override
    public void input() {
        while (SCANNER.hasNextLine()) {
            var line = SCANNER.nextLine();
            lines.add(line);
        }
    }
}
