package com.epam.igor.dao.api;

import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Event;

public interface EventDao {

    Event create(Event event) throws DaoException;
}
