<?php session_start();
	/*begin JAVA_BRIDGE*/
		require_once("../inc/JavaConnect.inc");
		$phpDebugOutput=setBridge("/Java_Bridge/GUIProfile");
		if(!(empty($_SESSION['ID'])))
		{
			java_context()->getServlet()->logout($_SESSION['ID']);
		}
	/*end JAVA_BRIDGE*/
		
	if (!(empty($_POST["newPassword1"])))
	{
		$update=java_context()->getServlet()->createNewProfile($_POST["userName"], $_POST["title"], 
				$_POST["affiliation"], $_POST["emailAddr"], $_POST["newPassword1"],
				$_POST["phoneNum"], $_POST["com"], $_POST["freq"]);
		if($update[0]=="DUP")
		{
			echo "The email ".$_POST["emailAddr"]." is already associated with an account.<br/>";
		}
		elseif($update[0]=="TRUE")
		{
			header("Location: ../side/proceed_to_login.php");
			exit;
		}
		echo "<br/>".$update[1]."<br/>";
	}
getHeader("New User", $_SESSION['ID'], false, $phpDebugOutput);
?>
<script>
function check_submit()
{
	if(!check_email("emailAddr"))
		return false;
	
	if(!check_tel("phoneNum"))
		return false;
	
	pw1 = document.getElementById("newPassword1").value;
	pw2 = document.getElementById("newPassword2").value;
	if(pw1=="" & pw2=="")
	{
		alert("Please enter a password to create new a new account.");
		return false;
	}
	else
	{
		if(pw1!=pw2)
		{
			//REQ63
			alert("The passwords do not match, Please reenter.");
			return false;
		}

		f0 = 0;
		if(document.getElementById("f1").checked)
			f0+=4;
		if(document.getElementById("f2").checked)
			f0+=2;
		if(document.getElementById("f3").checked)
			f0+=1;
		document.getElementById("f0").value = f0;

		c0 = 0;
		if(document.getElementById("c1").checked)
			c0+=4;
		if(document.getElementById("c2").checked)
			c0+=2;
		if(document.getElementById("c3").checked)
		{
			if(c0>0)
			{
				alert("Please either select ('No Notification') OR one or both of ('E-mail', 'Text')");
				return false;
			}
			else
				c0=1;
		}
		document.getElementById("c0").value = c0;

		if(f0==0 && c0==0)
		{
			alert("You must first select an 'Anomaly Alert Medium' and a 'Frequency of Anomally Alert'");
			return false;
		}
		if(f0==0)
		{
			alert("You must first select a 'Frequency of Anomally Alert'");
			return false;
		}
		if(c0==0)
		{
			alert("You must first select an 'Anomaly Alert Medium'");
			return false;
		}
		
		return true;
	}
}


</script>

</head> 
<body> 
    <div class="jumbotron">
      <div class="container">
		<h2>Welcome to SenQual!</h2>
      </div>
      </div>
      <div class="container">
<form action="newProfile.php" onsubmit="return check_submit()" method="post">
<table width="500" border="0">
<td colspan="2" style="background-color:#FFFFFF;">
<h2>Personal Information</h2>
</td>
<td><input type="submit" value="Create New Account"></td></tr>
<tr><td>Name*:             </td><td><input type="text" size=30 id="userName" name="userName" value="" required/><br></td></tr>
<tr><td>Title*:            </td><td><input type="text" size=30 id="title" name="title" value="" required/><br></td></tr>
<tr><td>Affiliation*:      </td><td><input type="text" size=30 id="affiliation" name="affiliation" value="" required/><br></td></tr>
<tr><td>E-mail Address*:   </td><td><input type="email" size=30 id="emailAddr" name="emailAddr" value="" required onchange="check_email('emailAddr')"/><br></td></tr>
<tr><td>New Password*:     </td><td><input type="password" size=30 id="newPassword1" name="newPassword1" value=""/><br></td></tr>
<tr><td>Confirm Password*: </td><td><input type="password" size=30 id="newPassword2" name="newPassword2" value=""/><br></td></tr>
<tr><td>Phone Number (optional):   </td><td><input type="tel" size=30 id="phoneNum" name="phoneNum" value="" onchange="check_tel('phoneNum')"/><br></td></tr>

<tr><td colspan="2" style="background-color:#FFFFFF;">
<h3>Anomaly Alert Medium*</h3>
</td></tr>
<tr><td colspan="3">Select how you would like to be notified when an anomaly is detected:</td></tr>
<tr><td>Text:           </td><td><input type="checkbox" id="c1" name="com" value="4" /></td></tr>
<tr><td>E-mail:         </td><td><input type="checkbox" id="c2" name="com" value="2" checked/></td></tr>
<tr><td>No Notification:</td><td><input type="checkbox" id="c3" name="com" value="1" /></td></tr>

<tr><td colspan="2" style="background-color:#FFFFFF;">
<h3>Frequency of Anomally Alert*</h3>
</td></tr>
<tr><td colspan="2">Select the frequency of notification</td></tr>
<tr><td>Each Anomaly Detected:      </td><td><input type="checkbox" id="f1" name="freq" value="4" /></td></tr>
<tr><td>Once for Each Rule Violated:</td><td><input type="checkbox" id="f2" name="freq" value="2" checked/></td></tr>
<tr><td>Once per Monitor:           </td><td><input type="checkbox" id="f3" name="freq" value="1" /></td></tr>

<tr><td colspan="2"><h5>*=must be filled on update</h5></td></tr>
</table>
<input type="hidden" id="f0" name="f0"/>
<input type="hidden" id="c0" name="c0"/>
</form>
<br/><br/>
<hr>
<?php include("../inc/footer.inc")?>
