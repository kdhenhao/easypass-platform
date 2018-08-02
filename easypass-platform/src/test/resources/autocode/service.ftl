package ${package}.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;

public interface ${interface} {
  
	Page<Map<String,Object>> findForPage(PageCondition cond);
}