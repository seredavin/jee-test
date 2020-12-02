package restController;

import dto.City;
import repo.CityRepo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/city")
public class CityController {
    @Inject
    private CityRepo cityRepo;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public List<City> getAllCity() {
        return cityRepo.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public City getCity(@PathParam("id") long id) {
        return cityRepo.getById(id);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public void addCity(City city) {
        cityRepo.add(city);
    }
}
