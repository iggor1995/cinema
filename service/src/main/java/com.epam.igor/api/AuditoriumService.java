package com.epam.igor.api;

import com.epam.igor.entity.Auditorium;
import com.epam.igor.entity.User;
import com.epam.igor.exception.ServiceException;

public interface AuditoriumService {

    void createAuditorium (Auditorium auditorium) throws ServiceException;
}
