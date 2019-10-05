package salary.income.predictor;

public class Deduction {
		
	public int year;
	public double startSal;
	public int numbDed;
	public double dedPerc;
	public double dedAmt;
	
	Deduction(
			int year,
			double startSal,
			int numbDed,
			double dedPerc,
			double dedAmt
		) { this.year = year; 
			this.startSal = startSal; 
			this.numbDed = numbDed;
			this.dedPerc = dedPerc;
			this.dedAmt = dedAmt;}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int value) {
		year = value;
	}
}
