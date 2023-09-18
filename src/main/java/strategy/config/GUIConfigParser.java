package strategy.config;

import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.gui.GUIConfig;
import strategy.gui.GUIType;
import strategy.gui.type.GUITypeParser;
import strategy.gui.type.GUITypeParserImpl;
import strategy.json.JSON;

public class GUIConfigParser implements ConfigParser<GUIConfig> {

	@Override
	public GUIConfig createConfig(JSON json) {
		try {
			String guiTypeStr = json.getString("type");
			GUIType guiType = createGUIType(guiTypeStr);
			return new GUIConfig(guiType);
		} catch (BasicAppError error) {
			throw new CriticalAppError("Something went wrong on creating gui config: " + error.getMessage());
		}
	}

	private GUIType createGUIType(String guiTypeStr) {
		GUITypeParser parser = new GUITypeParserImpl();
		return parser.parse(guiTypeStr);
	}
}
