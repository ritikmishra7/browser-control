package com.Dropwizard.resources;

import com.Dropwizard.service.BrowserService;
import com.Dropwizard.service.BrowserServiceFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/server")
public class BrowserControlResource {

    @GET
    @Path ("/start")
    public Response startBrowser(@QueryParam ("browser") String browser, @QueryParam("url") String url) {
        BrowserService browserService = BrowserServiceFactory.getBrowserService(browser);
        browserService.start(url);
        return Response.ok("Started " + browser + " with URL: " + url).build();
    }

    @GET
    @Path("/stop")
    public Response stopBrowser(@QueryParam("browser") String browser) {
        BrowserService browserService = BrowserServiceFactory.getBrowserService(browser);
        browserService.stop();
        return Response.ok("Stopped " + browser).build();
    }

    @GET
    @Path("/geturl")
    public Response getActiveTabUrl(@QueryParam("browser") String browser) {
        BrowserService browserService = BrowserServiceFactory.getBrowserService(browser);
        String currentUrl = browserService.getActiveTabUrl();
        return Response.ok("Active tab URL: " + currentUrl).build();
    }

    @GET
    @Path("/cleanup")
    public Response cleanupBrowser(@QueryParam("browser") String browser) {
        BrowserService browserService = BrowserServiceFactory.getBrowserService(browser);
        browserService.cleanup();
        return Response.ok("Cleaned up " + browser).build();
    }
}
