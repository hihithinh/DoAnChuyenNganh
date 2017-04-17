<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>Upload Successful</title>
<s:head />
</head>
<body>

<h3>Struts 2 file upload example</h3>
 File Name :   <s:property value="fileDocFileName"></s:property><br /> 
 Content type: <s:property value="fileDocContentType"></s:property><br /> 
 User file :   <s:property value="fileDoc"></s:property>
</body>
</html>