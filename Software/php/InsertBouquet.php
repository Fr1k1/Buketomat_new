<?php
$dbhost = 'localhost';
$dbuser = 'id19882868_user';
$password = "Stapic123456!";
$dbname = "id19882868_test_db";
$response = array();
$success = array();
$error = array();
header('Content-Type: application/json');

// Create connection
$conn = new mysqli($dbhost, $dbuser, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
if ($_SERVER["REQUEST_METHOD"] == "POST") {
 
    // collect value of input field
    $json = file_get_contents('php://input');
// Converts it into a PHP object
    $data = json_decode($json)[0];
    if (empty($data)) {
        $error["error"] = "data is empty";
        $response[] = $error;
        echo json_encode($response);
    } 
    else {
        $sql = "INSERT INTO buket(naziv, opis, cijena,slika) VALUES ('$data->naziv', '$data->opis','$data->cijena','$data->slika')";
        

        if ($conn->query($sql) === TRUE) {
            
            $success["success"] ="New record created successfully";
            $success["entryId"] ="$conn->insert_id";
            $response[]  = $success;
            echo json_encode($response);
        } 
        else {
            $error["error"] = $conn->error;
            $response[]  = $error;
            echo json_encode($response);
        }
    }
}
else{
    
    $error["error"] = "Wrong method!!!";
    $response[]  = $error;
    echo json_encode($response);
}
$conn->close();
?>


