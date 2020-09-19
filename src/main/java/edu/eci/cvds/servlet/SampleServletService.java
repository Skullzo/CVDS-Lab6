package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    urlPatterns = "/cosasPorHacer"
)
public class SampleServletService extends HttpServlet{
    static final long serialVersionUID = 35L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Writer responseWriter = resp.getWriter();
       //Crear la lista
       ArrayList<Todo> toDo=new ArrayList();
       try{
        Optional<String> optID = Optional.ofNullable(req.getParameter("id"));
        String id = optID.isPresent() && !optID.get().isEmpty() ? optID.get() : "";
        Todo todo = Service.getTodo(Integer.parseInt(id));
		//Agregar todo a la lista de cosas por hacer
		toDo.add(todo);
                //OK (Funciona)
		resp.setStatus(HttpServletResponse.SC_OK);
		//Mostrar la tabla HTML usando Service
		responseWriter.write(Service.todosToHTMLTable(toDo));	
         //No encontrado (Not Found)
       } catch(FileNotFoundException e){
           resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
         //Requerimiento invalido (Bad Request)  
       } catch(NumberFormatException e){
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
         //Error interno en el servidor (Internal Server Error)
       } catch(MalformedURLException e){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
         //Para cualquier otra excepcion, devolver el código equivalente a requerimiento inválido (Bad Request)
       } catch(Exception e){
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       } 
       responseWriter.flush();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Writer responseWriter = resp.getWriter();
       //Crear la lista
       ArrayList<Todo> toDo=new ArrayList();
       try{
        Optional<String> optID = Optional.ofNullable(req.getParameter("id"));
        String id = optID.isPresent() && !optID.get().isEmpty() ? optID.get() : "";
        Todo todo = Service.getTodo(Integer.parseInt(id));
		//Agregar todo a la lista de cosas por hacer
		toDo.add(todo);
		resp.setStatus(HttpServletResponse.SC_OK);
		//Mostrar la tabla HTML usando Service
		responseWriter.write(Service.todosToHTMLTable(toDo));	
         //No encontrado (Not Found)
       } catch(FileNotFoundException e){
           resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
         //Requerimiento invalido (Bad Request)  
       } catch(NumberFormatException e){
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
         //Error interno en el servidor (Internal Server Error)
       } catch(MalformedURLException e){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
         //Para cualquier otra excepcion, devolver el código equivalente a requerimiento inválido (Bad Request)
       } catch(Exception e){
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       } 
       responseWriter.flush();
    }

}

