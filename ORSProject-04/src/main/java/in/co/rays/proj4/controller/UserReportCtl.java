
package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a User list report in PDF or DOC format. Mapped
 * to {@code /ctl/UserReportCtl}; add {@code ?type=doc} for Word output.
 *
 * @author Rays EdTech
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/UserReportCtl")
public class UserReportCtl extends BaseReportCtl<UserBean> {

	private static Logger log = Logger.getLogger(UserReportCtl.class);

	/**
	 * Fetches all users from the database.
	 *
	 * @return list of all {@link UserBean} records
	 */
	public List<UserBean> getList() {

		log.debug("UserReportCtl getList() started");

		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		log.debug("Fetching all users from database");

		List<UserBean> users = model.search(bean, 1, 0);

		log.debug("Total users fetched: " + (users != null ? users.size() : 0));

		log.debug("UserReportCtl getList() completed");

		return users;
	}

	/**
	 * Returns the JRXML template path for the user list report.
	 *
	 * @return {@link ORSView#USER_REPORT_VIEW}
	 */
	public String getView() {

		log.debug("UserReportCtl getView() called");

		return ORSView.USER_REPORT_VIEW;
	}

	/**
	 * Returns the ServletContext cache key for the compiled user report.
	 *
	 * @return {@code "USER_LIST_COMPILED_REPORT"}
	 */
	public String getCompiledReportKey() {

		log.debug("UserReportCtl getCompiledReportKey() called");

		return "USER_LIST_COMPILED_REPORT";
	}

}
