package sorting;

import sorting.datatype.DataType;
import sorting.datatype.LineDataType;
import sorting.datatype.LongDataType;
import sorting.datatype.WordDataType;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(final String[] args) {
        try {

            Map<String, String> paramArgMap = extractArgs(args);
            String dataType = paramArgMap.get("-dataType")==null?"word":paramArgMap.get("-dataType");
            String sortedType = paramArgMap.get("-sortingType");
            String inputFile = paramArgMap.get("-inputFile");
            String outputFile = paramArgMap.get("-outputFile");
            DataType dt = setDT(dataType,inputFile);

            dt.input();
            if (outputFile==null||!Arrays.toString(args).contains("-outputFile")){
                if ("natural".equals(sortedType) || (sortedType == null && !Arrays.toString(args).contains("-sortingType"))) {
                    System.out.println(dt.sortByNaturalOrder());
                } else if ("byCount".equals(sortedType)) {
                    System.out.println(dt.sortByCount());
                }
            }else{
                if ("natural".equals(sortedType) || (sortedType == null && !Arrays.toString(args).contains("-sortingType"))) {
                    String str = dt.sortByNaturalOrder();
                    FileWriter fileWriter = new FileWriter(outputFile);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.print(str);
                } else if ("byCount".equals(sortedType)) {
                    String str = dt.sortByCount();
                    FileWriter fileWriter = new FileWriter(outputFile);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.print(str);
                }
            }

        } catch (RuntimeException | IOException e) {
            System.err.println(e.getMessage());
        }finally {
            System.err.println();
        }

    }

    private static Map<String, String> extractArgs(String[] args) {
//        Store parameter value pairs
        Map<String, String> argMap = new HashMap<>();
        String sortingType = null;
        String dataType = null;
        String inputType;
        String outputFile;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-sortingType":
                    sortingType = (i < args.length - 1) ? args[i + 1] : null;
                    argMap.put("-sortingType", sortingType);
                    break;
                case "-dataType":
                    dataType = (i < args.length - 1) ? args[i + 1] : null;
                    argMap.put("-dataType", dataType);
                    break;
                case "-inputFile":
                    inputType = (i < args.length - 1) ? args[i + 1] : null;
                    argMap.put("-inputType", inputType);
                    break;
                case "-outputFile":
                    outputFile = (i < args.length - 1) ? args[i + 1] : null;
                    argMap.put("-outputFile",outputFile);
                    break;
                default:
                    if (arg.startsWith("-")) {
                        System.out.println(arg + " is not a valid parameter. It will be skipped.");
                    }
                    break;
            }
        }

        if (sortingType == null && Arrays.toString(args).contains("-sortingType")) {
            System.out.format("No sorting type defined!%n");
        }
        if (dataType == null) {
            System.out.format("No data type defined!%n");
        }
        return argMap;
    }


    private static DataType setDT(String dataType,String inputFile) throws FileNotFoundException {
        DataType dt = switch (dataType) {
            case "word": {
                yield new WordDataType(inputFile);
            }
            case "line": {
                yield new LineDataType(inputFile);
            }
            case "long": {
                yield new LongDataType(inputFile);
            }
            default:
                throw new IllegalStateException("Unexpected dataType: " + dataType);
        };
        return dt;
    }
}

