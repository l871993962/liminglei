package com.yss.ams.visaval.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class APITree {
	/**
	 * 树节点或者叶子节点
	 */
	private String nodeCode;
	
	/**
	 * 左括号
	 */
	private String leftParenthese = "(";
	
	/**
	 * 右括号
	 */
	private String rightParenthese = ")";
	
	/**
	 * 树叶子
	 */
	private boolean isLeaf = false;
	
	/**
	 * 树集合
	 */
	private List<APITree> list = new ArrayList<APITree>();
	
	public String getLeftParenthese() {
		return leftParenthese;
	}

	public String getRightParenthese() {
		return rightParenthese;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public List<APITree> getList() {
		return list;
	}

	public void setList(List<APITree> list) {
		this.list = list;
	}
	
//	public static void main(String args[]){
//		String str = "a=函数1(金额1,组合1,日期1) + 函数2(组合2,日期2)*bb(b-c);##b=函数3(金额3,组合3,日期3); (8-0) 函数1(金额1,组合1,日期1)";
//		str = str.replaceAll("=", " = ");
//		str = str.replaceAll("\\+", " + ");
//		str = str.replaceAll("\\-", " - ");
//		str = str.replaceAll("\\*", " * ");
//		str = str.replaceAll("/", " / ");
////		String regex = "[\\S]+\\(\\S+\\)[\\s|\\;|\\+]";
//		String regex = "[\\S]+\\(\\S+\\)";
//		String regeb = "[\\S]+\\((?<=\\()[^\\)]+\\)";
//		Pattern pattern = Pattern.compile(regeb);
//		Matcher matcher = pattern.matcher(str);
//		while(matcher.find()){
////			System.out.println(matcher.group());
//			String funRegex = ".*?(?=\\()";
//			Pattern funPattern =  Pattern.compile(funRegex);
//			Matcher funMatcher = funPattern.matcher(matcher.group());
//			if(funMatcher.find()){
////				System.out.println(funMatcher.group());
//			}
//			
//			String paraRegex = "(?<=\\()[^\\)]+";
//			Pattern paraPattern = Pattern.compile(paraRegex);
//			Matcher paraMatcher = paraPattern.matcher(matcher.group());
//			if(paraMatcher.find()){
////				System.out.println(paraMatcher.group());
//			}
//		}
//		
//	}
}
