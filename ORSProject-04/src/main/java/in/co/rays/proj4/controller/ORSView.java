package in.co.rays.proj4.controller;

/**
 * ORSView interface contains constants for application context,
 * JSP view paths, controller URLs, and report file paths.
 * These constants are used for navigation between different
 * views and controllers in the ORS application.
 *
 * @author karunaChouhan
 */
public interface ORSView {

	/** Application context path. */
	public String APP_CONTEXT = "/ORSProject-04";

	/** Folder containing JSP pages. */
	public String PAGE_FOLDER = "/jsp";

	/** Path of the JavaDoc documentation page. */
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	/** Path of the error view page. */
	public String ERROR_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	/** Path of the marksheet view page. */
	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	/** Path of the marksheet list view page. */
	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	
	/** Path of the online shopping view page. */
	public String ONLINE_SHOPPING_VIEW = PAGE_FOLDER + "/OnlineShoppingView.jsp";

	/** Path of the online shopping view page. */
	public String ONLINE_SHOPPING_LIST_VIEW = PAGE_FOLDER + "/OnlineShoppingListView.jsp";

	/** Path of the get marksheet view page. */
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";

	/** Path of the user view page. */
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";

	/** Path of the user list view page. */
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";

	/** Path of the college view page. */
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";

	/** Path of the college list view page. */
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";

	/** Path of the student view page. */
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";

	/** Path of the student list view page. */
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";

	/** Path of the role view page. */
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";

	/** Path of the role list view page. */
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";

	/** Path of the course view page. */
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";

	/** Path of the course list view page. */
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";

	/** Path of the subject view page. */
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";

	/** Path of the subject list view page. */
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";

	/** Path of the user registration view page. */
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";

	/** Path of the login view page. */
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";

	/** Path of the welcome view page. */
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";

	/** Path of the change password view page. */
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";

	/** Path of the my profile view page. */
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";

	/** Path of the forget password view page. */
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	/** Path of the marksheet merit list view page. */
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";

	/** Path of the product view page. */
	public String PRODUCT_VIEW = PAGE_FOLDER + "/ProductView.jsp";

	/** Path of the product list view page. */
	public String PRODUCT_LIST_VIEW = PAGE_FOLDER + "/ProductListView.jsp";

	/** Path of the hospital view page. */
	public String HOSPITAL_VIEW = PAGE_FOLDER + "/HospitalView.jsp";

	/** Path of the hospital list view page. */
	public String HOSPITAL_LIST_VIEW = PAGE_FOLDER + "/HospitalListView.jsp";

	/** Path of the gym view page. */
	public String GYM_VIEW = PAGE_FOLDER + "/GymView.jsp";

	/** Path of the gym list view page. */
	public String GYM_LIST_VIEW = PAGE_FOLDER + "/GymListView.jsp";

	/** Path of the ATM view page. */
	public String ATM_VIEW = PAGE_FOLDER + "/ATMView.jsp";

	/** Path of the ATM list view page. */
	public String ATM_LIST_VIEW = PAGE_FOLDER + "/ATMListView.jsp";

	/** Path of the railway view page. */
	public String RAILWAY_VIEW = PAGE_FOLDER + "/RailwayView.jsp";

	/** Path of the railway list view page. */
	public String RAILWAY_LIST_VIEW = PAGE_FOLDER + "/RailwayListView.jsp";

	/** URL of the error controller. */
	public String ERROR_CTL = "/ctl/MarksheetCtl";

	/** URL of the marksheet controller. */
	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";

	/** URL of the marksheet list controller. */
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";
	
	/** URL of the online shopping controller. */
	public String ONLINE_SHOPPING_CTL = APP_CONTEXT + "/ctl/OnlineShoppingCtl";

	/** URL of the online shopping list controller. */
	public String ONLINE_SHOPPING_LIST_CTL = APP_CONTEXT + "/ctl/OnlineShoppingListCtl";

	/** URL of the user controller. */
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";

	/** URL of the user list controller. */
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";

	/** URL of the college controller. */
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";

	/** URL of the college list controller. */
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";

	/** URL of the student controller. */
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";

	/** URL of the student list controller. */
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";

	/** URL of the role controller. */
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";

	/** URL of the role list controller. */
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";

	/** URL of the course controller. */
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";

	/** URL of the course list controller. */
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";

	/** URL of the subject controller. */
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";

