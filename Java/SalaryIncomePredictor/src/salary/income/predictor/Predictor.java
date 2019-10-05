package salary.income.predictor;

import java.util.ArrayList;
import java.util.List;

public class Predictor {
	IPredictor pred;
	public Predictor() {
		pred = (double salary, double incrPerc, int incFrequency, double dedPerc, int dedFrequency, int predYears) -> {
			
			double yearlyIncSalry = salary;
			double yearlyDedSalry = salary;
			double salGrowth = salary;
			double incAmount, dedAmount = 0;
			double incGAmount, dedGAmount = 0;
			List<Increment> incList = new ArrayList<Increment>();
			List<Deduction> dedList = new ArrayList<Deduction>();
			List<Prediction> predList = new ArrayList<Prediction>();
			// Prepare the List
			for (int i=1; i<=predYears; i++)
			{
				incAmount = ((yearlyIncSalry *  incrPerc)/100) * incFrequency;
				incList.add( new Increment(i, yearlyIncSalry, incFrequency,  incrPerc, incAmount));
				dedAmount = ((yearlyDedSalry *  dedPerc)/100) * dedFrequency;
				dedList.add( new Deduction(i, yearlyDedSalry, dedFrequency,  dedPerc, dedAmount));
				incGAmount = ((salGrowth *  incrPerc)/100) * incFrequency;
				dedGAmount = ((salGrowth *  incrPerc)/100) * incFrequency;
				predList.add(new Prediction(i, salGrowth, incGAmount, dedGAmount, ((salGrowth - dedGAmount)+ incGAmount))) ;
				yearlyIncSalry += incAmount;
				yearlyDedSalry -= dedAmount;
				salGrowth -= dedGAmount;
				salGrowth += incGAmount;
			}
			
			// Increment Report
			System.out.println("a. Increment Report");
			System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------"));
			System.out.println(String.format("|%-10s|%-30s|%-25s|%-25s|%-30s|", "Year", "Starting Salary", "Number of Increments", "Increment %", "Increment Amount"));
			System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------"));
			incList.forEach(a-> {
				System.out.println(String.format("|%-10d|%-30.2f|%-25d|%-25.2f|%-30.2f|", a.year, a.startSal, a.numbInc, a.incPerc, a.incAmt));
				System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------"));
				
				});
			//Deduction Report
			System.out.println("b. Deduction Report");
			System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------"));
			System.out.println(String.format("|%-10s|%-30s|%-25s|%-25s|%-30s|", "Year", "Starting Salary", "Number of Deductions", "Deduction %", "Deduction Amount"));
			System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------"));
			dedList.forEach(a-> {
				System.out.println(String.format("|%-10d|%-30.2f|%-25d|%-25.2f|%-30.2f|", a.year, a.startSal, a.numbDed, a.dedPerc, a.dedAmt));
				System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------"));
				
				});
			// Prediction Report
			System.out.println("c. Prediction Report");
			System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------"));
			System.out.println(String.format("|%-10s|%-30s|%-25s|%-25s|%-30s|", "Year", "Starting Salary", "Increment Amount", "Deduction Amount", "Salary Growth"));
			System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------"));
			predList.forEach(a-> {
				System.out.println(String.format("|%-10d|%-30.2f|%-25.2f|%-25.2f|%-30.2f|", a.year, a.startSal, a.incAmt, a.dedAmt, a.salGrowth));
				System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------"));
				
				});
		};
	}
	
	public void generatereport(double salary, double incrperc, int incfrequency, double incomeded, int dedfrequency, int predYears)
	{
		pred.generatereport(salary, incrperc, incfrequency, incomeded, dedfrequency, predYears);
	}
}


