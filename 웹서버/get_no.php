<?php

$num = $_POST['num'];
$con = mysqli_connect("dogcat.ctokivcp0aft.ap-northeast-2.rds.amazonaws.com","admin","didqudtjr",dog_cat_db);
$query = "SELECT * FROM dog_cat where num = $num;";
$result = mysqli_query($con,$query);
$response = array();



while($row = mysqli_fetch_array($result)){
        array_push($response,array("num" => $row[0],"upload_date" => $row[1],"passwd" => $row[2], "kind" => $row[4],"area"=>$row[5] ,"image" =>$row[6] ));

}

 echo json_encode(array("response" => $response));
   mysqli_close($con);



?