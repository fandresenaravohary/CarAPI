package hei.school.carshow.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirebaseService {
    private final FirebaseAuth firebaseAuth;
    public FirebaseToken getFirebaseUserByToken(String token) throws FirebaseAuthException {
        return firebaseAuth.verifyIdToken(token);
    }
}
