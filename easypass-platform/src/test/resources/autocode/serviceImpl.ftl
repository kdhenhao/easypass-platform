package ${package}.service.impl;  

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import ${package}.core.mybatis.PageUtils;
import ${package}.service.${interface};
import ${package}.mapper.${MapperInterface};

@Service
@Transactional
public class ${class} implements ${interface}{

	@Autowired
    private ${MapperInterface} ${MapperInterface2};
  

	@Override
    public Page<Map<String, Object>> findForPage(PageCondition cond) {
        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        page.setList(${MapperInterface2}.selectForPage(cond.getMap()));
        return page;
    }
}