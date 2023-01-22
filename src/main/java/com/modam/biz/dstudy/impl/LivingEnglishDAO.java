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

@Repository("livingEnglishDAO")
public class LivingEnglishDAO {
  final Logger logger = LoggerFactory.getLogger(LivingEnglishDAO.class);
  
  private SqlSession mybatis;
  
  public LivingEnglishDAO() {
    this.mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
  }
  
  public Vector<DstudyVO> getDailyVocaNotAll(int pNum) {
    String varSql  = "";
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
    llist = this.mybatis.selectList("LivingEnglishDAO.getDailyVocaNotAll", inputData);
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
    return (String)this.mybatis.selectOne("LivingEnglishDAO.getFirstWord");
  }
  
  public List<DstudyVO> selectVocaTmp() {
    return this.mybatis.selectList("LivingEnglishDAO.selectVocaTmp");
  }
  
  public DstudyNumVO getRccntNum() {
    return (DstudyNumVO)this.mybatis.selectOne("LivingEnglishDAO.getRccntNum");
  }
  
  public DstudyNumVO getTotalCount() {
    return (DstudyNumVO)this.mybatis.selectOne("LivingEnglishDAO.getTotalCount");
  }
  
  public String getTotalTodayStudy() {
    return (String)this.mybatis.selectOne("LivingEnglishDAO.getTotalTodayStudy");
  }
  
  public void insertTtsText(String strKey, String strWord, String strSaveTTS) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    DstudyNumVO mvo = new DstudyNumVO();
    String strNum = "";
    try {
      mvo = (DstudyNumVO)this.mybatis.selectOne("LivingEnglishDAO.getMaxNum");
      strNum = mvo.getNum();
    } catch (Exception e) {
      strNum = "0";
    } 
    strNum = String.valueOf(Integer.valueOf(strNum).intValue() + 1);
    inputData.put("num", strKey);
    inputData.put("word", strWord);
    inputData.put("no", strNum);
    inputData.put("sentence", strSaveTTS);
    this.mybatis.insert("LivingEnglishDAO.insertTtsText", inputData);
    this.mybatis.commit();
  }
  
  public void insertDailyStudyList(String strKey, String strWord, String strStartDate) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", strKey);
    inputData.put("word", strWord);
    inputData.put("startdate", strStartDate);
    try {
      this.mybatis.insert("LivingEnglishDAO.insertDailyStudyList", inputData);
    } catch (Exception e) {
      this.mybatis.update("LivingEnglishDAO.updateDailyStudyList", inputData);
    } 
    this.mybatis.commit();
  }
  
  public void completeVocaTmp(String pnum, String pstatus, String pword, String pfrequency) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    if ("".equals(pfrequency))
      pfrequency = "0"; 
    inputData.put("num", pnum);
    inputData.put("status", pstatus);
    inputData.put("frequency", pfrequency);
    try {
      this.mybatis.insert("LivingEnglishDAO.completeVocaTmp", inputData);
    } catch (Exception e) {
      this.logger.debug(e.getMessage().toString());
    } 
    this.mybatis.commit();
  }
  
  public void updateStartDate(String num) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    this.mybatis.update("LivingEnglishDAO.updateStartDate", inputData);
    this.mybatis.commit();
  }
  
  public void updateFrequency(String num) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    this.mybatis.update("LivingEnglishDAO.updateFrequency", inputData);
    this.mybatis.commit();
  }
  
  public void updatePriority(String num) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    this.mybatis.update("LivingEnglishDAO.updatePriority", inputData);
    this.mybatis.commit();
  }
  
  public void updatePriorityDate(String num) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    this.mybatis.update("LivingEnglishDAO.updatePriorityDate", inputData);
    this.mybatis.commit();
  }
  
  public void updateDone(String num) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    this.mybatis.update("LivingEnglishDAO.updateDone", inputData);
    this.mybatis.commit();
  }
  
  public void clearStarMark() {
    this.mybatis.update("LivingEnglishDAO.clearStarMark");
    this.mybatis.commit();
  }
  
  public String getCrrNum() {
    return (String)this.mybatis.selectOne("LivingEnglishDAO.getCrrNum");
  }
  
  public String getCrrStatus(String num) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    return (String)this.mybatis.selectOne("LivingEnglishDAO.getCrrStatus", inputData);
  }
  
  public void updateNextNum(String num) {
    Map<String, Object> inputData = new HashMap<String, Object>();
    inputData.put("num", num);
    this.mybatis.update("LivingEnglishDAO.updateNextNum", inputData);
    this.mybatis.commit();
  }
  
  public void deleteVocaTmp() {
    this.mybatis.delete("LivingEnglishDAO.deleteVocaTmp");
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
    this.mybatis.insert("LivingEnglishDAO.insertVocaTmp", inputData);
    this.mybatis.commit();
  }
  
  public Vector<StudyXmlVO> writeStudyXml() {
	  
	List<StudyXmlVO> lStdyXml = new ArrayList<StudyXmlVO>();
	Vector<StudyXmlVO> vlist = new Vector<StudyXmlVO>();
	
    lStdyXml = this.mybatis.selectList("LivingEnglishDAO.getStudyXml");
    
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
    return this.mybatis.selectList("LivingEnglishDAO.selectLivingEngTmp", inputData);
  }
  
  public void closeMybatis() {
    this.mybatis.close();
  }
}