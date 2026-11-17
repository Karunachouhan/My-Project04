package in.co.rays.proj4.controller;

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

	/**
	 * Returns the BaseModel object.
	 *
	 * @return null because no model is required for the welcome page
	 */
	@Override
	public BaseModel getModel() {
		return null;
	}

	/**
	 * Returns the welcome page view.
	 *
	 * @return welcome view path
	 */
	@Override
	public String getView() {
		return ORSView.WELCOME_VIEW;
	}

}