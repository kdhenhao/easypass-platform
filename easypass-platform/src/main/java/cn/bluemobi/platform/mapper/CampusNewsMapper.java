
package cn.bluemobi.platform.mapper;

import java.util.List;

import cn.bluemobi.platform.entity.campusNews.CampusNews;

/**
 * Description: 校园新闻<br/>
 * Date: 2018年6月21日 <br/>
 * 
 * @author justin
 * @version
 * @see
 */
public interface CampusNewsMapper {

	List<CampusNews> findNews();

    int addNews(CampusNews campusNews);

    int updateNews(CampusNews campusNews);

}
