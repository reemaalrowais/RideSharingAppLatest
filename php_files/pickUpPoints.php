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
	$rideID= $_POST['rid'];

	$sql = "SELECT pick_point, pick_point_time, pick_price, _pp_id FROM ride_pick_points WHERE r_id = '".$rideID."' ";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) {

	while($row[] = $result->fetch_assoc()) {		 
		
		 $json = json_encode($row);
		}
		}else{
		 echo "OfferedRides: ".$result->num_rows;
		}
		echo $json;
	
	$conn->close();
?>