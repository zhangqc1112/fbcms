package com.comp.view;


import com.alibaba.fastjson.JSONObject;
import com.comp.admin.biz.AccountModuleBo;
import com.comp.admin.entities.AccountModule;
import com.comp.admin.utils.ConstantUtil;
import com.comp.menu.MenuTree;
import com.comp.menu.MenuTreeNode;
import com.comp.menu.MenuTreeUtils;
import com.fbcms.util.ConstantConfigUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DashboardView extends FreeMarkerView {

    private final String include = "freemarker/includeRes.html";

    private String frameTemplatePath;

    public String getFrameTmplatePath() {
        return frameTemplatePath;
    }

    public void setFrameTemplatePath(String frameTemplatePath) {
        this.frameTemplatePath = frameTemplatePath;
    }

    protected Configuration config;

    @SuppressWarnings("deprecation")
    public DashboardView() {
        config = new Configuration();
        config.setTemplateLoader(getClassTemplateLoader());
    }

    protected Map<String, Object> buildParamsMap(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("resources", loadResources());
        params.put("navFlag", false);
        params.put("logoutHost", "/login/loginOut");
        params.put("chgPwdHost","/changePwd");
        params.put("loginHost", "/login");
        params.put(ConstantUtil.SESS_MODULE, request.getSession().getAttribute(ConstantUtil.SESS_MODULE));
        JSONObject json = (JSONObject)request.getSession().getAttribute(ConstantUtil.SESS_MENU);
        params.put(ConstantUtil.SESS_MENU, json);
        params.put(ConstantUtil.LOGIN_NAME, request.getSession().getAttribute(ConstantUtil.LOGIN_NAME));
        if(json != null){
            MenuTree tree = MenuTreeUtils.buildTree(json.toString());
            //logger.info(" tree : " + json);
            MenuTreeNode currentMenuNode = MenuTreeUtils.getCurrTree(tree,request.getRequestURI());
            if (currentMenuNode != null) {
                params.put("curMenu", currentMenuNode.getMenu());
                List<MenuTreeNode> nav = new ArrayList<>();
                MenuTreeNode node = currentMenuNode;
                while (node.getParent() != null) {
                    nav.add(0, node);
                    node = node.getParent();
                }
                params.put("nav", nav);
            }
        }


        if (PageTag.getPageName() != null) {
            params.put("pageName", PageTag.getPageName());
        }

        return params;
    }

    protected ClassTemplateLoader getClassTemplateLoader() {
        return new ClassTemplateLoader(DashboardView.class, "/");
    }

    protected String loadResources() {
        StringWriter out = new StringWriter();
        try {
            Template template = config.getTemplate(include, "UTF-8");
            Map<String, Object> rootMap = new HashMap<>();
            rootMap.put("staticHost", ConstantConfigUtil.staticHost);
            template.process(rootMap, out);
            return out.toString();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void processTemplate(Template template, SimpleHash model, HttpServletResponse response)
            throws IOException, TemplateException {
        PageTag.clear();
        StringWriter out = new StringWriter();
        template.process(model, out);
        String finalText = out.toString();
        String frameTmplatePath = this.getFrameTmplatePath();
        if(frameTmplatePath != null) {
            Template dashTmpl = this.config.getTemplate(frameTmplatePath, template.getLocale(), "UTF-8");
            if(dashTmpl != null) {
                HttpRequestHashModel templateModel = (HttpRequestHashModel)model.get("Request");
                HttpServletRequest request = templateModel.getRequest();
                StringWriter dashOut = new StringWriter();

                try {
                    Map e = this.buildParamsMap(request);
                    //e.put("_ctx", model);
                    dashTmpl.process(e, dashOut);
                } catch (IOException | TemplateException var12) {
                    throw new RuntimeException(var12);
                }
                finalText = StringUtils.replaceOnce(dashOut.toString(), "<!-- main content -->", finalText);
            }
        }

        response.getWriter().write(finalText);
        response.getWriter().flush();
        PageTag.clear();
    }


    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void exposeHelpers(Map model, HttpServletRequest request) throws Exception {
        request.setAttribute("uri", request.getRequestURI());
        super.exposeHelpers(model, request);
    }

}