package com.modam.biz.dstudy.impl;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.modam.biz.common.SqlSessionFactoryBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// VO
import com.modam.biz.dstudy.DstudyNumVO;
import com.modam.biz.dstudy.DstudyVO;
import com.modam.biz.dstudy.StudyXmlVO;

@Repository("dstudyDAO")
public class DstudyDAO_org_backup { //RegisterMgr

	final Logger logger = LoggerFactory.getLogger(DstudyDAO_org_backup.class);
	private SqlSession mybatis;

	public DstudyDAO_org_backup() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
	}
		
	public Vector<DstudyVO> getDailyVocaNotAll(int pNum) {
        //2. 학습할 저장된 단어들을 조회한다.
	    String varSql     = "";
	    String varSql1    = "";
	    String varSql2    = "";
	    String varSql3    = "";
		
		List<DstudyVO> llist = new ArrayList<DstudyVO>();	    
		Vector<DstudyVO> vlist = new Vector<DstudyVO>();		

	    varSql1  = "SELECT num, word, tag, frequency_chg, order_priority, mean, "; 
	    varSql1 += "       word_eng_example, word_kor_example, word_eng_example2, word_kor_example2, start_date ";
	    varSql1 += "  FROM daily_voca ";
	  	varSql1 += " WHERE num = 'D' ";
  	
	  	varSql   = varSql1 + varSql2 + varSql3;
	  	
		Map<String, Object> inputData = new HashMap<String, Object>();		
		inputData.put("varSql", varSql);		  	
		
		llist = mybatis.selectList("DstudyDAO.getDailyVocaNotAll", inputData);
		
	    Iterator<DstudyVO> l_itrtr = llist.iterator();
		
		if (!llist.isEmpty()) {
			while(l_itrtr.hasNext()) {
				DstudyVO regBean = new DstudyVO();
				
				regBean = l_itrtr.next();
				vlist.add(regBean);
			}
		}  			
			
		mybatis.close();
		
		return vlist;
	}	
	
	public List<DstudyVO> getDailyVocaAll(String num) {
		
		List<DstudyVO> llist = new ArrayList<DstudyVO>();		
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("num", num);		
		
		llist = mybatis.selectList("DstudyDAO.getDailyVocaAll", inputData);

		mybatis.close();		
		
		return llist;
	}
	
	// purpose : to get current word number
	public DstudyNumVO getRccntNum() {
		
		DstudyNumVO mvo = new DstudyNumVO();
		
		mvo = (DstudyNumVO) mybatis.selectOne("DstudyDAO.getRccntNum");
		
		mybatis.close();
		
		return mvo;
	}
	
	// purpose : to get current word number
	public DstudyNumVO getTotalCount() {
		
		DstudyNumVO mvo = new DstudyNumVO();	
		
		mvo = (DstudyNumVO) mybatis.selectOne("DstudyDAO.getTotalCount");
		
		mybatis.close();
		
		return mvo;
	}		
	
	// purpose : to insert or current sentence for TTS	
	public void insertTtsText(String strKey, String strWord, String strSaveTTS) {
		
		Map<String, Object> inputData = new HashMap<String, Object>();		
		DstudyNumVO mvo = new DstudyNumVO();
		
		String strNum = "";
		
		try {
			mvo = (DstudyNumVO) mybatis.selectOne("DstudyDAO.getMaxNum", strWord);
			strNum = mvo.getNum();			
		} catch (Exception e){
			strNum = "0";
	    }  

		strNum = String.valueOf(Integer.valueOf(strNum)+1);
		
		inputData.put("num" , strKey);
		inputData.put("word", strWord);
		inputData.put("no"  , strNum);		
		inputData.put("sentence", strSaveTTS);
		
		mybatis.insert("DstudyDAO.insertTtsText", inputData);
		mybatis.commit();
		
		mybatis.close();
	}
	
	// purpose : to maintain the information of daily studying words
	public void insertDailyStudyList(String strKey, String strWord, String strStartDate) {
		Map<String, Object> inputData = new HashMap<String, Object>();		
		inputData.put("num", strKey);
		inputData.put("word", strWord);
		inputData.put("startdate", strStartDate);
		
		try {
			mybatis.insert("DstudyDAO.insertDailyStudyList", inputData);
		} catch (Exception e) {
			mybatis.update("DstudyDAO.updateDailyStudyList", inputData);
	    }
	    mybatis.commit();
	    
		mybatis.close();	    
	}
	
	// purpose : to insert or update current word number
	public void insertRcntNo(String strKey, String strSort) {
		Map<String, Object> inputData = new HashMap<String, Object>();		
		inputData.put("num" , strKey);
		inputData.put("sort", strSort);
		
		try {
			mybatis.insert("DstudyDAO.insertRcntNo", inputData);
		} catch (Exception e) {
			mybatis.update("DstudyDAO.updateRcntNo", inputData);
	    }
	    mybatis.commit();
	    
		mybatis.close();	    
	}
	
	public void updateFrequency(String num, String optCase) {
		Map<String, Object> inputData = new HashMap<String, Object>();		
		inputData.put("num", num);						
		
		if (optCase.equals("F"))
		{
			mybatis.update("DstudyDAO.updateFrequency", inputData);
		}
		mybatis.commit();
		
		mybatis.close();		
	}
	
	public void updatePriority(String num, String optCase) {
		Map<String, Object> inputData = new HashMap<String, Object>();		
		inputData.put("num", num);		
		
		if (optCase.equals("P"))
		{
			mybatis.update("DstudyDAO.updatePriority", inputData);
		}
		
		mybatis.commit();
		
		mybatis.close();		
	}
	
	public void updateDone(String num, String optCase) {
		Map<String, Object> inputData = new HashMap<String, Object>();		
		inputData.put("num", num);				
		
		if (optCase.equals("D"))
		{
			mybatis.update("DstudyDAO.updateDone", inputData);
			//mybatis.insert("DstudyDAO.insertBasicVoca", inputData);
		}
		
		mybatis.commit();
		
		mybatis.close();		
	}
	
	public void updateStatus() {
		
		mybatis.update("DstudyDAO.updateStatus");	
		mybatis.commit();
		
		mybatis.close();		
	}
	
	public void updateStatusToNew() {
		
		mybatis.update("DstudyDAO.updateStatusToNew");
		mybatis.commit();
		
		mybatis.close();		
	}	
	
	public Vector<StudyXmlVO> writeStudyXml() {
		
		List<StudyXmlVO> lStdyXml = new ArrayList<StudyXmlVO>();
		Vector<StudyXmlVO> vlist = new Vector<StudyXmlVO>();
		
		//2. 학습할 저장된 단어들을 조회한다.	
	    lStdyXml = mybatis.selectList("DstudyDAO.getStudyXml");
	    
	    mybatis.close();
	    
	    Iterator<StudyXmlVO> l_itrtr = lStdyXml.iterator();
	
		if (!lStdyXml.isEmpty()) {
			while(l_itrtr.hasNext()) {
				StudyXmlVO xmlStudyVo = new StudyXmlVO();
				xmlStudyVo = l_itrtr.next();
				
				xmlStudyVo.setTest(xmlStudyVo.getTest());					
				xmlStudyVo.setNo(xmlStudyVo.getNo());
				xmlStudyVo.setSeq(xmlStudyVo.getSeq());					
				xmlStudyVo.setOrgText(xmlStudyVo.getOrgText());
				xmlStudyVo.setShortText(xmlStudyVo.getShortText());
				xmlStudyVo.setCreateDate(xmlStudyVo.getCreateDate());	

				vlist.add(xmlStudyVo);
			}
		}  
		
		return vlist;
	}			
}
