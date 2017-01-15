package project_server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.regexp.internal.RE;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("database")
public class MyResource {
    static String datePattern = "dd-MM-yyyy";
    static SimpleDateFormat dateFormat=new SimpleDateFormat(datePattern);
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

    @GET
    @Path("/groups/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getGroups() {
        DBWorker worker = new DBWorker();
        List<Group> list = worker.getGroups();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(datePattern).create();
            String groupsJSON = gson.toJson(list);
            /*List<Group> groupList=gson.fromJson(groupsJSON, new TypeToken<List<Group>>(){}.getType());
            for (Group group:groupList) {
                System.out.println(dateFormat.format(group.getCreateDate()));
            }*/
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @POST
    @Path("/groups/add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addGroup(@Context UriInfo info) {
        DBWorker worker = new DBWorker();
        int specialityId=Integer.parseInt(info.getQueryParameters().getFirst("specialityid"));
        String groupCode = info.getQueryParameters().getFirst("code");
        Date createDate;
        try {
            createDate = dateFormat.parse(info.getQueryParameters().getFirst("date"));
        } catch (ParseException e) {
            createDate=new Date(1996, Calendar.MARCH,30);
        }
        String result=worker.addGroup(groupCode,new java.sql.Date(createDate.getTime()),specialityId);
        worker.closeConnection();
        if(result.equals("false"))
            return Response.status(200).entity("new Group was added").build();
        else
            return Response.status(400).entity(result).build();
    }

    @DELETE
    @Path("/groups/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteGroup(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int id=Integer.parseInt(info.getQueryParameters().getFirst("id"));
        String result=worker.deleteGroup(id);
        worker.closeConnection();
        if(result.equals("false"))
            return Response.status(200).entity("deleted").build();
        else
            return Response.status(400).entity(result).build();
    }

    @GET
    @Path("/students/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStudents() {
        DBWorker worker = new DBWorker();
        List<Student> list = worker.getStudents();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(datePattern).create();
            String groupsJSON = gson.toJson(list);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @POST
    @Path("students/add/{student}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addStudent(@PathParam("student") String string){
        Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
        Student student=gson.fromJson(string,new TypeToken<Student>(){}.getType());
        String result;
        if(student!=null){
            DBWorker worker=new DBWorker();
            result=worker.addStudent(student);
            worker.closeConnection();
        }else
            result="Wrong Data";
        return Response.status(200).entity(result).build();

    }

    @DELETE
    @Path("/students/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteStudent(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int id=Integer.parseInt(info.getQueryParameters().getFirst("id"));
        String result=worker.deleteStudent(id);
        worker.closeConnection();
        if(result.equals("true"))
            return Response.status(200).entity("deleted").build();
        else
            return Response.status(400).entity(result).build();
    }

    @GET
    @Path("/marks/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMarks() {
        DBWorker worker = new DBWorker();
        List<Mark> list = worker.getMarks();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
            String groupsJSON = gson.toJson(list);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @POST
    @Path("marks/add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addStudent(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int studentId=Integer.parseInt(info.getQueryParameters().getFirst("student_id"));
        int mark_id=Integer.parseInt(info.getQueryParameters().getFirst("mark_id"));
        String subject=info.getQueryParameters().getFirst("subject");
        String result=worker.addMark(studentId,mark_id,subject);
        if(result.equals("false"))
            return Response.status(200).entity("new Mark was added").build();
        else
            return Response.status(400).entity(result).build();

    }

    @DELETE
    @Path("/marks/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMark(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int studentId=Integer.parseInt(info.getQueryParameters().getFirst("student_id"));
        String subject=info.getQueryParameters().getFirst("subject");
        String result=worker.deleteMark(studentId,subject);
        worker.closeConnection();
        if(result.equals("false"))
            return Response.status(200).entity("deleted").build();
        else
            return Response.status(400).entity(result).build();
    }

    @GET
    @Path("/payment/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPayment() {
        DBWorker worker = new DBWorker();
        List<Payment> list = worker.getPayment();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(datePattern).create();
            String groupsJSON = gson.toJson(list);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @POST
    @Path("payment/add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addPayment(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int contractId=Integer.parseInt(info.getQueryParameters().getFirst("contract"));
        Date paymentDate;
        try {
            paymentDate = dateFormat.parse(info.getQueryParameters().getFirst("date"));
        } catch (ParseException e) {
            paymentDate=new Date(1996, Calendar.MARCH,30);
        }
        double paymentSum=Double.parseDouble(info.getQueryParameters().getFirst("sum"));
        String documentNo=info.getQueryParameters().getFirst("document");
        String result=worker.addPayment(contractId,paymentDate,paymentSum,documentNo);
        if(result.equals("false"))
            return Response.status(200).entity("new Payment was added").build();
        else
            return Response.status(400).entity(result).build();

    }

    @DELETE
    @Path("/payment/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePayment(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int paymenttId=Integer.parseInt(info.getQueryParameters().getFirst("id"));
        String result=worker.deletePayment(paymenttId);
        worker.closeConnection();
        if(result.equals("false"))
            return Response.status(200).entity("deleted").build();
        else
            return Response.status(400).entity(result).build();
    }

    @GET
    @Path("/violation/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getViolation() {
        DBWorker worker = new DBWorker();
        List<Violation> list = worker.getViolation();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(datePattern).create();
            String groupsJSON = gson.toJson(list);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @POST
    @Path("violation/add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addViolation(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int violationKindId=Integer.parseInt(info.getQueryParameters().getFirst("violation"));
        int punishKindId=Integer.parseInt(info.getQueryParameters().getFirst("punish"));
        Date violationDate;
        try {
            violationDate = dateFormat.parse(info.getQueryParameters().getFirst("date"));
        } catch (ParseException e) {
            violationDate=new Date(1996, Calendar.MARCH,30);
        }
        int personId=Integer.parseInt(info.getQueryParameters().getFirst("person"));
        String result=worker.addViolation(violationKindId,punishKindId,violationDate,personId);
        if(result.equals("false"))
            return Response.status(200).entity("new Violation was added").build();
        else
            return Response.status(400).entity(result).build();

    }

    @DELETE
    @Path("/violation/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteViolation(@Context UriInfo info){
        DBWorker worker=new DBWorker();
        int violationId=Integer.parseInt(info.getQueryParameters().getFirst("id"));
        String result=worker.deleteViolation(violationId);
        worker.closeConnection();
        if(result.equals("false"))
            return Response.status(200).entity("deleted").build();
        else
            return Response.status(400).entity(result).build();
    }

    @GET
    @Path("/violationkind/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getViolationKind() {
        DBWorker worker = new DBWorker();
        List<ViolationKind> list = worker.getViolationKind();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
            String groupsJSON = gson.toJson(list);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/punishkind/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPunishKind() {
        DBWorker worker = new DBWorker();
        List<PunishKind> list = worker.getPunishKind();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
            String groupsJSON = gson.toJson(list);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/persons/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPersons() {
        DBWorker worker = new DBWorker();
        List<Person> list = worker.getPersons();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
            String groupsJSON = gson.toJson(list);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/smarks/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getsMarks(){
        DBWorker worker=new DBWorker();
        List<MarkKind> list=worker.getMarkKinds();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
            String groupsJSON = gson.toJson(list);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/contracts/getall")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getContracts(){
        DBWorker worker=new DBWorker();
        List<Contract> list=worker.getContracts();
        worker.closeConnection();
        if (list != null) {
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
            String groupsJSON = gson.toJson(list);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/query1")
    @Produces(MediaType.TEXT_PLAIN)
    public Response query1(){
        DBWorker worker=new DBWorker();
        List<Query1> query1List=worker.getQuery1();
        worker.closeConnection();
        if (query1List != null) {
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
            String groupsJSON = gson.toJson(query1List);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/query2")
    @Produces(MediaType.TEXT_PLAIN)
    public Response query2(){
        DBWorker worker=new DBWorker();
        List<Query2> query2List=worker.getQuery2();
        worker.closeConnection();
        if (query2List != null) {
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
            String groupsJSON = gson.toJson(query2List);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/query3")
    @Produces(MediaType.TEXT_PLAIN)
    public Response query3(){
        DBWorker worker=new DBWorker();
        List<Query3> query3List=worker.getQuery3();
        worker.closeConnection();
        if (query3List != null) {
            Gson gson = new GsonBuilder().setDateFormat(datePattern).create();
            String groupsJSON = gson.toJson(query3List);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/query4")
    @Produces(MediaType.TEXT_PLAIN)
    public Response query4(){
        DBWorker worker=new DBWorker();
        List<Query4> query4List=worker.getQuery4();
        worker.closeConnection();
        if (query4List != null) {
            Gson gson = new GsonBuilder().setDateFormat(datePattern).create();
            String groupsJSON = gson.toJson(query4List);
            return Response.status(200).entity(groupsJSON).build();
        }
        return Response.status(404).build();
    }
}
