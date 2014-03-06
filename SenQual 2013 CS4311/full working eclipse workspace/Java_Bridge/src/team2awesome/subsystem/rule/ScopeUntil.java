package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public class ScopeUntil extends RuleDuration 
{
	//afterLuntilR		(nests)
	//in scope:		at and after L exists		leftBoundSensor, offset=0;
	//completed:	at and after L exists		leftBoundSensor, offset=0;
	//end:			just before R exists		rightBoundSensor, offset=-1
	
	public ScopeUntil(Formula left, Formula right) 
	{
		super(left, right);
		
		offset[0] = 0;
		offset[1] = 0;
		offset[2] = -1;
		
		doesReset = true;
		nests	  = true;
	}
	
	@Override
	public boolean checkScope(Datum[] allSlice) throws Exception
	{return super.left.checkDatumAgaistFormula(allSlice);}

	@Override
	public boolean checkCompleted(Datum[] allSlice) throws Exception
	{return super.left.checkDatumAgaistFormula(allSlice);}
}
