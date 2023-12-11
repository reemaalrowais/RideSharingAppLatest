<?php

$hostname = "localhost";
$username = "umarbyhn_rider";
$password = "Z0_PJv@Oe6ig";
$dbname = "umarbyhn_ride_sharing";

$con = mysqli_connect($hostname, $username, $password, $dbname);
if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
if ($con == true) {
    echo "good connect to MySQL: ";
}
//code begins :
//print((string)$con);
$mob_no = $_POST['mob_no'];
$name = $_POST['name'];
$email = $_POST['email'];
$college = $_POST['college'];
$dob = $_POST['dob'];
$sex = $_POST['sex'];
$pword = $_POST['pword'];
$enroll = $_POST['enroll'];
$fb_link = $_POST['fb_link'];

$Sql_Query = "insert into user_details(u_id, u_name, u_email, u_college, u_dob, u_sex, 
                u_pass, u_enroll, u_fb ) values 
                ('$mob_no' , '$name' , '$email', '$college' , '$dob' , '$sex' , '$pword' , '$enroll' , '$fb_link' )";
if (mysqli_query($con, $Sql_Query)) {
    echo 'Data Inserted Successfully';
} else {
    echo " Data Not added!";
}

mysqli_close($con);

?>