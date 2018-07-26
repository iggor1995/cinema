package com.epam.igor.soap;

import com.epam.igor.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding()
public interface UserWs {

    @WebMethod
    User getUserById(long id);
}
