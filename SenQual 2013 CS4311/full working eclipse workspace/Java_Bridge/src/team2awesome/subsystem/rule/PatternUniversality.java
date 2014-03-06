package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public class PatternUniversality extends RuleDefinition 
{
	
	public PatternUniversality(Formula bool, Formula prem) 
	{
		super(bool, prem);
		anomallyOccur = false;
	}

	@Override
	public boolean check(Datum[] allSlice) throws Exception 
	{
		//must always be TRUE in scope
		if(!anomallyOccur)
			return (anomallyOccur = !bool.checkDatumAgaistFormula(allSlice));
		return !bool.checkDatumAgaistFormula(allSlice);
	}

}
