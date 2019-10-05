package salary.income.predictor;

public class Prediction {

	public int year;
	public double startSal;
	public double incAmt;
	public double dedAmt;
	public double salGrowth;
		
	Prediction(
			int year,
			double startSal,
			double incAmt,
			double dedAmt,
			double salGrowth
			
		) { this.year = year; 
			this.startSal = startSal; 
			this.incAmt = incAmt;
			this.dedAmt = dedAmt;
			this.salGrowth = salGrowth;}
}
