package strategy.military.general.major;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.military.general.GeneralConfig;

@AllArgsConstructor
@Getter
public class MajorGeneralConfig implements Config {

    private final GeneralConfig warriorsGeneralConfig;
}
