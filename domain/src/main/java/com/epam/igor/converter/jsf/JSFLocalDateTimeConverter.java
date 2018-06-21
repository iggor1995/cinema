package com.epam.igor.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * {@inheritDoc}
 *
 * @author Ilya_Bondarenko
 */
@FacesConverter("localDateTimeFacesConverter")
public class JSFLocalDateTimeConverter extends DateTimeConverter {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String stringValue) {
        if (null == stringValue || stringValue.isEmpty()) {
            return null;
        }
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(stringValue, DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new ConverterException("Cannot get as object", e);
        }
        return localDateTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object localDateTimeValue) {
        if (null == localDateTimeValue) {
            return "";
        }
        return ((LocalDateTime) localDateTimeValue).format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
    }


}
