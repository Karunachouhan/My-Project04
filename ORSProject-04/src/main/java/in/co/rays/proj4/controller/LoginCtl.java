package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * LoginCtl handles user login and logout operations.
 * It validates user credentials, authenticates users, stores user and role
 * information in the session, and manages navigation to the appropriate views.
 *
 * @author karunaChouhan
 */
@WebServlet("/LoginCtl")
public class LoginCtl extends BaseCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(LoginCtl.class);

	/**
	 * Operation constant used to identify the sign-in action.
	 */
	public final static String OP_SIGN_IN = "SignIn";

	/**
	 * Validates the login credentials provided by the user.
	 * Checks whether login ID and password are entered.
	 *
	 * @param request HttpServletRequest containing login details
	 * @return true if all required fields are provided, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			log.warn("Login ID is missing");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			log.warn("Password is missing");
			pass = false;
		}

		log.debug("LoginCtl validate method completed");

		return pass;

	}

	/**
	 * Populates the UserBean object with login credentials
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing login details
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("LoginCtl populateBean method started");

		UserBean bean = new UserBean();
        bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		populateDTO(bean, request);

		log.debug("LoginCtl populateBean method completed");

		return bean;
	}

	/**
	 * Handles GET requests for the login controller.
	 * If an operation parameter is present, it logs out the user
	 * by invalidating the current session.
	 *
	 * @param request HttpServletRequest containing the request data
	 * @param response HttpServletResponse used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("LoginCtl doGet method started");

		String op = request.getParameter("operation");

		if (op != null) {
			HttpSession session = request.getSession();
			ServletUtility.setSuccessMessage("user logout successfully", request);
			session.invalidate();
			log.info("User logged out successfully");
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl doGet method completed");

	}

	/**
	 * Handles POST requests for user login.
	 * Authenticates the user, stores user and role information
	 * in the session, and redirects to the welcome page on success.
	 *
	 * @param request HttpServletRequest containing login credentials
	 * @param response HttpServletResponse used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("LoginCtl doPost method started");

		String op = DataUtility.getString(request.getParameter("operation"));
		HttpSession session = request.getSession();

		UserBean bean = populateBean(request);
		UserModel model = getModel();
		RoleModel rmodel = new RoleModel();

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			log.info("User login authentication started");

			bean = model.authenticate(bean.getLogin(), bean.getPassword());

			if (bean != null) {

				log.info("User authenticated successfully");

				session.setAttribute("user", bean);
				RoleBean rbean = rmodel.findByPk(bean.getRoleId());
				session.setAttribute("role", rbean.getName());

				log.info("User and role information stored in session");

				ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);

				log.debug("LoginCtl doPost method completed");
				return;

			} else {
				log.warn("User authentication failed");
				ServletUtility.setErrorMessage("Invalid login or password", request);
			}
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl doPost method completed");

	}

	/**
	 * Creates and returns the UserModel object.
	 *
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {

		log.debug("LoginCtl getModel method called");

		return new UserModel();
	}

	/**
	 * Returns the login view page.
	 *
	 * @return login view path
	 */
	@Override
	public String getView() {

		log.debug("LoginCtl getView method called");

		return ORSView.LOGIN_VIEW;
	}

}