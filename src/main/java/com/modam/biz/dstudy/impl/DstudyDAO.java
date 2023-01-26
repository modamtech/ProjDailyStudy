package com.modam.biz.dstudy.impl;

import com.modam.biz.common.SqlSessionFactoryBean;
import com.modam.biz.dstudy.DstudyNumVO;
import com.modam.biz.dstudy.DstudyVO;
import com.modam.biz.dstudy.LivingEngVo;
import com.modam.biz.dstudy.StudyXmlVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("dstudyDAO")
public class DstudyDAO {
  final Logger logger = LoggerFactory.getLogger(DstudyDAO.class);
  
  private SqlSession mybatis;
  
  public DstudyDAO() {
    this.mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
  }
  
  public Vector<DstudyVO> getDailyVocaNotAll(int pNum) {
    String varSql = "";
    String varSql1 = "";
    String varSql2 = "";
    String varSql3 = "";
    
	List<DstudyVO> llist   = new ArrayList<DstudyVO>();	    
	Vector<DstudyVO> vlist = new Vector<DstudyVO>();		
    
    varSql1 = "SELECT num, word, tag, frequency_chg, order_priority, mean, ";
    varSql1 = String.valueOf(varSql1) + "       word_eng_example, word_kor_example, word_eng_example2, word_kor_example2, start_date ";
    varSql1 = String.valueOf(varSql1) + "  FROM daily_voca ";
    varSql1 = String.valueOf(varSql1) + " WHERE num = 'D' ";
    varSql = String.valueOf(varSql1) + varSql2 + varSql3;
    
    Map<String, Object> inputData = new HashMap<String, Object>();
    
    inputData.put("varSql", varSql);
    llist = this.mybatis.selectList("DstudyDAO.getDailyVocaNotAll", inputData);
    Iterator<DstudyVO> l_itrtr = llist.iterator();
    if (!llist.isEmpty())
      while (l_itrtr.hasNext()) {
        DstudyVO regBean = new DstudyVO();
        regBean = l_itrtr.next();
        vlist.add(regBean);
      }  
    return vlist;
  }
  
  public String getFirstWord() {
    return (String)this.mybatis.selectOne("DstudyDAO.getFirstWord");
  }
  
  public String getMissingWord() {
    return (String)this.mybatis.selectOne("DstudyDAO.getMissingWord");
  }
  
  public List<DstudyVO> selectVocaTmp() {
    return this.mybatis.selectList("DstudyDAO.selectVocaTmp");
  }
  
  public DstudyNumVO getRccntNum() {
    return (DstudyNumVO)this.mybatis.selectOne("DstudyDAO.getRccntNum");
  }
  
  public DstudyNumVO getTotalCount() {
    return (DstudyNumVO)this.mybatis.selectOne("DstudyDAO.getTotalCount");
  }
  
  public String getTotalTodayStudy() {
    return (String)this.mybatis.selectOne("DstudyDAO.getTotalTodayStudy");
  }
  
  public void insertTtsText(String strKey, String strWord, String strSaveTTS) {
	  
    Map<String, Object> inputData = new HashMap<String, Object>();	  

    DstudyNumVO mvo = new DstudyNumVO();
    String strNum = "";
    
    try {
      mvo = (DstudyNumVO)this.mybatis.selectOne("DstudyDAO.getMaxNum");
      strNum = mvo.getNum();
    } catch (Exception e) {
      strNum = "0";
    } 
    strNum = String.valueOf(Integer.valueOf(strNum).intValue() + 1);
    inputData.put("num", strKey);
    inputData.put("word", strWord);
    inputData.put("no", strNum);
    inputData.put("sentence", strSaveTTS);
    this.mybatis.insert("DstudyDAO.insertTtsText", inputData);
    this.mybatis.commit();
  }
  
  public void insertDailyStudyList(String strKey, String strWord, String strStartDate) {
	  
	Map<String, Object> inputData = new HashMap<String, Object>();	  

    inputData.put("num", strKey);
    inputData.put("word", strWord);
    inputData.put("startdate", strStartDate);
    try {
      this.mybatis.insert("DstudyDAO.insertDailyStudyList", inputData);
    } catch (Exception e) {
      this.mybatis.update("DstudyDAO.updateDailyStudyList", inputData);
    } 
    this.mybatis.commit();
  }
  
