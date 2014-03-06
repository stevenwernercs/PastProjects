package team2awesome.subsystem.rule;

import team2awesome.subsystem.monitor.Datum;

public class ScopeAfterL extends RuleDuration {

	private Formula left = null;
	private int set = 0;
	
	@Override
	public Datum checkDatum(Datum datum) {
		// TODO Auto-generated method stub
		if (left.checkDatumAgaistFormula(datum))
		{	
			super.inScope = true;
		 	set+=1;
		}
		 
		 
		return datum;
	}
	
	public int getIsSet()
	{
		return this.set;
	}
	
	
	

}