	/** URL of the subject list controller. */
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";

	/** URL of the user registration controller. */
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";

	/** URL of the login controller. */
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";

	/** URL of the welcome controller. */
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";

	/** URL of the logout controller. */
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";

	/** URL of the get marksheet controller. */
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";

	/** URL of the change password controller. */
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";

	/** URL of the my profile controller. */
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

	/** URL of the forget password controller. */
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";

	/** URL of the marksheet merit list controller. */
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	/** URL of the upload photo controller. */
	public String UPLOAD_PHOTO_CTL = APP_CONTEXT + "/ctl/UploadPhotoCtl";

	/** Path of the faculty view page. */
	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";

	/** Path of the faculty list view page. */
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";

	/** URL of the faculty controller. */
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";

	/** URL of the faculty list controller. */
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	/** URL of the hospital controller. */
	public String HOSPITAL_CTL = APP_CONTEXT + "/ctl/HospitalCtl";

	/** URL of the hospital list controller. */
	public String HOSPITAL_LIST_CTL = APP_CONTEXT + "/ctl/HospitalListCtl";

	/** URL of the gym controller. */
	public String GYM_CTL = APP_CONTEXT + "/ctl/GymCtl";

	/** URL of the gym list controller. */
	public String GYM_LIST_CTL = APP_CONTEXT + "/ctl/GymListCtl";

	/** URL of the ATM controller. */
	public String ATM_CTL = APP_CONTEXT + "/ctl/ATMCtl";

	/** URL of the ATM list controller. */
	public String ATM_LIST_CTL = APP_CONTEXT + "/ctl/ATMListCtl";

	/** URL of the railway controller. */
	public String RAILWAY_CTL = APP_CONTEXT + "/ctl/RailwayCtl";

	/** URL of the railway list controller. */
	public String RAILWAY_LIST_CTL = APP_CONTEXT + "/ctl/RailwayListCtl";

	/** URL of the student report controller. */
	public String STUDENT_REPORT_CTL = APP_CONTEXT + "/ctl/StudentReportCtl";

	/** URL of the college report controller. */
	public String COLLEGE_REPORT_CTL = APP_CONTEXT + "/ctl/CollegeReportCtl";

	/** URL of the user report controller. */
	public String USER_REPORT_CTL = APP_CONTEXT + "/ctl/UserReportCtl";

	/** URL of the role report controller. */
	public String ROLE_REPORT_CTL = APP_CONTEXT + "/ctl/RoleReportCtl";

	/** URL of the course report controller. */
	public String COURSE_REPORT_CTL = APP_CONTEXT + "/ctl/CourseReportCtl";

	/** URL of the subject report controller. */
	public String SUBJECT_REPORT_CTL = APP_CONTEXT + "/ctl/SubjectReportCtl";

	/** URL of the marksheet report controller. */
	public String MARKSHEET_REPORT_CTL = APP_CONTEXT + "/ctl/MarksheetReportCtl";

	/** URL of the faculty report controller. */
	public String FACULTY_REPORT_CTL = APP_CONTEXT + "/ctl/FacultyReportCtl";

	/** URL of the product controller. */
	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/ProductCtl";

	/** URL of the product list controller. */
	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/ProductListCtl";

	/** Path of the course report JRXML file. */
	public String COURSE_REPORT_VIEW = "/reports/CourseListReport.jrxml";

	/** Path of the student report JRXML file. */
	public String STUDENT_REPORT_VIEW = "/reports/StudentListReport.jrxml";

	/** Path of the college report JRXML file. */
	public String COLLEGE_REPORT_VIEW = "/reports/CollegeListReport.jrxml";

	/** Path of the user report JRXML file. */
	public String USER_REPORT_VIEW = "/reports/UserListReport.jrxml";

	/** Path of the role report JRXML file. */
	public String ROLE_REPORT_VIEW = "/reports/RoleListReport.jrxml";

	/** Path of the subject report JRXML file. */
	public String SUBJECT_REPORT_VIEW = "/reports/SubjectListReport.jrxml";

	/** Path of the marksheet report JRXML file. */
	public String MARKSHEET_REPORT_VIEW = "/reports/MarksheetListReport.jrxml";

	/** Path of the faculty report JRXML file. */
	public String FACULTY_REPORT_VIEW = "/reports/FacultyListReport.jrxml";
	
	

}