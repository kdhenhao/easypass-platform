
package cn.bluemobi.platform.service;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.entity.campusNews.CampusNews;

public interface CampusNewsService {

    MapDto addNews(CampusNews campusNews);

    MapDto updateNews(CampusNews campusNews);

    Page<CampusNews> findNews(PageCondition cond);

}
