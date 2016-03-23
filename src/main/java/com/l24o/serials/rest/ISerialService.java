package com.l24o.serials.rest;

import javax.ws.rs.core.Response;
import java.util.List;

public interface ISerialService {

	Response getSerial(String id);

	Response getSerials(String keyword, Integer pageNum, Integer pageSize);

    List getAllSerials();
}
