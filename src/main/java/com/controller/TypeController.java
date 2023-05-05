package com.controller;

import com.model.Type;
import com.srevice.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/type_list")
    public String toTypeList(Model model){
        model.addAttribute("list",typeService.typeList());
        return "/admin/type_list";
    }

    @RequestMapping("/type_add")
    public String typeAdd(@RequestParam("name") String name, Model model){
        if(typeService.typeAdd(name) > 0)
        {
            model.addAttribute("msg", "添加成功");
        }else {
            model.addAttribute("failMsg","添加失败");
        }
        return "forward:/admin/type_list";
    }

    @RequestMapping("/type_delete")
    public String typeDelete(@RequestParam("id") int id,Model model){
        if(typeService.typeDelete(id))
        {
            model.addAttribute("msg", "删除成功");
        }else {
            model.addAttribute("failMsg","类目下包含商品，无法直接删除类目！");
        }
        return "forward:/admin/type_list";
    }

    @RequestMapping("type_edit")
    public String typeEdit(Type type,Model model){
        if(typeService.typeEdit(type) > 0){
            model.addAttribute("msg", "修改成功");
        }else {
            model.addAttribute("failMsg","类目重复，修改失败！");
            return "/admin/type_edit";
        }
        return "forward:/admin/type_list";
    }
}
