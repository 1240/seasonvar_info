package com.l24o.serials.rest;

import com.l24o.serials.entities.Serial;
import com.l24o.serials.repo.SerialRepo;
import com.l24o.serials.rest.response.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
    @Path("/one")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSerial(@QueryParam("code") String code) {
        Serial serial = serialRepo.findOne(code);
            return ResponseCreator.success(getHeaderVersion(), serial);
    }

    // returns list of serials meeting query params
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSerials(@QueryParam("keyword") String keyword,
                               @QueryParam("pagenum") Integer pageNum,
                               @QueryParam("pagesize") Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum, pageSize, new Sort(Sort.Direction.ASC, "name"));
        return ResponseCreator.success(getHeaderVersion(), serialRepo.searchByName(keyword, pageRequest).getContent());
    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSerials() {
        return ResponseCreator.success(getHeaderVersion(), serialRepo.findAll());

    }
}