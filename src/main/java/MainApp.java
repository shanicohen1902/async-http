
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


public class MainApp {

    public static void main(String [] args){

        List<String> userAgents = new ArrayList<String>(Arrays.asList("Linux%3B+NetCast%3B+U", "Windows+NT+10.0%3B+Win64%3B+x64", "Debian+APT-HTTP%2F1.3+%281.4%29"));

        List<CompletableFuture<String>> tasks = sendNewDevicesAsync("http://api.userstack.com/detect",userAgents);

        List<String> results = tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());

        results.forEach(result -> System.out.println(result));


    }

    public static List<CompletableFuture<String>> sendNewDevicesAsync(String url, List<String> userAgents) {

        WebClient webClient = new WebClient();

        List<CompletableFuture<String>> tasks = new ArrayList<CompletableFuture<String>>();

        userAgents.forEach(userAgent -> {

            tasks.add(webClient.sendTask(url + "?access_key=" + "your access key" + "&ua=" + userAgent));

        });


        return tasks;
    }

}
