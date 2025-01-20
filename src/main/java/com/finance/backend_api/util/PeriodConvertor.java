package com.finance.backend_api.util;

import com.finance.backend_api.exceptions.PeriodInvalidException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PeriodConvertor {
    private static final Map<String, String> monthMapping = new HashMap<>();

    static {
        monthMapping.put("enero", "01");
        monthMapping.put("febrero", "02");
        monthMapping.put("marzo", "03");
        monthMapping.put("abril", "04");
        monthMapping.put("mayo", "05");
        monthMapping.put("junio", "06");
        monthMapping.put("julio", "07");
        monthMapping.put("agosto", "08");
        monthMapping.put("septiembre", "09");
        monthMapping.put("octubre", "10");
        monthMapping.put("noviembre", "11");
        monthMapping.put("diciembre", "12");
    }

    // Método para convertir un periodo como "Enero 2025", "Q1 2025" o "2025" a un rango de fechas
    public static Date[] convertPeriodToDateRange(String period) {
        String[] parts = period.split(" ");

        if (parts.length == 1) {
            if (parts[0].matches("\\d{4}")) {
                // Caso: Solo un año, como "2025"
                return convertYearToDateRange(parts[0]);
            }
        } else if (parts.length == 2) {
            if (parts[0].toUpperCase().contains("Q")) {
                // Caso: Trimestres como "Q1 2025"
                return convertQuarterToDateRange(parts[0], parts[1]);
            } else {
                // Caso: Meses como "Enero 2025"
                return convertMonthYearToDateRange(period);
            }
        }

        throw new PeriodInvalidException("Formato de periodo no válido");
    }

    public static Date[] convertMonthYearToDateRange(String period) {
        String[] parts = period.split(" ");
        if (parts.length != 2) {
            throw new PeriodInvalidException("Formato de mes/año inválido");
        }
        String monthName = parts[0];
        String year = parts[1];

        String monthNumber = monthMapping.get(monthName);
        if (monthNumber == null) {
            throw new PeriodInvalidException("Mes no válido");
        }

        LocalDate startDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(monthNumber), 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        java.sql.Date startSqlDate = java.sql.Date.valueOf(startDate);
        java.sql.Date endSqlDate = java.sql.Date.valueOf(endDate);

        return new java.sql.Date[]{startSqlDate, endSqlDate};
    }

    public static Date[] convertQuarterToDateRange(String quarter, String year) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        switch (quarter.toUpperCase()) {
            case "Q1":
                startDate = LocalDate.of(Integer.parseInt(year), 1, 1);
                endDate = startDate.withMonth(3).withDayOfMonth(31);
                break;
            case "Q2":
                startDate = LocalDate.of(Integer.parseInt(year), 4, 1);
                endDate = startDate.withMonth(6).withDayOfMonth(30);
                break;
            case "Q3":
                startDate = LocalDate.of(Integer.parseInt(year), 7, 1);
                endDate = startDate.withMonth(9).withDayOfMonth(30);
                break;
            case "Q4":
                startDate = LocalDate.of(Integer.parseInt(year), 10, 1);
                endDate = startDate.withMonth(12).withDayOfMonth(31);
                break;
            default:
                throw new PeriodInvalidException("Trimestre no válido");
        }

        java.sql.Date startSqlDate = java.sql.Date.valueOf(startDate);
        java.sql.Date endSqlDate = java.sql.Date.valueOf(endDate);

        return new java.sql.Date[]{startSqlDate, endSqlDate};
    }

    public static Date[] convertYearToDateRange(String year) {
        LocalDate startDate = LocalDate.of(Integer.parseInt(year), 1, 1);
        LocalDate endDate = LocalDate.of(Integer.parseInt(year), 12, 31);

        java.sql.Date startSqlDate = java.sql.Date.valueOf(startDate);
        java.sql.Date endSqlDate = java.sql.Date.valueOf(endDate);

        return new java.sql.Date[]{startSqlDate, endSqlDate};
    }
}