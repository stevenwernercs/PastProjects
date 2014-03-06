package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public class PatternExistence extends RuleDefinition 
{	
	//no pattern existance real time after L, or Global
	public PatternExistence(Formula bool, Formula prem) 
	{	//assume there is an anomally until existance is found
		super(bool, prem);		
		anomallyOccur = true;
	}

	@Override
	public boolean check(Datum[] allSlice) throws Exception 
	{	//must be true at least once in scope
		//collect all datums and test at completed..
		if(anomallyOccur)
			anomallyOccur = bool.checkDatumAgaistFormula(allSlice);
		return anomallyOccur;
	}
}
