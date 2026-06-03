package coupling;

public class Browser {
    public void navigate(String url) {
        String ip = findIPAddress(url);
        String html = sendHTTPRequest(ip);
        System.out.println(html);
    }

    private String sendHTTPRequest(String ip) { // Less coupling due to abstraction
        return "<!DOCTYPE html><html><body>Hello, World!</body></html>";
    }

    private String findIPAddress(String url) { // Less coupling due to abstraction
        return "127.0.0.1";
    }

//    public String findIPAddress(String url, boolean cache) { // Less coupling due to abstraction
//        return "127.0.0.1";
//    }
}
