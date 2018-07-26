package com.minami.android.wakemeapp.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Minami on 2018/07/26.
 */

public class Config {
    public static final FirebaseUser CURRENT_USER = FirebaseAuth.getInstance().getCurrentUser();

}
