package oncall.domain;

public class Worker {

    private final String name;

    private Worker(String name) {
        this.name = name;
    }

    public static Worker from(String name) {
        return new Worker(name);
    }

    public String getName() {
        return name;
    }

}
