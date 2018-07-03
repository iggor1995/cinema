package com.epam.igor.api;

import com.epam.igor.entity.Auditorium;
import com.epam.igor.exception.ServiceException;

import java.util.List;

public interface AuditoriumService {

    void createAuditorium(Auditorium auditorium) throws ServiceException;

    List<Auditorium> getAll() throws ServiceException;
}
