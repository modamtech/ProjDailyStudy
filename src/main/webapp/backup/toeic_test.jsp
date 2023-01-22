<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, com.modam.biz.dstudy.*" %>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

    <!-- User Definition -->
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <link type="text/css" rel="stylesheet" href="css/daily_study.css" />
    <script src="js/modam_toeic_test.js"></script>

    <title>VOCBULARY STUDY</title>

    </head>
    <body>
    <div data-role="page" id="page1"> 
        <!-- 
	    <div data-role="header">
			<h2 style="color:#800000;">Daily Paragraph</h2> 
			<a href="/modam/modam.jsp" data-icon="home" data-iconpos="notext">home</a>
		</div>
        -->		
	    <div data-role="content">
	       <div class="ui-body ui-body-a gap" style="background-color:#E9E9E9; font-size:15px">
				<select id="pronun_nations" data-inline="true">
					<option value="Google US English" selected>US English</option>
					<option value="Google UK English Female">UK English</option>								
					<option value="Safari">Safari</option>								
				</select>
				<img src="img/speaker2.png" onclick="submitStnceTTS('ACT')" style="margin: 0px; width:28px; height:24px;"/><b> Actual </b>&nbsp;				
				<img src="img/speaker2.png" onclick="submitStnceTTS('TRN')" style="margin: 0px; width:28px; height:24px;"/><b> Training </b>&nbsp;
		    	<select id="selno" data-inline="true">
					<option value="1" selected>PART 1</option>
					<option value="7">PART 2</option>
					<option value="32">PART 3</option>
					<option value="71">PART 4</option>				
				</select>
		    	<button id="btnLoadToeicLcXml" data-inline="true">TOEIC LC</button>				
   		    	<button id="btnNext" style="background-color: #305496; color:#FFFFFF; font-weight: bold;" data-inline="true">Next</button>
   		    	<button id="btnPrev" style="background-color: #305496; color:#FFFFFF; font-weight: bold;" data-inline="true">Prev</button>
		    	<!-- 
		    	<button id="btnLoadJson" data-inline="true">JSON</button>	    	
		    	<button id="btnLoadHtml" data-inline="true">HTML</button> &nbsp;
 				-->		    	
				<textarea id="tts_text" name="tts_text" onclick="this.value='';" style="margin: 0px; width:100%; height:10px; background-color: #A9D08E; font-weight: bold;" rows="20" cols="50">
				</textarea>
		    </div>
	        <hr>
            <ul data-role="listview" id="listsentence" data-inset="true">
            	<li id="item">데이터 로드 하기 전에 .... </li>
            </ul>
		</div>
		<div data-role="footer" data-position="fixed"  data-fullscreen="true">
		</div>		
	</div>
    </body>
    </html>
    
    