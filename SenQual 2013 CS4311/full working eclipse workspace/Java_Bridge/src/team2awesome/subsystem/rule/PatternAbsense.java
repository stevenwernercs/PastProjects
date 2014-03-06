package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public class PatternAbsense extends RuleDefinition 
{

	public PatternAbsense(Formula bool, Formula prem) 
	{
		super(bool, prem);
		anomallyOccur = false;
	}

	@Override
	public boolean check(Datum [] allSlice) throws Exception 
	{
		//must always be FALSE in scope
		if(!anomallyOccur) //if no anomally check and update
			return (anomallyOccur = bool.checkDatumAgaistFormula(allSlice));
		return bool.checkDatumAgaistFormula(allSlice);		
	}

}
