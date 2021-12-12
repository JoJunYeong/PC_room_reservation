<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> <!-- 占쎌뮄占�筌욑옙��DB占쏙옙utf-8_unicode_ci嚥∽옙筌띾슢諭억옙�곻폒占쎈챷��占쏙옙占쎄쑵釉�占쎈슣��-->
<?php
$connect = mysql_connect("127.0.0.1", "root", "0216"); //DB媛��덈뒗 二쇱냼(�닿쾬���뱀꽌踰꾨줈 吏곸젒 �묒냽�섎뒗 寃껋씠湲��뚮Ц��猷⑦봽諛�二쇱냼瑜��⑤룄 ��
mysql_selectdb("zero"); //DB �좏깮
mysql_query("set names UTF8"); //�닿쾬 �먰븳 �쒓�(utf8)��吏�썝�섍린 �꾪븳 寃�


$birth=$_REQUEST['birth'];
$name=$_REQUEST['name'];

$result=  mysql_result(mysql_query("select time_table from customer where C_name='$name';"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; //xml�뚯씪��異쒕젰��肄붾뱶
$xmlcode .= "<result>$result</result>\n"; //DB inse1rt媛��깃났�곸쑝濡��먮뒗吏��щ�瑜��뺤씤�섍린 �꾪빐 result媛믪쓣 xml濡�異쒕젰�쒗궡

$dir = "C:/APM_Setup/htdocs"; //insertresult.xml �뚯씪����옣��寃쎈줈
$filename = $dir."/sendprofile.xml";
 
file_put_contents($filename, $xmlcode); //xmlcode占쏙옙占쎈똻�쒙옙占퐔ml占쎈슣�ゆ에占썹빊�뺤젾
?>

