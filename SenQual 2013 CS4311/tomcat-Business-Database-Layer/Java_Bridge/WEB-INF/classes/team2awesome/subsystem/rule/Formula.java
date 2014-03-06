package team2awesome.subsystem.rule;

import team2awesome.subsystem.monitor.Datum;


public class Formula 
{

	private String formulaString; 
	
	public Formula(String formulaString) 
	{
		if(validateFormula(formulaString))
			this.formulaString = formulaString;
	}
	
	/*---begin public methods---*/
	public boolean checkDatumAgaistFormula(Datum datum)
	{
		//TODO handel boolean that includes multiple sensors.. ??
		//OR just dont let user specify multiple sensors in a rule
		return true;
	}
	
	public String getFormula()
	{
		return this.formulaString;
	}
	
	private boolean validateFormula(String formulaString) 
	{
		return false;
	}	
	
	/*---begin private methods---*/

}
