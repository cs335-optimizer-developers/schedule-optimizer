package core;

/**
 * 
 * @author James White
 *
 */
public class Source {
	public static String generated_schedule;
	public static String testing_schedule;
	public static String catalog_folder;
	public static String curriculae_folder;
	public static String details_folder;
	public static String programs_folder;
	public static String fall_2018;
	public static String spring_2019;

	static {
		String basic = "././optimizer/";
		generated_schedule = basic + "output/generated-schedule.csv";
		testing_schedule = basic + "output/testing-schedule";
		String input = basic + "input/";
		catalog_folder = input + "catalog/";
		curriculae_folder = input + "curriculae/";
		details_folder = input + "details/";
		programs_folder = input + "programs/";
		fall_2018 = input + "schedules/fall-2018.csv";
		spring_2019 = input + "schedules/spring-2019.csv";
	}
}
