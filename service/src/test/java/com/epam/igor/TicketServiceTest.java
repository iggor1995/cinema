package com.epam.igor;

import com.epam.igor.dao.api.TicketDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.*;
import com.epam.igor.exception.ServiceException;
import com.epam.igor.impl.TicketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @Mock
    private TicketDao ticketDaoMock;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private User givenUser = new User();
    private Auditorium givenAuditorium = new Auditorium();
    private Event givenEvent = new Event();
    private Ticket givenTicket = new Ticket();

    public void initUser(){
        UserAccount userAccount = new UserAccount();
        userAccount.setCash(5000);
        givenUser = new User();
        givenUser.setId(5L);
        givenUser.setName(UUID.randomUUID().toString());
        String password = UUID.randomUUID().toString();
        givenUser.setPassword(password);
        givenUser.setEnabled(true);
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        givenUser.setUserRole(roles);
    }

    public void initAuditorium(){
        givenAuditorium.setSeatsNumber(100);
        givenAuditorium.setName("Name");
        givenAuditorium.setVipSeats("1,2,3,4,5");
    }

    public void initEvent(){
        initAuditorium();
        givenEvent.setId(1L);
        givenEvent.setRate(Rate.HIGH);
        givenEvent.setMovieId(1L);
        givenEvent.setDateTime(LocalDateTime.now());
        givenEvent.setAuditoriumId(1L);
        givenEvent.setBasePrice(600.0);
        givenEvent.setAuditorium(givenAuditorium);
    }

    @Before
    public void initTicket(){
        initEvent();
        initUser();
        givenTicket.setId(1L);
        givenTicket.setPrice(700.0);
        givenTicket.setEventId(givenEvent.getId());
        givenTicket.setEvent(givenEvent);
        givenTicket.setUserId(givenUser.getId());
        givenTicket.setUser(givenUser);
        givenTicket.setSeat("5");
        givenTicket.setPrice(givenEvent.getBasePrice());
    }

    @Test
    public void createTicketTest() throws DaoException, ServiceException {

        Mockito.when(ticketDaoMock.createTicket(any(Ticket.class))).thenReturn(givenTicket);

        Ticket resultTicket = ticketService.createTicket(givenTicket);
        assertEquals(resultTicket.getId(), givenEvent.getId());
        assertEquals(resultTicket.getEvent(), givenTicket.getEvent());
    }

    @Test
    public void getAvailableTicketsTest() throws DaoException, ServiceException {
        List<Ticket> givenTickets = new ArrayList<>();

        Ticket ticket2 = new Ticket();
        ticket2.setEventId(givenEvent.getId());
        ticket2.setEvent(givenEvent);
        ticket2.setSeat("2");

        Ticket ticket3 = new Ticket();
        ticket3.setEventId(givenEvent.getId());
        ticket3.setEvent(givenEvent);
        ticket3.setSeat("3");

        givenTickets.add(givenTicket);
        givenTickets.add(ticket2);
        givenTickets.add(ticket3);

        Mockito.when(ticketDaoMock.getAll()).thenReturn(givenTickets);

        List<String> resultTickets = ticketService.getAvailableSeats(givenEvent);

        assert(!resultTickets.contains("5"));
        assert(!resultTickets.contains("2"));
        assert(!resultTickets.contains("3"));
        assert(resultTickets.contains("1"));
    }

}
