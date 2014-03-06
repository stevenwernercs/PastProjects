<?php session_start();
	/*begin JAVA_BRIDGE*/
		require_once("../inc/JavaConnect.inc");
		setBridge("/Java_Bridge/GUIMonitor", "Monitor", $_SESSION['ID'], true);
	/*end JAVA_BRIDGE*/
		//do the stuff regardless of whether post is set	
			//inside code sets rules
			//inside code sets monitor with rules
			
		
		//stuff to do when post is set
		if ( !empty($_POST) )
		{
			//if add rule then add a rule to monitor
			if ( !empty($_POST['ruleButton'] ))
			
			{
				if($_POST['ruleButton'] == "add rule >>")
				{
					if($showDebugs=="TRUE")
						echo "add";
					//call monitorGUI to add the rule to the monitor
					java_context()->getServlet()->addRulesToMonitors($_SESSION['EMAIL'], $_POST['rule'], $_POST['monitors']);
				}
				//if delete rule then delete selected rules from monitors
				if($_POST['ruleButton'] == "remove rule")
				{
					if($showDebugs=="TRUE")
						echo "delete";
					//call monitorGUI to add the rule to the monitor
					java_context()->getServlet()->deleteRulesFromMonitors($_SESSION['EMAIL'], $_POST['monitorAndRule']);
				}
			}
			
			//if monitor button was pressed
			if ( !empty($_POST['monitorButton'] ))
			{
				//if new monitor then create a new monitor
				if($_POST['monitorButton'] == "New")
				{
					//if($showDebugs=="TRUE")
					//{
					//	echo "new";
					//	echo $_POST['newMonitorName'];
					//}
					
					//call monitor gui to add a new monitor
					java_context()->getServlet()->createNewMonitor($_SESSION['EMAIL'], $_POST['newMonitorName']);
				}
				//if rename monitor then rename a monitor
				if($_POST['monitorButton'] == "Rename")
				{
					if($showDebugs=="TRUE")
					{
						echo "rename";
						echo $_POST['newMonitorName'];
					}
					//call monitor gui to add a new monitor
					java_context()->getServlet()->renameAMonitor($_SESSION['EMAIL'], $_POST['monitors'], $_POST['newMonitorName']);
				}
				//if share a monitor, then share a monitor
				if($_POST['monitorButton'] == "Share")
				{
					if($showDebugs=="TRUE")
					{
						echo "share";
						echo $_POST['shareeEmail'];
					}
					
					//call monitor gui to add a new monitor
					java_context()->getServlet()->createNewMonitor($_SESSION['EMAIL'], $_POST['monitors'], $_POST['shareeEmail']);
				}
			}
			
			if ( !empty($_POST['sessionButton'] ))
			{
				//if near real time is clicked then initiate testing for those monitors
				if($_POST['sessionButton'] == "Near Real Time")
				{
					if($showDebugs=="TRUE")
						echo "nrt pressed";
					java_context()->getServlet()->sessionNearRealTime($_SESSION['EMAIL'], $_POST['monitors']);
				}
				//if logged data is clicked then initiate testing for those monitors
				if($_POST['sessionButton'] == "Logged Data")
				{
					if($showDebugs=="TRUE")
						echo "loddeg data pressed";
					java_context()->getServlet()->sessionHistorical($_SESSION['EMAIL'], $_POST['monitors']);
				}
			}
		}	
		
	
?>
<script>
function getNewMonitorName()
{
	var newName=prompt("Please enter the new monitor name","New Monitor Name");

	if (newName!=null)
	{
		document.getElementById("newMonitorNameId").setAttribute("value", newName);
	} 
}
function getShareeEmail()
{
	var shareeEmail=prompt("Please enter email address of profile to share with","sharee@email.here");

	if (shareeEmail!=null)
	{
		document.getElementById("shareeEmailId").setAttribute("value", shareeEmail);
	} 
}
function searchRules() 
{
    var filterValuefromBox = document.getElementById('searchTextBox').value;
    var r_list = document.getElementById('ruleList');
    for(var i = 0; i < r_list.options.length; i++)
    {
    	var choosenRule = r_list.options[i].text;
    	if(choosenRule.search(filterValuefromBox)>-1)
    	{
    		var temp = r_list.options[i];
    		r_list.add(temp, r_list.options[0]);
    	}
    }
}
</script> 
</head>
<body>

<!-- eniter page -->
      <hr>
      <div class="container">
      
<!-- <div id="MonitorPage">  -->
<input type="button" value="search" onclick="searchRules()"/>
<input type="search" id="searchTextBox" onchange="searchRules()"/>
<form action="monitor.php" method="post">
<!--search rules text box -->
<!--  <div id = "rules" style = "height:250px;width:280px;float:left;background-color:#FFFFFF;"> -->
<table cellspacing="10" style="position:relative;width:100%;">
	<tr>
		<td colspan="2">
			<h3>Choose rules from:</h3>
		</td>
		<td>
		</td> 
		<td colspan="3">
			<h3>Choose monitors from:</h3>
		</td>
		<td>
			<h3>Initiate Monitoring:</h3>
		</td>
	</tr>
	<tr><!--rules -->
		<td rowspan="3" colspan="2">
			<select style="width:220px; height: 400px;" name="rule[]" id="ruleList" multiple="multiple" >
				<?php
					//adds all the rules to the rule list
					echo java_context()->getServlet()->printAllRules($_SESSION['EMAIL']);
				?>
			</select>
		</td>
		<td rowspan="2">
			<input type="submit" name="ruleButton" value="add rule >>"/><br/>
			<input type="submit" name="ruleButton" value="remove rule"/>
		</td>
		<td colspan="3" >
			<table border="1"><tr><td>
			<ol class="tree" style="width:290px; height: 350px;">
				<?php
					//monitors with rules attached
					//populate tree	
					echo java_context()->getServlet()->printMonitorWithRules($_SESSION['EMAIL']);
					//set monitor names with rules in a multi dimensional array, for details see 4rth dimension
				?>
			</ol>
			</td></tr></table>
		</td>
		<td>
			<input type="submit" name="sessionButton" value="Near Real Time"/><br/>
			<input type="submit" name="sessionButton" value="Logged Data"/>
		</td>
	</tr>
	<tr>
		<td>
			<input type="submit" onclick="getNewMonitorName()" name="monitorButton" value="New"/>
			<input type="hidden" id="newMonitorNameId" name="newMonitorName" value="lalala"/>
		</td>
		<td>
		<input type="submit" onclick ="getShareeEmail()" name = "monitorButton" value="Share"/>
			<input type="hidden" id="shareeEmailId" name="shareeEmail" value="lalala"/>
		</td>
		<td>
			<input type="submit" onclick="getNewMonitorName()" name="monitorButton" value="Rename"/>
		</td>
	</tr>
	
<!-- </div>  -->

<!-- adding rules to monitor -->
<!-- <div id="addRuleToMonitor" style="float:left;background-color:#FFFFFF;"> -->
<!-- </div>  -->

<!--monitors-->
<!-- <div id = "monitorTree" style="height:300px:width:300px;float:left;background-color:#FFFFFF;"> -->
	
<!-- add delete and sharing a monitor -->

<!-- uses the same newMonitorNameId as above -->
<!-- </div>  -->

<!--initiate monitoring -->
<!--  <div id="initiateMonitior" style="height:300px;float:left;background-color:#FFFFFF;"> -->

</table>
<!-- </div>  -->
</form>


<!-- </div>  -->
<br/><br/>
      <hr>
<?php include("../inc/footer.inc")?>