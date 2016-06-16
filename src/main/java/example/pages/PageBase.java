package example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class PageBase<T extends PageBase<T>> extends LoadableComponent<T> {
	protected WebDriver driver;

	public PageBase(final WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
