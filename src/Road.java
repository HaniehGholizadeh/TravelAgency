import java.util.ArrayList;
import java.util.List;
public class Road {
    private final int id;
    private String name;
    private int from;
    private int to;
    private List<Integer> through;
    private int speedLimit;
    private int length;
    private boolean biDirectional;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public List<Integer> getThrough() {
        return through;
    }

    public void setThrough(int from, List<Integer> through, int to) {
        this.through = through;
        this.through.add(0, from);
        this.through.add(this.through.size(), to);
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isBiDirectional() {
        return biDirectional;
    }

    public void setBiDirectional(boolean beDirection) {
        this.biDirectional = beDirection;
    }

    public Road(int id, String name, int from, int to, List<Integer> through, int speedLimit, int length, boolean direction) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.through = new ArrayList<>();
        this.through.add(from);
        for (int city : through) {
            this.through.add(city);
        }
        this.through.add(to);
        this.speedLimit = speedLimit;
        this.length = length;
        this.biDirectional = direction;
    }

    public void update(String name, int from, int to, List<Integer> through, int speedLimit, int length, boolean direction) {
        setName(name);
        setFrom(from);
        setTo(to);
        setThrough(from, through, to);
        setSpeedLimit(speedLimit);
        setLength(length);
        setBiDirectional(direction);
    }

    public int getTime() {
        return length / speedLimit;
    }

    public String time() {
        int d, h, m;
        d = (length / speedLimit) / 24;
        h = (length / speedLimit) % 24;
        m = (int) ((length * 1000 / speedLimit * 3.6) % 3600) / 60;
        return String.format("%02d:%02d:%02d", d, h, m);
    }
}