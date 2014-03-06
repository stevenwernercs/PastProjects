package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public class PatternResponse extends RuleDefinition 
{
	
	public PatternResponse(Formula bool, Formula prem) 
	{
		super(bool, prem);
		anomallyOccur = false;
	}

	@Override
	public boolean check(Datum[] allSlice) throws Exception 
	{
		if(bool.checkDatumAgaistFormula(allSlice) && !prem.checkDatumAgaistFormula(allSlice))
		{
			if(!anomallyOccur)
				anomallyOccur = true;
			return true;
		}
		return false;
	}

}
