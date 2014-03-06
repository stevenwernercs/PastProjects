package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public class ScopeBetween extends RuleDuration 
{
	//betweenL&R		(nests)
	//in scope:		at and after L exists		leftBoundSensor, offset=0;
	//compeleted:	just before R exists		rightBoundSensor, offset=-1;
	//end:			just before R exists		rightBoundSensor, offset=-1;
	
	public ScopeBetween(Formula left, Formula right) 
	{
		super(left, right);
			
		offset[0] = 0;
		offset[1] = -1;
		offset[2] = 0;
		
		doesReset = true;
		nests	  = true;
	}

	@Override
	public boolean checkScope(Datum[] allSlice) throws Exception
	{return super.left.checkDatumAgaistFormula(allSlice);}

	@Override
	public boolean checkCompleted(Datum[] allSlice) throws Exception
	{return super.right.checkDatumAgaistFormula(allSlice);}

}
