package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.system.User;

public interface UserMapper {

    User loginByAccountAndPsd(String account, String passwordMd5);

    List<User> searchUsers(Map<String, Object> map);

    List<Map<String, Object>> findAllRoles();

    int canInsertUser(String username);

    int insertUser(User user);

    int canUpdateUser(String username, Long id);

    int updateUser(User user);

    int deleteUserById(String id);

    int insertUserAuth(Long id, String authId);

    int deleteUserAuth(Long id);

    List<String> findRoleAuth(Long roleId);

    int updatePwd(String p2, Long id, String p1);

    User findById(String id);

    List<String> findMyRolesByUserName(String username);

    List<String> findMyAuthsByUserName(String username);

    int updatePwdById(String encode, String id);

}