package com.yss.webservice.util;

import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * @ClassName: PojoUtil
 * @author: zhouxudong
 * @date: 2019年1月30日 下午3:17:45
 * @version 1.0
 */
public class PojoUtil {
    /**
     * @Title: reset 
     * @Desc: 重新设置<AuditableParamPojo>类型对象的操作人员以及操作时间
     * @param pojo 业务对象
     * @param operator 默认操作人员
     */
    public static <K extends BasePojo>  void resetOperatorAndDate (K pojo, String operator){
        if (pojo instanceof AuditableParamPojo) {
            AuditableParamPojo paramPojo = (AuditableParamPojo) pojo;
            String datestr = DateUtil.getNow(DateUtil.FORMAT_DATETIME);
            //如果对象中的操作人员为空则，将修改人员和操作人员字段都赋值为<默认操作人员>
            //否则直接将修改人员赋值为操作人员
            if (StringUtil.IsNullOrEmpty(paramPojo.getOperator())) {
                paramPojo.setModifier(operator);
                paramPojo.setOperator(operator);
            } else {
                paramPojo.setModifier(paramPojo.getOperator());
            }
            
            paramPojo.setModifyDate(datestr);
        }
    }
}
