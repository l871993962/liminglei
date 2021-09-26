package com.yss.uco.elecreco.automic.pojo;

import java.util.ArrayList;
import java.util.List;

import com.yss.fast.task.support.automatic.AutomaticResultConstants;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.automic.param.DzResultCheckParam;
import com.yss.uco.elecreco.support.util.FileTypeEnum;


public class ElecAutoInfo {
	private String result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
	private String dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
	private static final String SLIPT_MARK = "<br>";
	private static final String SPACE_TAB = "	";
	private StringBuilder resultMsg = new StringBuilder();
	private StringBuilder passMsg = new StringBuilder();
	private StringBuilder noPassMsg = new StringBuilder();
	private StringBuilder forcePassMsg = new StringBuilder();
	private StringBuilder errMsg = new StringBuilder();
	private boolean isExeError = false;
	private int passSize = 0;
	private int noPassSize = 0;
	private int forceSize = 0;
	private DzResultCheckParam param = null;
	
	/**
	 * 是否是强制通过
	 */
	private boolean isOverTimePass = false;
	/**
	 * 检查通过的数据
	 */
	private List<BEN_RECORD> ben_RecordList = new ArrayList<BEN_RECORD>();
	/**
	 * 检查未通过的数据：日期_对账类型_组合
	 * @param param
	 */
	private List<BEN_RECORD> noResult = new ArrayList<BEN_RECORD>();
	public ElecAutoInfo(DzResultCheckParam param)
	{
		this.param = param;
	}
	public String getResultMsg() {
		this.resultMsg.setLength(0);
		if(dzState.equalsIgnoreCase(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE)){
			if(this.getForceSize() > 0){
				this.resultMsg.append("对账结果检查为：强制通过！");
			}else {
				this.resultMsg.append("对账结果检查为：通过！");
			}
		}else{
			this.resultMsg.append("对账结果检查为：不通过！").append(SLIPT_MARK);
		}
		
		if(this.isExeError())
		{
			resultMsg.append("错误信息：" + this.getErrMsg().toString());
		}else
		{
			resultMsg.append("当前任务检查次数为 ：").append("0".equalsIgnoreCase(param.getFrmCheckCount())?"多次":"单次").append("! <br> ");
			resultMsg.append("轮询中止条件:").append(param.getEndWhereName()).append(SLIPT_MARK);
			resultMsg.append("节点绿色通过条件:").append(param.getGreenWhereName()).append(SLIPT_MARK);
			resultMsg.append("检测到检查结果为通过的数据:").append(SLIPT_MARK).append(this.getPassMsg());
			resultMsg.append("检测到检查结果为不通过的数据:").append(SLIPT_MARK).append(this.getNoPassMsg());
			if("1".equalsIgnoreCase(param.getForcePass())){
				resultMsg.append("强制通过条件:").append(param.getGloabVarName()).append(param.getGloabVarOper()).append(param.getGloabVarValue()).append(SLIPT_MARK);
				resultMsg.append("检测到检查结果为强制通过的数据:").append(SLIPT_MARK).append(StringUtil.IsNullOrEmptyT(this.getForcePassMsg()) ? "无" + SLIPT_MARK : this.getForcePassMsg());
			}
			if(isOverTimePass()){
				resultMsg.append("检测到检查结果为超时通过的数据:").append(SLIPT_MARK).append(this.getOverTimePassMsg());
			}
		}
		return resultMsg.toString();
	}
	public String getPassMsg() {
		if(this.passMsg.length() == 0){
			this.passMsg.append(SPACE_TAB).append("无").append(SLIPT_MARK);
		}
		return passMsg.toString();
	}
	public String getNoPassMsg() {
		if(this.noPassMsg.length() == 0){
			this.noPassMsg.append(SPACE_TAB).append("无").append(SLIPT_MARK);
		}
		return noPassMsg.toString();
	}
	public String getForcePassMsg() {
		if(this.forcePassMsg.length() == 0){
			this.forcePassMsg.append(SPACE_TAB).append("无").append(SLIPT_MARK);
		}
		return forcePassMsg.toString();
	}
	
	/**
	 * 获取强制通过的组合信息
	 * @return
	 */
	public String getOverTimePassMsg() {
		StringBuffer sb = new StringBuffer();
		String msg = "";
		for (BEN_RECORD record : this.noResult) {
			msg = new StringBuffer().append(record.getC_Port_Code()).append("(")
					.append(FileTypeEnum.getValueByKey(record.getC_Item_Code())).append(")").toString();
			sb.append(SPACE_TAB).append(msg).append(SLIPT_MARK);
		}
		if(sb.length() == 0){
			sb.append(SPACE_TAB).append("无").append(SLIPT_MARK);
		}
		return sb.toString();
	}
	
	public String getErrMsg() {
		return errMsg.toString();
	}
	public int getPassSize() {
		return passSize;
	}
	public int getNoPassSize() {
		return noPassSize;
	}
	public int getForceSize() {
		return forceSize;
	}
	
	public void addErrMsg(String msg)
	{
		this.isExeError = true;
		this.errMsg.append(msg).append(SLIPT_MARK);
	}
	
	public void addPassMsg(String msg)
	{
		this.passSize++;
		this.passMsg.append(SPACE_TAB).append(msg).append(SLIPT_MARK);
	}
	
	public void addNoPassMsg(String msg)
	{
		this.noPassSize++;
		this.noPassMsg.append(SPACE_TAB).append(msg).append(SLIPT_MARK);
	}
	
	public void addforcePassMsg(String msg)
	{
		this.forceSize++;
		this.forcePassMsg.append(SPACE_TAB).append(msg).append(SLIPT_MARK);
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDzState() {
		return dzState;
	}
	public void setDzState(String dzState) {
		this.dzState = dzState;
	}
	public static String getSliptMark() {
		return SLIPT_MARK;
	}
	public static String getSpaceTab() {
		return SPACE_TAB;
	}
	public DzResultCheckParam getParam() {
		return param;
	}
	public List<BEN_RECORD> getBen_RecordList() {
		return ben_RecordList;
	}
	public List<BEN_RECORD> getNoResult() {
		return noResult;
	}
	public boolean isExeError() {
		return isExeError;
	}
	
	public boolean isOverTimePass() {
		return isOverTimePass;
	}
	public void setOverTimePass(boolean isOverTimePass) {
		this.isOverTimePass = isOverTimePass;
	}
	
}
