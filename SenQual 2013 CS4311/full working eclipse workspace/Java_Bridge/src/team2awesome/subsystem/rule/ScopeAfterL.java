package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public class ScopeAfterL extends RuleDuration 
{
	//afterL
	//in scope:		at and after L exists		leftBoundSensor, offset=0;
	//compeleted:	at and after L exists		leftBoundSensor, offset=0;
	//end:			never						false
	
	public ScopeAfterL(Formula left, Formula right)
	{
		super(left, right);
		
		offset[0] = 0;
		offset[1] = 0;
		offset[2] = 0;
		
		doesReset = false;
		nests	  = false;
	}

	@Override
	public boolean checkScope(Datum[] allSlice) throws Exception 
	{return super.left.checkDatumAgaistFormula(allSlice);}

	@Override
	public boolean checkCompleted(Datum[] allSlice) throws Exception 
	{return super.left.checkDatumAgaistFormula(allSlice);}
}
