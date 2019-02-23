package core;

/**
 * Reads the two CSV course files, then populates a new data file. Will be separated/removed in final ver.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadPopulateCSV {

	public static void main(String[] args) {

		String csvFile = "input/schedules/2018csv.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] data = line.split(cvsSplitBy);

				System.out.println("[sub= " + data[0] + " , num=" + data[1] + " , sec=" + data[2] + 
						" , qud=" + data[3] + " , tit=" + data[4] + 
						" , crd=" + data[5] + " , tim=" + data[6] + "]");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
