
package cn.bluemobi.platform.service;

import java.util.List;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.appCampus.AppCampus;
import cn.bluemobi.platform.vo.AppCampusVO;

/**
 * Description: <br/>
 * Date: 2018年6月18日<br/>
 * 
 * @author justin
 * @version
 * @see
 */
public interface AppCampusService {

    Page<AppCampusVO> findAppCampusListByPage(Integer start, Integer length, AppCampusVO appCampus);

    MapDto updateAppCampus(AppCampus appCampus);

    MapDto addAppCampus(AppCampus appCampus);

    MapDto deleteAppCampusById(Long id);
    
    List<AppCampus> getAllCampusList();

}
