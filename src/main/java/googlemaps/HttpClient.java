package googlemaps;


public class HttpClient {

//    // one instance, reuse
//    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {

        GoogleMaps apiData = new GoogleMaps();
        apiData.setNavigationMode("driving".toUpperCase());


        try {
            System.out.println("Testing 1 - Send Http GET request");
            System.out.println("Testing 2 - Send Http POST request");
            apiData.setSource("19.970", "72.877");
            apiData.setDestination("19.070", "72.877");
            System.out.println(apiData.getNavigationMode());
            System.out.println(apiData.getSourceDestination());
            apiData.getDirections();

        }
        finally {

        }
    }
//
//    private void close() throws IOException {
//        httpClient.close();
//    }
//
//    private void sendGet() throws Exception {
//
//        HttpGet request = new HttpGet("https://www.google.com/search?q=mkyong");
//
//        // add request headers
//        request.addHeader("custom-key", "mkyong");
//        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
//
//        try (CloseableHttpResponse response = httpClient.execute(request)) {
//
//            // Get HttpResponse Status
//            System.out.println(response.getStatusLine().toString());
//
//            HttpEntity entity = response.getEntity();
//            Header headers = entity.getContentType();
//            System.out.println(headers);
//
//            if (entity != null) {
//                // return it as a String
//                String result = EntityUtils.toString(entity);
//                System.out.println(result);
//            }
//
//        }
//
//    }
//
//    private void sendPost() throws Exception {
//
//        HttpPost post = new HttpPost("https://httpbin.org/post");
//
//        // add request parameter, form parameters
//        List<NameValuePair> urlParameters = new ArrayList<>();
//        urlParameters.add(new BasicNameValuePair("username", "abc"));
//        urlParameters.add(new BasicNameValuePair("password", "123"));
//        urlParameters.add(new BasicNameValuePair("custom", "secret"));
//
//        post.setEntity(new UrlEncodedFormEntity(urlParameters));
//
//        try (CloseableHttpClient httpClient = HttpClients.createDefault();
//             CloseableHttpResponse response = httpClient.execute(post)) {
//
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        }
//
//    }

}