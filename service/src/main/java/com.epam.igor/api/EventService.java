package com.epam.igor.api;

import com.epam.igor.entity.Event;
import com.epam.igor.exception.ServiceException;

public interface EventService {

    void createEvent(Event event) throws ServiceException;
}
