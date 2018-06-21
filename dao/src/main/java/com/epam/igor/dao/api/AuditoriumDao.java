package com.epam.igor.dao.api;

import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Auditorium;

public interface AuditoriumDao {

    Auditorium create(Auditorium auditorium) throws DaoException;

}
