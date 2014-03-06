package team2awesome.subsystem.rule;

import team2awesome.subsystem.monitor.Datum;

public class ScopeBeforeR extends RuleDuration {

	private Formula right = null;
	
	@Override
	Datum checkDatum(Datum datum) {
		// TODO Auto-generated method stub
		 if (right.checkDatumAgaistFormula(datum))
			 super.inScope = true;
			 
		 
		return datum;
	}
	
	

}
