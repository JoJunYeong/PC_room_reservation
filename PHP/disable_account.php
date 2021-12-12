<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> <!-- �쒓� 吏�썝(DB��utf-8_unicode_ci濡�留뚮뱾�댁＜�몄슂)���꾪븳 �뚯뒪 -->
<?php
$connect = mysql_connect("127.0.0.1", "root", "0216"); //DB媛��덈뒗 二쇱냼(�닿쾬���뱀꽌踰꾨줈 吏곸젒 �묒냽�섎뒗 寃껋씠湲��뚮Ц��猷⑦봽諛�二쇱냼瑜��⑤룄 ��
mysql_selectdb("zero"); //DB �좏깮
mysql_query("set names UTF8"); //�닿쾬 �먰븳 �쒓�(utf8)��吏�썝�섍린 �꾪븳 寃�
 
	$password=$_REQUEST['password'];
	$birth=$_REQUEST['birth'];
	$name=$_REQUEST['name'];

$result=  mysql_result(mysql_query("select count(birth) from customer where name='$name' AND birth='$birth' AND pw='$password';"),0);
	
 
$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; //xml�뚯씪��異쒕젰��肄붾뱶
$xmlcode .= "<result>$result</result>\n"; //DB insert媛��깃났�곸쑝濡��먮뒗吏��щ�瑜��뺤씤�섍린 �꾪빐 result媛믪쓣 xml濡�異쒕젰�쒗궡

$dir = "C:/APM_Setup/htdocs"; //insertresult.xml �뚯씪����옣��寃쎈줈
$filename = $dir."/disableresult.xml";
 
file_put_contents($filename, $xmlcode); //xmlcode���댁슜��xml�뚯씪濡�異쒕젰
?>

