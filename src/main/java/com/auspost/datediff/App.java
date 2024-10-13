package com.auspost.datediff;

import java.util.List;
import java.io.File;

public class App {
    public static void main(String[] args) {

        String inputFile = null;
        String outputFile = "date-difference-report.dat";
        String outputFileContainingException = "date-difference-report-exception.dat";

        // Define the output directory relative to the project root
        String outputDirPath = "output";

        // Define the input directory relative to the project root
        String inputDirPath = "input";
        File inputDir = new File(inputDirPath);

        inputFile = readingInputDirectoryForInputFileProcessing(inputFile, inputDir);

        if (inputFile == null)
            inputFile = "parcel-sent-receive-date.dat";

        FileHandler fileHandler = new FileHandler();
        DateValidator dateValidator = new DateValidator();
        DateParser dateParser = new DateParser();
        DateCalculator dateCalculator = new DateCalculator();

        try {
            List<String> datePairs = fileHandler.readDatePairs(inputDirPath + "/" + inputFile);
            List<String> reportLines = new java.util.ArrayList<>();
            List<String> reportLinesException = new java.util.ArrayList<>();

            for (String pair : datePairs) {
                String[] dates = pair.split(",");
                if (dates.length != 2) {
                    reportLinesException
                            .add(pair.trim() + ", Invalid format. Expected two dates separated by a comma. Example:"
                                    + "DD MM YYYY, DD MM YYYY.");
                    continue;
                }

                String dateStr1 = dates[0].trim();
                String dateStr2 = dates[1].trim();

                String validationError1 = dateValidator.validateDate(dateStr1);
                String validationError2 = dateValidator.validateDate(dateStr2);

                if (validationError1 != null || validationError2 != null) {
                    StringBuilder errorDesc = new StringBuilder();
                    if (validationError1 != null) {
                        errorDesc.append("First date invalid: ").append(validationError1).append(". ");
                    }
                    if (validationError2 != null) {
                        errorDesc.append("Second date invalid: ").append(validationError2).append(".");
                    }
                    // Trim any trailing spaces
                    String errorLine = dateStr1 + ", " + dateStr2 + ", " + errorDesc.toString().trim();
                    reportLinesException.add(errorLine);
                    continue;
                }

                // Parse Dates
                int[] parsedDate1 = dateParser.parseDate(dateStr1);
                int[] parsedDate2 = dateParser.parseDate(dateStr2);

                // Calculate Difference
                int difference = dateCalculator.calculateDifference(parsedDate1, parsedDate2);

                // Prepare Report Line
                String reportLine = dateStr1 + ", " + dateStr2 + ", " + difference;
                reportLines.add(reportLine);
            }

            // Write Report
            fileHandler.writeReport(outputDirPath + "/" + outputFile, reportLines);
            fileHandler.writeReport(outputDirPath + "/" + outputFileContainingException, reportLinesException);
            System.out.println("Date difference report generated successfully. Check " + outputFile);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static String readingInputDirectoryForInputFileProcessing(String inputFile, File inputDir) {
        // Check if the input directory exists and is indeed a directory
        if (inputDir.exists() && inputDir.isDirectory()) {
            // List all files in the input directory
            File[] files = inputDir.listFiles();

            // Ensure there are files to process
            if (files != null && files.length > 0) {
                // Iterate through each file in the directory
                for (File file : files) {
                    if (file.isFile()) { // Ensure it's a file and not a subdirectory
                        // Assign the filename to the inputFile variable
                        inputFile = file.getName();
                        System.out.println("Processing input file: " + inputFile);
                    }
                }
            } else {
                System.out.println("No files found in the input directory.");
                inputFile = null;
            }
        } else {
            System.out.println("Input directory does not exist or is not a directory.");
        }
        return inputFile;
    }
}
