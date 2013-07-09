package com.github.russ4stall.reciperoots.utilities;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Date: 7/2/13
 * Time: 12:21 PM
 *
 * @author Russ Forstall
 */
public class ParameterHelper {
    private final HttpServletRequest request;

    public ParameterHelper(HttpServletRequest request) {
        this.request = request;
    }

    public Boolean getParameterAsBoolean(String name) {
        String value = request.getParameter(name);
        return Boolean.valueOf(value);
    }

    public Integer getParameterAsInteger(String name) {
        String value = request.getParameter(name);
        if (isEmpty(value)) {
            return null;
        }

        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
