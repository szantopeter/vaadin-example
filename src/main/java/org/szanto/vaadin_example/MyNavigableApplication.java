package org.szanto.vaadin_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.szanto.vaadin_example.dao.ParentDAO;
import org.szanto.vaadin_example.ui.ParentForm;
import org.szanto.vaadin_example.ui.StockPriceForm;
import org.vaadin.navigator.Navigator;
import org.vaadin.navigator.Navigator.NavigableApplication;

import com.vaadin.Application;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Configurable
public class MyNavigableApplication extends Application implements NavigableApplication {

	private static final long serialVersionUID = 3010251801316632777L;

	@Override
	public void init() {
		setMainWindow(createNewWindow());
	}

	@Override
	public Window getWindow(String name) {
		// Use navigator to manage multiple browser windows
		return Navigator.getWindow(this, name, super.getWindow(name));
	}

	public Window createNewWindow() {
		final Navigator navigator = new Navigator();
		MenuBar menu = new MenuBar();
		VerticalLayout layout = new VerticalLayout();
		Window w = new Window("Navigator example", layout);

		w.addComponent(menu);
		w.addComponent(navigator);
		layout.setMargin(false);
		layout.setSpacing(true);
		layout.setSizeFull();
		layout.setExpandRatio(navigator, 1.0f);
		menu.setWidth("100%");

		// Wire up the navigation
		for (final Class<?> viewClass : new Class[] { ParentForm.class, StockPriceForm.class }) {
			navigator.addView(viewClass.getSimpleName(), viewClass);
			menu.addItem(viewClass.getSimpleName(), new MenuBar.Command() {

				private static final long serialVersionUID = -5849355604079918088L;

				public void menuSelected(MenuItem selectedItem) {
					navigator.navigateTo(viewClass);
				}
			});
		}

		return w;
	}

}
