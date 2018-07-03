package com.epam.igor.entity;


import com.epam.igor.api.MovieService;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ManagedBean
@SessionScoped
public class MovieManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieManager.class);
    private static final String HOME = "/pages/home?faces-redirect=true";
    private static final String EDIT_MOVIE_PAGE = "/pages/admin/movie/movie-edit?faces-redirect=true";
    private Part file;

    @Inject
    private MovieService movieService;
    private Movie movie;

    @Produces
    @Named
    public Movie getMovie() {
        return movie;
    }

    @PostConstruct
    public void init() {
        this.movie = new Movie();
    }

    /**
     * Save new movie
     * If movie exists, update movie
     */
    public String saveMovie(Movie movie) {
        try {
            if (movie.getId() != null) {
                editMovie(movie);
            } else {
                movieService.create(movie);
            }
        } catch (ServiceException e) {
            LOGGER.error("Cannot save movie");
        }
        return HOME;
    }

    public Movie getById(long id) {
        try {
            return movieService.getById(id);
        } catch (ServiceException e) {
            LOGGER.error("Couldn't get movie", e);
        }
        return null;
    }

    /**
     * Call edit method from serivce
     */
    public void editMovie(Movie movie) {
        try {
            movieService.editMovie(movie);
        } catch (ServiceException e) {
            LOGGER.error("Couldn't edit movie");
        }
    }

    public String showEditMovie(Movie movie) {
        this.movie = movie;
        return EDIT_MOVIE_PAGE;
    }

    /**
     * Upload image
     */
    public String upload(Movie movie) {
        LOGGER.info("call upload...");
        try {
            byte[] bFile;
            if (file != null) {
                bFile = new byte[(int) file.getSize()];
                System.out.println("upload movie + " + movie);
                InputStream in = file.getInputStream();
                //convert file into array of bytes
                in.read(bFile);
                in.close();
            } else {
                bFile = movie.getImage();
            }
            movie.setImage(bFile);
            saveMovie(movie);
        } catch (IOException ex) {
            LOGGER.error(" ex @{0}", ex);
        }
        return HOME;
    }

    /**
     * Get all movies
     */
    public List<Movie> getAll() {
        try {
            return movieService.getAll();
        } catch (ServiceException e) {
            LOGGER.error("Couldn't get movies");
        }
        return null;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        if (file != null) {
            this.file = file;
        }
    }
}
