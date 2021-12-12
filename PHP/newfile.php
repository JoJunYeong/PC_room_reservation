<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> <!-- 한글 지원(DB는 utf-8_unicode_ci로 만들어주세요)을 위한 소스 -->
<?
$connect = mysql_connect("127.0.0.1", "root", "0216"); //DB가 있는 주소(이것은 웹서버로 직접 접속하는 것이기 때문에 루프백 주소를 써도 됨)
mysql_selectdb("zero"); //DB 선택
mysql_query("set names utf8"); //이것 또한 한글(utf8)을 지원하기 위한 것
 
	$password=$_REQUEST['password'];
	$birth=$_REQUEST['birth'];
	$name=$_REQUEST['name'];
	 
$qry = "insert into customer values('$name', '$birth', '$password');";
$result = mysql_query($qry);
 
$xmlcode = "<?xml version = \"1.0\" encoding = \"utf-8\" ?>\n"; //xml파일에 출력할 코드
$xmlcode .= "<result>$result</result>\n"; //DB insert가 성공적으로 됐는지 여부를 확인하기 위해 result값을 xml로 출력시킴
 
$dir = "C:/APM_Setup/htdocs"; //insertresult.xml 파일을 저장할 경로
$filename = $dir."/insertresult.xml";
 
file_put_contents($filename, $xmlcode); //xmlcode의 내용을 xml파일로 출력
?>