package restController;

import dto.CalcRequest;
import dto.CalcRequestXml;
import dto.CalcResponse;
import dto.DistanceType;
import repo.CityRepo;
import service.CalcService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/calc")
public class CalcController {
    @Inject
    CityRepo cityRepo;

    @Inject
    CalcService calcService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public Response getDistance(CalcRequestXml calcRequestXml) {
        int status = 200;
        CalcRequest calcRequest = new CalcRequest();
        calcRequest.setFrom(cityRepo.getById(calcRequestXml.getFrom()));
        calcRequest.setTo(cityRepo.getById(calcRequestXml.getTo()));
        calcRequest.setDistanceType(calcRequestXml.getDistanceType());
        if (calcRequest.getFrom() == null || calcRequest.getTo() == null) {
            return Response.status(404).entity("City not found.").build();
        }
        calcService.setCalcRequest(calcRequest);
        CalcResponse calcResponse = new CalcResponse();
        if (calcRequestXml.getDistanceType() == DistanceType.crowflight)
            calcResponse.setCrowflight(Double.toString(calcService.getCrowflightDistance()));
        if (calcRequestXml.getDistanceType() == DistanceType.matrix) {
            if (calcService.getMatrixDistance() == Integer.MAX_VALUE) {
                calcResponse.setMatrix("it is not in the distance table");
                return Response.status(404).entity(calcResponse).build();
            } else {
                calcResponse.setMatrix(Integer.toString(calcService.getMatrixDistance()));
            }
        }
        if (calcRequestXml.getDistanceType() == DistanceType.all) {
            calcResponse.setCrowflight(Double.toString(calcService.getCrowflightDistance()));
            if (calcService.getMatrixDistance() == Integer.MAX_VALUE) {
                calcResponse.setMatrix("it is not in the distance table");
            } else {
                calcResponse.setMatrix(Integer.toString(calcService.getMatrixDistance()));
            }
        }
        return Response.status(200).entity(calcResponse).build();
    }
}
