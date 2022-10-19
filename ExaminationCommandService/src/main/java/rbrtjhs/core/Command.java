package rbrtjhs.core;

public interface Command {
    default String getName() {
        return this.getClass().getSimpleName();
    }
}
