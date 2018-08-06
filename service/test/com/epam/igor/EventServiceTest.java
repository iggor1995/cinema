package com.epam.igor;

import com.epam.igor.dao.api.EventDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Auditorium;
import com.epam.igor.entity.Event;
import com.epam.igor.entity.Movie;
import com.epam.igor.entity.Rate;
import com.epam.igor.exception.ServiceException;
import com.epam.igor.impl.EventServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    private
    EventDao eventDaoMock;

    @InjectMocks
    private EventServiceImpl eventService;

    private Auditorium givenAuditorium = new Auditorium();
    private Event givenEvent = new Event();

    public void initAuditorium(){
        givenAuditorium.setSeatsNumber(100);
        givenAuditorium.setName("Name");
        givenAuditorium.setVipSeats("1,2,3,4,5");
    }

    @Before
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

    @Test
    public void createEventTest() throws DaoException, ServiceException {

        Mockito.when(eventDaoMock.create(any(Event.class))).thenReturn(givenEvent);

        Event resultEvent = eventService.createEvent(givenEvent);

        assertEquals(resultEvent.getId(), givenEvent.getId());
        assertEquals(resultEvent.getAuditoriumId(), givenEvent.getAuditoriumId());
    }

    @Test
    public void editEventTest() throws ServiceException, DaoException {

        eventService.editEvent(givenEvent);

        verify(eventDaoMock).edit(givenEvent);
    }

    @Test
    public void getByMovieIdTest() throws DaoException, ServiceException {

        List<Event> givenEvents = new ArrayList<>();

        Event event2 = new Event();
        Event event3 = new Event();

        givenEvents.add(givenEvent);
        givenEvents.add(event2);
        givenEvents.add(event3);

        Mockito.when(eventDaoMock.getByMovieId(any(Long.class))).thenReturn(givenEvents);

        List<Event> resultEvents = eventService.getByMovieId(1L);

        assertEquals(resultEvents.size(), givenEvents.size());
    }

    @Test
    public void getAllTest() throws DaoException, ServiceException {
        List<Event> givenEvents = new ArrayList<>();

        Event event2 = new Event();
        Event event3 = new Event();

        givenEvents.add(givenEvent);
        givenEvents.add(event2);
        givenEvents.add(event3);

        Mockito.when(eventDaoMock.getAll()).thenReturn(givenEvents);

        List<Event> resultEvents = eventService.getAll();

        assertEquals(givenEvents.size(), resultEvents.size());
    }
}
