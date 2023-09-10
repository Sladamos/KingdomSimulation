package strategy.util;

public class TimeImpl implements Time {
    private final int seconds;

    public TimeImpl(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public long getMiliseconds() {
        return seconds * 1000L;
    }

    @Override
    public int getSeconds() {
        return seconds;
    }
}
