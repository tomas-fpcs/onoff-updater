package se.fpcs.elpris.onoff.price.source.elprisetjustnu;

import static se.fpcs.elpris.onoff.price.source.elprisetjustnu.EPJN_DateUtil.toHour;
import static se.fpcs.elpris.onoff.price.source.elprisetjustnu.EPJN_DateUtil.toYYYYMMDD;

import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import se.fpcs.elpris.onoff.OnOffResponse;
import se.fpcs.elpris.onoff.OnOffService;
import se.fpcs.elpris.onoff.price.PriceForHour;
import se.fpcs.elpris.onoff.price.PriceNotFoundException;
import se.fpcs.elpris.onoff.price.PriceRepository;
import se.fpcs.elpris.onoff.price.PriceSource;
import se.fpcs.elpris.onoff.price.PriceUpdaterStatus;
import se.fpcs.elpris.onoff.price.PriceZone;
import se.fpcs.elpris.onoff.price.PricesNotRetrievedYetException;
import se.fpcs.elpris.onoff.user.User;

@Service
@RequiredArgsConstructor
@Log4j2
public class EPJN_OnOffService implements OnOffService {

  private final PriceRepository priceRepository;
  private final PriceUpdaterStatus priceUpdaterStatus;

  @Override
  public OnOffResponse on(
      final PriceZone priceZone,
      final int markupPercent,
      final int maxPriceOre,
      final UserDetails userDetails) {

    if (!priceUpdaterStatus.isReady(PriceSource.ELPRISETJUSTNU)) {
      throw new PricesNotRetrievedYetException();
    }

    final Date dateNow = new Date();
    Optional<PriceForHour> optionalPriceForHour =
        priceRepository.findByPriceSourceAndPriceZoneAndPriceDayAndPriceHour(
            PriceSource.ELPRISETJUSTNU.name(),
            priceZone.name(),
            toYYYYMMDD(dateNow),
            toHour(dateNow));

    if (optionalPriceForHour.isEmpty()) {
      throw new PriceNotFoundException();
    }

    final float priceSpotFloat = optionalPriceForHour.get()
        .getSekPerKWh().floatValue()
        * 100.0f;

    final float markupFactor = 1.0f + (markupPercent / 100f);

    final int priceSupplierOre = Math.round(markupFactor * priceSpotFloat);

    return OnOffResponse.builder()
        .on(priceSupplierOre <= maxPriceOre)
        .maxPrice(maxPriceOre)
        .priceSpot(Math.round(priceSpotFloat))
        .priceSupplier(priceSupplierOre)
        .serverTime(dateNow.toString())
        .userName(userDetails.getUsername())
        .build();

  }

}
