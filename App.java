import java.io.ObjectInputFilter.Config;
import java.net.URI;
import javax.naming.directory.SearchResult;
import java.awt.Desktop;
import edu.cmu.sphinx.api.*;

public class App {
    
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("src\\3412.dic");
        config.setLanguageModelPath("src\\3412.lm");
        try {
            LiveSpeechRecognizer rec = new LiveSpeechRecognizer(config);
            rec.startRecognition(true);
            Desktop desk = Desktop.getDesktop();
            while (rec.getResult() != null) {
                String result = rec.getResult().getHypothesis();
                if (result.toLowerCase().equals("google")){
                    System.out.println("Opening: "+result);
                    URI uri = new URI("http://google.com/");
                    desk.browse(uri);
                }else if(result.toLowerCase().equals("youtube")){
                    System.out.println("Opening: "+result);
                    URI uri = new URI("http://youtube.com/");
                    desk.browse(uri);
                }else if(result.toLowerCase().equals("gmail")){
                    System.out.println("Opening: "+result);
                    URI uri = new URI("https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox");
                    desk.browse(uri);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}