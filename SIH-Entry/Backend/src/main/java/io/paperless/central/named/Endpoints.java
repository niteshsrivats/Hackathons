package io.paperless.central.named;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class Endpoints {
    public static class Workflows {
        public final static String Base = "workflows";
        public final static String WorkflowById = Base + "/{id}";
        public final static String WorkflowProcess = Base + "/{id}/process";
    }

    public static class Processes {
        public final static String Base = "processes";
        public final static String ProcessById = Base + "/{id}";
    }

    public static class Stories {
        public final static String Base = "stories";
        public final static String StoryById = Base + "/{id}";
    }

    public static class Tasks {
        public final static String Base = "tasks";
        public final static String TaskById = Base + "/{id}";
    }

    public static class Files {
        public final static String Base = "files";
        public final static String FileById = Base + "/{id}";
    }

    public static class OldHabits {
        public final static String Base = "oldHabits";
    }
}
