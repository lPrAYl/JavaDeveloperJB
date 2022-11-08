package sorting.Data;

import java.io.*;
import java.util.*;

public class Data {
    protected Scanner scanner;
    protected SortingType sortingType;
    protected DataType dataType;

    public Data() {
        scanner = new Scanner(System.in);
        sortingType = SortingType.NATURAL;
        dataType = DataType.WORD;
    }

    private void setSortingType(String type) {
        switch (type) {
            case "natural" -> this.sortingType = SortingType.NATURAL;
            case "byCount" -> this.sortingType = SortingType.BY_COUNT;
        }
    }

    private void setDataType(String type) {
        switch (type) {
            case "long" -> this.dataType = DataType.LONG;
            case "line" -> this.dataType = DataType.LINE;
            case "word" -> this.dataType = DataType.WORD;
        }
    }

    private boolean isOpenInputFile(String inputFile) {
        File file = new File(inputFile);

        try (Scanner scanner = new Scanner(file)) {
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private boolean isOpenOutputFile(String outputFile) {
        File file = new File(outputFile);

        try {
            System.setOut(new PrintStream(file));
        } catch (FileNotFoundException e) {
            System.out.printf("There is no output file %s", outputFile);
            return false;
        }

        return true;
    }

    private boolean isParsing(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (Objects.equals(args[i], "-dataType")) {
                if (i < args.length - 1 && List.of("long", "line", "word").contains(args[i + 1])) {
                    setDataType(args[i + 1]);
                } else {
                    System.out.println("No data type defined!");
                    return false;
                }
            } else if (Objects.equals(args[i], "-sortingType")) {
                if (i < args.length - 1 && List.of("natural", "byCount").contains(args[i + 1])) {
                    setSortingType(args[i + 1]);
                } else {
                    System.out.println("No sorting type defined!");
                    return false;
                }
            } else if (Objects.equals(args[i], "-inputFile")) {
                if (!isOpenInputFile(args[i + 1])) {
                    return false;
                }
            } else if (Objects.equals(args[i], "-outputFile")) {
                if (!isOpenOutputFile(args[i + 1])) {
                    return false;
                }
            }
        }

        return true;
    }

    public void process(String[] args) {
        if (isParsing(args)) {
            switch (this.dataType.name()) {
                case "LONG" -> printInfoLong();
                case "LINE" -> printInfoLine();
                case "WORD" -> printInfoWord();
            }
        }
    }

    public void printInfoLong() {
        Map<Long, Integer> mapOfElem = new TreeMap<>();
        ArrayList<Long> listOfElem = new ArrayList<>();

        while (scanner.hasNext()) {
            String str = scanner.next();
            try {
                long number = Long.parseLong(str);
                listOfElem.add(number);
                mapOfElem.put(number, mapOfElem.getOrDefault(number, 0) + 1);
            } catch (NumberFormatException e) {
                System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", str);
            }
        }
        int totalNumbers = listOfElem.size();

        System.out.printf("Total numbers: %d.\n", totalNumbers);
        if (sortingType == SortingType.NATURAL) {
            System.out.print("Sorted data:");
            Collections.sort(listOfElem);
            for (Long number: listOfElem) {
                System.out.print(" " + number);
            }
        } else if (sortingType == SortingType.BY_COUNT) {
            mapOfElem.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.printf("%s: %d time(s), %d%%\n",
                            entry.getKey(), entry.getValue(), entry.getValue() * 100 / totalNumbers));
        }
    }

    public void printInfoLine() {
        Map<String, Integer> mapOfElem = new TreeMap<>();
        ArrayList<String> listOfElem = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            listOfElem.add(str);
            mapOfElem.put(str, mapOfElem.getOrDefault(str, 0) + 1);
        }
        int totalLines = listOfElem.size();

        System.out.printf("Total lines: %d.\n", totalLines);
        if (sortingType == SortingType.NATURAL) {
            System.out.print("Sorted data:");
            Collections.sort(listOfElem);
            for (String str: listOfElem) {
                System.out.print(" " + str);
            }
        } else if (sortingType == SortingType.BY_COUNT) {
            mapOfElem.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach( entry -> System.out.printf("%s: %d time(s), %d%%\n",
                            entry.getKey(), entry.getValue(), entry.getValue() * 100 / totalLines));
        }
    }

    public void printInfoWord() {
        Map<String, Integer> mapOfElem = new TreeMap<>();
        ArrayList<String> listOfElem = new ArrayList<>();

        while (scanner.hasNext()) {
            String str = scanner.next();
            listOfElem.add(str);
            mapOfElem.put(str, mapOfElem.getOrDefault(str, 0) + 1);
        }
        int totalNumbers = listOfElem.size();

        System.out.printf("Total words: %d.\n", totalNumbers);
        if (sortingType == SortingType.NATURAL) {
            System.out.print("Sorted data:");
            Collections.sort(listOfElem);
            for (String number: listOfElem) {
                System.out.print(" " + number);
            }
        } else if (sortingType == SortingType.BY_COUNT) {
            mapOfElem.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.printf("%s: %d time(s), %d%%\n",
                            entry.getKey(), entry.getValue(), entry.getValue() * 100 / totalNumbers));
        }
    }

}









