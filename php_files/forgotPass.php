<?php
$hostname = "localhost";
$username = "umarbyhn_rider";
$password = "Z0_PJv@Oe6ig";
$dbname = "umarbyhn_ride_sharing";

	// Create connection
	$conn = new mysqli($hostname, $username, $password, $dbname);

	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 
	$mob = $_POST['mob'];
	//$mob = "8586847364";

	$sql = "SELECT u_pass FROM user_details WHERE u_id = '$mob' ";

	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
		while($row = $result->fetch_assoc()) { 
		 echo $row["u_pass"];
		}
	}else{
		 echo "Err";
	}

	
	$conn->close();
?>
