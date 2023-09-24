package strategy.option;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppExitOptionImpl implements AppExitOption {

    private final Option exitOption;

    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public void execute() {
        exitOption.execute();
    }
}
