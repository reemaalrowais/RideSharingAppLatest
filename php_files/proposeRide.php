	<?php

	$hostname = "localhost";
	$username = "umarbyhn_rider";
	$password = "Z0_PJv@Oe6ig";
	$dbname = "umarbyhn_ride_sharing";

	$con = mysqli_connect($hostname, $username, $password, $dbname);
		if (mysqli_connect_errno())
		{
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		//print((string)$con);
		$rider_id = $_POST['riderID'];
		$ride_id = $_POST['rideID'];
		$pp_id = $_POST['ppid'];
	//$rider_id = "8586847364";
	//$ride_id = "10";
	//$pp_id = "18";
		
		$Sql_Query = "insert into rides_proposals(r_id, rider_id, _pp_id )values ('$ride_id' , '$rider_id' , '$pp_id')";
		if(mysqli_query($con, $Sql_Query)){ echo'OK';}else{echo "" ;}
		
		mysqli_close($con); 
	?>
