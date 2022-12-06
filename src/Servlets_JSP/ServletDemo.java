package Servlets_JSP;

//needed when pages must be built at runtime live in response to dynamic requests; on the server a web container helper application will contain
// servlets = java file that takes client requests and process it and provide response in html page
    //is a normal java class, that extends HTTPServlet
//web app contains; apache tomcat, JBoss, IBM websphere, glassfish
    //so must install on your server
//web app sees the requested page doesn't exist, so sends request to the servlet
// the page being called must map to the servlet
    //web.xml file is Deployment Descriptor: maps servlets against request types. 1 servlet per request
    //Can actually use annotations on servlets to map, so don't need web.xml file
//Client dynamic request -> server -> web container -> checks web.xml mapping / annotations mapping-> sends to appropriate servlet
//  -> servlet sends response in HTML/XML/JSON in form of response object

//web.xml
//   servlet tag & servlet mapping tag
public class ServletDemo {
}