  public void completeVocaTmp(String pnum, String pstatus, String pword, String pfrequency) {
	  
	Map<String, Object> inputData = new HashMap<String, Object>();	  
	  
    if ("".equals(pfrequency))  pfrequency = "0";
    
    inputData.put("num", pnum);
    inputData.put("status", pstatus);
    inputData.put("frequency", pfrequency);
    try {
      this.mybatis.insert("DstudyDAO.completeVocaTmp", inputData);
    } catch (Exception e) {
      this.logger.debug(e.getMessage().toString());
    } 
    this.mybatis.commit();
  }
  
  public void updateStartDate(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    this.mybatis.update("DstudyDAO.updateStartDate", inputData);
    this.mybatis.commit();
  }
  
  public void updateFrequency(String num, String optCase) {
	Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    if (optCase.equals("F"))
      this.mybatis.update("DstudyDAO.updateFrequency", inputData); 
    this.mybatis.commit();
  }
  
  public void updateWordGuess(String num, String strWordGuess) {
	Map<String, Object> inputData = new HashMap<String, Object>();	 
		
	inputData.put("num",        num);
	inputData.put("word_guess", strWordGuess);

    this.mybatis.update("DstudyDAO.updateWordGuess", inputData);
   
    this.mybatis.commit();
  }
  
  public void updatePriority(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    this.mybatis.update("DstudyDAO.updatePriority", inputData);
    this.mybatis.commit();
  }

  public String selectPriority(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    return (String)this.mybatis.selectOne("DstudyDAO.selectPriority", inputData);
  }  
  
  public void updateVerb(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    this.mybatis.update("DstudyDAO.updateVerb", inputData);
    this.mybatis.commit();
  }
  
  public void initConnection() {
    this.mybatis.update("DstudyDAO.initConnection");
    this.mybatis.commit();
  }
  
  public void initTodayStatus() {
    this.mybatis.update("DstudyDAO.initTodayStatus");
    this.mybatis.commit();
  }  
  
  public String selectVerb(String num) {
	Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    return (String)this.mybatis.selectOne("DstudyDAO.selectVerb", inputData);
  }  
    
  public void updateDone(String num, String optCase) {
    Map<String, Object> inputData = new HashMap<String, Object>();	  
    inputData.put("num", num);
    if (optCase.equals("D"))
      this.mybatis.update("DstudyDAO.updateDone", inputData); 
    this.mybatis.commit();
  }
  
  public void updateStatus(String num) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    this.mybatis.update("DstudyDAO.updateStatus", inputData);
    this.mybatis.commit();
  }
  
  public void deleteVocaTmp() {
    this.mybatis.delete("DstudyDAO.deleteVocaTmp");
    this.mybatis.commit();
  }
  
  public void insertVocaTmp(String pword) {

    Map<String, Object> inputData = new HashMap<String, Object>();
    int intChr = pword.charAt(0);
    if (intChr > 122)
      intChr = 97; 
    char chr_lower = (char)intChr;
    char chr_upper = Character.toUpperCase(chr_lower);
    String str_lower = "'" + String.valueOf(chr_lower) + "'";
    String str_upper = "'" + String.valueOf(chr_upper) + "'";
    inputData.put("lower_apha", str_lower);
    inputData.put("upper_apha", str_upper);

    this.mybatis.insert("DstudyDAO.insertVocaTmp", inputData);
    this.mybatis.commit();
  }
  
  public Vector<StudyXmlVO> writeStudyXml() {
	  
	List<StudyXmlVO> lStdyXml = new ArrayList<StudyXmlVO>();
	Vector<StudyXmlVO> vlist = new Vector<StudyXmlVO>();
	
    lStdyXml = this.mybatis.selectList("DstudyDAO.getStudyXml");
    Iterator<StudyXmlVO> l_itrtr = lStdyXml.iterator();
    if (!lStdyXml.isEmpty())
      while (l_itrtr.hasNext()) {
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
    return vlist;
  }
  
  public List<LivingEngVo> selectLivingEngTmp(String pNum) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", pNum);
    return this.mybatis.selectList("DstudyDAO.selectLivingEngTmp", inputData);
  }
  
  public void closeMybatis() {
    this.mybatis.close();
  }
  
  public String getTodayStudyCount() {
    return (String)this.mybatis.selectOne("DstudyDAO.getTodayStudyCount");
  }
}