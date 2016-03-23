package com.l24o.serials.rest;

import javax.ws.rs.core.Response;

public interface ISerialService {

	Response getSerial(String id);
//	Response removeSerial(String id);
//	Response createSerial(Serial str);
//	Response updateSerial(Serial str);
	Response getSerials(String keyword, String orderBy, String order, Integer pageNum, Integer pageSize);
}
