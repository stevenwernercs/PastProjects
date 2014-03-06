package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public class ScopeBeforeR extends RuleDuration 
{
	//beforeR
	//in scope:		always						true
	//completed:	just before R exists		rightBoundSensor, offset=-1;
	//end:			just before R exists		rightBoundSensor, offset=-1;
	
	public ScopeBeforeR(Formula left, Formula right) 
	{
		super(left, right);
		
		offset[0] = 0;
		offset[1] = -1;
		offset[2] = -1;
		
		doesReset = true;
		nests	  = false;
	}

	@Override
	public boolean checkScope(Datum [] allSlice) throws Exception
	{return true;}

	@Override
	public boolean checkCompleted(Datum[] allSlice) throws Exception
	{return super.right.checkDatumAgaistFormula(allSlice);}
}
