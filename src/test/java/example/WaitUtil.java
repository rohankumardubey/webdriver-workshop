package example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WaitUtil {

	public static void checkPendingRequests(final String context, final EventFiringWebDriver driver) {
		final int timeoutInNumberOfTries = 50;
		try {
			if (driver instanceof JavascriptExecutor) {
				final JavascriptExecutor jsDriver = driver;
				System.out.println("Number of active calls " + context + ": ");
				boolean timeout = true;
				for (int i = 0; i < timeoutInNumberOfTries; i++) {
					Thread.sleep(100);
					final Object numberOfAjaxConnections = jsDriver.executeScript("return window.openHTTPs");
					// return should be a number
					if (numberOfAjaxConnections instanceof Long) {
						final Long n = (Long) numberOfAjaxConnections;
						System.out.println(" " + n);
						if (n.longValue() == 0L) {
							timeout = false;
							break;
						}
					} else {
						// If it's not a number, the page might have been freshly loaded indicating the monkey
						// patch is replaced or we haven't yet done the patch.
						monkeyPatchXMLHttpRequest(driver);
					}
				}
				if (timeout) {
					throw new RuntimeException("Pending XHR requests even after 50 times checking (100 msec) for:" + context);
				}
			} else {
				System.out.println("Web driver: " + driver + " cannot execute javascript");
			}
		} catch (final InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void monkeyPatchXMLHttpRequest(final EventFiringWebDriver driver) {
		try {
			if (driver instanceof JavascriptExecutor) {
				final JavascriptExecutor jsDriver = driver;
				final Object numberOfAjaxConnections = jsDriver.executeScript("return window.openHTTPs");
				if (numberOfAjaxConnections instanceof Long) {
					return;
				}
				final String script = "  (function() {" + "var oldOpen = XMLHttpRequest.prototype.open;" + "window.openHTTPs = 0;" +
						"XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {" + "window.openHTTPs++;" +
						"this.addEventListener('readystatechange', function() {" + "if(this.readyState == 4) {" + "window.openHTTPs--;" + "}" +
						"}, false);" + "oldOpen.call(this, method, url, async, user, pass);" + "}" + "})();";
				jsDriver.executeScript(script);
			} else {
				System.out.println("Web driver: " + driver + " cannot execute javascript");
			}
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
