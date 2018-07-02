package com.epam.igor.impl;

import com.epam.igor.api.AuditoriumService;
import com.epam.igor.dao.api.AuditoriumDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Auditorium;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AuditoriumServiceImpl implements AuditoriumService {

    private AuditoriumDao auditoriumDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriumServiceImpl.class);

    @Inject
    public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }

    @Override
    public void createAuditorium(Auditorium auditorium) throws ServiceException {
        try {
            auditoriumDao.create(auditorium);
        } catch (DaoException e) {
            throw new ServiceException("Cannot create auditorium", e);
        }
    }

    @Override
    public List<Auditorium> getAll() throws ServiceException {
        try {
            return auditoriumDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Couldn't get all auditoriums");
        }
    }
}
