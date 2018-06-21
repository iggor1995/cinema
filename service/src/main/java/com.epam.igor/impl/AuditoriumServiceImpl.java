package com.epam.igor.impl;

import com.epam.igor.api.AuditoriumService;
import com.epam.igor.dao.api.AuditoriumDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Auditorium;
import com.epam.igor.exception.ServiceException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuditoriumServiceImpl implements AuditoriumService {

    private AuditoriumDao auditoriumDao;

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
}
