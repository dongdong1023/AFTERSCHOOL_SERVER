package com.wx.base.controller.admin.system;

import imba.game.base.common.JsonResult;
import imba.game.base.config.annotation.Log;
import imba.game.base.controller.BaseController;
import imba.game.base.entity.admin.Resource;
import imba.game.base.entity.system.SystemLog;
import imba.game.base.service.IResourceService;
import imba.game.base.vo.ZtreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/resource")
public class ResourceController extends BaseController {
    @Autowired
    private IResourceService resourceService;

    @RequestMapping("/tree/{resourceId}")
    @ResponseBody
    public List<ZtreeView> tree(@PathVariable Integer resourceId) {
        List<ZtreeView> list = resourceService.tree(resourceId);
        return list;
    }

    @Log(value = "查询资源列表子级", action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/children/{parentId}",method = RequestMethod.GET)
    public String children(ModelMap map,@PathVariable Integer parentId){
            List<Resource> resources = resourceService.children(parentId);
            map.put("pageInfo", resources);
            map.put("parentId",parentId);
            return "admin/resource/index";
    }

//    @Log(value = "资源管理页", action = SystemLog.LOG_ACTION_SELECT)
//    @RequestMapping("/index")
//    public String index(ModelMap map) {
//        Page<Resource> page = resourceService.findAll(getPageRequest());
//        map.put("pageInfo", page);
//        return "admin/resource/index";
//    }

    @Log(value = "添加资源", action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        List<Resource> list = resourceService.findAll();
        map.put("list", list);
        return "admin/resource/form";
    }


    @Log(value = "修改资源", action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Resource resource = resourceService.find(id);
        map.put("resource", resource);

        List<Resource> list = resourceService.findAll();
        map.put("list", list);
        return "admin/resource/form";
    }

    @Log(value = "修改资源", action = SystemLog.LOG_ACTION_UPDATE)
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Resource resource, Integer parentId, ModelMap map) {
        try {
            if (parentId != null) {
                Resource parent = new Resource();
                parent.setId(parentId);
                resource.setParent(parent);
            }
            resourceService.saveOrUpdate(resource);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @Log(value = "删除资源", action = SystemLog.LOG_ACTION_DELETE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id, ModelMap map) {
        try {
            resourceService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
