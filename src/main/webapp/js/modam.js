//verb 여부 처리 함수    
function initConnection(pOrder)
{
	$.ajax(
	 	  {
			 url: '/initconnection.do',
			 success: function(dto){
				    doSubmit(pOrder);
			 },
			 error: function(){				 
				    doSubmit(pOrder);
			 }
	      }
    );
};

function doSubmit(pOrder){
	
	var objDoc = document.getElementById("frmModam");

	if (pOrder==1){
		objDoc.method = "post";
		objDoc.action ="/selectdlstudy.do";		
		objDoc.submit();
	} else if (pOrder==2){
		objDoc.method = "post";
		objDoc.action ="/livingenglish.do";
		objDoc.submit();
	} else if (pOrder==3){
		objDoc.method = "post";
		objDoc.action ="/livingenglishdata.do";
		objDoc.submit();		
	//} else if (pOrder==2){
	//	objDoc.method = "post";
	//	objDoc.action ="/studyxml.do";
	//	objDoc.submit();									
	} else if (pOrder==4){
		objDoc.method = "post";
		objDoc.action ="/toeic_test.jsp";
		objDoc.submit();
	} else if (pOrder==5){
		objDoc.method = "post";
		objDoc.action ="/sentence.jsp";
		objDoc.submit();
	}
}