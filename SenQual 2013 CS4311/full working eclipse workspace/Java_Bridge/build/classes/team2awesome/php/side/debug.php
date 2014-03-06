<?php session_start();
	
	//This is an unlinked file that toggles debug mode 
	
	/*begin JAVA_BRIDGE*/
		require_once("JavaConnect.inc");
		setBridge("/Java_Bridge/GUIStatic", "Settings", $_SESSION['ID'], true);
	/*end JAVA_BRIDGE*/
	echo "</head>";
	echo "<body>";
	echo "<div class=\"jumbotron\">";
	echo "<div class=\"container\">";
	echo java_context()->getServlet()->debugToggle();
?>
		</div>
		</div>
		<br/><br/>
		<hr>
	<div class="container">
<?php include("../inc/footer.inc")?>