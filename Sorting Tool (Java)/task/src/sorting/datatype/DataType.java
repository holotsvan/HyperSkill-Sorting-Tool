package sorting.datatype;

import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class DataType {

    protected Scanner SCANNER = new Scanner(System.in);
    public abstract String sortByNaturalOrder();
    public abstract String sortByCount();
    public abstract void input();

}
