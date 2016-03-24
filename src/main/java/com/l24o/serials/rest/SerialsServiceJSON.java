package com.l24o.serials.rest;

import com.l24o.serials.entities.Serial;
import com.l24o.serials.repo.SerialRepo;
import com.l24o.serials.rest.response.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static com.l24o.serials.rest.exceptions.Error.NOT_FOUND;

public class SerialsServiceJSON implements ISerialService {

    @Autowired
    private SerialRepo serialRepo;

    // for retrieving request headers from context
    // an injectable interface that provides access to HTTP header information.
    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion() {
        return "0.1";
    }

    // get by id service
    @GET
    @Path("/{id}")
    public Response getSerial(@PathParam("code") String code) {
        Serial serial = serialRepo.findOne(code);
        if (serial != null) {
            return ResponseCreator.success(getHeaderVersion(), serial);
        } else {
            return ResponseCreator.error(404, NOT_FOUND.getCode(),
                    getHeaderVersion());
        }
    }

	/*// remove row from the serials table according with passed id and returned
    // status message in body
	@DELETE
	@Path("/{id}")
	public Response removeSerial(@PathParam("id") String id) {
		if (serialsDAO.removeSerial(id)) {
			return ResponseCreator.success(getHeaderVersion(), "removed");
		} else {
			return ResponseCreator.success(getHeaderVersion(), "no such id");
		}
	}*/

    /*// create row representing serial and returns created serial as
    // object->JSON structure
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSerial(Serial serial) {
        System.out.println("POST");
        Serial creSerial = serialsDAO.createSerial(serial);
        if (creSerial != null) {
            return ResponseCreator.success(getHeaderVersion(), creSerial);
        } else {
            return ResponseCreator.error(500, Error.SERVER_ERROR.getCode(),
                    getHeaderVersion());
        }
    }
*/
    // update row and return previous version of row representing serial as
    // object->JSON structure
    /*@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSerial(Serial serial) {
		Serial updSerial = serialsDAO.updateSerial(serial);
		if (updSerial != null) {
			return ResponseCreator.success(getHeaderVersion(), updSerial);
		} else {
			return ResponseCreator.error(500, Error.SERVER_ERROR.getCode(),
					getHeaderVersion());
		}
	}
*/
    // returns list of serials meeting query params
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSerials(@QueryParam("keyword") String keyword,
                               @QueryParam("pagenum") Integer pageNum,
                               @QueryParam("pagesize") Integer pageSize) {
        return ResponseCreator.success(getHeaderVersion(), serialRepo.searchByCode(keyword));

    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSerials() {
        return ResponseCreator.success(getHeaderVersion(), serialRepo.findAll());

    }
}