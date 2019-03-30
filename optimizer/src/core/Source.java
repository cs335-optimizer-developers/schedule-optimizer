package core;

/**
 * 
 * Class to model locations of files to be referenced throughout. Used
 * especially if files move locations, and to keep the code organized.
 *
 */
public class Source {
	public static final String generated_schedule;
	public static final String testing_schedule;
	public static final String catalog_folder;
	public static final String curriculae_folder;
	public static final String details_folder;
	public static final String programs_folder;
	public static final String fall_2018;
	public static final String spring_2019;
	public static final String error_log;

	static {
		String basic = "";
		generated_schedule = basic + "output/generated-schedule.csv";
		testing_schedule = basic + "output/testing-schedule.csv";
		error_log = basic + "output/error-log.txt";
		String input = basic + "input/";
		catalog_folder = input + "catalog/";
		curriculae_folder = input + "curriculae/";
		details_folder = input + "details/";
		programs_folder = input + "programs/";
		fall_2018 = input + "schedules/fall-2018.csv";
		spring_2019 = input + "schedules/spring-2019.csv";
	}
}
