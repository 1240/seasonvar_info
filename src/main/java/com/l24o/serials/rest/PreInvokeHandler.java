package com.l24o.serials.rest;

import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.message.Message;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PreInvokeHandler implements ClientRequestFilter {

    // just for test
    int count = 0;

    private boolean validate(String ss_id) {
        // just for test
        // needs to implement
        count++;
        System.out.println("SessionID: " + ss_id);
        if (count == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        Map<String, List<String>> headers = CastUtils.cast((Map<?, ?>) clientRequestContext.getProperty(Message.PROTOCOL_HEADERS));

		/*if (headers.get("ss_id") != null && validate(headers.get("ss_id").get(0))) {
			// let request to continue
			return null;
		} else {
			// authentication failed, request the authentication, add the realm
			return ResponseCreator.error(401, Error.NOT_AUTHORIZED.getCode(), headers.get("version").get(0));
		}	*/
    }
}
