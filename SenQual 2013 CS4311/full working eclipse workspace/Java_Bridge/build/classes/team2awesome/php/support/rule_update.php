<?php session_start();
	/*begin JAVA_BRIDGE*/
		require_once("JavaConnect.inc");
		setBridge("/Java_Bridge/GUIRule", "Add Rule", $_SESSION['ID'], true);
	/*end JAVA_BRIDGE*/

//<input type="text" name="name" id="name" value=""/>
//<input type="text" name="lbound_sensor" id="lbound_sensor" value=""/>  			<!-- ID -->
//<input type="text" name="duration" id="duration" value=""/>
//<input type="text" name="lbound_formula" id="lbound_formula" value=""/>
//<input type="text" name="rbound_formula" id="rbound_formula" value=""/>
//<input type="text" name="definition" id="definition" value=""/>
//<input type="text" name="premise_formula" id="premise_formula" value=""/>
//<input type="text" name="boolean_formula" id="boolean_formula" value=""/>
//<input type="text" name="rbound_sensor" id="rbound_sensor"/>		 	<!-- Share boolean -->


/*
 * 	public String setRule(String ownerEmail, String ruleName, String description, 
 * 			int ruleDuration, int ruleDefinition, String leftBoundSensor, String leftBoundFormula, 
 * 			String rightBoundSensor, String rightBoundFormula, String premiseSensor, 
 * 			String premiseFormula, String booleanSensor, String booleanFormula)
 * */
$success =java_context()->getServlet()->setRule($_SESSION['EMAIL'], $_POST[name], "", 
			$_POST[duration], $_POST[definition], "" ,$_POST[lbound_formula],
			"",$_POST[rbound_formula],"",$_POST[premise_formula],"",
			$_POST[boolean_formula], $_POST[rbound_sensor]);

	if($success=="TRUE")
	{
		echo "<meta http-equiv=\"refresh\" content=\"1; URL=rule.php\">";
		echo "</head>";
		echo "<body>";
		echo "1 record added";
	}
	else
	{
		echo "</head>";
		echo "<body>";
		echo "save failed<br/>";
		echo $success;
	}

?>
</body>

</html>
