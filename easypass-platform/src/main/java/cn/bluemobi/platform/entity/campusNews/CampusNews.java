
package cn.bluemobi.platform.entity.campusNews;

/**
 * Description: news<br/>
 * Date: 2018年6月20日 <br/>
 * 
 * @author justin
 * @version
 * @see
 */
public class CampusNews{

    private Integer id;

    private Integer campusId;
    
    private String campusName;

    private String campusUrl;

    private String createTime;

    private String modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public String getCampusUrl() {
        return campusUrl;
    }

    public void setCampusUrl(String campusUrl) {
        this.campusUrl = campusUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }
}
