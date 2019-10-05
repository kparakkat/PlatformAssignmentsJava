package salary.income.predictor;

public class Increment {
	
	public int year;
	public double startSal;
	public int numbInc;
	public double incPerc;
	public double incAmt;
	
	Increment(
			int year,
			double startSal,
			int numbInc,
			double incPerc,
			double incAmt
		) { this.year = year; 
			this.startSal = startSal; 
			this.numbInc = numbInc;
			this.incPerc = incPerc;
			this.incAmt = incAmt;}

}
