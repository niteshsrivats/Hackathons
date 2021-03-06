package com.remote.exec.central.named;

import static com.remote.exec.central.named.Placeholders.VersionPrefix;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class Endpoints {
    public static class Auth {
        public final static String Base = VersionPrefix + "auth";
    }

    public static class Users {
        public final static String Base = VersionPrefix + "users";
        public final static String Login = Base + "/login";
        public final static String SignUp = Base + "/signup";
        public final static String Id = Base + "/{id}";
    }

    public static class Projects {
        public final static String Base = VersionPrefix + "projects";
        public final static String Id = Base + "/{id}";
        public final static String Submissions = Id + "/submissions";
    }

    public static class Submissions {
        public final static String Base = VersionPrefix + "submissions";
        public final static String Id = Base + "/{id}";
    }

    public static class Execution {
        public final static String Base = Projects.Id + "/execute";
    }
}
