
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.model.BaseModel;
import jakarta.servlet.annotation.WebServlet;

/**
 * WelcomeCtl handles requests for the welcome page. It provides the welcome
 * view of the application.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/WelcomeCtl")
public class WelcomeCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(WelcomeCtl.class);

	/**
	 * Returns the BaseModel object.
	 *
	 * @return null because no model is required for the welcome page
	 */
	@Override
	public BaseModel getModel() {

		log.debug("WelcomeCtl getModel() started");

		log.debug("No model required for welcome page");

		log.debug("WelcomeCtl getModel() completed");

		return null;
	}

	/**
	 * Returns the welcome page view.
	 *
	 * @return welcome view path
	 */
	@Override
	public String getView() {

		log.debug("WelcomeCtl getView() started");

		log.debug("Returning welcome page view: " + ORSView.WELCOME_VIEW);

		log.debug("WelcomeCtl getView() completed");

		return ORSView.WELCOME_VIEW;
	}

}
