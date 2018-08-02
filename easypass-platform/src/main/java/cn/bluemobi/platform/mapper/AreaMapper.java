package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

public interface AreaMapper {

    List<Map<String, Object>> findProvinces();

    List<Map<String, Object>> findByParent(String string);

    List<Map<String, Object>> findHotPage();

    int updateHot(Long long1, String cityId);

    int deleteHot(String cityId);

    int isHotAlready(String cityId);
}