package salary.income.predictor;
import java.util.Scanner;

public class PredictorMain {

	public static void main(String[] args) {
		//Read Data
		double salary = Double.parseDouble(getInput("Salary"));
		double incPerc = Double.parseDouble(getInput("Increment percentage"));
		int incFrequency =  Integer.parseInt(getInput("Increment frequency"));
		double dedPerc = Double.parseDouble(getInput("Deduction percentage"));
		int dedFrequency = Integer.parseInt(getInput("Deduction frequency"));
		int predYears =  Integer.parseInt(getInput("Predication for years"));
		// Generate Report
		Predictor pred = new Predictor();
		pred.generatereport(salary, incPerc, incFrequency, dedPerc, dedFrequency, predYears);
	}
	
	static String getInput(String fieldName)
	{
		String readInput = "";
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		boolean isInputInValid = true;
		do {
			System.out.println(String.format("Enter %s:", fieldName));
			if (fieldName.contains("frequency"))
			{
				System.out.println("4-Quarterly, 2-Half Yearly, 1-Annually..etc");
			}
			try {
			readInput = input.nextLine();
						
			if (Double.parseDouble(readInput) < 0)
				System.out.println(String.format("%s should not be less than 1", fieldName));
			else
				isInputInValid = false;
			}
			catch(Exception e)
			{
				System.out.println(String.format("%s is invalid", fieldName));
			}
			
		} while(isInputInValid);
		
		return readInput;
	}

}
