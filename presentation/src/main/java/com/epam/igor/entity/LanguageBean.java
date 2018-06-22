package com.epam.igor.entity;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

@ManagedBean(name = "language")
@SessionScoped
public class LanguageBean implements Serializable {

    private static final long serialVersionUID = -6831921342246196194L;

    private Locale locale;

    /**
     * Method initializes session's locale
     */
    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    /**
     * @return present locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @return present language name
     */
    public String getLanguage() {
        return locale.getLanguage();
    }

    /**
     * Method changes present language on another
     *
     * @param language on changing
     */
    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
