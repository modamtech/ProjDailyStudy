package com.modam.view.dstudy;

import java.io.FileWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.modam.biz.dstudy.StudyXmlVO;
import com.modam.biz.dstudy.impl.DstudyDAO;

public class StudyXmlController implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest  request, 
			                    HttpServletResponse response) {

		int i  = 0;
		String strStmt = "";
	    String MyFile  = "./user_xml/study_sentence.xml";

	    Vector<StudyXmlVO> vlist = null;
		
		try {
		    FileWriter writeFile = new FileWriter(MyFile, false);
			DstudyDAO  xmlStudyStnce  = new DstudyDAO();
			
			vlist = xmlStudyStnce.writeStudyXml();	
		    int counter = vlist.size();
			
		     strStmt = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
		     strStmt += "<toeic>";

		     while(i < counter)
	         {
		    	 StudyXmlVO	stdyXmlVo = vlist.get(i);		    	 
	   	    	strStmt +=  "<item>";
	   	    	strStmt +=  "  <test>" + stdyXmlVo.getTest() +"</test>" ;	   	    	
	   	    	strStmt +=  "  <no>" + stdyXmlVo.getNo() +"</no>" ;
	   	    	strStmt +=  "  <seq>" + stdyXmlVo.getSeq() +"</seq>" ;	   	    	
	   	    	strStmt +=  "  <text>";
	   	    	strStmt +=  "    <org_text>" + stdyXmlVo.getOrgText() + "</org_text>" ;	   	    	
	   	    	strStmt +=  "    <short_text>" + stdyXmlVo.getShortText() + "</short_text>" ;	   	    	
	   	    	strStmt +=  "  </text>";	   	    	
	   	    	strStmt +=  "</item>";
	   	    	
	   	    	i = i + 1;
	         }
		     strStmt += "</toeic>";

		     writeFile.write(strStmt);
	         writeFile.close();
	         
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
		
	    ModelAndView mav = new ModelAndView();		
		
		return mav;		
	}
	
}
