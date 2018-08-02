/**
 * Project Name:demo
 * File Name:SystemRoleController.java
 * Package Name:cn.bluemobi.demo.controller.backend.system
 * Date:2015年12月9日下午3:33:35
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.system.Auth;
import cn.bluemobi.platform.entity.system.TreeNode;
import cn.bluemobi.platform.service.RoleService;

/**
 * Description: <br/>
 * Date: 2015年12月9日 下午3:33:35 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@RequestMapping("/admin/systemRole")
@Controller
public class SystemRoleController extends PlatformBaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/toRolePage")
    public String toRolePage(Model model) {
        model.addAttribute("authList", roleService.getAuthList());
        return "system/roleList";
    }

    @RequestMapping("/findRoles")
    @ResponseBody
    public DatatablePage findRoles(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length) {
        return new DatatablePage(draw, roleService.findAllRoles(start, length));
    }

    @RequestMapping("/saveRole")
    @ResponseBody
    public MapDto saveRole(@RequestParam("roleId") String id, @RequestParam("roleName") String roleName,
            @RequestParam("roleCode") String roleCode) {
        return roleService.saveOrUpdataRole(id, roleName, roleCode, getUser());
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public MapDto deleteRole(@RequestParam("id") String id) {
        return roleService.deleteRole(id, getUser());
    }

    @RequestMapping("/findAuthTree")
    @ResponseBody
    public List<TreeNode> findAuthTree() {
        List<Auth> auths = roleService.getAuthList();
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for (Auth auth : auths) {
            TreeNode node = copyFormAuth(auth);
            node.setNodes(new ArrayList<TreeNode>());
            List<Auth> children = auth.getChildren();
            for (Auth child : children) {
                node.getNodes().add(copyFormAuth(child));
            }
            nodes.add(node);
        }
        return nodes;
    }

    private TreeNode copyFormAuth(Auth auth) {
        TreeNode node = new TreeNode();
        node.setId(auth.getId());
        node.setText(auth.getAuthName());
        return node;
    }

    @RequestMapping("/findRoleAuth")
    @ResponseBody
    public MapDto findRoleAuth(@RequestParam("roleId") String roleId) {
        return roleService.findRoleAuth(roleId);
    }

    @RequestMapping("/saveAuth")
    @ResponseBody
    public MapDto saveAuth(@RequestParam("id") String id, @RequestParam("array") String array) {
        return roleService.saveAndFlush(id, array, getUser());
    }
}
