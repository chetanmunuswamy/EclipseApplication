package org.eclipse.e4.ui.examples.css.rcp;

import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.css.swt.theme.IThemeManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * This workbench advisor creates the window advisor, and specifies the
 * perspective id for the initial window.
 */
@SuppressWarnings("restriction")
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	public static ApplicationWorkbenchAdvisor INSTANCE;

	public ApplicationWorkbenchAdvisor() {
		super();
		INSTANCE = this;
	}

	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	@Override
	public String getInitialWindowPerspectiveId() {
		return Perspective.ID;
	}

	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		Bundle b = FrameworkUtil.getBundle(getClass());
		BundleContext context = b.getBundleContext();
		ServiceReference serviceRef = context
				.getServiceReference(IThemeManager.class.getName());
		IThemeManager themeManager = (IThemeManager) context
				.getService(serviceRef);

		final IThemeEngine engine = themeManager.getEngineForDisplay(Display
				.getCurrent());
		engine.setTheme("org.eclipse.e4.ui.examples.css.rcp", true);
		if (serviceRef != null) {
			serviceRef = null;
		}
		if (themeManager != null) {
			themeManager = null;
		}
	}

}
