<?php session_start();
	/*begin JAVA_BRIDGE*/
		require_once("../inc/JavaConnect.inc");
		setBridge("/Java_Bridge/GUIProfile", "Profile", $_SESSION['ID'], true);
	/*end JAVA_BRIDGE*/
		
	if (!(empty($_POST["oldPassword"])))
	{
		$update=java_context()->getServlet()->updateInfo($_SESSION['ID'], $_POST["oldPassword"], 
				$_POST["userName"], $_POST["title"], $_POST["affiliation"], $_POST["emailAddr"], $_POST["newPassword1"],
				$_POST["phoneNum"], $_POST["c0"], $_POST["f0"]);
		if($update[0]=="BOOT")
		{
			header("Location: session_jump_error.php");
			exit;
		}
		if($update[0]=="TRUE")
		{
			$id=uniqid($_POST['emailAddr'].":", true);
			java_context()->getServlet()->updateID($_SESSION['ID'], $id);
			$_SESSION['EMAIL'] = $_POST['emailAddr'];
			$_SESSION['ID'] = $id;
		}
		
	}
	
	$profile=java_context()->getServlet()->getProfileArray($_SESSION['ID']);
	if(java_is_null($profile))
		$profile=Array("", "", "", "", "", "", "", "");
	else
	{
		$com = Array("","","");
		if($profile[6]=="1")
			$com = Array("","","checked");
		elseif($profile[6]=="2")
			$com = Array("","checked","");
		elseif($profile[6]=="4")
			$com = Array("checked","","");
		elseif($profile[6]=="6")
			$com = Array("checked","checked","");
		
		$freq = Array("","","");
		if($profile[7]=="1")
			$freq = Array("","","checked");
		elseif($profile[7]=="2")
			$freq = Array("","checked","");
		elseif($profile[7]=="3")
			$freq = Array("","checked","checked");
		elseif($profile[7]=="4")
			$freq = Array("checked","","");
		elseif($profile[7]=="5")
			$freq = Array("checked","","checked");
		elseif($profile[7]=="6")
			$freq = Array("checked","checked","");
		elseif($profile[7]=="7")
			$freq = Array("checked","checked","checked");
	}
?>
<script>
function check_submit()
{
	if(!check_email("emailAddr"))
		return false;

	if(!check_tel("phoneNum"))
		return false;
	
	pw0 = document.getElementById("oldPassword").value;
	if(pw0=="")
	{
		alert("Please enter existing password for changes to take effect.");
		return false;
	}
	else
	{
		pw1 = document.getElementById("newPassword1").value;
		pw2 = document.getElementById("newPassword2").value;
		if((pw1!="" || pw2!="") && (pw1!=pw2))
		{
			//REQ63
			alert("Change existing password: The passwords do not match. Please reenter");
			return false;
		}
		if(pw0==pw1)
		{
			alert("New Password should not be the same as current password.");
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

		
		<hr>
	<div class="container">
<form action="Profile.php" onsubmit="return check_submit()" method="post">
<table width="500" border="0">
<tr><td colspan="4" style="background-color:#FFFFFF;">
<h1><?php echo $_SESSION['EMAIL'];?></h1>
</td>

<tr><td colspan="2" style="background-color:#FFFFFF;">
<h2>Personal Information</h2>
</td>
<td><input type="submit" value="Update Changes"></td></tr>
<tr><td>Name*:             </td><td><input type="text" size=30 id="userName" name="userName" value="<?php echo $profile[0]; ?>" required/><br></td></tr>
<tr><td>Title*:            </td><td><input type="text" size=30 id="title" name="title" value="<?php echo $profile[1]; ?>" required/><br></td></tr>
<tr><td>Affiliation*:      </td><td><input type="text" size=30 id="affiliation" name="affiliation" value="<?php echo $profile[2]; ?>" required/><br></td></tr>
<tr><td>E-mail Address*:   </td><td><input type="email" size=30 id="emailAddr" name="emailAddr" value="<?php echo $profile[3]; ?>" required onchange="check_email('emailAddr')"/><br></td></tr>
<tr><td>Current Password*: </td><td><input type="password" size=30 id="oldPassword" name="oldPassword" value=""/><br></td></tr>
<tr><td>New Password:     </td><td><input type="password" size=30 id="newPassword1" name="newPassword1" value=""/><br></td></tr>
<tr><td>Confirm New Password:     </td><td><input type="password" size=30 id="newPassword2" name="newPassword2" value=""/><br></td></tr>
<tr><td>Phone Number (optional):   </td><td><input type="tel" size=30 id="phoneNum" name="phoneNum" value="<?php echo $profile[5]; ?>" onchange="check_tel('phoneNum')"/><br></td></tr>

<tr><td colspan="2" style="background-color:#FFFFFF;">
<h3>Anomaly Alert Medium*</h3>
</td></tr>
<tr><td colspan="3">Select how you would like to be notified when an anomaly is detected:</td></tr>
<tr><td>Text:           </td><td><input type="checkbox" id="c1" name="c1" value="4" <?php echo $com[0]?>/><br></td></tr>
<tr><td>E-mail:         </td><td><input type="checkbox" id="c2" name="c2" value="2" <?php echo $com[1]?>/><br></td></tr>
<tr><td>No Notification:</td><td><input type="checkbox" id="c3" name="c3" value="1" <?php echo $com[2]?>/><br></td></tr>

<tr><td colspan="2" style="background-color:#FFFFFF;">
<h3>Frequency of Anomally Alert*</h3>
</td></tr>
<tr><td colspan="2">Select the frequency of notification</td></tr>
<tr><td>Each Anomaly Detected:      </td><td><input type="checkbox" id="f1" name="f1" value="4" <?php echo $freq[0]?>/><br></td></tr>
<tr><td>Once for Each Rule Violated:</td><td><input type="checkbox" id="f2" name="f2" value="2" <?php echo $freq[1]?>/><br></td></tr>
<tr><td>Once per Monitor:           </td><td><input type="checkbox" id="f3" name="f3" value="1" <?php echo $freq[2]?>/><br></td></tr>

<tr><td colspan="2"><h5>*=must be filled on update</h5></td></tr>
</table>
<input type="hidden" id="f0" name="f0"/>
<input type="hidden" id="c0" name="c0"/>
</form>
<br/><br/>
<hr>
<?php include("../inc/footer.inc")?>
