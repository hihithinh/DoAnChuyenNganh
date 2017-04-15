<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>Upload Successful</title>
<s:head />
</head>
<body>

<h3>Struts 2 file upload example</h3>
 File Name :   <s:property value="uploadDocFileName"></s:property><br /> 
 Content type: <s:property value="uploadDocContentType"></s:property><br /> 
 User file :   <s:property value="uploadDoc"></s:property>
</body>
</html>