<?php 
	session_start();
	session_destroy();
	/*begin JAVA_BRIDGE*/
		require_once("../inc/JavaConnect.inc");
		setBridge("/Java_Bridge/GUIStatic", "Error", $_SESSION['ID'], false);
	/*end JAVA_BRIDGE*/
	echo "</head>";
	echo "<body>";
	echo "<div class=\"jumbotron\">";
	echo "<div class=\"container\">";
	if(!empty($_SESSION['ID']))
		echo "Sorry, We restarted our servers with an update, Please log back in.";
	else
		echo "<h3>Sorry, we can't let you in there.</h3><br/>".
			 "Please log in first.<br/>".
			 "<h5><i>if you feel this is a mistake, ensure to enable cookies on your browser</i></h5><br/>";
	
?>
		</div>
		</div>
		<br/><br/>
		<hr>
	<div class="container">
<?php include("../inc/footer.inc")?>