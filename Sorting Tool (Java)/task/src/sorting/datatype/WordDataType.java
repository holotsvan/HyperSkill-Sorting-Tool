package sorting.datatype;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class WordDataType extends DataType{
    List<String> words=new ArrayList<>();
    public WordDataType(String inputFile) throws FileNotFoundException {
        if ("".equals(inputFile)){
            SCANNER = new Scanner(new File(inputFile));
        }else {
            SCANNER = new Scanner(System.in);
        }
    }
    @Override
    public String sortByNaturalOrder() {
        words.sort(Comparator.comparing(Long::valueOf));

        StringBuilder out = new StringBuilder();
        out.append("Total words: ").append(words.size()).append(".\n");
        out.append("Sorted data: ");
        words.forEach(elem -> out.append(elem).append(" "));

        SCANNER.close();
        return out.toString();
    }

    //    Need to be overwritten like at LineDataType
//    Too much lines but working
    @Override
    public String sortByCount() {

        StringBuilder sb = new StringBuilder();
        Map<String, Long> countMap = words.stream()
                .collect(Collectors.groupingBy(
                        word -> word, // Key: the word itself
                        LinkedHashMap::new, // Use a LinkedHashMap to maintain order of insertion
                        Collectors.counting() // Value: count of occurrences
                ));

        //Sorting map by appearance of valued
        List<Map.Entry<String, Long>> sortedByValue = countMap.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toList());

        sb.append(String.format("Total words: %s%n",words.size()));
        for (int i=0;i<sortedByValue.size();i++
        ) {
            var entry= sortedByValue.get(i);
            int percent = Math.round((float) entry.getValue() /words.size()*100);
            String toAppend =String.format("%s: %s time(s), %s%c%n",entry.getKey(),entry.getValue(),percent,'%');
            sb.append(toAppend);
        }

        SCANNER.close();
        return sb.toString();
    }

    @Override
    public void input() {
        while (SCANNER.hasNextLine()) {
            var line= SCANNER.nextLine();
            Collections.addAll(words, line.split("[\\s]+"));
        }
    }
}
