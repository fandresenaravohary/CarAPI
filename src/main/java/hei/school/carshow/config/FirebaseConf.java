package hei.school.carshow.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConf {
    @Bean
    public FirebaseAuth firebaseApp() throws IOException {
        String firebaseKey = System.getenv("FIREBASE_KEY");
        var stream = new ByteArrayInputStream(firebaseKey.getBytes());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(stream))
                .build();
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
        return FirebaseAuth.getInstance(firebaseApp);
    }
}
