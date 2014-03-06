<?php session_start();
	/*begin JAVA_BRIDGE*/
		require_once("../inc/JavaConnect.inc");
		setBridge("/Java_Bridge/GUIDash", "Dashboard", $_SESSION['ID'], true);
	/*end JAVA_BRIDGE*/
	//include('../../../../Dash/dashboard.html');
?>

<script type="text/javascript" src="../support/FusionCharts/FusionCharts.js">
</script>
<style>
div.scroll
{
overflow:scroll;
}
</style>
</head>
<body>


<!-- Main jumbotron for a primary marketing message or call to action -->
    
    <div class="jumbotron">
      <div class="container">
<?php      
	$dashout=java_context()->getServlet()->printWholePage($_SESSION['EMAIL']);
	if($dashout==null || $dashout=="" || $dashout=="<br/>")
	{
		echo "<h3>You have yet to monitor any data</h3>";
		echo "<p>Frist create a rule on Rule Page, 
				then create a monitor on Monitor Page. 
				Add the rule to the monitor, and click
				monitor! <br/>It's just that easy :)</p>";
			echo "</div>";
		echo "</div>";
	}
	else 
	{
		echo "Your monitored data:";
		echo "</div>";
		echo "</div>";
		echo "<div class=\"container\">";
		echo $dashout;
		echo "</div>";
	}
?>    
<br/><br/>
      <hr>
	<div class="container">
<?php include("../inc/footer.inc")?>