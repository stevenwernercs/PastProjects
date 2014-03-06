<?php session_start();
	/*begin JAVA_BRIDGE*/
		require_once("../inc/JavaConnect.inc");
		setBridge("/Java_Bridge/GUIProfile", "Profile", $_SESSION['ID'], false);
	/*end JAVA_BRIDGE*/
		
	if (!(empty($_POST["emailAddr"])))
	{
		//$update=Array("TRUE", "Your password has been sent to '".$_POST["emailAddr"]."'");
		$update=Array("FALSE", "The information provided is incorrect for '".$_POST["emailAddr"]."'");
		//$update=java_context()->getServlet()->getForgortPW($_POST["userName"], $_POST["title"], 
		//		$_POST["affiliation"], $_POST["emailAddr"]);
	}
?>
<script>
function check_submit()
{
	pw0 = document.getElementById("oldPassword").value;
	if(pw0=="")
	{
		alert("Please enter existing password for changes to take effect.");
		return false;
	}
	return true;
}

</script>

</head> 
<body> 

<?php
	if(!(empty($update)))
	{	
		echo "<div class=\"jumbotron\">";
		echo "<div class=\"container\">";
		echo $update[1];
		echo "</div>";
		echo "</div>";
	}
?>

	<div class="container">
	
<?php
	if(empty($update) || $update[0]=="FALSE")
	{	
		echo "<form action=\"../main/forgotPW.php\" onsubmit=\"return check_submit()\" method=\"post\">";
		echo "<table width=\"500\" border=\"0\">";
		echo "<tr><td colspan=\"4\" style=\"background-color:#FFFFFF;\">";
		echo "<h3>Complete the following to recive a password reset email</h3>";
		echo "</td>";

		echo "<tr><td colspan=\"2\" style=\"background-color:#FFFFFF;\">";
		echo "<h4>Personal Information</h4>";
		echo "</td>";
		echo "<td><input type=\"submit\" value=\"Update Changes\"></td></tr>";
		echo "<tr><td>Name*:             </td><td><input type=\"text\" size=30 id=\"userName\" name=\"userName\" value=\"".$profile[0]."\" required/><br></td></tr>";
		echo "<tr><td>Title*:            </td><td><input type=\"text\" size=30 id=\"title\" name=\"title\" value=\"".$profile[1]."\" required/><br></td></tr>";
		echo "<tr><td>Affiliation*:      </td><td><input type=\"text\" size=30 id=\"affiliation\" name=\"affiliation\" value=\"".$profile[2]."\" required/><br></td></tr>";
		echo "<tr><td>E-mail Address*:   </td><td><input type=\"email\" size=30 id=\"emailAddr\" name=\"emailAddr\" value=\"".$profile[3]."\" required/><br></td></tr>";

		echo "<tr><td colspan=\"2\"><h5>*=must be filled on update</h5></td></tr>";
		echo "</table>";
		echo "</form>";
	}
	echo "<br/><br/>";
	echo "<hr>";
	include("../inc/footer.inc")
?>