package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final int DATA_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOUR_INDEX = 2;
    public static final int PAY_INDEX = 3;
    public static final int EXTRA_DAY = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateF = LocalDate.parse(dateFrom.replace(".", "-"), FORMATTER);
        LocalDate dateT = LocalDate.parse(dateTo.replace(".", "-"), FORMATTER);
        int totalSalary;
        String[] array;
        String result = "";
        for (String name : names) {
            totalSalary = 0;
            for (String datum : data) {
                array = datum.split(" ");
                if (name.equals(array[NAME_INDEX])
                        && (LocalDate.parse(array[DATA_INDEX].replace(".", "-"), FORMATTER)
                        .isAfter(dateF)
                        && LocalDate.parse(array[DATA_INDEX].replace(".", "-"), FORMATTER)
                        .isBefore(dateT.plusDays(EXTRA_DAY)))) {
                    totalSalary += Integer.parseInt(array[HOUR_INDEX])
                            * Integer.parseInt(array[PAY_INDEX]);
                }
            }
            StringBuilder builder = new StringBuilder();
            builder.append(name).append(" - ").append(totalSalary);
            result += builder.toString() + System.lineSeparator();
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator()
                + result.trim();
    }
}
