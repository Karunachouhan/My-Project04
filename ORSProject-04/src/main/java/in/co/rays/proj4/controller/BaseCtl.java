package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Base Controller class that provides common functionality for all
 * controllers in the ORS project.
 *
 * This class follows the MVC architecture and acts as a common parent
 * controller for handling HTTP requests, validating input data,
 * populating beans, managing database operations and forwarding requests
 * to the appropriate JSP views.
 *
 * It uses Java Generics to work with different Bean and Model classes.
 *
 * @param <B> Bean class that extends BaseBean
 * @param <M> Model class that extends BaseModel
 * @author karunaChouhan
 */
public abstract class BaseCtl<B extends BaseBean, M extends BaseModel> extends HttpServlet {

    private static Logger log = Logger.getLogger(BaseCtl.class);

    /** Operation constant for saving a record. */
    public static final String OP_SAVE = "Save";

    /** Operation constant for cancelling an operation. */
    public static final String OP_CANCEL = "Cancel";

    /** Operation constant for deleting a record. */
    public static final String OP_DELETE = "Delete";

    /** Operation constant for displaying the list of records. */
    public static final String OP_LIST = "List";

    /** Operation constant for searching records. */
    public static final String OP_SEARCH = "Search";

    /** Operation constant for viewing a particular record. */
    public static final String OP_VIEW = "View";

    /** Operation constant for navigating to the next page. */
    public static final String OP_NEXT = "Next";

    /** Operation constant for navigating to the previous page. */
    public static final String OP_PREVIOUS = "Previous";

    /** Operation constant for creating a new record. */
    public static final String OP_NEW = "New";

    /** Operation constant for navigating to a particular page. */
    public static final String OP_GO = "Go";

    /** Operation constant for going back to the previous page. */
    public static final String OP_BACK = "Back";

    /** Operation constant for logging out the user. */
    public static final String OP_LOG_OUT = "Logout";

    /** Request attribute key used to indicate whether validation errors exist. */
    public static final String HAS_ERROR = "haserror";

    /** Request attribute key used to store messages. */
    public static final String MESSAGE = "message";

    /** Message type indicating successful execution. */
    public static final String MSG_SUCCESS = "success";

    /** Message type indicating an error. */
    public static final String MSG_ERROR = "error";

    /**
     * Validates the input data submitted by the user.
     *
     * Child controllers can override this method to implement
     * their own validation logic.
     *
     * @param request HttpServletRequest containing user input data
     * @return true if the input data is valid, otherwise false
     */
    protected boolean validate(HttpServletRequest request) {
        log.debug("BaseCtl validate method started");
        log.debug("BaseCtl validate method completed");
        return true;
    }

    /**
     * Loads predefined data required by the view.
     *
     * Child controllers can override this method to load data
     * such as dropdown lists before displaying the JSP page.
     *
     * @param request HttpServletRequest object containing request information
     */
    protected void preload(HttpServletRequest request) {
        log.debug("BaseCtl preload method started");
        log.debug("BaseCtl preload method completed");
    }

    /**
     * Retrieves data submitted through the view and populates
     * the corresponding Bean object.
     *
     * Child controllers override this method to get form parameters
     * and set them into their respective Bean properties.
     *
     * @param request HttpServletRequest containing form data
     * @return populated Bean object
     */
    protected B populateBean(HttpServletRequest request) {
        log.debug("BaseCtl populateBean method called");
        log.warn("populateBean method is not implemented in BaseCtl");
        return null;
    }

    /**
     * Populates common audit information into the Bean object.
     *
     * It sets createdBy, modifiedBy, createdDatetime and modifiedDatetime.
     * If the user is not logged in, root is considered the default user.
     * If the record is being created for the first time, createdBy
     * is set to the login ID of the currently logged-in user.
     *
     * @param dto BaseBean object in which audit information is populated
     * @param request HttpServletRequest containing session and request data
     * @return updated BaseBean object
     */
    protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {

        log.debug("BaseCtl populateDTO method started");

        String createdBy = request.getParameter("createdBy");
        String modifiedBy = null;
        UserBean userbean = (UserBean) request.getSession().getAttribute("user");

        if (userbean == null) {
            log.warn("No logged-in user found, using root as default user");
            createdBy = "root";
            modifiedBy = "root";
        } else {
            modifiedBy = userbean.getLogin();
            log.info("Audit information populated for logged-in user");

            // If record is created first time
            if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
                createdBy = modifiedBy;
                log.debug("CreatedBy set from logged-in user");
            }
        }

        dto.setCreatedBy(createdBy);
        dto.setModifiedBy(modifiedBy);

        long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

