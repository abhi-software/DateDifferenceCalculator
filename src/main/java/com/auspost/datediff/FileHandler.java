package com.auspost.datediff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {

    /**
     * Reads date pairs from the input file.
     * Each line should contain two dates separated by a comma.
     */
    public List<String> readDatePairs(String inputFile) throws IOException {
        List<String> datePairs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Skip empty lines
                    datePairs.add(line.trim());
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Input file not found: " + inputFile);
        } catch (IOException e) {
            throw new IOException("Error reading input file: " + e.getMessage(), e);
        }
        return datePairs;
    }

    /**
     * Writes the report lines to the output file.
     */
    public void writeReport(String outputFile, List<String> reportLines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : reportLines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            throw new IOException("Error writing to output file: " + e.getMessage());
        }
        
    }
}
