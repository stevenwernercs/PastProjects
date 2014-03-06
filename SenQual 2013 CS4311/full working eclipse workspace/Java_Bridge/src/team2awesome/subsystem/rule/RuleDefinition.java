package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public abstract class RuleDefinition 
{
	boolean anomallyOccur = false;
	
	Formula bool;
	Formula prem;
	
	protected abstract boolean check(Datum[] allSlice) throws Exception;
	
	public RuleDefinition(Formula bool, Formula prem) 
	{
		this.bool = bool;
		this.prem = prem;
	}

	public boolean checkAnomally(Datum[] allSlice) throws Exception
	{
		return check(allSlice);
	}
	
	public void reset()
	{
		this.anomallyOccur = false;
	}
}
