package com.epam.igor;

import com.epam.igor.entity.Event;
import com.epam.igor.entity.Rate;
import org.junit.Before;

import java.time.LocalDateTime;

public class EventTest {

    private Event event;

    @Before
    public void initEvent() {
        event = new Event();
        event.setBasePrice(600);
        event.setDateTime(LocalDateTime.now());
        event.setMovieId(1L);
        event.setRate(Rate.HIGH);
    }


}
