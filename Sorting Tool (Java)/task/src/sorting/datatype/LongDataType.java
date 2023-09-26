package sorting.datatype;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class LongDataType extends DataType {
    List<Long> longs = new ArrayList<>();

    public LongDataType(String inputFile) throws FileNotFoundException {
        if (inputFile!=null){
            SCANNER = new Scanner(new File(inputFile));
        }else {
            SCANNER = new Scanner(System.in);
        }
    }
    @Override
    public String sortByNaturalOrder() {
        Collections.sort(longs);

        StringBuilder sb = new StringBuilder();
        sb.append("Total numbers: ").append(longs.size()).append(".\n");
        sb.append("Sorted data: ");
        longs.forEach(elem -> sb.append(elem).append(" "));

        SCANNER.close();
        return sb.toString();
    }

//    Need to be overwritten like at LineDataType
//    Too much lines but working
    @Override
    public String sortByCount() {
        StringBuilder sb = new StringBuilder();
        Map<Long, Long> countMap = longs.stream()
                .collect(Collectors.groupingBy(
                        // Group by the long value
                        Long::longValue,
                        // Count occurrences
                        Collectors.counting()
                ));
        //Sorting map by appearance of valued
        List<Map.Entry<Long, Long>> sortedByValue = countMap.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toList());

        sb.append(String.format("Total numbers: %s.%n", longs.size()));


        for (int i=0;i<sortedByValue.size();i++
        ) {
            var entry= sortedByValue.get(i);
            int percent = Math.round((float) entry.getValue() /longs.size()*100);
           String toAppend =String.format("%s: %s time(s), %s%c%n",entry.getKey(),entry.getValue(),percent,'%');
            sb.append(toAppend);
        }
        SCANNER.close();
        return sb.toString();
    }

    @Override
    public void input() {
        while (SCANNER.hasNext()) {
            String longNumber = SCANNER.next();
            try {
                String[] longArr = longNumber.split("[\\s]+");
                for (var l : longArr
                ) {
                    longs.add(Long.parseLong(l));
                }
            } catch (NumberFormatException e) {
                System.out.println("\""+longNumber+" is not a long. It will be skipped.");
            }
        }
    }

}
