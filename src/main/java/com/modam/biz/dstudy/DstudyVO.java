package com.modam.biz.dstudy;

import jdk.nashorn.internal.objects.annotations.Getter;

public class DstudyVO {
  private String num;
  
  private String word;
  
  private String tag;
  
  private String frequency;
  
  private String order_priority;
  
  private String mean;
  
  private String word_eng_example;
  
  private String word_kor_example;
  
  private String word_eng_example2;
  
  private String word_kor_example2;
  
  private String startDate;
  
  private String isVerb;
  
  private String word_guess;
  
  private String status;  
  
  private String todayCnt;
  
  private String todayStudyCnt;
  
  public void setNum(String num) {
    this.num = num;
  }

  @Getter  
  public String getNum() {
    return this.num;
  }
  
  public void setWord(String word) {
    this.word = word;
  }
  
  @Getter
  public String getWord() {
    return this.word;
  }
  
  public void setTag(String tag) {
    this.tag = tag;
  }
  
  @Getter
  public String getTag() {
    return this.tag;
  }
  
  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }
  
  @Getter
  public String getFrequency() {
    return this.frequency;
  }
  
  public void setOrderPriority(String order_priority) {
    this.order_priority = order_priority;
  }
  
  @Getter
  public String getOrderPriority() {
    return this.order_priority;
  }
  
  public void setMean(String mean) {
    this.mean = mean;
  }
  
  @Getter
  public String getMean() {
    return this.mean;
  }
  
  public void setWordEngExample(String word_eng_example) {
    this.word_eng_example = word_eng_example;
  }
  
  @Getter
  public String getWordEngExample() {
    return this.word_eng_example;
  }
  
  public void setWordKorExample(String word_kor_example) {
    this.word_kor_example = word_kor_example;
  }
  
  @Getter
  public String getWordKorExample() {
    return this.word_kor_example;
  }
  
  public void setWordEngExample2(String word_eng_example2) {
    this.word_eng_example2 = word_eng_example2;
  }
  
  @Getter
  public String getWordEngExample2() {
    return this.word_eng_example2;
  }
  
  public void setWordKorExample2(String word_kor_example2) {
    this.word_kor_example2 = word_kor_example2;
  }
  
  @Getter
  public String getWordKorExample2() {
    return this.word_kor_example2;
  }
  
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  
  @Getter
  public String getStartDate() {
    return this.startDate;
  }
    
  public void setIsVerb(String isVerb) {
	    this.isVerb = isVerb;
  }
	  
  @Getter
  public String getIsVerb() {
    return this.isVerb;
  }
  
  public void setIsGuess(String word_guess) {
    this.word_guess = word_guess;
  }
	  
  @Getter
  public String getIsGuess() {
    return this.word_guess;
  }  
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  @Getter
  public String getStatus() {
    return this.status;
  }
  
  public void setTodayCnt(String todayCnt) {
    this.todayCnt = todayCnt;
  }
  
  @Getter
  public String getTodayCnt() {
    return this.todayCnt;
  }
  
  public void setTodayStudyCnt(String todayStudyCnt) {
    this.todayStudyCnt = todayStudyCnt;
  }
  
  @Getter
  public String getTodayStudyCnt() {
    return this.todayStudyCnt;
  }
}