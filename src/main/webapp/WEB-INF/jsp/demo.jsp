<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="org.apache.http.util.EntityUtils"%>
<%@page import="org.apache.http.entity.StringEntity"%>
<%@page import="org.apache.http.client.methods.CloseableHttpResponse"%>
<%@page import="org.apache.http.client.entity.UrlEncodedFormEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.http.message.BasicNameValuePair"%>
<%@page import="org.apache.http.NameValuePair"%>
<%@page import="java.util.List"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.apache.http.impl.client.HttpClients"%>
<%@page import="org.apache.http.impl.client.CloseableHttpClient"%>
<%@page import="org.apache.http.impl.client.HttpClientBuilder"%>
<%@page import="org.apache.http.client.methods.HttpPost"%>
<%@page import="org.apache.http.client.HttpClient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	String name = request.getParameter("name");
	String number = request.getParameter("currency");
	String resultName = "";
	String resultCurrency = "";
	String message = "Please enter Name and Number to convert in words";
	
	if (!StringUtils.isEmpty(number) && !StringUtils.isEmpty(name)) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("http://localhost:8080/api/convert-to-string");

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("number", Double.parseDouble(number.trim()));

			httpPost.setEntity(new StringEntity(json.toString()));
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			CloseableHttpResponse resp = client.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == 200) {
				JSONObject op = new JSONObject(EntityUtils.toString(resp.getEntity()));
				resultName = op.optString("name");
				resultCurrency = op.optString("stringOfNumber");
			}else if(resp.getStatusLine().getStatusCode() == 400){
				message="maximum currency conversion value support exceeded";
			}
			client.close();
		} catch(NumberFormatException e){
			message="Only numbers are expected in Currency";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Please enter details</title>
</head>
<body>
	<form action="#" method="get">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>Currency:</td>
				<td><input type="text" name="currency" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" /></td>
			</tr>
		</table>
		<%
			if (!StringUtils.isEmpty(resultName) && !StringUtils.isEmpty(resultCurrency)) {
				out.println(resultName);
				out.print("</BR>");
				out.println(resultCurrency);
			} else {
				out.println(message);
			}
		%>
	</form>
</body>
</html>