package project_server;

import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("database")
public class MyResource {
    @GET
    @Path("/cafedras/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCafedras() {
        DBWorker worker = new DBWorker();
        List<Cafedra> list = worker.getCafedras();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new Gson();
            String cafedrasJSON = gson.toJson(list);
            return Response.status(200).entity(cafedrasJSON).build();
        }
        return Response.status(404).build();
    }

    @POST
    @Path("/cafedras/add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addCafedra(@Context UriInfo info) {
        DBWorker worker = new DBWorker();
        String name = info.getQueryParameters().getFirst("name");
        System.out.println(name);
        String shifr = info.getQueryParameters().getFirst("shifr");
        System.out.println(shifr);
        String result=worker.addCafedra(name,shifr);
        worker.closeConnection();
        if(result.equals("false"))
            return Response.status(200).entity("new Cafedra was added").build();
        else
            return Response.status(400).entity(result).build();
    }

    @DELETE
    @Path("/cafedras/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCafedra(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int id=Integer.parseInt(info.getQueryParameters().getFirst("id"));
        String result=worker.deleteCafedra(id);
        worker.closeConnection();
        if(result.equals("false"))
            return Response.status(200).entity("deleted").build();
        else
            return Response.status(400).entity(result).build();
    }

    @GET
    @Path("/specialisations/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSpecialisations() {
        DBWorker worker = new DBWorker();
        List<Speciality> list = worker.getSpecialisations();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new Gson();
            String specialitiesJSON = gson.toJson(list);
            return Response.status(200).entity(specialitiesJSON).build();
        }
        return Response.status(404).build();
    }

    @POST
    @Path("/specialisations/add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addSpeciality(@Context UriInfo info) {
        DBWorker worker = new DBWorker();
        int cafedra=Integer.parseInt(info.getQueryParameters().getFirst("cafedra"));
        String name = info.getQueryParameters().getFirst("name");
        String shifr = info.getQueryParameters().getFirst("shifr");
        String result=worker.addSpeciality(cafedra,name,shifr);
        worker.closeConnection();
        if(result.equals("false"))
            return Response.status(200).entity("new Speciality was added").build();
        else
            return Response.status(400).entity(result).build();
    }

    @DELETE
    @Path("/specialisations/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSpeciality(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int id=Integer.parseInt(info.getQueryParameters().getFirst("id"));
        String result=worker.deleteSpeciality(id);
        worker.closeConnection();
        if(result.equals("false"))
            return Response.status(200).entity("deleted").build();
        else
            return Response.status(400).entity(result).build();
    }
}
