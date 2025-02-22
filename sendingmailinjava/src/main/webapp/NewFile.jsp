<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="sendingemail" method="post">
    <label for="recipient">Recipient:</label>
    <input type="email" id="recipient" name="recipient" required><br><br>
    
    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject" required><br><br>
    
    <label for="message">Message:</label>
    <textarea id="message" name="message" required></textarea><br><br>
    
    <input type="submit" value="Send Email">
</form>
	
</body>
</html>