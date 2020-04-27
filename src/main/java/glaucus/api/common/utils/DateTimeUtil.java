package glaucus.api.common.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static glaucus.api.common.constants.AppConstants.DATE_FORMAT_STR;
import static glaucus.api.common.constants.AppConstants.ZONE_ID_ASIA_KOLKATA_STR;

public class DateTimeUtil {

    private static final ZoneId ZONE_IST = ZoneId.of(ZONE_ID_ASIA_KOLKATA_STR);

    private static final DateTimeFormatter yyyy_MM_dd_HH_mm_ss_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT_STR);

    public static ZonedDateTime getNowInIST() {
        return ZonedDateTime.now(ZONE_IST);
    }
}
