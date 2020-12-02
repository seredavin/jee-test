package servlet;

import dto.City;
import dto.CityDistanceXml;
import dto.Distance;
import dto.DistanceXml;
import repo.CityRepo;
import repo.DistanceRepo;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Inject
    CityRepo cityRepo;

    @Inject
    DistanceRepo distanceRepo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/upload.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        try {
            JAXBContext context = JAXBContext.newInstance(CityDistanceXml.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CityDistanceXml cityDistanceXml = (CityDistanceXml) unmarshaller.unmarshal(fileContent);
            for (City city : cityDistanceXml.getCityList()) {
                cityRepo.add(city);
            }
            for (DistanceXml distance : cityDistanceXml.getDistanceXmlList()) {
                distanceRepo.add(new Distance(cityRepo.getById(distance.getFrom()),
                        cityRepo.getById(distance.getTo()),
                        distance.getDistance()));
            }

        }catch(Exception ex) {
            ex.printStackTrace();
            response.sendError(500);
        }
    }

}
