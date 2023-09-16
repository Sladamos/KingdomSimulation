package strategy.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;

@AllArgsConstructor
@Getter
public class GUIConfig implements Config {

	private final GUIType guiType;
}
