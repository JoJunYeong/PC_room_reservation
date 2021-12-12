<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> <!-- 占쎌뮄占�筌욑옙��DB占쏙옙utf-8_unicode_ci嚥∽옙筌띾슢諭억옙�곻폒占쎈챷��占쏙옙占쎄쑵釉�占쎈슣��-->
<?php
$connect = mysql_connect("127.0.0.1", "root", "0216"); //DB揶쏉옙占쎈뜄��雅뚯눘��占쎈떯苡э옙占쏙옙諭�퐣甕곌쑬以�筌욊낯��占쎈쵐�쏙옙�롫뮉 野껉퍔�졿묾占쏙옙���옙占썹뙴��늄獄쏉옙雅뚯눘�쇘몴占쏙옙�ㅻ즲 占쏙옙
mysql_selectdb("zero"); //DB 占쎌쥚源�
mysql_query("set names UTF8"); //占쎈떯苡�占쎈�釉�占쎌뮄占�utf8)占쏙옙筌욑옙�앾옙�띾┛ 占쎄쑵釉�野껓옙
 
	$password=$_REQUEST['password'];
	$birth=$_REQUEST['birth'];
	$name=$_REQUEST['name'];

$result=  mysql_result(mysql_query("select count(birth) from customer where name='$name' AND birth='$birth' AND pw='$password';"),0);
	
 
$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; //xml占쎈슣�わ옙占썹빊�뺤젾占쏙옙�꾨뗀諭�
$xmlcode .= "<result>$result</result>\n"; //DB insert揶쏉옙占쎄퉫�э옙怨몄몵嚥∽옙占쎈Ŧ�쀯쭪占쏙옙�占썹몴占쏙옙類ㅼ뵥占쎌꼵由�占쎄쑵鍮�result揶쏅���xml嚥∽옙�곗뮆�곤옙�쀪땀

$dir = "C:/APM_Setup/htdocs"; //insertresult.xml 占쎈슣�わ옙占쏙옙占쎌삢占쏙옙野껋럥以�
$filename = $dir."/create_success.xml";
 
file_put_contents($filename, $xmlcode); //xmlcode占쏙옙占쎈똻�쒙옙占퐔ml占쎈슣�ゆ에占썹빊�뺤젾
?>

