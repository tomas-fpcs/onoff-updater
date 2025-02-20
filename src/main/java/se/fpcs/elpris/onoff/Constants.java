package se.fpcs.elpris.onoff;

import java.util.TimeZone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

  public static final TimeZone defaultTimeZone = TimeZone.getTimeZone("Europe/Stockholm");

}
