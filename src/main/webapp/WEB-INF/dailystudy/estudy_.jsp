<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <link type="text/css" rel="stylesheet" href="css/daily_study.css" />
    <link type="text/css" rel="stylesheet" href="css/modam.css" />    
    <script src="js/daily_study.js"></script>
    <script src="js/modam.js"></script>  
    <script>
	// function 위치    
	function submitWord(pVal)
	{
		alert("222");
	    var objDrctStatus = document.getElementById("drctStatus");
	    objDrctStatus.value = pVal;
	    
	    var objSortStatus = document.getElementById("sortStatus");
	    var v_sortStatus = ${varSortStatus};
	    alert(v_sortStatus);
	    objSortStatus.value = v_sortStatus;
	    
		// 현재 학습 위치 setup
		var objNum = document.getElementById("num");
		objNum.value = ${intCrrntNum};        
		
	    document.getElementById("frmVoca").submit();
	}
	</script>

    <title>VOCBULARY STUDY</title>

    </head>
    <body>
    <div data-role="page" id="page1">  
	    <div data-role="header"  data-position="fixed" data-theme="e">          
			<h2 style="color:#800000;">Daily Vocabulary</h2>
			<a href="/modam.html" data-icon="home" data-iconpos="notext">home</a>
			<div data-role="navbar">
				<ul>
					<li><a href="#page2" data-icon="search">MEANING</a></li>
					<li><a href="#"      data-icon="arrow-r"  onClick="submitWord('2');">NEXT</a></li>
					<li><a href="#"      data-icon="arrow-l"  onClick="submitWord('1');">BEFORE</a></li>
					<li><a href="#"      data-icon="back"     onClick="submitWord('0');">FIRST</a></li>
					<li><a href="#"      data-icon="info"     data-iconpos="notext" >Help</a></li>
				</ul>                  	                  	
			</div>			        		
		</div>
	    <div data-role="content">		

			<form id="frmVoca" name="frmVoca" method=post action="/selectdlstudy.do"> 
				<div id="vocaDiv" style="display:block;">  			
					<div class="ui-grid-c">
					    <div data-role="controlgroup" data-type="horizontal">
					        <a href="#" id="sort1"    data-role="button"  data-icon="action"  onClick="submitSort('DV',    '${intCrrntNum}')" style="background-color:#E9E9E9;"><b>DAILY</b></a>
					        <a href="#" id="sort2"    data-role="button"  data-icon="action"  onClick="submitSort('NEW', '${intCrrntNum}')" style="background-color:#E9E9E9;"><b>NEW</b></a>					        
					        <a href="#" id="sort2"    data-role="button"  data-icon="action"  onClick="submitSort('TOEIC', '${intCrrntNum}')" style="background-color:#E9E9E9;"><b>E.VOCA</b></a>			        
					        <a href="#" id="sort3"    data-role="button"  data-icon="action"  onClick="submitSort('ALL',   '${intCrrntNum}')" style="background-color:#E9E9E9;"><b>ALL</b></a>
					        <a href="#" id="sort3"    data-role="button"  data-icon="info" style="background-color:#E9E9E9; color:black;"><b>COUNT : ${viewCounter} </b></a>					        
                        </div>	                           	  
						<div class="ui-block-a">
							<div class="ui-bar ui-bar-a" align="center">
							     <strong>NO</strong>
							</div>
						</div> 
						<div class="ui-block-b">
							<div class="ui-bar ui-bar-a" align="center">
							     <strong>WORDS [<span style="color:blue;"> ${pageStatus} </span>]</strong>
							</div>
						</div>
		 		    </div>								
					<div class="ui-grid-c">
						<div class="ui-block-a">
							<div class="ui-bar"  align="center">
							     ${viewIntNum}
							</div>
						</div>
						<div class="ui-block-b">
							<div class="ui-bar"  align="center">
							  <strong><a href="#" id="ttsText" onclick="submitTTS(3, 1)">${regBean.getWord()} </a></strong> &nbsp;
	  		                 <input type="button" data-icon="audio" value="US" onclick="submitTTS(3, 0)" data-inline="true" >
  				          	 <input type="button" data-icon="audio" value="UK" onclick="submitTTS(4, 0)" data-inline="true" >  				          	 
  				          	 <input type="button" data-icon="audio" value="Apple" onclick="submitTTS(18, 0)" data-inline="true" >  				          	 
							</div>
						</div>
					</div>
					<div class="ui-grid-c">
						<div class="ui-block-a">
							<div class="ui-bar" style="background-color:#E9E9E9;" align="center"><b>CLUE</b></div>
						</div> 
						<div class="ui-block-b">
							<div class="ui-bar" align="center">
							     <span id="tag_info1" style="display:block;">-</span>							
							     <span id="tag_info2" style="display:none;">${regBean.getTag()}</span>
							</div>
						</div>
		 		    </div>						
				</div>
		        <input id="num" name="num" type="hidden" />        
		        <input id="freqStatus"  name="freqStatus"  type="hidden" />
		        <input id="prrtyStatus" name="prrtyStatus" type="hidden" />		        
		        <input id="doneStatus"  name="doneStatus"  type="hidden" />
		        <input id="drctStatus"  name="drctStatus"  type="hidden" />
		        <input id="sortStatus"  name="sortStatus"  type="hidden" />
		        
   		        <input id="tts_sntn"    name="tts_sntn"    type="hidden" />
		        <input id="tts_num"     name="tts_num"     type="hidden" value="${regBean.getNum()}"/>   		        
		        <input id="tts_word"    name="tts_word"    type="hidden" value="${regBean.getWord()}"/>
	        </form>
			<br>
 		</div>
		<div data-role="footer" data-position="fixed">
		    <h4>" Vocabulary is an instrument of English "</h4>	       
		</div>
 	</div>	
    <div data-role="page" id="page2"> 
        <!--  
	    <div data-role="header">
			<h2 style="color:#800000;">DAILY VOCBULARY</h2>                   		
		</div>
		 -->
	    <div data-role="content">
		    <div data-role="controlgroup"        data-type="horizontal">
	            <a href="#page1" id="home"       data-role="button" data-icon="home" onClick="submitWord('2',  '${varSortStatus}', '${intCrrntNum}')" style="background-color:#C6E0B4;">HOME</a>
	       	    <a href="#page1" id="frequency"  data-role="button" data-icon="plus" onClick="updateFreq('F',  '${varSortStatus}', '${intCrrntNum}')" style="background-color:#C6E0B4;">HIGH FREQ.</a>	            
	       	    <a href="#page1" id="prrty"      data-role="button" data-icon="plus" onClick="updatePrrty('P', '${varSortStatus}', '${intCrrntNum}')" style="background-color:#C6E0B4;">PRIORITY</a> 	       
	       	    <a href="#page1" id="cmplte"     data-role="button" data-icon="star" onClick="updateDone('D',  '${varSortStatus}', '${intCrrntNum}')" style="background-color:#C6E0B4;color:#305496;">COMPLETE</a>
	       	    <a href="#"      id="cmplte"     data-role="button" data-icon="" style="background-color:#A9D08E; padding: 5px; margin-left: 6px;color:#C65911; font-size:26px "><b>${regBean.getWord()}</b></a>	       	    
            </div>
	  	   	<div id="meanDiv"> 
				<div class="ui-grid-a">
					<div class="ui-block-a">
						<div class="ui-bar" style="background-color:#E9E9E9; font-size:15px">
							 <b>▣ PRONUN. ▣</b> &nbsp;
  				          	 ⊙ <b>WORD</b>
  				          	 <input type="button" data-icon="audio" value="US" onclick="submitTTS(3, 0)" data-inline="true" >
  				          	 <input type="button" data-icon="audio" value="UK" onclick="submitTTS(4, 0)" data-inline="true" >  				          	 
                         	 ⊙ <b>TEXT</b> 
  				          	 <input type="button" data-icon="audio" value="US" onclick="submitTextTTS(3,'STN')" data-inline="true" >
  				          	 <input type="button" data-icon="audio" value="UK" onclick="submitTextTTS(4,'STN')" data-inline="true" >  				          	 
                         	 ⊙ <b>CHECK</b> 
                         	 <input type="button" data-icon="audio" value="US" onclick="submitTextTTS(3,'CHK')" data-inline="true" >
  				          	 <input type="button" data-icon="audio" value="UK" onclick="submitTextTTS(4,'CHK')" data-inline="true" >  				          	 
                             <input id="tts_text" type="text" onclick="this.value='';" style="margin: 0px; width:100%; height:24px; background-color: #A9D08E;"/></b>                          
						</div>
					</div>
				</div>
				<div class="ui-grid-a">
					<div class="ui-block-a">
						<div class="ui-bar" style="background-color:#C6E0B4;">
					         ${regBean.getMean()}
						</div>
					</div>
				</div><br/>
			</div>			
		</div>
		<div data-role="footer" data-position="fixed" data-fullscreen="true">
			<div data-role="navbar">
				<ul>
                    <li><a href="#"  data-icon="info">For Apple Browser>>></a></li>				
					<li><a href="#"  data-icon="audio" onclick="submitTTS(18, 0)">WORD</a></li>
					<li><a href="#"  data-icon="audio" onclick="submitTextTTS(18,'STN')">TEXT</a></li>
					<li><a href="#"  data-icon="audio" onclick="submitTextTTS(18,'CHK')">CHECK</a></li>
					<li></li>					
				</ul>                  	              	
			</div>	       
		</div>		
	</div>
    </body>
    </html>