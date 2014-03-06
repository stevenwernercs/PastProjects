package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public class ScopeGlobal extends RuleDuration
{	
	//global
	//in scope:		always 						true
	//completed:	always 						true
	//end:			never  						false
	
	public ScopeGlobal(Formula left, Formula right) 
	{
		super(left, right);
		
		offset[0] = 0;
		offset[1] = 0;
		offset[2] = 0;
		
		doesReset = false;
		nests	  = false;
	}

	@Override
	public boolean checkScope(Datum[] allSlice) 
	{return true;}

	@Override
	public boolean checkCompleted(Datum[] allSlice) 
	{return true;}
}
