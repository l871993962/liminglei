package com.yss.uco.elecreco.support.restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.uco.elecreco.support.restservice.pojo.RestfulResponseWrapper;

@Path("/")
@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8"})
public interface IErResultRestfulService {
	
	@GET
    @Path("/elecreco/result/{portCode}/{startDate}/{fileType}")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8"})
	public RestfulResponseWrapper queryErResult(@PathParam("portCode") String portCode,
			@PathParam("startDate") String startDate,@PathParam("fileType") String fileType);
	
}
