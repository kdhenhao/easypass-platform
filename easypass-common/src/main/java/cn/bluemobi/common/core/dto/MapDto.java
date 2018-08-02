package cn.bluemobi.common.core.dto;

import java.util.HashMap;
import java.util.Map;

import cn.bluemobi.common.core.utils.BlueMobiConstants;

/**
 * 客户端数据封装类 建议后台使用
 */
public class MapDto extends BaseDto {

    private static final long serialVersionUID = -2250993982058067962L;

    public static final MapDto ERROR = new MapDto(BlueMobiConstants.FAIL, "错误");

    private Map<String, Object> data = null;

    /**
     * 构造函数
     */
    public MapDto() {
    }

    public MapDto(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    /**
     * 初始化一个map, 并往map中添加数据
     */
    public void putInData(Map<String, Object> map) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        if (map != null) {
            for (String key : map.keySet()) {
                data.put(key, map.get(key));
            }
        }
    }

    /**
     * 初始化一个map, 并往map中添加数据
     */
    public void putInData2(Map<String, String> map) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        if (map != null) {
            for (String key : map.keySet()) {
                data.put(key, map.get(key));
            }
        }
    }

    /**
     * 初始化一个map, 并往map中添加数据
     */
    public void putInData(String key, Object o) {
        if (data == null)
            data = new HashMap<String, Object>();
        data.put(key, o);
    }
}
