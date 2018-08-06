package com.epam.igor;

import com.epam.igor.dao.api.MovieDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Movie;
import com.epam.igor.exception.ServiceException;
import com.epam.igor.impl.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieDao movieDaoMock;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie givenMovie = new Movie();

    @Before
    public void initMovie(){
        givenMovie.setId(1L);
        givenMovie.setName("Movie");
        givenMovie.setDescription("Nice Movie");
        givenMovie.setReleaseDate(LocalDate.now());
    }

    @Test
    public void createMovieTest() throws DaoException, ServiceException {

        Mockito.when(movieDaoMock.saveMovie(any(Movie.class))).thenReturn(givenMovie);

        Movie resultMovie = movieService.create(givenMovie);

        assertEquals(resultMovie.getId(), givenMovie.getId());
        assertEquals(resultMovie.getDescription(), givenMovie.getDescription());
        assertEquals(resultMovie.getReleaseDate(), givenMovie.getReleaseDate());
    }

    @Test
    public void getAllTest() throws DaoException, ServiceException {
        Movie movie2 = new Movie();
        Movie movie3 = new Movie();

        List<Movie> givenMovies = new ArrayList<>();

        givenMovies.add(givenMovie);
        givenMovies.add(movie2);
        givenMovies.add(movie3);

        Mockito.when(movieDaoMock.getAll()).thenReturn(givenMovies);

        List<Movie> resulMovies = movieService.getAll();

        assertEquals(resulMovies.size(), givenMovies.size());
    }

    @Test
    public void getByIdTest() throws DaoException, ServiceException {

        Mockito.when(movieDaoMock.getById(any(Long.class))).thenReturn(givenMovie);

        Movie resultMovie = movieService.getById(1L);

        assertEquals(resultMovie.getId(), givenMovie.getId());
        assertEquals(resultMovie.getName(), givenMovie.getName());
        assertEquals(resultMovie.getReleaseDate(), givenMovie.getReleaseDate());
    }

    @Test
    public void editMovieTest() throws ServiceException {

        movieService.editMovie(givenMovie);

        verify(movieDaoMock).edit(any(Movie.class));
    }
}
