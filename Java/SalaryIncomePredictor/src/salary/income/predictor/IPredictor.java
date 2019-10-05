package salary.income.predictor;

public interface IPredictor {
	void generatereport(double salary, double incrPerc, int incFrequency, double dedPerc, int dedDrequency, int predYears);
}
