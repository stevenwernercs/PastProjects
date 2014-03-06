package team2awesome.subsystem.rule;

import team2awesome.subsystem.monitor.Datum;

public abstract class RuleDuration {

	boolean inScope;
	
	abstract Datum checkDatum(Datum datum);
	
	
	//beforeR
		//in scope:		always
		//completed:	just before R exists
		//reset:		once R exists
	
	//afterL
		//in scope:		at and after L exists
		//compeleted:	at and after L exists
		//resets:		never
	
	//betweenL&R		(nests)
		//in scope:		afterL
		//compeleted:	beforeR
		//reset:		beforeR
	
	//afterLuntilR		(nests)
		//in scope:		afterL
		//completed:	afterL
		//resets:		beforeR
	
	
	

}
