<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>DAILY VOCBULARY</title>		
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
		<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
		
		<!-- User Definition -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />
        <link type="text/css" rel="stylesheet" href="css/daily_study.css" />		
        <link type="text/css" rel="stylesheet" href="css/modam.css" />		
        <script src="js/modam.js"></script>
	</head>
	<body>
		<div data-role="page" id="page1" data-theme="a">
  	        <div data-role="header"  data-position="fixed" data-theme="e">          
				<h2 style="color:#800000;">DAILY VOCBULARY</h2>
			</div>
			<div data-role="content">
		        <p><img src="img/coat_of_arms.png"></p>			
				<form id="frmModam" name="frmModam">
		            <ul data-role="listview" data-inset="true">
		            	<li><a href="#" onclick="doSubmit(1);"> Daily Vocabulary </a></li>
		            	<li><a href="#" onclick="doSubmit(2);"> Daily Sentence XML ${rcVal}</a></li>
		            	<li><a href="#" onclick="doSubmit(3);"> Daily Toeic Test </a></li>
		            </ul>
				</form>				
			</div>
			<div data-role="footer" data-position="fixed">
			    <h4>" Vocabulary is an instrument of English "</h4>	       
			</div>
		</div>		
	</body>
</html>