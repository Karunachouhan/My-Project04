
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.model.ATMModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * ATMCtl handles HTTP requests related to ATM information.
 * It validates request parameters, populates the ATMBean,
 * and provides the ATMModel and corresponding view.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/ATMCtl")
public class ATMCtl extends BaseCtl<ATMBean, ATMModel> {

    private static Logger log = Logger.getLogger(ATMCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("ATMCtl validate method started");

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("location"))) {
            request.setAttribute("location", "location is required");
            log.warn("Location is required");
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("bankName"))) {
            request.setAttribute("bankName", "bank name is required");
            log.warn("Bank name is required");
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("cashAvailable"))) {
            request.setAttribute("cashAvailable", "cash is required");
            log.warn("Cash available is required");
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("status"))) {
            request.setAttribute("status", "status is required");
            log.warn("Status is required");
            pass = false;
        }

        if (pass) {
            log.info("ATM form validation successful");
        } else {
            log.error("ATM form validation failed");
        }

        log.debug("ATMCtl validate method completed");

        return pass;
    }

    @Override
    protected ATMBean populateBean(HttpServletRequest request) {

        log.debug("ATMCtl populateBean method started");

        ATMBean bean = new ATMBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setLocation(DataUtility.getString(request.getParameter("location")));
        bean.setBankName(DataUtility.getString(request.getParameter("bankName")));
        bean.setCashAvailable(DataUtility.getDouble(request.getParameter("cashAvailable")));
        bean.setStatus(DataUtility.getString(request.getParameter("status")));

        populateDTO(bean, request);

        log.info("ATMBean populated successfully");
        log.debug("ATMCtl populateBean method completed");

        return bean;
    }

    @Override
    public ATMModel getModel() {

        log.debug("ATMCtl getModel method started");

        ATMModel model = new ATMModel();

        log.info("ATMModel object created successfully");
        log.debug("ATMCtl getModel method completed");

        return model;
    }

    @Override
    public String getView() {

        log.debug("ATMCtl getView method started");

        log.info("ATM view returned successfully");

        log.debug("ATMCtl getView method completed");

        return ORSView.ATM_VIEW;
    }
}
