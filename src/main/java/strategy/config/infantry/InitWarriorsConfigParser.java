package strategy.config.infantry;

import org.json.JSONException;
import strategy.config.ConfigParser;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.item.military.InitMilitaryConfig;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.json.JSON;

public class InitWarriorsConfigParser implements ConfigParser<InitMilitaryConfig> {
    @Override
    public InitWarriorsConfig createConfig(JSON json) {
        try {
            int numberOfUnits = json.getInt("number_of_units");
            if(numberOfUnits < 0) {
                throw new BasicAppError("Number of initial units can't be less than 0.");
            }
            int maxDamage = json.getInt("max_damage");
            if(maxDamage < 0) {
                throw new BasicAppError("Initial unit max damage can't be less than 0.");
            }
            int maxDefense = json.getInt("max_defense");
            if(maxDefense < 0) {
                throw new BasicAppError("Initial unit max defense can't be less than 0.");
            }
            int health = json.getInt("health");
            if(health <= 0) {
                throw new BasicAppError("Initial unit health must be greater than 0.");
            }
            return new InitWarriorsConfig(numberOfUnits, maxDamage, maxDefense, health);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating initializing warriors config. " + err.getMessage());
        }
    }
}
