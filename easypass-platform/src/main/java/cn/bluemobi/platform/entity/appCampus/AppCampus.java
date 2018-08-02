/**
 * Project Name:easypass-platform
 * File Name:AppCampus.java
 * Package Name:cn.bluemobi.platform.entity.appCampus
 * Date:2018年6月8日
 *
*/

package cn.bluemobi.platform.entity.appCampus;

/**
 * Description: appCampus实体 <br/>
 * 
 * @author justin
 * @version
 * @see
 */
public class AppCampus {

    private Integer id;

    private String name;

    private String logoImg;

    private String backgroundImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }
}