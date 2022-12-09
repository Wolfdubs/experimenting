package APIs;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//to use json for json requests, must install & import & add to project; gson
// gson treats json like an object; where each key is a field of the gson object
//gson can then auto convert the object you create into the json string the object's fields represent
public class APIDemo {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        //CREATE THE POST REQUEST
//        HttpRequest postRequest1 = HttpRequest.newBuilder()      //uses Builder design pattern so can add all the elements of the request in a chain of method calls
//                .uri(new URI("https://api.openweathermap.org/data/3.0/onecall")) //sets the url to call. method accepts a URI object
//                .header("appid", "529c72ca6de13443b0027f2c0fd23e3e")  //to add headers by KV to the request
//                .POST(HttpRequest.BodyPublishers.ofString("{\"lat\":\"51.362150\",\"lon\":\"-1.184940\"}"))    //specify http method for the request. Pass in a BodyPublisher object. If sending json, add .ofString
//                .build();

        //can pass in json either manually to the BodyPublishers, or via custom object created using gson
        JSONBody jb = new JSONBody();
        jb.setLat("51.362150");
        jb.setLon("-1.184940");
        Gson gs = new Gson();                        //use gson to translate the object into json
        String jsonRequest = gs.toJson(jb);
        System.out.println(jsonRequest);

//        HttpRequest postRequest2 = HttpRequest.newBuilder()
//                .uri(new URI("https://api.openweathermap.org/data/3.0/onecall"))
//                .header("appid", "529c72ca6de13443b0027f2c0fd23e3e")
//                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))    //passing in the gson generated json object
//                .build();
//        //SEND THE POST REQUEST
//        HttpClient hc2 = HttpClient.newHttpClient();
//        HttpResponse<String> postResponse = hc2.send(postRequest1, HttpResponse.BodyHandlers.ofString());   //also must tell method what response type to expect. Store response in HTTPResponse object (type string, as response specified to be of string)
//        System.out.println(postResponse.body());   //get the body of the response in json format


//        //READING JSON RESPONSE - TRANSLATING JSON VALUE INTO AN OBJECT. NEED TO HAVE A CLASS TO DEFINE THE OBJECT (could be same as used for json body object)
//        MyResponseBody rb1 = gs.fromJson(postResponse.body(), MyResponseBody.class);  //transform the json response into a custom object; pass in the json string to convert, and the class to convert it into
//        System.out.println(rb1.getResponsecode());



        //CREATE THE GET REQUEST
        MyResponseBody rb2 = null;   //This isn't needed for GET request, but is so that if you do a GET using some details from a prior request, this demo's how to pass in a json value from a past request e.g. a jobID
        HttpRequest getRequest1 = HttpRequest.newBuilder()      //uses Builder design pattern so can add all the elements of the request in a chain of method calls
                .uri(new URI("https://api.openweathermap.org/data/3.0/onecall"))// + rb2.getResponsecode())) //including the rb2 as demo for adding on data parsed from prior requests
                .header("appid", "529c72ca6de13443b0027f2c0fd23e3e")  //to add headers by KV to the request
                .build();   //default httpmethod is GET so don't have to specify it before build()



        //SEND THE GET REQUEST
        HttpClient hc1 = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = hc1.send(getRequest1, HttpResponse.BodyHandlers.ofString());
        System.out.println(getResponse.body());

        //READ JSON RESPONSE
        MyResponseBody getRB = gs.fromJson(getResponse.body(), MyResponseBody.class);  //saves json response into a MyResponseBody object
        System.out.println(getRB.getResponsecode() + getRB.getMessage());
        //to keep polling the API until status==successful, use a while(true) loop and only break if jsonObj.status.equals("success"), or .equals("error")
    }

}

class JSONBody{   //using gson to turn the json body of the request into an object that can be passed in to BodyPublishers.ofString()
    private String lat;   //fields must have the same name as in the original json
    private String lon;

    public String getLat() {return lat;}
    public void setLat(String lat) {this.lat = lat;}
    public String getLon() {return lon;}
    public void setLon(String lon) {this.lon = lon;}
}

class MyResponseBody {
    String cod;
    String message;

    public String getResponsecode() {        return cod;    }
    public void setResponsecode(String responsecode) {        this.cod = responsecode;    }
    public String getMessage() {       return message;    }
    public void setMessage(String message) {        this.message = message;    }
}









/*
    OkHttpClient client = new OkHttpClient();

    RequestBody body = new FormBody.Builder()
            .add("userId", "<REQUIRED>")
            .add("accessToken", "<REQUIRED>")
            .add("trackId", "<REQUIRED>")
            .build();

    Request request = new Request.Builder()
            .url("https://soundcloudstefan-skliarovv1.p.rapidapi.com/checkUserLikedTrack")
            .post(body)
            .addHeader("content-type", "application/x-www-form-urlencoded")
            .addHeader("X-RapidAPI-Key", "SIGN-UP-FOR-KEY")
            .addHeader("X-RapidAPI-Host", "Soundcloudstefan-skliarovV1.p.rapidapi.com")
            .build();

    Response response = client.newCall(request).execute();
*/
