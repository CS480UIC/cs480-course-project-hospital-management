package user.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.NurseDao;
import user.dao.PatientDao;
import user.dao.PhysicianDao;
import user.domain.*;
/**
 * Servlet implementation class patientServlet
 */

public class nurseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private NurseDao userDAO;

    public void init() {
        userDAO = new NurseDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
    	int action1 = Integer.parseInt(request.getParameter("nursing"));
        System.out.println(action);
        System.out.println(action1);
        

        try {
            switch (action1) {
                case 1:
                    showNewForm(request, response);
                    break;
                case 2:
                    insertUser(request, response);
                    break;
                case 3:
                    deleteUser(request, response);
                    break;
                case 4:
                    showEditForm(request, response);
                    break;
                case 5:
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException, InstantiationException, IllegalAccessException {
        List<Nurse> listUser = userDAO.listAllNurse();
        request.setAttribute("listNurses", listUser);
        //System.out.println(listUser);
        for(int i=0;i<listUser.size();i++){
            System.out.println(listUser.get(i));
        } 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/Nurse/nurseList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/Nurse/nurseForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        Nurse existingUser = userDAO.getNurse(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/Nurse/nurseForm.jsp");
        request.setAttribute("nurse", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, InstantiationException, IllegalAccessException {
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String position = request.getParameter("position");
        System.out.println(first);
        System.out.println(last);
        System.out.println(position);
        
        Nurse newNurse = new Nurse(first, last, position);
        userDAO.insertNurse(newNurse);
        response.sendRedirect("nurseServlet?nursing=6");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, InstantiationException, IllegalAccessException {
        int id = Integer.parseInt(request.getParameter("id"));
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String position = request.getParameter("position");
        
        Nurse newNurse = new Nurse(id, first, last, position);
        userDAO.updateNurse(newNurse);
        response.sendRedirect("nurseServlet?nursing=6");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, InstantiationException, IllegalAccessException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteNurse(id);
        response.sendRedirect("nurseServlet?nursing=6");

    }

}
