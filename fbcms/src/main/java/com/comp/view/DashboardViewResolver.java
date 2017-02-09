package com.comp.view;

import freemarker.template.Configuration;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

public class DashboardViewResolver extends AbstractTemplateViewResolver {

	private String frameTemplatePath;

	@Override
    protected void initApplicationContext(ApplicationContext context) {
        this.setViewClass(DashboardView.class);
        super.initApplicationContext(context);
        FreeMarkerConfig config = (FreeMarkerConfig) BeanFactoryUtils.beanOfTypeIncludingAncestors(context, FreeMarkerConfig.class, true, false);
        Configuration cfg = config.getConfiguration();
        if(cfg.getSharedVariable("page") == null) {
            cfg.setSharedVariable("page", new PageTag());
        }



    }
	/**
	 * if viewName start with / , then ignore prefix.
	 */
	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        DashboardView view = (DashboardView) super.buildView(viewName);
		// start with / ignore prefix
		if (viewName.startsWith("/")) {
			view.setUrl(viewName + getSuffix());
		}
		view.setFrameTemplatePath(frameTemplatePath);
		return view;
	}

	public void setFrameTemplatePath(String frameTemplatePath) {
		this.frameTemplatePath = frameTemplatePath;
	}

}