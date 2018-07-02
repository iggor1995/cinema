package com.epam.igor.entity;


import com.epam.igor.api.AuditoriumService;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ManagedBean
@SessionScoped
public class AuditoriumManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriumManager.class);
    private static final String HOME = "/pages/home?faces-redirect=true";

    @Inject
    private AuditoriumService auditoriumService;
    private Auditorium auditorium;

    @Produces
    @Named
    public Auditorium getAuditorium() {
        return auditorium;
    }

    @PostConstruct
    public void init() {
        this.auditorium = new Auditorium();
    }

    public String saveAuditorium(Auditorium auditorium) {
        try {
            auditoriumService.createAuditorium(auditorium);
        } catch (ServiceException e) {
            LOGGER.error("Cannot save auditorium");
        }
        return HOME;
    }

    public List<Auditorium> getAll(){
        try {
            return auditoriumService.getAll();
        } catch (ServiceException e) {
            LOGGER.error("Couldn't get all auditoriums");
        }
        return null;
    }
}