        if (cdt > 0) {
            dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
            log.debug("Existing created datetime assigned");
        } else {
            dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
            log.debug("Current created datetime assigned");
        }

        dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());

        log.info("Audit information populated successfully");
        log.debug("BaseCtl populateDTO method completed");

        return dto;
    }

    /**
     * Handles HTTP GET requests.
     *
     * It retrieves the record ID from the request. If the ID is greater
     * than zero, it fetches the corresponding record from the database
     * using the Model class and stores the Bean in the request.
     * Finally, it forwards the request to the appropriate JSP view.
     *
     * @param request HttpServletRequest object containing request data
     * @param response HttpServletResponse object used to send the response
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.debug("BaseCtl doGet method started");

        long id = DataUtility.getLong(request.getParameter("id"));
        M model = getModel();

        if (id > 0) {
            log.info("Fetching record for ID: " + id);
            BaseBean bean = model.findByPk(id);
            ServletUtility.setBean(bean, request);
            log.info("Record fetched successfully");
        }

        log.debug("Forwarding request to view");
        ServletUtility.forward(getView(), request, response);

        log.debug("BaseCtl doGet method completed");
    }

    /**
     * Handles HTTP POST requests.
     *
     * It populates the Bean object from the submitted form data.
     * If the Bean ID is greater than zero, the existing record is updated.
     * Otherwise, a new record is inserted into the database.
     *
     * It also handles duplicate record exceptions, sets success or error
     * messages, stores the Bean in the request and forwards the request
     * to the corresponding JSP view.
     *
     * @param request HttpServletRequest object containing submitted form data
     * @param response HttpServletResponse object used to send the response
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.debug("BaseCtl doPost method started");

        B bean = populateBean(request);
        M model = getModel();
        Long id = DataUtility.getLong(request.getParameter("id"));

        try {
            if (bean.getId() > 0) {
                log.info("Updating existing record with ID: " + bean.getId());
                model.update(bean);
                ServletUtility.setSuccessMessage("record updated successfully", request);
                log.info("Record updated successfully");
            } else {
                log.info("Adding new record");
                model.add(bean);
                ServletUtility.setSuccessMessage("record saved successfully ", request);
                log.info("Record saved successfully");
            }
        } catch (DuplicateRecordException e) {
            log.warn("Duplicate record found");
            log.error("DuplicateRecordException occurred", e);
            ServletUtility.setErrorMessage("record already exist", request);
        }

        ServletUtility.setBean(bean, request);
        ServletUtility.forward(getView(), request, response);

        log.debug("BaseCtl doPost method completed");
    }

    /**
     * Handles the complete HTTP request processing lifecycle.
     *
     * This method is called before doGet() or doPost().
     * It performs the following operations:
     *
     * 1. Initializes the MessageSource.
     * 2. Loads predefined data using preload().
     * 3. Validates the submitted data for POST requests.
     * 4. If validation fails, forwards the request to the view.
     * 5. Otherwise, passes control to the parent service() method
     *    for further HTTP request processing.
     *
     * @param request HttpServletRequest object containing request information
     * @param response HttpServletResponse object used to send the response
     * @throws ServletException if a servlet-related error occurs
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.debug("BaseCtl service method started");

        getMessageSource(request);

        System.out.println("service method");

        preload(request);

        if (request.getMethod().equals("POST") && validate(request) == false) {

            log.warn("Request validation failed");

            // BaseBean bean = populateBean(request);
            // ServletUtility.setBean(bean, request);

            ServletUtility.forward(getView(), request, response);
            log.debug("Request forwarded because validation failed");
            return;
        }

        log.debug("Passing request to parent service method");
        super.service(request, response);

        log.debug("BaseCtl service method completed");
    }

    /**
     * Returns the Model object associated with the child controller.
     *
     * Every child controller must implement this method to provide
     * its respective Model class object.
     *
     * @return Model object extending BaseModel
     */
    public abstract M getModel();

    /**
     * Returns the JSP view path associated with the child controller.
     *
     * Every child controller must implement this method to specify
     * the JSP page to which the request should be forwarded.
     *
     * @return JSP view path
     */
    public abstract String getView();

    /**
     * Returns the MessageSource instance used for managing
     * application messages and language resources.
     *
     * @param request HttpServletRequest object containing request information
     * @return MessageSource instance
     */
    public MessageSource getMessageSource(HttpServletRequest request) {

        log.debug("BaseCtl getMessageSource method started");

        MessageSource messageSource = MessageSource.getInstance();

        log.info("MessageSource instance retrieved successfully");
        log.debug("BaseCtl getMessageSource method completed");

        return messageSource;
    }
}