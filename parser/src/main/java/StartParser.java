import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexander Popov on 17.02.2016.
 */
public class StartParser {

    public static void main(String[] args) throws IOException {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        String html = "";
        String home = "http://seasonvar.ru";
//        try {
//            url = new URL(home);
//            is = url.openStream();  // throws an IOException
//            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//            while ((line = br.readLine()) != null) {
//                html += line;
//            }
//        } catch (MalformedURLException mue) {
//            mue.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } finally {
//            try {
//                if (is != null) is.close();
//            } catch (IOException ioe) {
//                // nothing to see here
//            }
//        }
//        Document parse = Jsoup.parse(html);
        Document parse = Jsoup.connect(home).get();
        Elements elements = parse.select("a.betterT");
        for (Element element : elements) {
            String urlSerial = home + element.attributes().get("href");
            Document document = Jsoup.connect(urlSerial).get();
            String info = document.select("div.pg-s-rb").select("p").get(0).text();
            String urlImage = document.select("div.full-news-img").select("img").get(0).attributes().get("src");
            String text = document.select("div.seasonlist").last().select("a").text();
            text = text.replaceAll("\\>", "");
            String title = "";
            String date = "";
            String episode = "";
            text = text.split("[СC][еe]ри[аa]л")[text.split("[CС][еe]ри[аa]л").length - 1];
            if (text.indexOf("с[eе]зон") > -1) {
                Matcher matcher = Pattern.compile(" [0-9] сезон ").matcher(text);
                if (matcher.find()) {
                    String sezon = matcher.group(0).replace(" ", "").replace("сезон", "");
                }
                String[] strings = text.split(" [0-9] сезон");
                title = strings[0];
                String[] split = strings[1].replace("(", "").replace(")", "").split(" ");
                date = split[0];
                episode = split[1];
            } else {
                String[] split = text.split(" \\(");
                title = split[0];
                date = split[1].split(" ")[0];
                episode = split[1].replace(split[1].split(" ")[0], "").replace(" серия)", "").replace(")", "");
            }
            System.out.println(title + " " + date + " " + episode);
        }

    }

}
